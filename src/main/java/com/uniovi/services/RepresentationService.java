package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Location;
import com.uniovi.entities.Representation;
import com.uniovi.repositories.RepresentationRepository;

@Service
public class RepresentationService {
	
	@Autowired
	private RepresentationRepository representationRepository;
	
	public Iterable<Representation> getRepresentations() {
		return representationRepository.findAll();
	}

	public Representation getRepresentation(Long id) {
		return representationRepository.findById(id).get();
		//return representationRepository.findOne(id);
	}
	
	public void addRepresentation(Representation publication) {
		representationRepository.save(publication);
	}
	
	public void deleteRepresentation(Long id) {
		representationRepository.deleteById(id);
	}
	
	public List<Location> getLocations() {
		List<Location> locations = new ArrayList<Location>();
		for(Representation rep : representationRepository.findAll()) {
			locations.add(new Location(rep.getLatitude(), rep.getLongitude(), rep.getId()));
		}
		return locations;
	}
}
