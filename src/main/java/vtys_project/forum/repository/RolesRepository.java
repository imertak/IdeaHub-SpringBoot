package vtys_project.forum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RolesRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RolesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getAllRoles() {
        String sql = "SELECT * FROM Roles";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> getRoleById(int roleId) {
        String sql = "SELECT * FROM Roles WHERE RoleID = ?";
        return jdbcTemplate.queryForMap(sql, roleId);
    }

    public void addRole(String roleName) {
        String sql = "INSERT INTO Roles (RoleName) VALUES (?)";
        jdbcTemplate.update(sql, roleName);
    }

    public void updateRole(int roleId, String roleName) {
        String sql = "UPDATE Roles SET RoleName = ? WHERE RoleID = ?";
        jdbcTemplate.update(sql, roleName, roleId);
    }

    public void deleteRole(int roleId) {
        String sql = "DELETE FROM Roles WHERE RoleID = ?";
        jdbcTemplate.update(sql, roleId);
    }
}
