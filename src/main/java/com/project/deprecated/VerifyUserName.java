package com.project.deprecated;

public class VerifyUserName {
	
	public String registerUsername(String newUsername) {

		System.out.println("Enter a new Username: ");

		newUsername = scanner.nextLine();

		if (!newUsername.isEmpty()) {
			
			//TODO: 
			// Compare regdUsername to stored usersnames to avoid overwriting a user.
			//if (/*SQL QUERY exists*/){ 
			if (newUsername.equals("test")) {
				System.out.println("Username already exists!");
				return registerUsername(newUsername);
			}
			
			System.out.println("Username set to: " + newUsername);
			return newUsername;
		} else {
				System.out.println("You must enter a Username!");
				return registerUsername(newUsername);
			}
		
	}
}
