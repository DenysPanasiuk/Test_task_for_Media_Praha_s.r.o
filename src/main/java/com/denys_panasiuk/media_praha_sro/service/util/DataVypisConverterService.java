package com.denys_panasiuk.media_praha_sro.service.util;

import com.denys_panasiuk.media_praha_sro.model.gpc.DataVypis;

public interface DataVypisConverterService {
    DataVypis convertStringToDataVypis(String dataVypisContent);
}
