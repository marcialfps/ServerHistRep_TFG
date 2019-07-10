package com.uniovi.serverhistrep;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.uniovi.entities.Admin;
import com.uniovi.entities.Representation;
import com.uniovi.repositories.AdminRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerHistRepApplicationTests {
	
	@Test
	public void testAdminInfo() throws Exception {
		String email = "test@test.com";
		String name ="testName";
		String lastname = "testLastName";
		
		Admin admin = new Admin("test@test.com", "testName", "testLastName");
		
		assertTrue(admin.getEmail().equals(email));
		assertTrue(admin.getName().equals(name));
		assertTrue(admin.getLastName().equals(lastname));
	}
	
	@Test
	public void testRepresentationInfo() throws Exception {
		String title = "testTitle";
		String description = "testDescription";
		String history = "testHistory";
		String interest = "testInterest";
		String technical = "testTechnical";
		double longitude = 1.23456;
		double latitude = 4.56789;
		
		Representation rep = new Representation("testTitle", "testDescription", 
				"testHistory", "testInterest", "testTechnical", 1.23456, 4.56789);
		
		assertTrue(rep.getTitle().equals(title));
		assertTrue(rep.getDescription().equals(description));
		assertTrue(rep.getHistory().equals(history));
		assertTrue(rep.getInterestInfo().equals(interest));
		assertTrue(rep.getTechnicalInfo().equals(technical));
		assertTrue(rep.getLongitude() == longitude);
		assertTrue(rep.getLatitude() == latitude);
	}

}
