package io.aspect.springbootdemo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestApi {
    private final Logger logger = LoggerFactory.getLogger(LogTestApi.class);

    @GetMapping("/log")
    public String log() {
        logger.info("info --- log");
        logger.warn("warn --- log");
        logger.error("error --- log");
        logger.debug("debug --- log");

        return "logtest";
    }
}
