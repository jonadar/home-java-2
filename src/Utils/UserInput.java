package Utils;

import java.util.Scanner;

public class UserInput {
	public static Scanner s = new Scanner(System.in);
	
	/**
	 * @param valueName name of value being received for user prompt
	 * @return none empty string
	 */
	public static String getName(String valueName) {			
		String userValue = "";
		
		// keep asking until user provides correct value 
		while(true) {
			System.out.println("enter " + valueName + ": ");
			userValue = s.nextLine();
			if(Validation.isName(userValue)) return userValue;
			System.out.println("invalid " + valueName + ". must be none empty string with only characters");
		}
	}
	
	/**
	 * @param valueName name of value being received for user prompt
	 * @return int greater or equals to 0
	 */
	public static int getInt(String valueName) {		
		int userValue = -1;
		
		// keep asking until user provides correct value 
		while(true) {	
			System.out.println("enter " + valueName + ": ");
			if(s.hasNextInt()) {
				userValue = s.nextInt();
				s.nextLine(); // avoid issues with newline
				if(userValue >= 0) return userValue;
			} else {
				s.next();
			}
			System.out.println("invalid " + valueName + ". must be positive int");
		}
	}
	
	/**
	 * @param a int bottom of range
	 * @param b int top of range
	 * @param valueName name of value being received for user prompt
	 * @return int such that a <= number <= b
	 */
	public static int getIntFromRange(int a, int b, String valueName) {		
		int userValue = a;
		
		// avoid infinite loops, if range doesn't make sense
		if(a > b) return 0;
		
		// keep asking until user provides correct value 
		while(true) {		
			System.out.println("enter " + valueName + ": (" + a + " to " + b + ")");
			if(s.hasNextInt()) {				
				userValue = s.nextInt();
				s.nextLine(); // avoid issues with newline
				if(userValue <= b && userValue >= a) return userValue;
			}else {				
				s.next();
			}
			System.out.println("invalid " + valueName + ". must be int in range (" + a + " to "+ b + ")");
		}
	}
	
	/**
	 * @param a double bottom of range
	 * @param b double top of range
	 * @param valueName name of value being received for user prompt
	 * @return double such that a <= number <= b
	 */
	public static double getDoubleFromRange(double a, double b, String valueName) {		
		double userValue = a;
		
		// avoid infinite loops, if range doesn't make sense
		if(a > b) return 0;
		
		// keep asking until user provides correct value 
		while(true) {	
			System.out.println("enter " + valueName + ": [" + a + ", " + b + "]");
			if(s.hasNextDouble()) {				
				userValue = s.nextDouble();
				s.nextLine(); // avoid issues with newline
				if(Validation.isNumberInRange(a, b, userValue)) return userValue;
			}else {				
				s.next();
			}
			System.out.println("invalid " + valueName + ". must be number in range [" + a + ", "+ b + "]");
		}
	}
	
	/**
	 * @param valueName name of value being received for user prompt
	 * @return double greater or equals to 0
	 */
	public static double getDouble(String valueName) {		
		double userValue = -1.0;
		
		// keep asking until user provides correct value 
		while(true) {
			System.out.println("enter " + valueName + ": ");
			if(s.hasNextDouble()) {				
				userValue = s.nextDouble();
				s.nextLine(); // avoid issues with newline
				if(userValue >= 0) return userValue;
			} else {				
				s.next();
			}
			System.out.println("invalid " + valueName + ". must be positive number");
		}
	}
	
	/**
	 * @param question to prompt user
	 * @return true if user entered 1 or false if user entered 2
	 */
	public static boolean getBoolean(String question) {		
		int userValue = 0;
		
		// keep asking until user provides correct value 
		while(true) {
			System.out.println(question + " (1 - yes, 2 - no)");
			if(s.hasNextInt()) {				
				userValue = s.nextInt();
				s.nextLine(); // avoid issues with newline
				if(userValue == 1) return true;
				if(userValue == 2) return false;
			} else {
				s.next();
			}
			System.out.println("invalid value. choose (1 - yes, 2 - no)");
		}
	}
	
	/**
	 * @param options array of strings to pick from
	 * @return user selected option or empty string if no options where given
	 */
	public static String getStringFromOptions(String[] options) {		
		if (options == null || options.length == 0) {
			System.out.println("no options to pick from");
			return "";
		}
		
		int userValue = 0;
		
		// keep asking until user provides correct value
		while(true) {
			// display options
			System.out.println("choose option:");
			for (int i = 0; i < options.length; i++) {
				System.out.println((i+1) + ". " + options[i]);
			}
			
			// check for input
			if(s.hasNextInt()) {				
				userValue = s.nextInt() - 1;
				s.nextLine(); // avoid issues with newline
				if(userValue < options.length && userValue >= 0) return options[userValue];
			} else {
				s.next();
			}
			
			System.out.println("invalid option. choose between 1 and " + options.length);
		}
	}
	
	/**
	 * @param valueName name of value being received for user prompt
	 * @return date String
	 */
	public static String getDate(String valueName) {		
		String userValue = "";
				
		// keep asking until user provides correct value 
		while(true) {
			System.out.println("enter " + valueName + ": ");
			userValue = s.nextLine();
			if(Validation.isDate(userValue)) return userValue;
			System.out.println("invalid date. must be in dd/mm/yyyy format between the years 2000 and 2026");
		}
	}
	
	/**
	 * @return date String
	 */
	public static String getAddress() {		
		String city = "";
		String street = "";
		String mikud = "";
		
		// keep asking until user provides correct value 
		while(true) {
			System.out.println("enter city: ");
			city = s.nextLine();
			if(Validation.isCity(city)) break;
			System.out.println("invalid city, must be none empty string with no numbers or symbols");
		}
		
		while(true) {
			System.out.println("enter street: ");
			street = s.nextLine();
			if(Validation.isStreet(street)) break;
			System.out.println("invalid street, must be none empty string with no symbols");
		}
		
		while(true) {
			System.out.println("enter mikud: ");
			mikud = s.nextLine();
			if(Validation.isMikud(mikud)) break;
			System.out.println("invalid mikud. must be not empty only digits");
		}
		
		return street + " " + city + " " + mikud; 
	}
	
	/**
	 * @return phone number from user
	 */
	public static String getPhoneNumber() {		
		String userValue;
		
		// keep asking until user provides a valid answer 
		while(true) {	
			System.out.println("enter phone number: ");
			userValue = s.next();
			s.nextLine(); // avoid issues with newline
			if(Validation.isPhoneNumber(userValue)) return userValue;
			System.out.println("invalid phone number. must be 10 digits");
		}
	}
	
	/**
	 * @return id from user
	 */
	public static String getId() {
		String userValue = "";
		
		// keep asking until user provides correct value 
		while(true) {
			System.out.println("enter id: ");
			userValue = s.nextLine();
			if(Validation.isId(userValue.trim())) return userValue.trim();
			System.out.println("invalid id. must be none empty string with 9 digits");
		}
	}
	
	/**
	 * @return username from user
	 */
	public static String getUsername() {
		String userValue = "";
		
		// keep asking until user provides correct value 
		while(true) {
			System.out.println("enter username:");
			userValue = s.nextLine();
			if(Validation.isUsername(userValue)) return userValue.trim();
			System.out.println("invalid username. must be none empty string");
		}
	}
	
	/**
	 * @return password from user
	 */
	public static String getPassword() {			
		String userValue = "";
		
		// keep asking until user provides correct value 
		while(true) {
			System.out.println("enter password:");
			userValue = s.nextLine();
			if(Validation.isPassword(userValue)) return userValue;
			System.out.println("invalid password. must be none empty string of length 3 or more and only using letters and digits");
		}
	}
	
	/**
	 * @return email from user
	 */
	public static String getEmail() {			
		String userValue = "";
		
		// keep asking until user provides correct value 
		while(true) {
			System.out.println("enter email:");
			userValue = s.nextLine();
			if(Validation.isEmail(userValue)) return userValue;
			System.out.println("invalid email. must be none empty string with only characters, digits, '.' and @ but only one @ symbol");
		}
	}
}
