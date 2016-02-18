package com.springmvc.services;

import com.springmvc.domain.Product;

public interface ProductService {

	
    Product add(Product product);
    Product get(long id);

}
