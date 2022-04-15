package nextstep.helloworld.auth.application;

import nextstep.helloworld.auth.dto.MemberResponse;
import nextstep.helloworld.auth.dto.TokenRequest;
import nextstep.helloworld.auth.dto.TokenResponse;
import nextstep.helloworld.auth.infrastructure.JwtTokenProvider;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private static final String EMAIL = "email@email.com";
    private static final String PASSWORD = "1234";

    private JwtTokenProvider jwtTokenProvider;

    public AuthService(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public boolean checkInvalidLogin(String principal, String credentials) {
        return !EMAIL.equals(principal) || !PASSWORD.equals(credentials);
    }

    public MemberResponse findMember(String principal) {
        return new MemberResponse(1L, principal, 10);
    }

    public MemberResponse findMemberByToken(String token) {
        String payload = jwtTokenProvider.getPayload(token);
        return findMember(payload);
    }

    public TokenResponse createToken(TokenRequest tokenRequest) {
        if (checkInvalidLogin(tokenRequest.getEmail(), tokenRequest.getPassword())) {
            throw new AuthorizationException();
        }

        String accessToken = jwtTokenProvider.createToken(tokenRequest.getEmail());
        return new TokenResponse(accessToken);
    }
}
