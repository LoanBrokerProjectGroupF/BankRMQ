/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupfbank_rmq;

import com.kryptag.rabbitmqconnector.Enums.ExchangeNames;
import com.kryptag.rabbitmqconnector.RMQConnection;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author Plamen
 */
public class BankRMQ {
    public static void main(String[] args) {
        RMQConnection rmqPub = new RMQConnection("guest", "guest", "datdb.cphbusiness.dk", 5672, ExchangeNames.ENTRY_POINT.toString());
        RMQConnection rmqCon = new RMQConnection("guest", "guest", "datdb.cphbusiness.dk", 5672, ExchangeNames.CREDITSCORE_TOGETBANKS.toString());
        ConcurrentLinkedQueue q = new ConcurrentLinkedQueue();
        Producer producer = new Producer(q, rmqPub);
        Consumer consumer = new Consumer(q, rmqCon);
        producer.start();
        consumer.start();
    }
}
