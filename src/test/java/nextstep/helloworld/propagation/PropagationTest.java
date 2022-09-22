package nextstep.helloworld.propagation;

import nextstep.helloworld.domain.Payment;
import nextstep.helloworld.domain.PaymentDao;
import nextstep.helloworld.domain.Play;
import nextstep.helloworld.domain.PlayDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PropagationTest {

    @Autowired
    public PaymentService paymentService;
    @Autowired
    public PaymentDao paymentDao;

    @Autowired
    public PlayDao playDao;

    @Test
    void saveWithRequired() {
        try {
            paymentService.saveWithRequired();
        } catch (Exception e) {

        }

        List<Payment> payments = paymentDao.findAll();
        assertThat(payments.size()).isEqualTo(0);

        List<Play> plays = playDao.findAll();
        assertThat(plays.size()).isEqualTo(0);
    }

    @Test
    void saveWithRequiresNew() {
        try {
            paymentService.saveWithRequiresNew();
        } catch (Exception e) {

        }

        List<Payment> payments = paymentDao.findAll();
        assertThat(payments.size()).isEqualTo(0);

        List<Play> plays = playDao.findAll();
        assertThat(plays.size()).isEqualTo(1);
    }

    @Test
    void saveWithNoTransaction() {
        try {
            paymentService.saveWithNoTransaction();
        } catch (Exception e) {

        }

        List<Payment> payments = paymentDao.findAll();
        assertThat(payments.size()).isEqualTo(1);

        List<Play> plays = playDao.findAll();
        assertThat(plays.size()).isEqualTo(1);
    }
}