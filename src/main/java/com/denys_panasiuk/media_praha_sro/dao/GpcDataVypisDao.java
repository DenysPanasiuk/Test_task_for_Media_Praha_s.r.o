package com.denys_panasiuk.media_praha_sro.dao;

import com.denys_panasiuk.media_praha_sro.model.gpc.DataVypis;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GpcDataVypisDao extends MongoRepository<DataVypis, String> {
}
