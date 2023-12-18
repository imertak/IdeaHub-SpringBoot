package vtys_project.forum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import vtys_project.forum.entity.Roles;
import vtys_project.forum.entity.Users;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersRepository {

    @Value("${spring.datasource.url}")
    private String sql_url;
    @Value("${spring.datasource.username}")
    private String sql_username;
    @Value("${spring.datasource.password}")
    private String sql_password;

    LocalDateTime currentDateTime = LocalDateTime.now();

    public Users addUser(Users user) {
        String sql = "INSERT INTO users (username, email, pass, profileImage, creationDate, roleID) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setBytes(4, user.getProfileImage());
                java.sql.Date sqlCreationDate = java.sql.Date.valueOf(currentDateTime.toLocalDate());
                preparedStatement.setDate(5, sqlCreationDate);
                preparedStatement.setInt(6, user.getRoleID());
                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setUserID(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Users getUserById(int id) {
        Users user = new Users();
        String sql = "SELECT * FROM users WHERE userID=?";
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user.setUserID(resultSet.getInt("userID"));
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("pass"));
                    user.setProfileImage(resultSet.getBytes("profileImage"));
                    user.setCreationDate(resultSet.getDate("creationDate"));
                    user.setRoleID(resultSet.getInt("roleID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Users findByUsername(String username){
        Users user = new Users();
        String sql = "SELECT * FROM users WHERE username=?";
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user.setUserID(resultSet.getInt("userID"));
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("pass"));
                    user.setProfileImage(resultSet.getBytes("profileImage"));
                    user.setCreationDate(resultSet.getDate("creationDate"));
                    user.setRoleID(resultSet.getInt("roleID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Users findByMail(String mail){
        Users user = new Users();
        String sql = "SELECT * FROM users WHERE email=?";
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, mail);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user.setUserID(resultSet.getInt("userID"));
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("pass"));
                    user.setProfileImage(resultSet.getBytes("profileImage"));
                    user.setCreationDate(resultSet.getDate("creationDate"));
                    user.setRoleID(resultSet.getInt("roleID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<Users> getAllUsers() {
        List<Users> usersList = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Users user = new Users();
                user.setUserID(resultSet.getInt("userID"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("pass"));
                user.setProfileImage(resultSet.getBytes("profileImage"));
                user.setCreationDate(resultSet.getDate("creationDate"));
                user.setRoleID(resultSet.getInt("roleID"));
                usersList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList;
    }

    public String getRoles(int userID){
        String roleName = null;
        String sql = "SELECT roles.roleName FROM users JOIN roles ON users.roleID=roles.roleID WHERE users.userID=?";
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
    PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, userID);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                roleName=resultSet.getString("roleName");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return roleName;
    }

    public void deleteUserById(int userId) {
        String sql = "DELETE FROM users WHERE userID = ?";

        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(int id, Users updatedUser) {
        String sql = "UPDATE users SET username = ?, email = ?, pass = ?, profileImage = ?,  roleID = ? WHERE userID = ?";

        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, updatedUser.getUsername());
            preparedStatement.setString(2, updatedUser.getEmail());
            preparedStatement.setString(3, updatedUser.getPassword());
            preparedStatement.setBytes(4, updatedUser.getProfileImage());
            preparedStatement.setInt(5, updatedUser.getRoleID());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Users> getNewUsers(){
        List<Users> usersList = new ArrayList<>();
        String sql = "SELECT * FROM users JOIN roles ON users.roleID=roles.roleID ORDER BY userID DESC LIMIT 10";

        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Users user = new Users();
                user.setUserID(resultSet.getInt("userID"));
                user.setUsername(resultSet.getString("username"));
                user.setProfileImage(resultSet.getBytes("profileImage"));
                user.setCreationDate(resultSet.getDate("creationDate"));
                user.setRoleID(resultSet.getInt("roleID"));
                usersList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList;
    }
}
