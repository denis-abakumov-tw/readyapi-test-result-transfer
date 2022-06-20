package ca.thoughtwire.readyapi.testresulttransfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TestResultTransferApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestResultTransferApplication.class, args);
    }

}
