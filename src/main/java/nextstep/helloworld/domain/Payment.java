package nextstep.helloworld.domain;

public class Payment {
    private Long id;
    private String memberName;
    private int amount;

    public Payment(Long id, String memberName, int amount) {
        this.id = id;
        this.memberName = memberName;
        this.amount = amount;
    }

    public Payment(String memberName, int amount) {
        this.memberName = memberName;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public int getAmount() {
        return amount;
    }
}
