package com.denys_panasiuk.media_praha_sro.model.gpc;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Gpc")
@Data
@Getter
@Setter
public class Gpc {
    @Id
    private String id;
    private DataVypis dataVypis;
    private List<DataObratovaPolozka> dataObratovaPolozkaList;

    public Gpc() {
        dataObratovaPolozkaList = new ArrayList<>();
    }
}
