package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Admin;
import com.uniovi.repositories.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Admin getAdmin(Long id) {
		return adminRepository.findById(id).get();
	}
	
	/**
	 * It needs to encrypt the password that is received.
	 * @param admin
	 */
	public void addAdmin(Admin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		adminRepository.save(admin);
	}
	
	public List<Admin> getAdminByEmail(String email) {
		return adminRepository.findByEmail(email);
	}
	
	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);
	}
	
	/**
	 * If admin password and the new password are equals, we can update it.
	 * @param admin
	 * @param password
	 */
	public void updateAdmin(Admin admin, String password) {
		if (admin.getPassword().equals(passwordEncoder.encode(password))) {
			admin.setPassword(password);
			adminRepository.save(admin);
		}
	}
}
