package com.denys_panasiuk.media_praha_sro.service.database;

import com.denys_panasiuk.media_praha_sro.dao.GpcDataObratovaPolozkaDao;
import com.denys_panasiuk.media_praha_sro.dao.GpcDataVypisDao;
import com.denys_panasiuk.media_praha_sro.model.gpc.DataObratovaPolozka;
import com.denys_panasiuk.media_praha_sro.model.gpc.DataVypis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseServiceImpl implements DatabaseService {
    @Autowired
    private GpcDataObratovaPolozkaDao gpcDataObratovaPolozkaDao;
    @Autowired
    private GpcDataVypisDao gpcDataVypisDao;

    @Override
    public boolean saveGpcDataObratovaPolozka(DataObratovaPolozka gpcObject) {
        if (gpcObject != null) {
            gpcDataObratovaPolozkaDao.save(gpcObject);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean saveGpcDataVypis(DataVypis gpcObject) {
        if (gpcObject != null) {
            gpcDataVypisDao.save(gpcObject);
            return false;
        } else {
            return true;
        }
    }
}
