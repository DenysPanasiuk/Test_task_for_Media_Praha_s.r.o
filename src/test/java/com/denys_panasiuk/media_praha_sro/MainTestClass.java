package com.denys_panasiuk.media_praha_sro;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.UnsupportedEncodingException;

public class MainTestClass {
    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();

    @Test
    @DisplayName("TestSaveDataVypis")
    public void testSaveDataVypis() throws UnsupportedEncodingException {
        String gpcUrl = "0740000002400222222Novak, Jan          26061200000000019500+00000000019501+00000000000100-000000000001010000300612              LF";
        try {
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append("/handleGpcUrlAndSaveEntity");
            urlBuilder.append("?gpcURL=").append(gpcUrl);

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
    @DisplayName("TestSaveDataObratovaPolozka")
    public void testSaveDataObratovaPolozka() throws UnsupportedEncodingException {
        String gpcUrl = "0750000002400222222000000290023333300011487345300000000001002000000000000201005580000000000260612Novak, Pavel        00203260612CR";
        try {
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append("/handleGpcUrlAndSaveEntity");
            urlBuilder.append("?gpcURL=").append(gpcUrl);

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
    @DisplayName("TestSaveDPC")
    public void TestSaveDPC() throws UnsupportedEncodingException {
        String gpcUrl = "0740000002400222222Novak, Jan          26061200000000019500+00000000019501+00000000000100-000000000001010000300612              LF" +
                        "0750000002400222222000000290023333300011487345300000000001002000000000000201005580000000000260612Novak, Pavel        00203260612CR";
        try {
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append("/handleGpcUrlAndSaveEntity");
            urlBuilder.append("?gpcURL=").append(gpcUrl);

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
