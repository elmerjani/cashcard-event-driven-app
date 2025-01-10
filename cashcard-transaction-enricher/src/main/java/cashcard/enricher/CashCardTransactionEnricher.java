package cashcard.enricher;

import cashcard.domain.EnrichedTransaction;
import cashcard.domain.Transaction;
import cashcard.service.EnrichmentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * @author El-Merjani Mohamed
 **/
@Configuration
public class CashCardTransactionEnricher {

    @Bean
    EnrichmentService enrichmentService() {
        return new EnrichmentService();
    }

    @Bean
    public Function<Transaction, EnrichedTransaction> enrichTransaction(EnrichmentService enrichmentService) {
        return enrichmentService::enrichTransaction;
    }
}
