package fine;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@WebServlet(Application.ROOT)
public final class PbApplication extends Application {
    private DataSource configureDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/pokubuy");
        config.setUsername("postgres");
        config.setPassword("");
        config.setMaximumPoolSize(20);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(config);
    }

    @Override
    protected Action frontAction() {
        DataSource dataSource = this.configureDataSource();
        Users users = new PgUsers(dataSource);
        Categories categories = new ConstantCategories(
                new PgCategories(dataSource)
        );
        Goods goods = new ConstantGoods(
                new PgGoods(dataSource)
        );

        Auth auth = new CookieAuth(
            new PbAuthCodec(users),
            "PBSIGNEDUSER",
            3600
        );

        return new AcLogged(
            new AcRouting(
                new RtGet(
                    new RtFixed(
                        "/",
                        req -> {
                            Categories populars = new ConstantCategories(
                                    new PopCategories(
                                            dataSource, 5
                                    )
                            );
                            List<Category> categoryList = new ArrayList<>();
                            populars.forEach(categoryList::add);

                            return new PbAction(
                                    auth,
                                    users,
                                    categories,
                                    "/WEB-INF/index.jsp",
                                    new NamedAttribute(
                                            "populars",
                                            categoryList
                                    )
                            ).act(req);
                        }
                    )
                ),
                new RtFork(
                    "/signin",
                     new AcFixed(
                         new RsForward(
                             "/WEB-INF/signin.jsp"
                         )
                     ),
                     new AcSignIn(
                         auth,
                         "/WEB-INF/signin.jsp"
                     )
                ),
                new RtFork(
                    "/signup",
                    new AcFixed(
                        new RsForward(
                            "/WEB-INF/signup.jsp"
                        )
                    ),
                    new AcSignUp(
                        "/WEB-INF/signup.jsp",
                        users,
                        auth
                    )
                ),
                new RtGet(
                    new RtFixed(
                        "/signout",
                        new AcSignOut(auth)
                    )
                ),
                new RtFork(
                    "/categories/add",
                    new PbAction(
                        auth,
                        users,
                        categories,
                        "/WEB-INF/add_category.jsp"
                    ),
                    new AcAddCategory(
                        new PgCategories(dataSource),
                        success -> new PbAction(
                            auth,
                            users,
                            categories,
                            "/WEB-INF/add_category.jsp",
                            success
                        ),
                        error -> new PbAction(
                            auth,
                            users,
                            categories,
                            "/WEB-INF/add_category.jsp",
                            error
                        )
                    )
                ),
                new RtGet(
                    new RtRegex(
                        "^/categories/([0-9]+)$",
                        new AcCategory(
                            categories,
                            attributes -> new PbAction(
                                auth,
                                users,
                                categories,
                                "/WEB-INF/category.jsp",
                                attributes
                            )
                        )
                    )
                ),
                new RtGet(
                    new RtRegex(
                        "^/categories/([0-9]+)/delete$",
                        new AcDeleteCategory(
                            new PgCategories(dataSource)
                        )
                    )
                ),
                new RtGet(
                    new RtRegex(
                        "^/goods/([0-9]+)/edit",
                        new AcGetEditGood(
                            goods,
                            good -> new PbAction(
                                    auth,
                                    users,
                                    categories,
                                    "/WEB-INF/edit_good.jsp",
                                    good
                            )
                        )
                    )
                ),
                new RtPost(
                    new RtRegex(
                        "^/goods/([0-9]+)/edit",
                        new AcEditGood(
                            new PgGoods(dataSource),
                            good -> new PbAction(
                                auth,
                                users,
                                categories,
                                "/WEB-INF/edit_good.jsp",
                                good
                            )
                        )
                    )
                ),
                new RtGet(
                    new RtRegex(
                        "^/goods/([0-9]+)/delete$",
                        new AcDeleteGood(
                            new PgGoods(dataSource)
                        )
                    )
                ),
                new RtFork(
                    "/goods/add",
                    new AcGetAddGood(
                        attributes -> new PbAction(
                            auth,
                            users,
                            categories,
                            "/WEB-INF/add_good.jsp",
                            attributes
                        )
                    ),
                    new AcAddGood(
                        new PgCategories(dataSource),
                        success -> new PbAction(
                                    auth,
                                    users,
                                    categories,
                                    "/WEB-INF/add_good.jsp",
                                    success
                        ),
                        error -> new PbAction(
                                    auth,
                                    users,
                                    categories,
                                    "/WEB-INF/add_good.jsp",
                                    error
                        )
                    )
                ),
                new RtAssets(
                    "css", "js"
                ),
                new RtFallback(
                    new AcFixed(
                        new RsForward(
                            "/WEB-INF/404.jsp"
                        )
                    )
                )
            ), "PbApplication");
    }
}
