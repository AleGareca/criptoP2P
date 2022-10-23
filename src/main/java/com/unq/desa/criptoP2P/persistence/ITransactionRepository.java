package com.unq.desa.criptoP2P.persistence;

import com.unq.desa.criptoP2P.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<Transaction,Integer> {
}
