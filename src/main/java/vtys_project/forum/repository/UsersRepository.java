package vtys_project.forum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vtys_project.forum.entity.Users;

import java.util.List;
import java.util.Map;

@Repository
public class UsersRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getAllUsers() {
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> getUserById(int userId) {
        String sql = "SELECT * FROM Users WHERE userID = ?";
        return jdbcTemplate.queryForMap(sql, userId);
    }

    public void addUser(Users users) {
        String sql = "INSERT INTO Users (username, email, pass, profileImage, roleID) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, users.getUsername(), users.getEmail(), users.getPassword(), users.getProfileImage(), users.getRoleID());
    }

    public void updateUser(int userId, Users users) {
        String sql = "UPDATE Users SET username = ?, email = ?, pass = ?, profileImage = ?, roleID = ? WHERE userID = ?";
        jdbcTemplate.update(sql, users.getUsername(), users.getEmail(), users.getPassword(), users.getProfileImage(), users.getRoleID(), userId);
    }

    public void deleteUser(int userId) {
        String sql = "DELETE FROM Users WHERE userID = ?";
        jdbcTemplate.update(sql, userId);
    }
}
