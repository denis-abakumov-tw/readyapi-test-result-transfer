package ca.thoughtwire.readyapi.testresult.application.rest;

import ca.thoughtwire.readyapi.testresult.application.response.PerformanceTestImportResult;
import ca.thoughtwire.readyapi.testresult.domain.model.PerformanceTestExecution;
import ca.thoughtwire.readyapi.testresult.domain.service.PerformanceTestExecutionService;
import ca.thoughtwire.readyapi.testresult.domain.service.TestResultTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/testresult")
@Slf4j
public class TestResultController {

    @Autowired
    private TestResultTransferService testResultTransferService;

    @Autowired
    private PerformanceTestExecutionService performanceTestExecutionService;

    @Operation(summary = "Imports ReadyAPI performance test metrics from LoadUITestStepsHistory.xml")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Illegal argument"),
            @ApiResponse(responseCode = "500", description = "Unable to import metrics")})
    @PostMapping("/import")
    public ResponseEntity<PerformanceTestImportResult> importPerformanceResults(@RequestParam @NonNull String environment,
                                                                                @RequestParam("file") MultipartFile file) throws IOException {
        File zip = new File(System.getProperty("java.io.tmpdir"), file.getOriginalFilename());
        file.transferTo(zip);
        try (ZipFile zipFile = new ZipFile(zip)) {
            Path tempDirWithPrefix = Files.createTempDirectory("ReadyAPI_XML_");
            zipFile.extractAll(tempDirWithPrefix.toString());
            PerformanceTestImportResult result = testResultTransferService.importPerformanceResults(environment, tempDirWithPrefix);
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/list")
    public List<PerformanceTestExecution> getPerformanceTestExecutions() {
        return performanceTestExecutionService.list();
    }

}
