package fine;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PopCategories implements Categories {
    private final transient DataSource dataSource;
    private final int limit;

    public PopCategories(final DataSource dataSource, final int limit) {
        this.dataSource = dataSource;
        this.limit = limit;
    }

    @Override
    public Category add(String name) throws IOException {
        return null;
    }

    @Override
    public Optional<Category> category(long id) {
        return null;
    }

    @Override
    public void remove(long categoryId) throws IOException {

    }

    @Override
    public Iterator<Category> iterator() {
        try (final Connection connection = this.dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT c.category_id FROM categories c LEFT OUTER JOIN goods g ON c.category_id = g.good_category_id GROUP BY c.category_id ORDER BY COUNT(g.*) DESC LIMIT ?"
            );

            statement.setInt(1, this.limit);

            ResultSet resultSet = statement.executeQuery();
            List<Category> categories = new ArrayList<>();

            while (resultSet.next()) {
                categories.add(
                        new PgCategory(
                                this.dataSource,
                                resultSet.getLong("category_id")
                        )
                );
            }

            return categories.iterator();
        } catch (SQLException e) {
            Logger.getLogger(PgCategories.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return Collections.<Category>emptyList().iterator();
        }
    }
}
