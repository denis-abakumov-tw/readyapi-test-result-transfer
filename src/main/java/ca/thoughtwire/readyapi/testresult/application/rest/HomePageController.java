package ca.thoughtwire.readyapi.testresult.application.rest;

import ca.thoughtwire.readyapi.testresult.domain.model.PerformanceTestExecution;
import ca.thoughtwire.readyapi.testresult.domain.service.PerformanceTestExecutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class HomePageController {

    @Autowired
    private PerformanceTestExecutionService performanceTestExecutionService;


    @GetMapping("/")
    public List<PerformanceTestExecution> getPerformanceTestExecutions() {
        return performanceTestExecutionService.list();
    }

}
