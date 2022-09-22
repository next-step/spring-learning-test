package nextstep.helloworld.isolation;

import nextstep.helloworld.domain.Payment;
import nextstep.helloworld.domain.PaymentDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LowerPaymentService {
    private PaymentDao paymentDao;

    public LowerPaymentService(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    /**
     * 상위 트랜잭션이 커밋하지 않은 정보도 읽을 수 있음
     */
    public List<Payment> findAllWithReadUncommitted() {
        return paymentDao.findAll();
    }

    /**
     * 상위 트랜잭션이 커밋하지 않은 정보는 읽을 수 없지만
     * 하위 트랜잭션에서 해당 로우를 수정할 수 있음
     */
    public Payment findByIdWithReadCommitted(Long id) {
        return paymentDao.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateNameWithRepeatableRead(Long id, String newName) {
        paymentDao.updateName(id, newName);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveWithSerializable(Payment payment) {
        paymentDao.save(payment);
    }
}
