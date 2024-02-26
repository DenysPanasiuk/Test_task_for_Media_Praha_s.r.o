package com.denys_panasiuk.media_praha_sro.service.util;

import com.denys_panasiuk.media_praha_sro.model.gpc.Gpc;

public interface GpcConvertService {
    Gpc convertStringContentToGpcDtoEntity(String url);
}
