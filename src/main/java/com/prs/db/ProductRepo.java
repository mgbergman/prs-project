package com.prs.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.prs.business.Product;
import com.prs.business.Vendor;

public interface ProductRepo extends JpaRepository<Product, Integer>
{

}
