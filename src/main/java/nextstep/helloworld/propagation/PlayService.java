package nextstep.helloworld.propagation;

import nextstep.helloworld.domain.Play;
import nextstep.helloworld.domain.PlayDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayService {
    private PlayDao playDao;

    public PlayService(PlayDao playDao) {
        this.playDao = playDao;
    }

    /**
     * 새로운 트랜잭션을 만들지 않고 상위 트랜잭션에 참여
     */
    public void saveWithRequired(Play play) {
        playDao.save(play);
    }

    /**
     * 상위 트랜잭션과 무관하게 새로운 트랜잭션이 생성됨
     * 상위 작업이 실패하더라도 해당 작업은 영향받지 않음
     */
    public void saveWithRequiresNew(Play play) {
        playDao.save(play);
    }

    /**
     * 상위 트랜잭션에 영향을 받지 않고 트랜잭션을 무시
     */
    @Transactional
    public void saveWithNoTransaction(Play play) {
        playDao.save(play);
    }
}
