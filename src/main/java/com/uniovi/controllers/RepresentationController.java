package com.uniovi.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uniovi.entities.Location;
import com.uniovi.entities.Representation;
import com.uniovi.services.RepresentationService;

@RestController
public class RepresentationController {
	
	@Autowired
	private RepresentationService representationService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Receives representation id, if it is related with a representation,
	 * it tries to remove it.
	 * @param id
	 * @return message
	 */
	@RequestMapping(value="/representation/delete/{id}", method=RequestMethod.GET)
	public String deleteRepresentation(@PathVariable Long id){
		Representation representation = representationService.getRepresentation(id);
		log.info("Trying to remove representation "+id+".");
		if(representation == null)
			return "Error";
		else 
			representationService.deleteRepresentation(id);
		
		log.info("Removed representation "+id+".");

		return "OK";
	}
	
	/**
	 * Creates a new representation from all the received data and saves
	 * it.
	 * @param title
	 * @param description
	 * @param history
	 * @param interestInfo
	 * @param technicalInfo
	 * @param latitude
	 * @param longitude
	 * @return created representation.
	 */
	@RequestMapping(value="/representation/add", method=RequestMethod.POST)
	public List<Representation> setRepresentation(@RequestParam("title") String title,
								@RequestParam("description") String description,
								@RequestParam("history") String history,
								@RequestParam("interest") String interestInfo,
								@RequestParam("technical") String technicalInfo,
								@RequestParam("latitude") String latitude,
								@RequestParam("longitude") String longitude){
		
		Representation representation = new Representation(title, description, history, interestInfo, technicalInfo, 
				Double.parseDouble(latitude), Double.parseDouble(longitude));
		representationService.addRepresentation(representation);
		
		log.info("Creation of representation with title "+representation.getTitle()+" and id "+representation.getId());
		
		List<Representation> aux = new ArrayList<>();
		aux.add(representation);
		return aux;
	}

	/**
	 * Receives all the fields and changes it. Finally, it saves
	 * the representation.
	 * @param id
	 * @param title
	 * @param description
	 * @param history
	 * @param interestInfo
	 * @param technicalInfo
	 * @param latitude
	 * @param longitude
	 * @return representation information.
	 */
	@RequestMapping(value="/representation/update/{id}", method=RequestMethod.POST)
	public List<Representation> updateRepresentation(@PathVariable Long id,
								@RequestParam("title") String title,
								@RequestParam("description") String description,
								@RequestParam("history") String history,
								@RequestParam("interest") String interestInfo,
								@RequestParam("technical") String technicalInfo,
								@RequestParam("latitude") String latitude,
								@RequestParam("longitude") String longitude){
		
		Representation representation = representationService.getRepresentation(id);
		representation.setTitle(title);
		representation.setDescription(description);
		representation.setHistory(history);
		representation.setInterestInfo(interestInfo);
		representation.setTechnicalInfo(technicalInfo);
		representation.setLongitude(Double.parseDouble(longitude));
		representation.setLatitude(Double.parseDouble(latitude));
		representationService.addRepresentation(representation);
		
		log.info("Update of representation with title "+representation.getTitle()+" and id "+representation.getId()+".");
		
		List<Representation> aux = new ArrayList<>();
		aux.add(representation);
		return aux;
	}
	
	/**
	 * It receives a file, if it is an image, is copied to the images folder.
	 * Otherwise, it is copied to the videos folder.
	 * @param id
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/representation/loadMultimedia/{id}", method=RequestMethod.POST)
	public String uploadRepresentation(@PathVariable Long id,
								@RequestParam("file") MultipartFile file){
		if(!file.isEmpty()) {
			try {
				log.info("Content type: "+file.getContentType());
				InputStream is = file.getInputStream();
				if(file.getContentType().contains("image")) {
					Files.copy(is, Paths.get("src/main/resources/static/images/img-"+id+".jpg"), StandardCopyOption.REPLACE_EXISTING);
				} else if(file.getContentType().contains("video")) {
					Files.copy(is, Paths.get("src/main/resources/static/videos/rep-"+id+".mp4"), StandardCopyOption.REPLACE_EXISTING);
				}
			} catch (IOException e) {
				e.printStackTrace();
				log.error("Exception when trying to upload multimedia file.");
				return "Error";
			}
		}
		
		log.info("Uploading multimedia of representation "+id+".");
		
		return "OK";
	}
	
	/**
	 * @return the information of all the representations.
	 */
	@RequestMapping(value="/representations", method=RequestMethod.GET)
	public Iterable<Representation> getRepresentations(){
		log.info("Getting information of all represenations.");
		return representationService.getRepresentations();
	}
	
	/**
	 * @return the list of locations.
	 */
	@RequestMapping(value="/location/list", method=RequestMethod.GET)
	public List<Location> getLocations(){
		log.info("Getting list of locations: "+representationService.getLocations().toString());
		return representationService.getLocations();
	}
	
	/**
	 * Return the information of a given representation.
	 * @param representation id
	 * @return representation information.
	 */
	@RequestMapping(value="/representation/{id}", method=RequestMethod.GET)
	public List<Representation> getRepresentation(@PathVariable Long id){
		log.info("Getting information of representation: "+id);
		List<Representation> rep = new ArrayList<Representation>();
		if (representationService.getRepresentation(id) != null) {
			rep.add(representationService.getRepresentation(id));
			return rep;
		} else {
			return null;
		}
	}
	
	/**
	 * @param id
	 * @return the path to the video folder.
	 */
	@RequestMapping(value="/representation/video/{id}", method=RequestMethod.GET)
	public String getRepresentationVideo(@PathVariable Long id){
		log.info("Getting video of representation: "+id);
		return "/videos/rep-"+id+".mp4";
	}
	
	/**
	 * @param id
	 * @return the path to the image folder.
	 */
	@RequestMapping(value="/representation/image/{id}", method=RequestMethod.GET)
	public String getRepresentationImage(@PathVariable Long id){
		log.info("Getting image of representation: "+id);
		return "/images/img-"+id+".jpg";
	}
}
