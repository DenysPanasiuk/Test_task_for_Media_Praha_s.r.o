package com.denys_panasiuk.media_praha_sro.dao;

import com.denys_panasiuk.media_praha_sro.model.gpc.Gpc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GpcDao extends MongoRepository<Gpc, String> {

}
