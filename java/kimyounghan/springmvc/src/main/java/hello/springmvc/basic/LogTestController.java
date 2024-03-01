package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@Slf4j
public class LogTestController {

    @GetMapping("/log-test")
    public String logTest() {
        String name = "spring";

        log.trace(name);
        log.debug(name);
        log.info(name);
        log.warn(name);
        log.error(name);
        return "ok";
    }
}
