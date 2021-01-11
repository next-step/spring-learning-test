package nextstep.helloworld.ui;

import nextstep.helloworld.application.AuthService;
import nextstep.helloworld.application.AuthorizationException;
import nextstep.helloworld.dto.MemberResponse;
import nextstep.helloworld.dto.TokenRequest;
import nextstep.helloworld.dto.TokenResponse;
import nextstep.helloworld.infrastructure.AuthorizationExtractor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class AuthController {
    private static final String SESSION_KEY = "USER";
    private static final String USERNAME_FIELD = "email";
    private static final String PASSWORD_FIELD = "password";
    private AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login/session")
    public ResponseEntity sessionLogin(HttpServletRequest request, HttpSession session) {
        Map<String, String[]> paramMap = request.getParameterMap();
        String email = paramMap.get(USERNAME_FIELD)[0];
        String password = paramMap.get(PASSWORD_FIELD)[0];

        if (authService.checkInvalidLogin(email, password)) {
            throw new AuthorizationException();
        }

        session.setAttribute(SESSION_KEY, email);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/members/me")
    public ResponseEntity findMyInfo(HttpSession session) {
        String email = (String) session.getAttribute(SESSION_KEY);
        MemberResponse member = authService.findMember(email);
        return ResponseEntity.ok().body(member);
    }

    @PostMapping("/login/token")
    public ResponseEntity tokenLogin(@RequestBody TokenRequest tokenRequest) {
        TokenResponse tokenResponse = authService.createToken(tokenRequest);
        return ResponseEntity.ok().body(tokenResponse);
    }

    @GetMapping("/members/you")
    public ResponseEntity findYourInfo(HttpServletRequest request) {
        String token = AuthorizationExtractor.extract(request);
        MemberResponse member = authService.findMemberByToken(token);
        return ResponseEntity.ok().body(member);
    }
}
