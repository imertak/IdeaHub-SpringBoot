package vtys_project.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vtys_project.forum.entity.Roles;
import vtys_project.forum.repository.RolesRepository;

import java.util.List;

@Service
public class RolesService {

    private final RolesRepository rolesRepository;

    @Autowired
    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Roles addRole(Roles role) {
        return rolesRepository.addRole(role);
    }

    public Roles getRoleById(String roleName) {
        return rolesRepository.getRoleByRoleName(roleName);
    }

    public List<Roles> getAllRoles() {
        return rolesRepository.getAllRoles();
    }

    public void deleteRoleById(int roleId) {
        rolesRepository.deleteRoleById(roleId);
    }

    public void updateRole(int id, Roles updatedRole) {
        rolesRepository.updateRole(id, updatedRole);
    }
}
