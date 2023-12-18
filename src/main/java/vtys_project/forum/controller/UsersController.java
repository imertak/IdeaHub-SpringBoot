package vtys_project.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vtys_project.forum.entity.Users;
import vtys_project.forum.service.UserEntityService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserEntityService usersService;

    @Autowired
    public UsersController(UserEntityService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<Users> addUser(@RequestBody Users user) {
        return new ResponseEntity<>(usersService.addUser(user), HttpStatus.CREATED) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(usersService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return new ResponseEntity<>(usersService.getAllUsers(), HttpStatus.OK) ;
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable int id) {
        usersService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody Users updatedUser) {
        usersService.updateUser(id, updatedUser);
    }

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<Users> getUserByUsername(@PathVariable String username){
        return new ResponseEntity<>(usersService.getUserByUsername(username),HttpStatus.OK);
    }

    @GetMapping("/getNewUsers")
    public ResponseEntity<List<Users>> getNewUsers(){
        return new ResponseEntity<>(usersService.getNewUsers(),HttpStatus.OK);
    }
}
