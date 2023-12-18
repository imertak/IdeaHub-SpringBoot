package vtys_project.forum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import vtys_project.forum.entity.Roles;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RolesRepository {

    @Value("${spring.datasource.url}")
    private String sql_url;
    @Value("${spring.datasource.username}")
    private String sql_username;
    @Value("${spring.datasource.password}")
    private String sql_password;

    public Roles addRole(Roles role) {
        String sql = "INSERT INTO roles (RoleName) VALUES (?)";
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, role.getRoleName());
                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        role.setRoleID(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public Roles getRoleByRoleName(String roleName) {
        Roles role = new Roles();
        String sql = "SELECT * FROM roles WHERE roleName=?";
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, roleName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    role.setRoleID(resultSet.getInt("RoleID"));
                    role.setRoleName(resultSet.getString("RoleName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public List<Roles> getAllRoles() {
        List<Roles> rolesList = new ArrayList<>();
        String sql = "SELECT * FROM roles";

        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Roles role = new Roles();
                role.setRoleID(resultSet.getInt("RoleID"));
                role.setRoleName(resultSet.getString("RoleName"));
                rolesList.add(role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rolesList;
    }

    public void deleteRoleById(int roleId) {
        String sql = "DELETE FROM roles WHERE RoleID = ?";

        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, roleId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRole(int id, Roles updatedRole) {
        String sql = "UPDATE roles SET RoleName = ? WHERE RoleID = ?";

        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, updatedRole.getRoleName());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
