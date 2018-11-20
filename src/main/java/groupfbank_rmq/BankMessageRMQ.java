/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupfbank_rmq;

import com.kryptag.rabbitmqconnector.MessageClasses.BasicMessage;

/**
 *
 * @author Plamen
 */
public class BankMessageRMQ extends BasicMessage{
    private final int creditScore;
    private float interestRate;
    public BankMessageRMQ(String ssn, double loanAmount, int loanDuration, int creditScore, float interestRate) {
        super(ssn, loanAmount, loanDuration);
        this.creditScore = creditScore;
        this.interestRate = interestRate;

    }

    public int getCreditScore() {
        return creditScore;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }
    
    
    
    
}
