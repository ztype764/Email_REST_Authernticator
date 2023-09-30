package com.api.test.register;

public record RegistrationRequest(
		
		 String firstName,
	     String lastName,
	    
	     String email,
	     String password,
	     String role
		) {

}
