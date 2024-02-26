package com.denys_panasiuk.media_praha_sro.service.util;

import com.denys_panasiuk.media_praha_sro.model.gpc.DataObratovaPolozka;

public interface DataObratovaPolozkaConverterService {
    DataObratovaPolozka convertStringToDataObratovaPolozka(String dataObratovaPolozkaContent);
}
