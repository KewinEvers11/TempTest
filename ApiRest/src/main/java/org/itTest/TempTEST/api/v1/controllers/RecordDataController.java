package org.itTest.TempTEST.api.v1.controllers;

import org.itTest.TempTEST.api.v1.dto.request.RecordDataListRequest;
import org.itTest.TempTEST.api.v1.service.RecordDataService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/records")
public class RecordDataController {

    private final RecordDataService recordDataService;

    public RecordDataController(RecordDataService recordDataService) {
        this.recordDataService = recordDataService;
    }

    @PostMapping({"","/"})
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void registerRecords(@RequestBody RecordDataListRequest request) {
        recordDataService.registerRecords(request);
    }


}
