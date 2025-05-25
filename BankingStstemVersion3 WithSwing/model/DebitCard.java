/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author adeli
 */
@Entity
@Table(name= "debit_card")
public class DebitCard {
    @Id
    private int id;
    @Column(name="card_number", nullable=false)
    private String cardNumber;
    private Boolean active;
    @OneToOne
    @JoinColumn(name="account_id")
    private Account account;

    public DebitCard() {
    }

    public DebitCard(int id, String cardNumber, Boolean active, Account account) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.active = active;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    
    
}
