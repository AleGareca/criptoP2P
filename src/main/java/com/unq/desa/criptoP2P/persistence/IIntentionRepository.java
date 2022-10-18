package com.unq.desa.criptoP2P.persistence;

import com.unq.desa.criptoP2P.model.Intencion.Intention;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIntentionRepository extends JpaRepository<Intention,Integer> {
}
