package com.juancrud.dao.market;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.juancrud.entity.Market;
import com.juancrud.framework.dao.GenericDao;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class MarketDao extends GenericDao<Market, Long> implements IMarketDao {

}
