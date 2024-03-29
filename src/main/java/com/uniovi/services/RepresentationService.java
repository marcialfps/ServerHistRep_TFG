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
	}
	
	public void addRepresentation(Representation representation) {
		representationRepository.save(representation);
	}
	
	public void deleteRepresentation(Long id) {
		representationRepository.deleteById(id);
	}
	
	/**
	 * It obtains all the representations and creates a list of locations
	 * obtaining the latitude and longitude of each representation.
	 * @return
	 */
	public List<Location> getLocations() {
		List<Location> locations = new ArrayList<Location>();
		for(Representation rep : representationRepository.findAll()) {
			locations.add(new Location(rep.getLatitude(), rep.getLongitude(), rep.getId()));
		}
		return locations;
	}
}
