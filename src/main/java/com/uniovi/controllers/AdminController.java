package com.uniovi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Admin;
import com.uniovi.services.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * First it verifies that the passwords are equal. Then, save
	 * the admin and return the admin in a list in order to be
	 * readed by the AdminHistRep module.
	 * @param name
	 * @param lastName
	 * @param email
	 * @param password
	 * @param passwordConfirm
	 * @return admin information.
	 */
	@RequestMapping(value="/admin/add", method=RequestMethod.POST)
	public List<Admin> setAdmin(@RequestParam("name") String name,
								@RequestParam("lastname") String lastName,
								@RequestParam("email") String email,
								@RequestParam("password") String password,
								@RequestParam("passwordConfirm") String passwordConfirm){
		
		Admin admin = new Admin(email, name, lastName);
		
		if (password.equals(passwordConfirm)) {
			admin.setPassword(password);
			admin.setPasswordConfirm(passwordConfirm);
			adminService.addAdmin(admin);
		} else {
			return null;
		}
		
		log.info("Creation of admin "+admin+".");
		
		List<Admin> aux = new ArrayList<Admin>();
		aux.add(admin);
		return aux;
	}
	
	/**
	 * It receives the id of the admin. If there is not admin
	 * with that id, it returns an error. Else, delete the admin
	 * and returns a message.
	 * @param id
	 * @return status message.
	 */
	@RequestMapping(value="/admin/delete/{id}", method=RequestMethod.GET)
	public String deleteAdmin(@PathVariable Long id){
		Admin admin = adminService.getAdmin(id);
		if(admin == null)
			return "{\"status\": \"Error\"}";
		else 
			adminService.deleteAdmin(id);
		
		log.info("Removed admin "+id+".");

		return "{\"status\": \"Done\"}";
	}
	
	/**
	 * If the id is not associated with an admin account, null is returned.
	 * Else, it updates all the fields and save the admin. 
	 * @param id
	 * @param name
	 * @param lastName
	 * @param email
	 * @param password
	 * @param passwordConfirm
	 * @return admin information.
	 */
	@RequestMapping(value="/admin/update/{id}", method=RequestMethod.POST)
	public List<Admin> updateAdmin(@PathVariable Long id,
							@RequestParam("name") String name,
							@RequestParam("lastname") String lastName,
							@RequestParam("email") String email,
							@RequestParam("password") String password,
							@RequestParam("passwordConfirm") String passwordConfirm) {
		Admin admin = adminService.getAdmin(id);
		
		
		if(admin == null)
			return null;
		else {
			admin.setName(name);
			admin.setLastName(lastName);
			admin.setEmail(email);
			admin.setPassword(password);
			admin.setPasswordConfirm(passwordConfirm);
			adminService.addAdmin(admin);
		}
		
		log.info("Updated admin "+id+".");
		List<Admin> aux = new ArrayList<Admin>();
		aux.add(admin);
		return aux;
	}	
	
	/**
	 * @param email
	 * @param password
	 * @return true if the login is correct.
	 */
	@RequestMapping(value="/admin/login", method=RequestMethod.POST)
	public boolean login(@RequestParam("email") String email,
								@RequestParam("password") String password){
		log.info("Trying to log user with email: "+email);
		
		if (passwordEncoder.matches(password, adminService.getAdminByEmail(email).get(0).getPassword())) {
			log.info("Correct login.");
			return true;
		}
		else {
			log.info("Invalid login.");
			return false;
		}
	}
	
	/**
	 * @param user email
	 * @return admin information.
	 */
	@RequestMapping(value="/admin/{email}", method=RequestMethod.GET)
	public List<Admin> getAdmin(@PathVariable("email") String email) {
		log.info("Obtaining admin: "+email);
		return adminService.getAdminByEmail(email);
	}
	
	/**
	 * @param user id
	 * @return admin information.
	 */
	@RequestMapping(value="/adminId/{id}", method=RequestMethod.GET)
	public List<Admin> getAdminId(@PathVariable("id") Long id) {
		log.info("Obtaining admin: "+id);
		List<Admin> aux = new ArrayList<Admin>();
		aux.add(adminService.getAdmin(id));
		return aux;
	}
}
