package fine;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PgCategories implements Categories {
    private final transient DataSource dataSource;

    public PgCategories(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Iterator<Category> iterator() {
        try (final Connection connection = this.dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT c.category_id FROM categories c"
            );

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

    @Override
    public Category add(final String name) throws IOException {
        try (final Connection connection = this.dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO categories (category_name) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            statement.setString(1, name);

            int insertedCount = statement.executeUpdate();
            if (insertedCount == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return new PgCategory(
                        this.dataSource,
                        generatedKeys.getLong(1)
                    );
                }
            }

            throw new IOException("Не удалось добавить новую категорию");
        } catch (SQLException e) {
            Logger.getLogger(PgCategories.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IOException(e);
        }
    }

    @Override
    public Optional<Category> category(long id) {
        try (final Connection connection = this.dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT c.category_id FROM categories c WHERE c.category_id = ?"
            );

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())  {
                return Optional.of(
                    new PgCategory(
                        this.dataSource,
                        id
                    )
                );
            }
        } catch (SQLException e) {
            Logger.getLogger(PgCategories.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return Optional.empty();
    }

    @Override
    public void remove(long categoryId) throws IOException {
        try (final Connection connection = this.dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM categories WHERE category_id = ?"
            );

            statement.setLong(1, categoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(PgCategories.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IOException(e);
        }
    }
}
