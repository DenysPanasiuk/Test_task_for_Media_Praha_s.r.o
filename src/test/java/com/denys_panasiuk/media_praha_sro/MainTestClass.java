package com.denys_panasiuk.media_praha_sro;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

public class MainTestClass {
    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();

    @Test
    @DisplayName("TestSaveGPC1DV1DOPWithLocalPath")
    public void TestSaveGPC1DV1DOPWithLocalPath() {
        try {
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append("/handleLocalGpcPathAndSaveEntity");
            urlBuilder.append("?gpcURL=").append("C:\\MediaPraha\\payments\\paymentGpcWith1DataVypis1DataObratovaPolozka.gpc");

            Boolean isSaved = webClient
                    .get()
                    .uri(urlBuilder.toString())
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            Assertions.assertEquals(Boolean.TRUE, isSaved);
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    @DisplayName("TestSaveGPC1DV3DOPWithLocalPath")
    public void TestSaveGPC1DV3DOPWithLocalPath() {
        try {
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append("/handleLocalGpcPathAndSaveEntity");
            urlBuilder.append("?gpcURL=").append("C:\\MediaPraha\\payments\\paymentGpcWith1DataVypis3DataObratovaPolozka.gpc");

            Boolean isSaved = webClient
                    .get()
                    .uri(urlBuilder.toString())
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            Assertions.assertEquals(Boolean.TRUE, isSaved);
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    @DisplayName("TestSaveGPC1DVWithLocalPath")
    public void TestSaveGPC1DVWithLocalPath() {
        try {
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append("/handleLocalGpcPathAndSaveEntity");
            urlBuilder.append("?gpcURL=").append("C:\\MediaPraha\\payments\\paymentGpcWith1DataVypis0DataObratovaPolozka.gpc");

            Boolean isSaved = webClient
                    .get()
                    .uri(urlBuilder.toString())
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            Assertions.assertEquals(Boolean.TRUE, isSaved);
        } catch (Exception e) {
            throw e;
        }
    }
}
