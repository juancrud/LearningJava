package com.juancrud.framework.entity.interfaces;

import java.util.Date;

public interface IAuditable {
	
	void setDateCreated(Date dateCreated);
	Date getDateCreated();
	
	void setDateUpdated(Date dateUpdated);
	Date getDateUpdated();
	
}
