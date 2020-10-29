package com.prs.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



import com.prs.business.User;

public interface UserRepo extends JpaRepository<User, Integer>
{
		User findByUserNameAndPassword(String userName, String password);
}
