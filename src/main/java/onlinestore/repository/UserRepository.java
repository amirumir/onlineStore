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
                     "SELECT id, name, nickname, age, password, role, birthDate, mail, avatar FROM \"user\" WHERE nickname ")) {

            statement.setString(1, nickname);
            ResultSet resultSet = statement.executeQuery();



            while (resultSet.next()) {




            }

            System.out.println(users);

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }


    public void save(User user) {
        DataSource dataSource = DataSource
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
        return usersData.get(id);
    }

    public User findByEmail(String mail) {
        for (User user : usersData.values()) {
            if (user.getMail().equals(mail)) {
                return user;
            }
        }
        return null;
    }

    public List<User> findAll() {
        return new ArrayList<>(usersData.values());
    }

    public void update(User user) {


    }

}
