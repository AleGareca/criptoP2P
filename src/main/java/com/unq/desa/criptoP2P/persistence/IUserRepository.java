package com.unq.desa.criptoP2P.persistence;


import com.unq.desa.criptoP2P.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {

}
