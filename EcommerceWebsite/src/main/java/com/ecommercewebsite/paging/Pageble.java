package com.ecommercewebsite.paging;

import com.ecommercewebsite.sort.Sorter;

public interface Pageble {
	Integer getPage();
	Integer getOffset();
	Integer getLimit();
	Sorter getSorter();
	
}
