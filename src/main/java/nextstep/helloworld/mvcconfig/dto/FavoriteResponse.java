package nextstep.helloworld.mvcconfig.dto;

public class FavoriteResponse {
    private Long id;
    private Long source;
    private Long target;

    public FavoriteResponse() {
    }

    public FavoriteResponse(Long id, Long source, Long target) {
        this.id = id;
        this.source = source;
        this.target = target;
    }

    public Long getId() {
        return id;
    }

    public Long getSource() {
        return source;
    }

    public Long getTarget() {
        return target;
    }
}
