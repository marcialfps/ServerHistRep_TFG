package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uniovi.entities.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long> {
	public List<Admin> findByEmail(String email);
}
