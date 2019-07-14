    
package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Admin;
import com.uniovi.entities.Representation;

@Service
public class InsertSampleDataService {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private RepresentationService representationService;
	
	/**
	 * Here, sample data is introduced. 
	 * It is not used in production environment.
	 */
	@PostConstruct
	public void init() {
		
	}

}