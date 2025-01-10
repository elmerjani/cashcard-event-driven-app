package cashcard.domain;

import java.util.UUID;

/**
 * @author El-Merjani Mohamed
 **/
public record CardHolderData(UUID userId, String name, String address) {
}
