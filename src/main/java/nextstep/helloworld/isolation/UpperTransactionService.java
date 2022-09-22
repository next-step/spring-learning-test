package nextstep.helloworld.isolation;

import nextstep.helloworld.domain.Payment;
import nextstep.helloworld.domain.PaymentDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UpperTransactionService {
    private PaymentDao paymentDao;
    private LowerPaymentService lowerPaymentService;

    public UpperTransactionService(PaymentDao paymentDao, LowerPaymentService lowerPaymentService) {
        this.paymentDao = paymentDao;
        this.lowerPaymentService = lowerPaymentService;
    }

    @Transactional
    public List<Payment> readUncommitted() {
        Payment payment = new Payment("이름", 22_000);
        paymentDao.save(payment);

        return lowerPaymentService.findAllWithReadUncommitted();
    }

    @Transactional
    public Payment readCommitted(String newName) {
        paymentDao.updateName(1L, newName);

        return lowerPaymentService.findByIdWithReadCommitted(1L);
    }

    /**
     * 하위 트랜잭션에서 변경된 내용이 반영되지 않음
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public boolean repeatableRead(String newName) {
        Payment payment = paymentDao.findById(1L);
        lowerPaymentService.updateNameWithRepeatableRead(1L, newName);
        Payment newPayment = paymentDao.findById(1L);

        return Objects.equals(payment.getMemberName(), newPayment.getMemberName());
    }

    /**
     * 여러 트랜잭션이 동시에 같은 정보를 액세스 할 수 없음
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public boolean serializable() {
        List<Payment> payments = paymentDao.findAll();
        lowerPaymentService.saveWithSerializable(new Payment("이름", 22_000));
        List<Payment> newPayments = paymentDao.findAll();

        return Objects.equals(payments.size(), newPayments.size());
    }
}
