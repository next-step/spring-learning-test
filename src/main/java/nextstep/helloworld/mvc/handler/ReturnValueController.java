package nextstep.helloworld.mvc.handler;

import nextstep.helloworld.mvc.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/return-value")
public class ReturnValueController {

//    public void string() {
//        return "message";
//    }
//
//    public void responseBodyForUser() {
//        return new User("name", "email");
//    }
//
//    public void responseEntity(@PathVariable Long id) {
//        return ResponseEntity.ok(new User("name", "email"));
//    }
//
//    public void responseEntityFor400() {
//        return ResponseEntity.badRequest().build();
//    }
}