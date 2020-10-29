package com.prs.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import com.prs.business.Lineitem;
import com.prs.business.Product;
import com.prs.business.Request;
import com.prs.business.User;

public interface LineitemRepo extends JpaRepository<Lineitem, Integer>
{
List<Lineitem> findAllByRequestId(int id);

List<Request> findAllLinesByRequestId(int id);




 
}
