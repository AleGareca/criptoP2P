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

    private String symbolCripto;

    private Integer amountOfOperation;

    private Double amountOfOperationInPesos;
    @ManyToOne(fetch=FetchType.EAGER)
    private User user;

    private Integer numberOfOperations;

    private Integer reputationOfUser;

    private StateTransaction stateTransaction;
    @ManyToOne()
    private Intention intention;

    private String shippingAddress;

    public void shippingAddress() {
        if (this.intention.getOperacion() == Operation.Purchase){
            this.shippingAddress = this.user.getCvu();
        } else {
            this.shippingAddress = this.user.getWalletAddress();
        }
    }

    public void transfer(Cryptocurrency systemPrice) {
        if (this.theTransferOfSaleIsValid()) {
            this.transferOrCancel(systemPrice);
        }
    }

    private boolean theTransferOfSaleIsValid() {
        return this.intention.getOperacion() == Operation.Purchase;
    }

    private void transferOrCancel(Cryptocurrency systemPrice) {
       /* if (this.intention.getQuotation().getCryptocurrencyDto().getPrice() > systemPrice.getPrice()
                || this.intention.getQuotation().getCryptocurrencyDto().getPrice() < systemPrice.getPrice()) {
            this.setStateTransaction(StateTransaction.Cancelled);
            this.intention.setActive(false);
        } else {
            this.setStateTransaction(StateTransaction.Transferred);
            this.shippingAddress();
        }*/
    }

    public void confirm() {
        if(this.stateTransaction == StateTransaction.Transferred) {
            this.stateTransaction = StateTransaction.Confirm;
            this.shippingAddress();
            this.increaseUserReputationPoints();
            this.user.setSuccessfulOperation(1);
            this.user.setNumberOfOperations(1);
            this.numberOfOperations = this.user.getNumberOfOperations();
            this.intention.setActive(false);
        }
    }

    public void operationCanceledByUser() {
        if(this.stateTransaction == StateTransaction.Cancelled) {
            this.user.setReputation(this.user.getReputation() - 20);
            this.reputationOfUser = this.user.getReputation();
            this.intention.setActive(false);
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
