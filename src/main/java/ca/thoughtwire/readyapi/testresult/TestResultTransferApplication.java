package ca.thoughtwire.readyapi.testresult;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TestResultTransferApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TestResultTransferApplication.class, args);
    }

}
