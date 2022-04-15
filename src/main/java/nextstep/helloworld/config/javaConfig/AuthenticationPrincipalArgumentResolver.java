package nextstep.helloworld.config.javaConfig;

public class AuthenticationPrincipalArgumentResolver {
    private AuthService authService;

    public AuthenticationPrincipalArgumentResolver(AuthService authService) {
        this.authService = authService;
    }

    public String findMemberName() {
        return authService.findMemberName();
    }

    public AuthService getAuthService() {
        return authService;
    }
}
