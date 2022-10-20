package com.unq.desa.criptoP2P.persistence;

import com.unq.desa.criptoP2P.model.Intencion.Intention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IIntentionRepository extends JpaRepository<Intention,Integer> {
    @Query("SELECT * FROM Intention i WHERE i.isActive = true")
    public List<Intention> listTntentionsActiveOfAUser();
}
