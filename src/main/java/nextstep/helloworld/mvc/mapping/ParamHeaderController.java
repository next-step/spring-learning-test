package nextstep.helloworld.mvc.mapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/param-header")
public class ParamHeaderController {

    public ResponseEntity<String> message() {
        return ResponseEntity.ok().body("message");
    }

    public ResponseEntity<String> messageForParam() {
        return ResponseEntity.ok().body("hello");
    }

    public ResponseEntity<String> messageForHeader() {
        return ResponseEntity.ok().body("hi");
    }
}