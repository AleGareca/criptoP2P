package com.unq.desa.criptoP2P.persistence;

import com.unq.desa.criptoP2P.model.intencion.Intention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface IIntentionRepository extends JpaRepository<Intention,Integer> {
    List<Intention> findIntentionByIsActive(Boolean isActive);
    @Query("from Intention i where i.userCripto.id = ?1 and i.quotation.dayAndTime BETWEEN ?2 and ?3")
    List<Intention> findIntentionByUserCriptoAndQuotationDayAndTimeBetween(Integer id, LocalDateTime initDate, LocalDateTime endDate);

}
