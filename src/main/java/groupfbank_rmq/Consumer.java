/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupfbank_rmq;

import com.google.gson.Gson;
import com.kryptag.rabbitmqconnector.MessageClasses.LoanResponse;
import com.kryptag.rabbitmqconnector.RMQConnection;
import com.kryptag.rabbitmqconnector.RMQConsumer;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author Plamen
 */
public class Consumer extends RMQConsumer{
    
    public Consumer(ConcurrentLinkedQueue q, RMQConnection rmq) {
        super(q, rmq);
    }
    
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            doWork();
        }
    }

    private void doWork() {
        Gson g = new Gson();
        this.getRmq().createConnection();
        while (Thread.currentThread().isAlive()) {
            if (!this.getQueue().isEmpty()) {
                BankMessageRMQ bankmsg = g.fromJson(this.getQueue().remove().toString(), BankMessageRMQ.class);
                LoanResponse lrmsg = createLoanResponse(bankmsg);
                this.getRmq().sendMessage(g.toJson(lrmsg));
                System.out.println(lrmsg.toString());
            }
        }
    }

    // not finished
    private float somethingWithInterestRate(String ssn) {
        // loan response service
//        CreditScoreService_Service csss = new CreditScoreService_Service();
        try {
//            interestRate = csss.getCreditScoreServicePort().creditScore(ssn);
        } catch (UnsupportedOperationException e) {
        }
//        return interestRate;
        return 0; 
    }

    private LoanResponse createLoanResponse(BankMessageRMQ bankmsg) {
        LoanResponse lrmsg = new LoanResponse(somethingWithInterestRate(bankmsg.getSsn()), bankmsg.getSsn());
        return lrmsg;
    }

    
    
    
}
