package fine;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PgUsers implements Users {
    private final transient DataSource dataSource;

    public PgUsers(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> user(final String name, final String password) {
        try (Connection connection = this.dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT u.* FROM users u WHERE u.user_name = ? AND u.user_pass = ?"
            );

            statement.setString(1, name);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(
                    new ConstantUser(
                        resultSet.getLong("user_id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("user_pass"),
                        resultSet.getString("user_avatar"),
                        Role.valueOf(resultSet.getString("user_role"))
                    )
                );
            }
        } catch (SQLException e) {
            Logger.getLogger(PgUsers.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> user(String name) {
        try (Connection connection = this.dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT u.* FROM users u WHERE u.user_name = ?"
            );

            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(
                        new ConstantUser(
                                resultSet.getLong("user_id"),
                                resultSet.getString("user_name"),
                                resultSet.getString("user_pass"),
                                resultSet.getString("user_avatar"),
                                Role.valueOf(resultSet.getString("user_role"))
                        )
                );
            }
        } catch (SQLException e) {
            Logger.getLogger(PgUsers.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return Optional.empty();
    }

    @Override
    public User add(final String name, final String password, final Role role) throws IOException {
        try (Connection connection = this.dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (user_name, user_pass, user_avatar, user_role) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            statement.setString(1, name);
            statement.setString(2, password);
            statement.setString(3, "avatar");
            statement.setString(4, role.toString());

            int affected = statement.executeUpdate();
            if (affected == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return new ConstantUser(
                            generatedKeys.getLong(1),
                            name,
                            password,
                            "avatar",
                            role
                    );
                }
            }

            throw new IOException("Не удалось добавить нового пользователя");
        } catch (SQLException e) {
            Logger.getLogger(PgUsers.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IOException(e);
        }
    }

    @Override
    public Iterator<User> iterator() {
        return null;
    }
}
