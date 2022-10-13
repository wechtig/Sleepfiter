package at.fh.Sleepfiter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitoring")
public class SleepInfoController {

    @GetMapping("/data")
    public String getSleepData() {
        return "hallo";
    }
}
