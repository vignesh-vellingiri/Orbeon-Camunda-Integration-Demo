package com.aot.forms.repository;
import java.util.List;

import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.aot.forms.formApi.OrbeonMetaData;

@Component
@Repository
//@Import(OrbeonMetaData.class)
public interface  OrbeonMetaDataRepository extends JpaRepository <OrbeonMetaData, Long>{

	  @Query(value = "select u FROM OrbeonMetaData u WHERE u.camunda_id = ?1")
	  OrbeonMetaData findByCamundaIdEquals(String camundaId);
	  
	  @Query(value = "select u FROM OrbeonMetaData u WHERE u.documentid = ?1")
	  OrbeonMetaData findByDocumentIdEquals(String documentId);
}
