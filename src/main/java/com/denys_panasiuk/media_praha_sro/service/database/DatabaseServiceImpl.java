package com.denys_panasiuk.media_praha_sro.service.database;

import com.denys_panasiuk.media_praha_sro.dao.GpcDao;
import com.denys_panasiuk.media_praha_sro.model.gpc.Gpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseServiceImpl implements DatabaseService {
    @Autowired
    private GpcDao gpcDao;

    @Override
    public void saveGpc(Gpc gpc) {
        gpcDao.save(gpc);
    }
}
