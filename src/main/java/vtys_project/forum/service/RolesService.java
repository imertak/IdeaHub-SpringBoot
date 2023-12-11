package vtys_project.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vtys_project.forum.repository.RolesRepository;

import java.util.List;
import java.util.Map;

@Service
public class RolesService {

    private final RolesRepository rolesRepository;

    @Autowired
    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public List<Map<String, Object>> getAllRoles() {
        return rolesRepository.getAllRoles();
    }

    public Map<String, Object> getRoleById(int roleId) {
        return rolesRepository.getRoleById(roleId);
    }

    public void addRole(String roleName) {
        rolesRepository.addRole(roleName);
    }

    public void updateRole(int roleId, String roleName) {
        rolesRepository.updateRole(roleId, roleName);
    }

    public void deleteRole(int roleId) {
        rolesRepository.deleteRole(roleId);
    }
}
