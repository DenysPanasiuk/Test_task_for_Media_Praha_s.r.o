package com.denys_panasiuk.media_praha_sro.service.util;

import com.denys_panasiuk.media_praha_sro.model.gpc.DataObratovaPolozka;
import com.denys_panasiuk.media_praha_sro.model.gpc.DataVypis;
import com.denys_panasiuk.media_praha_sro.model.gpc.Gpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GpcConvertServiceImpl implements GpcConvertService {
    @Autowired
    private DataVypisConverterService dataVypisConverterService;
    @Autowired
    private DataObratovaPolozkaConverterService dataObratovaPolozkaConverterService;
    @Override
    public Gpc convertStringContentToGpcDtoEntity(String content) {
        if (content != null) {
            Gpc gpc = new Gpc();
            if ((content.length() % 130) == 0) {
                String urlRepresantation;

                for (int i = 0; i < content.length() / 130; i++) {
                    urlRepresantation = content.substring((i * 130), ((i + 1) * 130));
                    if (i == 0) {
                        DataVypis dataVypis = convertStringToDataVypis(urlRepresantation);
                        if (dataVypis == null) return null;
                        else gpc.setDataVypis(dataVypis);
                    } else {
                        DataObratovaPolozka dataObratovaPolozka = convertStringToDataObratovaPolozka(urlRepresantation);
                        if (dataObratovaPolozka == null) return null;
                        else gpc.getDataObratovaPolozkaList().add(dataObratovaPolozka);
                    }
                }
            } else {
                return null;
            }
            return gpc;
        } else {
            return null;
        }
    }

    private DataVypis convertStringToDataVypis(String dataVypisContent) {
        return dataVypisConverterService.convertStringToDataVypis(dataVypisContent);
    }

    private DataObratovaPolozka convertStringToDataObratovaPolozka(String dataObratovaPolozkaContent) {
        return dataObratovaPolozkaConverterService.convertStringToDataObratovaPolozka(dataObratovaPolozkaContent);
    }
}
