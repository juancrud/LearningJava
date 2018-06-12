package com.juancrud.mywebapp.mapping;

import org.springframework.beans.factory.annotation.Autowired;

import com.juancrud.framework.mapper.ServiceMapper;
import com.juancrud.framework.mapper.interfaces.GenericAutoMapper;
import com.juancrud.framework.mapper.interfaces.IMapper;

public class AutoMapper extends GenericAutoMapper {
	
	@Autowired
	private IMapper marketMapper;
	
	@Autowired 
	private IMapper measureMapper;
	
	@Autowired 
	private IMapper categoryMapper;
	
	@Autowired 
	private IMapper productMapper;
	
	@Autowired
	private IMapper serviceMapper;
	
	public void init() {
		marketMapper.setup(this);
		measureMapper.setup(this);
		categoryMapper.setup(this);
		productMapper.setup(this);
		serviceMapper.setup(this);
	}
	
	public void initForTesting() {
		this.marketMapper = new MarketMapper();
		this.measureMapper = new MeasureMapper();
		this.categoryMapper = new CategoryMapper();
		this.productMapper = new ProductMapper();
		this.serviceMapper = new ServiceMapper();
	}
	
}
