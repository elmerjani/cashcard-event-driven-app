package cashcard.domain;

/**
 * @author El-Merjani Mohamed
 **/

public record EnrichedTransaction(Long id,
                                  CashCard cashCard,
                                  ApprovalStatus approvalStatus,
                                  CardHolderData cardHolderData) {
}