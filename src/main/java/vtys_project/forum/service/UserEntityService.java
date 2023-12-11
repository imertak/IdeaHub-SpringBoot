package vtys_project.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vtys_project.forum.entity.Users;
import vtys_project.forum.repository.UsersRepository;

import java.util.List;
import java.util.Map;

@Service
public class UserEntityService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserEntityService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Map<String, Object>> getAllUsers() {
        return usersRepository.getAllUsers();
    }

    public Map<String, Object> getUserById(int userId) {
        return usersRepository.getUserById(userId);
    }

    public void addUser(Users users) {
        usersRepository.addUser(users);
    }

    public void updateUser(int userId, Users users) {
        usersRepository.updateUser(userId, users);
    }

    public void deleteUser(int userId) {
        usersRepository.deleteUser(userId);
    }
}
