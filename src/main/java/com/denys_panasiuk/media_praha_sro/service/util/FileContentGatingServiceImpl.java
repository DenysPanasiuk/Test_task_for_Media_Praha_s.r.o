package com.denys_panasiuk.media_praha_sro.service.util;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class FileContentGatingServiceImpl implements FileContentGatingService{
    @Override
    public String getGpcContentFromLocalPath(String gpcPath) {

        try (BufferedReader reader = new BufferedReader(new FileReader(gpcPath))) {
            StringBuilder contentBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
            }

            return contentBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
