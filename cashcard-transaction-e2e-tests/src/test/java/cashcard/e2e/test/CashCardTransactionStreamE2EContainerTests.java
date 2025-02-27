package cashcard.e2e.test;

import cashcard.enricher.CashCardTransactionEnricher;
import cashcard.sink.CashCardTransactionSink;
import cashcard.stream.CashCardTransactionStream;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;



import org.testcontainers.kafka.ConfluentKafkaContainer;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author El-Merjani Mohamed
 **/
@SpringBootTest(classes = CashCardTransactionStreamE2EContainerTests.StreamTestConfig.class, properties = {
        "spring.cloud.function.definition=approvalRequest;enrichTransaction;cashCardTransactionFileSink",
        "spring.cloud.stream.bindings.approvalRequest-out-0.destination=approval-requests",
        "spring.cloud.stream.bindings.enrichTransaction-in-0.destination=approval-requests",
        "spring.cloud.stream.bindings.enrichTransaction-out-0.destination=enriched-transactions",
        "spring.cloud.stream.bindings.cashCardTransactionFileSink-in-0.destination=enriched-transactions"
})
public class CashCardTransactionStreamE2EContainerTests {

    @Test
    void cashCardTransactionStreamEndToEnd() throws IOException {
        Path path = Paths.get(CashCardTransactionSink.CSV_FILE_PATH);;

        if (Files.exists(path)) {
            Files.delete(path);
        }
        Awaitility.await().until(() -> Files.exists(path));
        List<String> lines = Files.readAllLines(path);
        String csvLine = lines.get(0);

        assertThat(csvLine).contains("sarah1");
        assertThat(csvLine).contains("123 Main street");
        assertThat(csvLine).contains("APPROVED");

    }
    @EnableAutoConfiguration
    @Import({CashCardTransactionStream.class, CashCardTransactionEnricher.class, CashCardTransactionSink.class})
    public static class StreamTestConfig{
        @Bean
        @ServiceConnection
        public ConfluentKafkaContainer kafkaContainer() {
            return new ConfluentKafkaContainer("confluentinc/cp-kafka:latest");

        }
    }
}
