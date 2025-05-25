/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author adeli
 */
@Entity
public class Account {
    @Id
    @Column(name="account_number")
    private String accountNumber;
    private Double accountBalance;
    private boolean active;
    
    @OneToOne(mappedBy="account")
    private DebitCard debitCard;
    
    
}
