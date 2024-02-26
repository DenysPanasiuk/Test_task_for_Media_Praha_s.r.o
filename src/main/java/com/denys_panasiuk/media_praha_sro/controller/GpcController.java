package com.denys_panasiuk.media_praha_sro.controller;

import com.denys_panasiuk.media_praha_sro.model.gpc.Gpc;
import com.denys_panasiuk.media_praha_sro.service.database.DatabaseService;
import com.denys_panasiuk.media_praha_sro.service.util.FileContentGatingService;
import com.denys_panasiuk.media_praha_sro.service.util.GpcConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class GpcController {
    private final Logger logger = Logger.getLogger(String.valueOf(GpcController.class));

    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private GpcConvertService gpcConvertService;
    @Autowired
    private FileContentGatingService fileContentGatingService;

    @GetMapping("/handleLocalGpcPathAndSaveEntity")
    private ResponseEntity<Boolean> handleLocalGpcPathAndSaveEntity(@RequestParam("gpcURL") String gpcUrl) {
        logger.info("Processing of the request for storage of the GPC object from local path");
        if (gpcUrl != null) {
            String content = fileContentGatingService.getGpcContentFromLocalPath(gpcUrl);
            Gpc gpc = gpcConvertService.convertStringContentToGpcDtoEntity(content);

            if (gpc != null) {
                databaseService.saveGpc(gpc);
                return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }
}
