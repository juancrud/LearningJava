package com.juancrud.dao.measure;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.juancrud.entity.Measure;
import com.juancrud.framework.dao.GenericDao;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class MeasureDao extends GenericDao<Measure, Long> implements IMeasureDao {

}
