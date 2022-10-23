package com.unq.desa.criptoP2P.model.stateTransition;

import com.unq.desa.criptoP2P.model.transaction.Transaction;

public abstract class TansactionState {

    protected Transaction transaction;

    public TansactionState(Transaction transaction) {
        this.transaction = transaction;
    }

     public abstract void makeTheTransfer(Integer idUser);
     public abstract void Confirm(Integer idUser);
     public abstract void Cancel();
}
