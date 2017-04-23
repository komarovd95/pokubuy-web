package fine;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PgCategory implements Category {
    private final transient DataSource dataSource;
    private final long id;

    public PgCategory(final DataSource dataSource, final long id) {
        this.dataSource = dataSource;
        this.id = id;
    }

    @Override
    public long id() {
        return this.id;
    }

    @Override
    public String name() {
        try (final Connection connection = this.dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT c.category_name FROM categories c WHERE c.category_id = ?"
            );

            statement.setLong(1, this.id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }

            throw new RuntimeException("Not Found");
        } catch (SQLException e) {
            Logger.getLogger(PgCategory.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Goods goods() {
        return new PgCategoryGoods(this.dataSource, this.id);
    }
}
