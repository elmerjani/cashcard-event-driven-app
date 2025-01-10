package cashcard.stream;

import cashcard.domain.Transaction;
import cashcard.service.DataSourceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

/**
 * @author El-Merjani Mohamed
 **/

@Configuration
public class CashCardTransactionStream {

    @Bean
    public DataSourceService dataSourceService() {
        return new DataSourceService();
    }

    @Bean
    public Supplier<Transaction> approvalRequest(DataSourceService dataSourceService) {
        return dataSourceService::getData;
    }
}
