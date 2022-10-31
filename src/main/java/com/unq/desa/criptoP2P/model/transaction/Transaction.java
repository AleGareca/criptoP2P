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

    @OneToOne(fetch=FetchType.EAGER)
    private Cryptocurrency cripto;

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
        if (this.intention.getOperacion() == Operation.Sale){
            System.out.println("Entro al CVU");
            this.shippingAddress = this.user.getCvu();
        } else {
            this.shippingAddress = this.user.getWalletAddress();
            System.out.println("Entro al Wallet Address");
        }
    }

    public void transfer(Cryptocurrency systemPrice) {
        if (this.theTransferOfSaleIsValid()) {
            System.out.println("Entro al Tranferrrrrrrrrrrrrrrrrr");
            this.transferOrCancel(systemPrice);
        }
    }

    private boolean theTransferOfSaleIsValid() {
        return this.intention.getOperacion() == Operation.Purchase && this.intention.getIsActive() && this.stateTransaction == StateTransaction.Transferred;
    }

    private void transferOrCancel(Cryptocurrency systemPrice) {
        if (this.intention.getActiveCripto().getPrice() > systemPrice.getPrice()
                || this.intention.getActiveCripto().getPrice() < systemPrice.getPrice()) {
            this.setStateTransaction(StateTransaction.Cancelled);
            this.intention.setActive(false);
            System.out.println("Entro al Cancelar");
        } else {
            this.setStateTransaction(StateTransaction.Transferred);
            this.shippingAddress();
            this.intention.setActiveCripto(systemPrice);
            //this.increaseUserReputationPoints();
            System.out.println("Entro al Transfer");
        }
    }

    public void confirm() {
        if(this.stateTransaction == StateTransaction.Confirm) {
            this.shippingAddress();
            this.increaseUserReputationPoints();
            this.intention.setActive(false);
        }
    }

    public void operationCanceledByUser() {
        if(this.stateTransaction == StateTransaction.Cancelled) {
            this.user.setReputation(this.user.getReputation() - 20);
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
