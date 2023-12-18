package vtys_project.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vtys_project.forum.entity.Roles;
import vtys_project.forum.service.RolesService;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {

    private final RolesService rolesService;

    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @PostMapping
    public Roles addRole(@RequestBody Roles role) {
        return rolesService.addRole(role);
    }

    @GetMapping("/{roleName}")
    public Roles getRoleById(@PathVariable String roleName) {
        return rolesService.getRoleById(roleName);
    }

    @GetMapping
    public List<Roles> getAllRoles() {
        return rolesService.getAllRoles();
    }

    @DeleteMapping("/{id}")
    public void deleteRoleById(@PathVariable int id) {
        rolesService.deleteRoleById(id);
    }

    @PutMapping("/{id}")
    public void updateRole(@PathVariable int id, @RequestBody Roles updatedRole) {
        rolesService.updateRole(id, updatedRole);
    }
}
