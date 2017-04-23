package fine;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PgGoods implements Goods {
    private final transient DataSource dataSource;

    public PgGoods(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Good add(final String title, final String description) throws IOException {
//        try (final Connection connection = this.dataSource.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(
//                    "INSERT INTO goods (good_title, good_description, good_category_id) VALUES (?, ?, ?)",
//                    Statement.RETURN_GENERATED_KEYS
//            );
//
//            statement.setString(1, title);
//            statement.setString(2, description);
//            statement.setLong(3, this.categoryId);
//
//            int affected = statement.executeUpdate();
//            if (affected == 1) {
//                ResultSet generatedKeys = statement.getGeneratedKeys();
//                if (generatedKeys.next()) {
////                    return new Pg
//                }
//            }
//        } catch (SQLException e) {
//            Logger.getLogger(PgCategory.class.getName()).log(Level.SEVERE, e.getMessage(), e);
////            return new ConstantGoods(Collections.emptyList());
//        }

        return null;
    }

    @Override
    public Optional<Good> good(final long id) throws IOException {
        try (final Connection connection = this.dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM goods WHERE good_id = ?"
            );

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new PgGood(
                        this.dataSource,
                        id,
                        resultSet.getString("good_title"),
                        resultSet.getString("good_description"),
                        resultSet.getLong("good_category_id")
                ));
            }
        } catch (SQLException e) {
            Logger.getLogger(PgGoods.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return Optional.empty();
    }

    @Override
    public void remove(final long id) throws IOException {
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
    public void update(final long id, final String title, final String description,
                       final long categoryId) throws IOException {
        try (final Connection connection = this.dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE goods SET good_title = ?, good_description = ?, good_category_id = ? WHERE good_id = ?"
            );

            statement.setString(1, title);
            statement.setString(2, description);
            statement.setLong(3, categoryId);
            statement.setLong(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(PgCategoryGoods.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IOException(e);
        }
    }

    @Override
    public Iterator<Good> iterator() {
        try (final Connection connection = this.dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM goods"
            );

            ResultSet resultSet = statement.executeQuery();
            List<Good> goods = new ArrayList<>();

            while (resultSet.next()) {
//                goods.add(new ConstantGood(
//                        resultSet.getLong("good_id"),
//                        resultSet.getString("good_title"),
//                        resultSet.getString("good_description"),
//                        this
//                ));
            }

            return goods.iterator();
        } catch (SQLException e) {
            Logger.getLogger(PgGoods.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return Collections.<Good>emptyList().iterator();
        }
    }
}
