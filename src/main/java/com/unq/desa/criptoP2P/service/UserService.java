package com.unq.desa.criptoP2P.service;


import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements IUserService {
/*    @Autowired
    private IUserRepository userRepository;

    //@Autowired
    //private IIntentionService intentionService;

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

    @Override
    public void cryptoAssetSaleIntention(Intention intention,Integer userId) {
        User user = this.getById(userId);
        //intention.setUserCripto(user);
       // this.intentionService.save(intention);
    }

    @Override
    public void selectTheBestSeller() {

    }*/


}
