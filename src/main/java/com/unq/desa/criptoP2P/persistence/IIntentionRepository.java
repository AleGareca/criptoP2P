package com.unq.desa.criptoP2P.persistence;

import com.unq.desa.criptoP2P.model.Intencion.Intention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IIntentionRepository extends JpaRepository<Intention,Integer> {
    List<Intention> findIntentionByIsActive(Boolean aBoolean);

    List<Intention> findByIsActiveAndUserCripto_ReputationAndAmountOfActiveCripto(Boolean isActive, Integer reputation, int amountOfActiveCripto);

}
