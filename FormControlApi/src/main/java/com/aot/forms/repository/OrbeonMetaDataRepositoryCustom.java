package com.aot.forms.repository;

import java.util.List;

import com.aot.forms.formApi.OrbeonMetaData;

public interface OrbeonMetaDataRepositoryCustom {
	  List<OrbeonMetaData> findByCamundaId(String camunda_id);
}
