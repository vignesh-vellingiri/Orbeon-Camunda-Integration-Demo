package com.aot.forms.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import  javax.persistence.Query;
import org.springframework.stereotype.Repository;

import com.aot.forms.formApi.OrbeonMetaData;

@Repository
//@Transactional(readOnly = true)
public class OrbeonMetaDataRepositoryImpl implements OrbeonMetaDataRepositoryCustom {
	@PersistenceContext
    EntityManager entityManager;
    @Override
    public List<OrbeonMetaData> findByCamundaId(String camunda_id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM orbeon_meta_data " +
                "WHERE camunda_id LIKE ?", OrbeonMetaData.class);
        query.setParameter(1, camunda_id + "%");
        return query.getResultList();
    }
}
