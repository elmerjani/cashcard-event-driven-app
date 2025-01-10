package cashcard.service;
import java.util.UUID;

import cashcard.domain.ApprovalStatus;
import cashcard.domain.CardHolderData;
import cashcard.domain.EnrichedTransaction;
import cashcard.domain.Transaction;
/**
 * @author El-Merjani Mohamed
 **/
public class EnrichmentService {
    public EnrichedTransaction enrichTransaction(Transaction transaction) {
        return new EnrichedTransaction(transaction.id(), transaction.cashCard(), ApprovalStatus.APPROVED,
                new CardHolderData(UUID.randomUUID(), transaction.cashCard().owner(), "123 Main street"));
    }
}