package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.user.User;

import java.util.List;

public interface IUserService {

    public List<User> get();

    public void save(User user);

    public User getById(Integer id);

    public void delete(Integer id);

}
