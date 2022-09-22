package nextstep.helloworld.propagation;

import nextstep.helloworld.domain.Payment;
import nextstep.helloworld.domain.PaymentDao;
import nextstep.helloworld.domain.Play;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PaymentService {
    private PaymentDao paymentDao;
    private PlayService playService;

    public PaymentService(PaymentDao paymentDao, PlayService playService) {
        this.paymentDao = paymentDao;
        this.playService = playService;
    }

    @Transactional
    public void saveWithRequired() {
        Payment payment = new Payment("이름", 22000);
        Play play = new Play("이름", LocalDateTime.now());
        paymentDao.save(payment);
        playService.saveWithRequired(play);
        throw new RuntimeException();
    }

    @Transactional
    public void saveWithRequiresNew() {
        Payment payment = new Payment("이름", 22000);
        Play play = new Play("이름", LocalDateTime.now());
        paymentDao.save(payment);
        playService.saveWithRequiresNew(play);
        throw new RuntimeException();
    }

    /**
     * 상위 트랜잭션에 영향을 받지 않고 트랜잭션을 무시
     */
    @Transactional
    public void saveWithNoTransaction() {
        Payment payment = new Payment("이름", 22000);
        Play play = new Play("이름", LocalDateTime.now());
        paymentDao.save(payment);
        playService.saveWithNoTransaction(play);
        throw new RuntimeException();
    }
}
