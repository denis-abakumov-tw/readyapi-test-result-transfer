package ca.thoughtwire.readyapi.testresult.application.rest;

import ca.thoughtwire.readyapi.testresult.domain.service.TestResultTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.String.format;

@RestController
@RequestMapping("/testresult")
@Slf4j
public class TestResultController {

    @Autowired
    private TestResultTransferService testResultTransferService;

    private static final String SUCCESS_MESSAGE_METRICS = "Metrics for %d test cases successfully uploaded";

    private static final String SUCCESS_MESSAGE_STATISTICS = "Statistics for %d test cases successfully uploaded";

    private static final String FAILURE_MESSAGE = "Error occurred, Please contact administrator";

    @Operation(summary = "Imports ReadyAPI performance test metrics from LoadUITestStepsHistory.xml")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success flag"),
            @ApiResponse(responseCode = "400", description = "Illegal argument"),
            @ApiResponse(responseCode = "500", description = "Unable to import metrics")})
    @PostMapping("/steps/history")
    public String importStepsHistory(@RequestParam @NonNull String environment,
                                     @RequestParam("file") MultipartFile file) {
        int records = -1;
        try {
            File zip = new File(System.getProperty("java.io.tmpdir"), file.getOriginalFilename());
            file.transferTo(zip);
            try (ZipFile zipFile = new ZipFile(zip)) {
                Path tempDirWithPrefix = Files.createTempDirectory("ReadyAPI_XML_");
                zipFile.extractAll(tempDirWithPrefix.toString());
                records = testResultTransferService.importLoadUITestStepsHistory(environment, tempDirWithPrefix);
            }
        } catch (Exception e) {
            log.error("Error occurred!", e);
        }
        if (records >= 0) {
            return format(SUCCESS_MESSAGE_METRICS, records);
        } else {
            return FAILURE_MESSAGE;
        }
    }

}