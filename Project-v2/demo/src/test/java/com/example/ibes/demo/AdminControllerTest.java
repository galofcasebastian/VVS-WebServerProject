package com.example.ibes.demo;

import org.junit.Test;

public class AdminControllerTest {
    
    private AdminController adminController = new AdminController();

    @Test(expected = VvsException.class) 
	public void manageAdminPanelTest() {
		System.out.println("Checking for VVSException...");
		adminController.manageAdminPanel();

		System.out.println("manageAdminPanel test was successful!");
	}

	@Test(expected = VvsException.class) 
	public void deleteUserTest() {
		System.out.println("Checking for VVSException...");
		adminController.deleteUser();

		System.out.println("deleteUser test was successful!");
	}
}
