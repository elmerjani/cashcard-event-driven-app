package cashcard.controller;

import cashcard.domain.Transaction;
import cashcard.ondemand.CashCardTransactionOnDemand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author El-Merjani Mohamed
 **/
@RestController
public class CashCardController {

    private final CashCardTransactionOnDemand cashCardTransactionOnDemand;

    public CashCardController(CashCardTransactionOnDemand cashCardTransactionOnDemand){
        this.cashCardTransactionOnDemand = cashCardTransactionOnDemand;
    }

    @PostMapping(path = "/publish/txn")
    public void publishTxn(@RequestBody Transaction transaction) {
        this.cashCardTransactionOnDemand.publishOnDemand(transaction);
    }
}
