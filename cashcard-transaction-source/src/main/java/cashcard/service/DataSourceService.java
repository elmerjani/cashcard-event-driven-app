package cashcard.service;

import cashcard.domain.CashCard;
import cashcard.domain.Transaction;

import java.util.Random;

/**
 * @author El-Merjani Mohamed
 **/

public class DataSourceService {

    private static final Random rand = new Random();

    public Transaction getData() {
        CashCard cashCard = new CashCard(rand.nextLong(), "sarah1", rand.nextDouble(100.00));
        return new Transaction(rand.nextLong(), cashCard);
    }
}
