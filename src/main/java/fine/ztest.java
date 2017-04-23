package fine;

/**
 * Created by ehpgm on 15.04.2017.
 */
public class ztest {
    public static void m() {
        new AcRouting(
            new RtGet(
                new RtFixed(
                    "/",
                    new AcFixed(
                        new RsForward(
                            "/WEB-INF/index.jsp"
                        )
                    )
                )
            ),
            new RtAssets(
                "css", "js"
            )
        );
    }
}
