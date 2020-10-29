package com.prs.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



import com.prs.business.Vendor;

public interface VendorRepo extends JpaRepository<Vendor, Integer>
{

}
