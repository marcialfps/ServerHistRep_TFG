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
		Admin admin2 = new Admin("admin2@gmail.com", "Pedro", "Picapiedra");
		admin2.setPassword("admin1234");
		
		adminService.addAdmin(admin1);
		adminService.addAdmin(admin2);
		
		Representation rep1 = new Representation("Escuela de ingeniería informática", 
				"Esta es la escuela de ingeniería informática de la uniovi.", 
				"Aquí se representa la historia de esta escuela.", 
				"En el pasado fue una guardería.", 
				"Es una representación de prueba.", 43.360481, -5.842514);
		Representation rep2 = new Representation("Colegio Mayor San Gregorio", 
				"Este es el Colegio Mayor San Gregorio en Oviedo.", 
				"Aquí se representa su historia pasada.", 
				"", 
				"Es una representación de prueba.", 43.354561, -5.852249);
		Representation rep3 = new Representation("Colegio Mayor América", 
				"Esta es la escuela de ingeniería informática de la uniovi.", 
				"Aquí se representa la historia de esta escuela.", 
				"En el pasado fue una guardería.", 
				"Es una representación de prueba.", 43.353989, -5.853267);
		Representation rep4 = new Representation("Casa", 
				"Esto es una prueba en casa.", 
				"Aquí se representa la historia.", 
				"Nada importante.", 
				"Es una representación de prueba.", 29.054460, -13.633982);
		
		representationService.addRepresentation(rep1);
		representationService.addRepresentation(rep2);
		representationService.addRepresentation(rep3);
		representationService.addRepresentation(rep4);
	}

}
