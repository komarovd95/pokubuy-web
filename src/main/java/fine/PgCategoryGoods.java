package fine;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PgCategoryGoods implements Goods {
    private final transient DataSource dataSource;
    private final long categoryId;

    public PgCategoryGoods(final DataSource dataSource, final long categoryId) {
        this.dataSource = dataSource;
        this.categoryId = categoryId;
    }

    @Override
    public Good add(final String title, final String description) throws IOException {
        try (final Connection connection = this.dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO goods (good_title, good_description, good_category_id) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            statement.setString(1, title);
            statement.setString(2, description);
            statement.setLong(3, this.categoryId);

            int affected = statement.executeUpdate();
            if (affected == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return new PgGood(
                            this.dataSource,
                            generatedKeys.getLong(1),
                            title,
                            description,
                            this.categoryId
                    );
                }
            }

            throw new IOException("Не удалось добавить новый товар");
        } catch (SQLException e) {
            Logger.getLogger(PgCategoryGoods.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IOException(e);
        }
    }

    @Override
    public Optional<Good> good(final long id) throws IOException {
        return null;
    }

    @Override
    public void remove(long id) throws IOException {
        try (final Connection connection = this.dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM goods WHERE good_id = ?"
            );

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(PgCategoryGoods.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IOException(e);
        }
    }

    @Override
    public void update(long id, String title, String description, long categoryId) throws IOException {

    }

    @Override
    public Iterator<Good> iterator() {
        try (final Connection connection = this.dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM goods g WHERE g.good_category_id = ?"
            );

            statement.setLong(1, this.categoryId);

            ResultSet resultSet = statement.executeQuery();
            List<Good> goods = new ArrayList<>();

            while (resultSet.next()) {
                goods.add(new PgGood(
                        this.dataSource,
                        resultSet.getLong("good_id"),
                        resultSet.getString("good_title"),
                        resultSet.getString("good_description"),
                        this.categoryId
                ));
            }

            return goods.iterator();
        } catch (SQLException e) {
            Logger.getLogger(PgGoods.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return Collections.<Good>emptyList().iterator();
        }
    }
}
