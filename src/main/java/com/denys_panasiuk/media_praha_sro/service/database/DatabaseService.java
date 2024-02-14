package com.denys_panasiuk.media_praha_sro.service.database;

import com.denys_panasiuk.media_praha_sro.model.gpc.DataObratovaPolozka;
import com.denys_panasiuk.media_praha_sro.model.gpc.DataVypis;

public interface DatabaseService {
    boolean saveGpcDataObratovaPolozka(DataObratovaPolozka gpcObject);
    boolean saveGpcDataVypis(DataVypis gpcObject);
}
