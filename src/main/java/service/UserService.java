package service;

import model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.IUserRepository;

import java.util.List;
@Service
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

    public void createUser(User user) throws Exception {
        if(user.isValidateFullName()){
            throw new Exception("error");
        }
    }
}
