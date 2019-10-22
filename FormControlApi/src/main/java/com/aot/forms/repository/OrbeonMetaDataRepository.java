package com.aot.forms.repository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.aot.forms.formApi.OrbeonMetaData;

@Component
@Repository
public interface  OrbeonMetaDataRepository extends JpaRepository <OrbeonMetaData, Long> {

}
