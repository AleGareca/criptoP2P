package service;

import model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import persistence.IUserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public List<User> get(){
        return this.userRepository.findAll();
    }

    public void save(User user) {
        this.userRepository.save(user);
    }

    public User getById(Integer id) {
        return this.userRepository.findById(id).get();
    }

    public void delete(Integer id) {
        this.userRepository.deleteById(id);
    }

}
