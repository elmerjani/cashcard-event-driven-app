package cashcard.stream;

import cashcard.domain.CashCard;
import cashcard.domain.Transaction;
import cashcard.service.DataSourceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
class CashCardApplicationTests {

    @MockitoBean
    private DataSourceService dataSourceService;

    @Test
    void basicCashCardSupplier1(@Autowired OutputDestination outputDestination) throws IOException {
        Transaction testTransaction = new Transaction(1L, new CashCard(123L, "sarah1", 1.00));
        given(dataSourceService.getData()).willReturn(testTransaction);

        Message<byte[]> result = outputDestination.receive(5000, "approvalRequest-out-0");
        assertThat(result).isNotNull();

        ObjectMapper objectMapper = new ObjectMapper();
        Transaction transaction = objectMapper.readValue(result.getPayload(), Transaction.class);
        assertThat(transaction.id()).isEqualTo(1L);
        assertThat(transaction.cashCard()).isEqualTo(testTransaction.cashCard());
    }
}