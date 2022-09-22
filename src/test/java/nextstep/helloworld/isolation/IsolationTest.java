package nextstep.helloworld.isolation;

import nextstep.helloworld.domain.Payment;
import nextstep.helloworld.domain.PaymentDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IsolationTest {
    @Autowired
    public UpperTransactionService upperTransactionService;

    @Autowired
    public PaymentDao paymentDao;

    @DisplayName("원래 성공하는 테스트")
    @Test
    void readUncommitted() {
        List<Payment> payments = upperTransactionService.readUncommitted();

        assertThat(payments.size()).isEqualTo(1);
    }

    @Test
    void readCommitted() {
        paymentDao.save(new Payment("이름", 10_000));

        Payment payment = upperTransactionService.readCommitted("새이름");
        assertThat(payment.getMemberName()).isEqualTo("이름");

        payment = paymentDao.findById(1L);
        assertThat(payment.getMemberName()).isEqualTo("새이름");
    }

    @Test
    void repeatableRead() {
        paymentDao.save(new Payment("이름", 10_000));

        boolean result = upperTransactionService.repeatableRead("새이름");
        assertThat(result).isEqualTo(true);
    }

    @Test
    void serializable() {
        paymentDao.save(new Payment("이름", 10_000));

        boolean result = upperTransactionService.serializable();
        assertThat(result).isEqualTo(true);
    }
}