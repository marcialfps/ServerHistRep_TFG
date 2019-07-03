    
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
	
	@PostConstruct
	public void init() {
		Admin admin1 = new Admin("admin@gmail.com", "Marcial", "Parrilla");
		admin1.setPassword("admin123");
		
		adminService.addAdmin(admin1);
		
		Representation rep1 = new Representation("Escuela de ingeniería informática", 
				"Esta es la escuela de ingeniería informática de la uniovi.", 
				"Aquí se representa la historia de esta escuela.", 
				"En el pasado fue una guardería.", 
				"Es una representación de prueba.", 43.360481, -5.842514);
		
		representationService.addRepresentation(rep1);
	}

}