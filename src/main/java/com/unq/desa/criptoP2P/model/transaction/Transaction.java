package com.unq.desa.criptoP2P.model.transaction;

import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import com.unq.desa.criptoP2P.model.enums.stateTransaction.StateTransaction;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDateTime dayAndTimeOfOperation;

    @ManyToOne
    private Cryptocurrency activeCripto;

    private int amountOfActiveCripto;

    @ManyToOne
    private Quotation quotation;

    private Double amountOfOperationInPesos;
    @ManyToOne()
    private User user;

    private Integer numberOfOperations;

    private Integer reputationOfUser;

    private StateTransaction stateTransaction;
    @ManyToOne()
    private Intention intention;

    private String shippingAddress;

    public void shippingAddress() {
        if (this.intention.getOperacion() == Operation.Sale){
            this.shippingAddress = user.getCvu();
        } else {
            this.shippingAddress = user.getWalletAddress();
        }
    }

    public void transfer(Double systemPrice) {
        if (theTransferOfSaleIsValid()) {
            transferOrCancel(systemPrice);
        }
    }

    private boolean theTransferOfSaleIsValid() {
        return this.intention.getOperacion() == Operation.Sale && this.intention.getIsActive() && this.stateTransaction == StateTransaction.Transferred;
    }

    private void transferOrCancel(Double systemPrice) {
        if (this.intention.getActiveCripto().getPrice() > systemPrice || this.intention.getActiveCripto().getPrice() < systemPrice) {
            this.setStateTransaction(StateTransaction.Cancelled);
            this.intention.setIsActive(false);
        } else {
            this.setStateTransaction(StateTransaction.Transferred);
            this.shippingAddress();
            this.increaseUserReputationPoints();
        }
    }

    public void confirm() {
        if(this.stateTransaction == StateTransaction.Confirm) {
            this.increaseUserReputationPoints();
            this.intention.setIsActive(false);
        }
    }

    public void operationCanceledByUser() {
        if(this.stateTransaction == StateTransaction.Cancelled) {
            this.user.setReputation(this.user.getReputation() - 20);
            this.intention.setIsActive(false);
        }
    }

    public void increaseUserReputationPoints() {
        LocalDateTime currentTime = LocalDateTime.now();
        if(dayAndTimeOfOperation.getHour() - currentTime.getHour() < 30) {
            this.operationPerformedInside30Minutes();
        } else {
            this.operationPerformedAfter30Minutes();
        }
    }


    private void operationPerformedInside30Minutes() {
        this.user.setReputation(this.user.getReputation() + 10);
    }

    private void operationPerformedAfter30Minutes() {
        this.user.setReputation(this.user.getReputation() + 5);
    }

}
