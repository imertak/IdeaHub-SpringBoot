package vtys_project.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vtys_project.forum.entity.Users;
import vtys_project.forum.service.UserEntityService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {
    
    @Autowired
    UserEntityService usersService;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllUsers() {
        List<Map<String, Object>> users = usersService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable int userId) {
        Map<String, Object> user = usersService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody Users users) {
        usersService.addUser(users);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable int userId, @RequestBody Users users) {
        usersService.updateUser(userId, users);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        usersService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
