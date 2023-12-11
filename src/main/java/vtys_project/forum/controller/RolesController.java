package vtys_project.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vtys_project.forum.service.RolesService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roles")
public class RolesController {

    private final RolesService rolesService;

    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping
    public List<Map<String, Object>> getAllRoles() {
        return rolesService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Map<String, Object> getRoleById(@PathVariable int id) {
        return rolesService.getRoleById(id);
    }

    @PostMapping
    public void addRole(@RequestBody Map<String, Object> role) {
        rolesService.addRole((String) role.get("roleName"));
    }

    @PutMapping("/{id}")
    public void updateRole(@PathVariable int id, @RequestBody Map<String, Object> updatedRole) {
        rolesService.updateRole(id, (String) updatedRole.get("roleName"));
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable int id) {
        rolesService.deleteRole(id);
    }
}
