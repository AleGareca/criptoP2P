package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.config.MapperComponent;
import com.unq.desa.criptoP2P.model.dto.UserDto;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private MapperComponent modelMapper;

    public List<UserDto> get(){
        return modelMapper.ToList(this.userRepository.findAll(), UserDto.class);
    }

    public void save(User user) {
        this.userRepository.save(user);
    }

    public UserDto getById(Integer id) {
        return modelMapper.To(this.userRepository.findById(id).get(),UserDto.class);
    }

    public void delete(Integer id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public void updateUser(UserDto user) {
        userRepository.save(this.modelMapper.To(user, User.class));
    }


}
