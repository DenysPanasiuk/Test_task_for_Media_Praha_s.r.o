package com.denys_panasiuk.media_praha_sro.controller;

import com.denys_panasiuk.media_praha_sro.model.gpc.DataObratovaPolozka;
import com.denys_panasiuk.media_praha_sro.model.gpc.DataVypis;
import com.denys_panasiuk.media_praha_sro.service.database.DatabaseService;
import com.denys_panasiuk.media_praha_sro.service.util.GpcConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class GpcController {
    private final Logger logger = Logger.getLogger(String.valueOf(GpcController.class));

    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private GpcConvertService gpcConvertService;

    @GetMapping("/handleGpcUrlAndSaveEntity")
    private ResponseEntity<Boolean> handleGpcUrlAndSaveEntity(@RequestParam("gpcURL") String gpcUrl) {
        logger.info("Processing of the request for storage of the GPC object");
        if (gpcUrl != null) {
            boolean isError = false;
            List<Object> gpcList = gpcConvertService.convertUrlToGpcDtoEntity(gpcUrl);
            if (gpcList != null && !gpcList.isEmpty()) {
                for (Object gpcObject : gpcList) {
                    if (gpcObject == null) {
                        isError = true;
                    } else if (gpcObject instanceof DataVypis) {
                        isError = databaseService.saveGpcDataVypis((DataVypis) gpcObject);
                    } else if (gpcObject instanceof DataObratovaPolozka) {
                        isError = databaseService.saveGpcDataObratovaPolozka((DataObratovaPolozka) gpcObject);
                    }

                    if (isError) break;
                }
                if (!isError) {
                    return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }
}
