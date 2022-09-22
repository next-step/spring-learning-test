package nextstep.helloworld.domain;

import java.time.LocalDateTime;

public class Play {
    private Long id;
    private String memberName;
    private LocalDateTime dateTime;

    public Play(Long id, String memberName, LocalDateTime dateTime) {
        this.id = id;
        this.memberName = memberName;
        this.dateTime = dateTime;
    }

    public Play(String memberName, LocalDateTime dateTime) {
        this.memberName = memberName;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
