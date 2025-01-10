package cashcard.domain;

/**
 * @author El-Merjani Mohamed
 **/
public record CashCard(Long id, String owner, Double amountRequestedForAuth) {
}
