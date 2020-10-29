package com.prs.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.prs.business.Request;
import com.prs.business.User;
import com.prs.business.Vendor;

public interface RequestRepo extends JpaRepository<Request, Integer>
{


	Iterable<Request> findAllByUserIdNotAndStatus(int id, String status);

	Optional<Request> findByStatus(String string);
	
}
