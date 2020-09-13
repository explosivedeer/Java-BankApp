package com.project.driver;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.project.dao.DaoContract;
import com.project.model.UserTable;
import com.project.services.Customer;
import com.project.services.Employee;
import com.project.services.NewUser;
import com.project.services.User;

public class Menu {

	/*
	 * User: 1. Can Login 2. Can Register
	 * 
	 * Customer: 1. View Balance 2. Withdrawal 3. Deposit 4. Transfer 5*. Loan 6*.
	 * Pay Loan test
	 * 
	 * Employee 1. Approve or Reject Account Creation 2. View Transaction Logs 3.
	 * Approve of Loans
	 * 
	 * Manager: 1. Make Accounts 2. Delete Accounts 3. View logs of all actions in
	 * app
	 * 
	 */

	private static Scanner scanner = new Scanner(System.in);

	public static void mainMenu() {

		System.out.println("Banking Application: ");
		System.out.println("Choose an option: \n" + 
							"1.) Register an account\n" + 
							"2.) Login\n" + 
							"3.) Quit\n" +
							"4.) Test a Method\n"
							);
		int userSelection = scanner.nextInt();
		scanner.nextLine();

		switch (userSelection) {
		case 1:
			System.out.println("Banking Application: ");
			System.out.println("Register an account: ");
			registerMenu();
			break;
		case 2:
			System.out.println("Banking Application: ");
			System.out.println("Login: ");
			loginMenu();
			break;
		case 3:
			quit();
			break;
		case 4:
			testMethod();
			break;
		default:
			System.out.println("Invalid Option.");
			break;
		}

	}

	public static void registerMenu() {
		// TODO:
		/*
		 * 1. create edge case for user/pass fields. Prevent certain characters. 2.
		 * create validation for entered text, ensure its within the rules. 3. compare
		 * to existing accounts and prevent duplicates of usernames.
		 */
		String newUsername = "";
		String newPassword = "";

		String regdUsername = registerUsername(newUsername);
		String regdPassword = registerPassword(newPassword);
		// System.out.println("SEE ME: " + regdUsername);
		// System.out.println("SEE ME: " + regdPassword);

		NewUser user = new NewUser(regdUsername, regdPassword);
		
		// TODO Store information in a new User Object!
				/*
				*	Store NewUserObject in SQL database
				*	
				* 	As Employee, get NewUserObjects from SQL database
				* 	Approve objects.
				* 
				*/
		
		DaoContract<User, Integer> dc = new UserTable();
		dc.registerUser(user);
		
		
		System.out.println("You've registered a new account with the details:");
		System.out.println("Username: " + user.getUsername() + "\nPassword: " + user.getPassword());
		System.out.println("Your ID is: " + user.getId());
		
		
		// int id2 = Driver.id;

		System.out.println();

		// System.out.println("Password set to: " + registerPassword(newPassword));

		

	}

	public static String registerUsername(String newUsername) {

		System.out.println("Enter a new Username: ");
		DaoContract<User, Integer> dc = new UserTable();
		newUsername = scanner.nextLine();
		
		if (!newUsername.isEmpty()) {
			
			// Compare regdUsername to stored usersnames to avoid overwriting a user.
			//if (/*SQL QUERY exists*/){ 
			if (dc.isTaken(newUsername)) {
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

	public static String registerPassword(String newPassword) {

		System.out.println("Enter a new Password: ");

		newPassword = scanner.nextLine();

		if (!newPassword.isEmpty()) {
			System.out.println("Password set to: " + newPassword);
			return newPassword;
		} else {
			System.out.println("You must enter a Password!");
			return registerPassword(newPassword);

		}
	}

	public static void loginMenu() {
		// TODO:
		/*
		 * 1. create edge case for user/pass fields. Prevent certain characters. 2.
		 * compare to existing accounts to deny or grant access to the account. 3.
		 * create a separate method called verify to handle comparison of account
		 * information.
		 */
		String userName = "";
		String passWord = "";
		
		int n = 0;
		NewUser user = new NewUser(userName, passWord);
		String verifiedUsername = loginUsername(userName);
		user.setUsername(verifiedUsername);
		
		loginPassword(user, n);
		
		System.out.println("NEWUSER OBJECT:");
		System.out.println("ID: " + user.getId());
		System.out.println("USER: " + user.getUsername());
		System.out.println("PASS: " + user.getPassword());
		
		if (user.getId() > -1) {
			/* TODO:
			 * Call a method from UserTable that checks the matched account's
			 * Access Level associated with user.
			 */
			int accessLevel = user.getId();
			int switchLevel = 0;
			
			//this is horribly hard coded... deciding access level by userid...
			if (accessLevel > 1) {
				switchLevel = 1;
			} else if (accessLevel == 1) {
				switchLevel = 2;
			} else if (accessLevel == 0) {
				switchLevel = 1;
			}
			
			
			switch (switchLevel) {
			case 1: 
				System.out.println();
				System.out.println("You've logged in as a Customer!");
				System.out.println("Username: " + user.getUsername() + "\nWith ID: " + user.getId());
				Customer customer = new Customer(user.getUsername(), user.getPassword(), user.getId());
//				System.out.println("CUSTOMER OBJECT: ");
//				System.out.println("Username: " + customer.getUsername());
//				System.out.println("Password: " + customer.getPassword());
//				System.out.println("ID: " + customer.getId());
				customerMenu(customer);
				break;
			case 2:
				System.out.println();
				System.out.println("You've logged in as an Employee!");
				System.out.println("Username: " + user.getUsername() + "\nWith ID: " + user.getId());
				Employee employee = new Employee(user.getUsername(), user.getPassword(), user.getId());
				employeeMenu(employee);
				break;
			case 3:
				System.out.println("You've logged in as a Manager!");
				System.out.println("Username: " + user.getUsername() + "\nWith ID: " + user.getId());
				managerMenu();
				break;
			default:
				mainMenu();
				break;
			}
			
		} else {
			System.out.println("Failed to login!");
			System.out.println("Returning to the main menu...");
			mainMenu();
		}
		
		// TODO Store information in a new User Object!
				/*
				*	Store NewUserObject in SQL database
				*	
				* 	As Employee, get NewUserObjects from SQL database
				* 	Approve objects.
				* 
				*/
		
//		DaoContract<NewUser, Integer> dc = new UserTable();
//		dc.verifyLogin(user);
		
		System.out.println();
		
		
	}
	
	public static String loginUsername(String userName) {

		System.out.println("Enter Username: ");

		userName = scanner.nextLine();

		//boolean verified = false;

		if (!userName.isEmpty()) {
			String vfdUsername = userName;
			return vfdUsername;
		} else {
			System.out.println("You must enter a Username!");
			return loginUsername(userName);
		}
	}
	
//	public boolean loginPassword(String passWord, String verifiedUsername, int n) {
//		
//		System.out.println("Username: " + verifiedUsername + "\nPassword: " + passWord);
//		
//		DaoContract<NewUser, Integer> dc = new UserTable();
//		NewUser user = new NewUser(verifiedUsername, passWord);
//		boolean verified = false;
//		
//		System.out.println("Enter a Password: ");
//		passWord = scanner.nextLine();
//		user.setPassword(passWord);
//		
//		int tries = 3;
//		if (tries - 1 == n) {
//			System.out.println("The FBI is looking for you.");
//			mainMenu();
//		}
//		// TODO:
//		// Compare userName to stored usersnames to login.
//		// if (/*SQL QUERY exists*/){
//		
//		if (!passWord.isEmpty()) {
//			System.out.println("OBJECT Username: " + user.getUsername() + "\n OBJECT Password: " + user.getPassword());
//			System.out.println("Result of verifyLogin: " + dc.verifyLogin(user));
//			if(dc.verifyLogin(user)) {
//				verified = true;
//				System.out.println("Sucess!");
//				return verified;
//			}
//			System.out.println("Username or Password is incorrect!");
//			System.out.println("Username: " + verifiedUsername + "\nPassword: " + passWord);
//			n++;
//			return loginPassword(passWord, verifiedUsername, n);
//		} else {
//			System.out.println("You must enter a Password!");
//			n++;
//			return loginPassword(passWord, verifiedUsername, n);
//		}
//	}
	
	
	public static boolean loginPassword(NewUser user, int n) {
		
		DaoContract<User, Integer> dc = new UserTable();
		boolean verified = false;
		System.out.println("Username: " + user.getUsername() + "\nPassword: " + user.getPassword());
		
		System.out.println("Enter a Password: ");
		String getPass = scanner.nextLine();
		user.setPassword(getPass);
		
		int tries = 3;
		if (tries - 1 == n) {
			System.out.println("The FBI is looking for you.");
			user.setUsername("");
			user.setPassword("");
			mainMenu();
		}
		// TODO:
		// Compare userName to stored usersnames to login.
		// if (/*SQL QUERY exists*/){
		
		if (!getPass.isEmpty()) {
			System.out.println("OBJECT Username: " + user.getUsername() + "\n OBJECT Password: " + user.getPassword());
			System.out.println("Result of verifyLogin: " + dc.verifyLogin(user));
			if(dc.verifyLogin(user)) {
				verified = true;
				System.out.println("Sucess!");
				user.setPassword(getPass);
				return verified;
			}
			System.out.println("Username or Password is incorrect!");
			System.out.println("Username: " + user.getUsername() + "\nPassword: " + getPass);
			n++;
			return loginPassword(user, n);
		} else {
			System.out.println("You must enter a Password!");
			n++;
			return loginPassword(user, n);
		}
	}
	

	public static void customerMenu(Customer c) {

		// TODO:
		/*
		 * If customer, employee, or manager, provide additional menu options Ensure
		 * "userName" is accessible
		 */
		DaoContract<User, Integer> dc = new UserTable();
		
		
		System.out.println();
		System.out.println("Banking Application: ");
		System.out.println("Welcome " + c.getUsername());
		System.out.println("Choose an option: \n" + 
							"1.) Open Account \n" +
							"2.) View Balance \n" + 
							"3.) Withdraw \n" + 
							"4.) Deposit \n" + 
							"5.) Transfer \n" + 
							"6.) Loan \n" + 
							"7.) Pay Loan \n" + 
							"8.) Quit"
							);
		int userSelection = scanner.nextInt();
		scanner.nextLine();
		//Employee would fill the getters assigning username and password from user registration
		//Customer customer = new Customer(username, password);
		
		
		
		switch (userSelection) {
		case 1:
			System.out.println("Would you like to open a new Account?");
			System.out.println("Choose an option: \n" +
								"1.) Yes \n" +
								"2.) No \n"
								);
			int answer = scanner.nextInt();
			if (answer == 2) {
				System.out.println("Cancelling...");
				customerMenu(c);
			} else {
				int id = c.getId();
				int account_id = c.create_account(id);
				System.out.println("Success! You've opened an account with an ID: " + account_id);
				customerMenu(c);
			}
			break;
			
		case 2:
			System.out.println("View Balance");
			System.out.println("Enter Account ID:");
			viewBalance(dc, c);
			
			customerMenu(c);
			break;
		case 3:
			System.out.println("Banking Application: ");
			System.out.println("Choose an Option: \n" +
								"1.)Withdraw Money \n" +
								"2.)Cancel "
								);
			withdrawMenu(dc, c, scanner.nextInt());
			break;
		case 4:
			System.out.println("Banking Application: ");
			System.out.println("Choose an Option: \n" +
								"1.)Deposit Money \n" +
								"2.)Cancel "
								);
			depositMenu(dc, c, scanner.nextInt());
			break;
		case 5:
			System.out.println("Banking Application: ");
			System.out.println("Choose an Option: \n" + 
								"1.) Transfer Funds \n" +
								"2.) Cancel \n"
								);
			transferMenu(dc, c, scanner.nextInt());
			break;
		case 6:
			//manageLoan();
			break;
		case 7:
			//payLoan();
			break;
		case 8:
			quit();
			break;
		default:
			System.out.println("Invalid Option.");
			break;
		}
	}
	
	public static void withdrawMenu(DaoContract<User, Integer> dc, Customer c, int option) {
		switch (option) {
		case 1:
			System.out.println("Withdraw Menu: ");
			System.out.println("Enter the ID of the account you are withdrawing from.");
			//TODO: List AccountID's associated to this CustomerID
			c.setAccount_id(scanner.nextInt());
			
			
			//TODO: Make this a separate method, clean up
			boolean flag = dc.verifyAccountID(c);
			if (flag) {
				System.out.println("You have entered a valid Account ID!");
				System.out.println("Enter the amount to withdraw: $(0.0)");
				System.out.print("$");
				//update accountid
				//System.out.println("Current Account ID: " + c.getAccount_id());
				try {
				BigDecimal money = scanner.nextBigDecimal();
				
				BigDecimal negmon = money.negate();
				//TODO: grab money entered and pass to customer
				//System.out.println("PRINTING NEGATIVE MONEY" + negmon);
				c.setTransaction(negmon);
				
				
				c.updateBalance();
				BigDecimal resultDebited = dc.debitAccount(c);
				BigDecimal result = resultDebited.abs();
				//TODO:
				/*
				 * Do not calculate balance here, do it inside Customer.class
				 * Rather, simply c.getBalance() to see current balance.
				 */
				//call method to calculate balance
				
				
				System.out.println("You have succesfully withdrawn: $" + result);
				System.out.println("From account: " + c.getAccount_id());
				} catch (InputMismatchException e) {
					e.printStackTrace();
					System.out.println("You did not follow the instructions... Please try again!");
				}
				customerMenu(c);
			} else {
				System.out.println("You have entered an invalid Account ID!");
				System.out.println("Cancelling...");
				customerMenu(c);
			}
			break;
		case 2:
			System.out.println("Cancelling...");
			customerMenu(c);
			break;
		default:
			System.out.println("Invalid option!");
			customerMenu(c);
		}
	}
	
	public static void depositMenu(DaoContract<User, Integer> dc, Customer c, int option) {
		switch (option) {
		case 1:
			System.out.println("Deposit Menu: ");
			System.out.println("Enter the ID of the account you are depositing to.");
			//TODO: List AccountID's associated to this CustomerID
			c.setAccount_id(scanner.nextInt());
			
			
			//TODO: Make this a separate method, clean up
			boolean flag = dc.verifyAccountID(c);
			if (flag) {
				System.out.println("You have entered a valid Account ID!");
				System.out.println("Enter the amount to deposit: $(0.0)");
				System.out.print("$");
				//update accountid
				//System.out.println("Current Account ID: " + c.getAccount_id());
				try {
				BigDecimal money = scanner.nextBigDecimal();
				
				// grab money entered and pass to customer
				c.setTransaction(money);
				c.updateBalance();
				BigDecimal resultCredited = dc.creditAccount(c);
				
				//TODO:
				/*
				 * Do not calculate balance here, do it inside Customer.class
				 * Rather, simply c.getBalance() to see current balance.
				 */
				//call method to calculate balance
				
				
				System.out.println("You have succesfully deposited: $" + resultCredited);
				System.out.println("Into account: " + c.getAccount_id());
				} catch (InputMismatchException e) {
					e.printStackTrace();
					System.out.println("You did not follow the instructions... Please try again!");
				}
				customerMenu(c);
			} else {
				System.out.println("You have entered an invalid Account ID!");
				System.out.println("Cancelling...");
				customerMenu(c);
			}
			break;
		case 2:
			System.out.println("Cancelling...");
			customerMenu(c);
			break;
		default:
			System.out.println("Invalid option!");
			customerMenu(c);
		}
	}
	
	public static void transferMenu(DaoContract<User, Integer> dc, Customer c, int option) {
		switch (option) {
		case 1:
			System.out.println("Transfer Funds In");
			System.out.println("Enter the ID of the account you are transfering from: ");
			
			//TODO: List AccountID's associated to this CustomerID
			c.setAccount_id(scanner.nextInt());
			int acct1 = c.getAccount_id();
			boolean flag1 = dc.verifyAccountID(c);
			System.out.println("Enter the ID of the account you are transfering to: ");
			
			c.setAccount_id(scanner.nextInt());
			int acct2 = c.getAccount_id();
			boolean flag2 = dc.verifyAccountID(c);
			//TODO: Make this a separate method, clean up
			
			//this is really dirty
			if (flag1 && flag2) {
				c.setAccount_id(acct1);
				System.out.println("You have entered valid Account IDs!");
				System.out.println("Enter the amount to deposit: $(0.0)");
				System.out.print("$");
				BigDecimal money = scanner.nextBigDecimal();
				//TODO:
				/*
				 * Execute both deposit and withdraw on matching accts
				 */
				BigDecimal debitMoney= money.negate();
				//debit from account 1
				c.setTransaction(debitMoney);
				c.updateBalance();
				
				BigDecimal resultDebited = dc.debitAccount(c);
				
				c.setAccount_id(acct2);
				c.setTransaction(money);
				c.updateBalance();
				BigDecimal resultCredited = dc.creditAccount(c);
				
				
				System.out.println("PRINTING CREDIT: " + resultCredited);
				System.out.println("PRINTING DEBIT: " + resultDebited);
				
				System.out.println("You have succesfully transferred: $" + money);
				System.out.println("From the account: \n" + 
									acct1 + "\n" +
									"To the account: " + acct2
									);
				
			} else {
				System.out.println("You have entered an invalid Account ID!");
				System.out.println("Cancelling...");
				customerMenu(c);
			}
			break;
		
		case 2:
			System.out.println("Cancelling...");
			customerMenu(c);
			break;
		
		default:
			System.out.println("Invalid option!");
			customerMenu(c);
		}
			//TODO: 
			/*
			 * Distinguish original AccountID from requested AccountID
			 * (Can't transfer money from the same account into itself)
			 * Verify that AccountID is associated to UserID
			 */

	}

	public static BigDecimal viewBalance(DaoContract<User, Integer> dc, Customer c) {
		BigDecimal result = new BigDecimal(0);

		// TODO: List AccountID's associated to this CustomerID
		c.setAccount_id(scanner.nextInt());
		
		boolean flag = dc.verifyAccountID(c);
		if (flag) {
			System.out.println("You have entered a valid Account ID!");
			
			result = dc.viewBalance(c);
			
			if (result != null) {
			
			System.out.println("The Current Balance for AccountID: " + 
								c.getAccount_id() +
								"\n is: $" + result
								);
			customerMenu(c);
			} else {
				System.out.println("This account has no transactions! \n" +
									"Please Deposit or Transfer before trying to view the Balance."
								);
			customerMenu(c);
			}
			
		} else {
			System.out.println("You have entered an invalid Account ID!");
			System.out.println("Cancelling...");
			customerMenu(c);
		}
		
		return result;
	}
	
	
	public static void employeeMenu(Employee e) {

		// TODO: Ensure "userName" is accessible
		/*
		 * Create Methods: 1. viewPendingAccounts() 2. viewtransactionLogs() 3.
		 * viewPendingLoans()
		 * 
		 */
		
		
		System.out.println("Banking Application: ");
		System.out.println("Welcome " + e.getUsername());
		System.out.println("Choose an option: \n" +
							"1.) View Pending Accounts \n" + 
							"2.) View Transaction Logs \n" + 
							"3.) View Pending Loans \n" + 
							"4.) Quit");
		int userSelection = scanner.nextInt();
		scanner.nextLine();

		switch (userSelection) {
		case 1:
			//viewPendingAccounts();
			
			System.out.println("List of Pending Accounts to Approve: ");
			
			break;
		case 2:
			//viewTransactionLogs();
			break;
		case 3:
			//viewPendingLoans();
			break;
		case 4:
			quit();
			break;
		default:
			System.out.println("Invalid Option.");
			break;
		}
	}

	public static void managerMenu() {

		// TODO: Ensure "userName" is accessible
		/*
		 * Create Methods: 1. makeAccount() 2. viewAccounts() 3. viewActivityLogs()
		 * 
		 */

		System.out.println("Banking Application: ");
		System.out.println("Welcome " + userName);
		System.out.println("Choose an option: \n" + "1.) Make Account \n" + "2.) View Existing Accounts \n"
				+ "3.) View Acitvity Logs \n" + "4.) Quit");
		int userSelection = scanner.nextInt();
		scanner.nextLine();

		switch (userSelection) {
		case 1:
			this.makeAccount();
			break;
		case 2:
			this.viewAccounts();
			break;
		case 3:
			this.viewActivityLogs();
			break;
		case 4:
			this.quit();
			break;
		default:
			System.out.println("Invalid Option.");
			break;
		}
	}
	
	
	public static void testMethod() {
		
	}
	
	
	private static void quit() {
		System.out.println("Quitting...");
		System.exit(1);
	}

}