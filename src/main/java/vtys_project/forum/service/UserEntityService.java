package vtys_project.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vtys_project.forum.entity.Users;
import vtys_project.forum.repository.UsersRepository;

import java.util.List;

@Service
public class UserEntityService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserEntityService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users addUser(Users user) {
        return usersRepository.addUser(user);
    }

    public Users getUserById(int id) {
        return usersRepository.getUserById(id);
    }

    public List<Users> getAllUsers() {
        return usersRepository.getAllUsers();
    }

    public void deleteUserById(int userId) {
        usersRepository.deleteUserById(userId);
    }

    public void updateUser(int id, Users updatedUser) {
        usersRepository.updateUser(id, updatedUser);
    }

    public Users getUserByUsername(String username){
        return usersRepository.findByUsername(username);
    }

    public List<Users> getNewUsers(){
        return usersRepository.getNewUsers();
    }
}
