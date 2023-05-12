package onlinestore.repository;

import onlinestore.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserRepository {
    private HashMap<Long, User> usersData = new HashMap<>();

    public User findByNickname(String nickname) {
        DataSource dataSource = DataSourceConfig.dataSource();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id, name, nickname, age, password, role, birthDate, mail,  FROM \"user\" WHERE nickname = ? ")) {

            statement.setString(1, nickname);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String nickname1 = resultSet.getString("nickname");
            int age = resultSet.getInt("age");
            String password = resultSet.getString("password");
            User.Role role = User.Role.valueOf(resultSet.getString("role"));
            LocalDate birthDate = resultSet.getDate("birthDate").toLocalDate();
            String mail = resultSet.getString("mail");
            byte[] avatar = resultSet.getBytes("avatar");

            User user = new User(id, name, nickname1, age, password, role, birthDate, mail);
            user.setAvatar(avatar);
                System.out.println(user);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }


    public void save(User user) {
        DataSource dataSource = DataSourceConfig.dataSource();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO \"user\" (name, nickname,age, password, role, birthDate, mail, avatar)" +
                     "VALUES (?,?,?,?,?,?,?,?)")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getNickname());
            statement.setInt(3, user.getAge());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().toString());
            statement.setDate(6, Date.valueOf(user.getBirthDate()));
            statement.setString(7, user.getMail());
            statement.setBytes(8, user.getAvatar());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findByID(Long id) {
        DataSource dataSource = DataSourceConfig.dataSource();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id, name, nickname, age, password, role, birthDate, mail,  FROM \"user\" WHERE id = ? ")) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id1 = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String nickname1 = resultSet.getString("nickname");
                int age = resultSet.getInt("age");
                String password = resultSet.getString("password");
                User.Role role = User.Role.valueOf(resultSet.getString("role"));
                LocalDate birthDate = resultSet.getDate("birthDate").toLocalDate();
                String mail = resultSet.getString("mail");
                byte[] avatar = resultSet.getBytes("avatar");

                User user = new User(id1, name, nickname1, age, password, role, birthDate, mail);
                user.setAvatar(avatar);
                System.out.println(user);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User findByEmail(String mail) {
        DataSource dataSource = DataSourceConfig.dataSource();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id, name, nickname, age, password, role, birthDate, mail,  FROM \"user\" WHERE mail = ? ")) {

            statement.setString(1, mail);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String nickname1 = resultSet.getString("nickname");
                int age = resultSet.getInt("age");
                String password = resultSet.getString("password");
                User.Role role = User.Role.valueOf(resultSet.getString("role"));
                LocalDate birthDate = resultSet.getDate("birthDate").toLocalDate();
                String mail1 = resultSet.getString("mail");
                byte[] avatar = resultSet.getBytes("avatar");

                User user = new User(id, name, nickname1, age, password, role, birthDate, mail1);
                user.setAvatar(avatar);
                System.out.println(user);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<User> findAll() {

        return new ArrayList<>(usersData.values());
    }

    public void update(User user) {


    }

}
