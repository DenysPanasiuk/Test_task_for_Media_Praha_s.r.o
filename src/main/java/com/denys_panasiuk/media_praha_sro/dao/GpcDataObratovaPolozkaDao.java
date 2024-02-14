package com.denys_panasiuk.media_praha_sro.dao;

import com.denys_panasiuk.media_praha_sro.model.gpc.DataObratovaPolozka;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GpcDataObratovaPolozkaDao extends MongoRepository<DataObratovaPolozka, String> {
}
