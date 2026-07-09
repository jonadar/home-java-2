package Utils;

import java.util.ArrayList;

public class Validation {
	/**
	 * @param str String to check
	 * @param failMessage String message if validation fails
	 * @return true if str is not null or empty
	 */
	public static boolean validate(String str) {
		if (str == null || str.isBlank() || str.contains(";")) { // must not contain ';' because we use it for saves
			return false;
		}
		return true;
	}
	
	/**
	 * @param num int to check
	 * @param failMessage String message if validation fails
	 * @return true if num >= 0
	 */
	public static boolean validate(int num) {
		if (num < 0) {
			return false;
		}
		return true;
	}

	/**
	 * @param num double to check
	 * @param failMessage String message if validation fails
	 * @return true if num >= 0
	 */
	public static boolean validate(double num) {
		if (num < 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * @param newItem Object to check if already in array
	 * @param items Object[] of items to compare to newItem
	 * @return true if newItem not in array
	 */
	// TODO: remove
	public static boolean validateNotInArray(Object newItem, Object[] items) {
		if(newItem == null) return false;
		for(Object item: items) {
			if (item != null && item.equals(newItem)) return false;
		}
		
		return true;
	}
	
	/**
	 * @param newItem to check if already in array
	 * @param items ArrayList of items to compare to newItem
	 * @return true if newItem not in array
	 */
	public static <T> boolean validateNotInArray(T newItem, ArrayList<T> items) {
		if(newItem == null || items == null) return false;
		
		return !items.contains(newItem);
	}
	
	
	/**
	 * @param date String to check
	 * @return true if valid date otherwise false
	 */
	public static boolean isDate(String date) {
		if (date == null || date.isBlank()) return false;
		
		// check that date without slashes is only digits
		if(!isOnlyDigits(date.replace("/", ""))) {
			return false;
		}
		
		String[] splitDate = date.split("/");
		if(splitDate.length != 3) return false;
		
		if(splitDate[0].length() > 2 || splitDate[0].length() == 0) return false;
		if(splitDate[1].length() > 2 || splitDate[1].length() == 0) return false;
		if(splitDate[2].length() != 4) return false;

		int day = Integer.parseInt(splitDate[0]);
		int month = Integer.parseInt(splitDate[1]);
		int year = Integer.parseInt(splitDate[2]);
		
		if (day < 0 || day > 31) return false;
		if (month < 1 || month > 12) return false;
		if (year < 2000 || year > 2026) return false;
		
		return true;
	}
	
	/**
	 * @param str String to check
	 * @return true if not null or only whitespaces
	 */
	public static boolean isEmptyString(String str) {
		return str == null || str.isBlank();
	}
	
	/**
	 * @param str String to check
	 * @return true if only contains alphabetic and number chars
	 */
	public static boolean isOnlyNumbersAndChars(String str) {
		if (isEmptyString(str)) return false;
		
		for (char ch: str.toCharArray()) {
			if (!Character.isLetterOrDigit(ch)) return false;
		}
		
		return true;
	}
	
	/**
	 * @param str String to check
	 * @return true if only contains alphabetic chars
	 */
	public static boolean isOnlyChars(String str) {
		if (isEmptyString(str)) return false;
		
		for (char ch: str.toCharArray()) {
			if (!Character.isAlphabetic(ch)) return false;
		}
	
		return true;	
	}

	/**
	 * @param str String to check
	 * @return true if only contains digits
	 */
	public static boolean isOnlyDigits(String str) {
		if (isEmptyString(str)) return false;
		
		for (char ch: str.toCharArray()) {
			if (!Character.isDigit(ch)) return false;
		}
	
		return true;	
	}
	
	
	/**
	 * @param str String to check
	 * @return true if str is valid email, otherwise false
	 */
	public static boolean isEmail(String str) {
		if (isEmptyString(str)) return false;
		
		for (char ch: str.toCharArray()) {
			if (!(Character.isLetterOrDigit(ch) || ch == '@' || ch == '.')) {
				return false;
			}
		}
		
		// has a '@' and only 1.
		if (str.contains("@") && str.indexOf('@') == str.lastIndexOf('@')) return true;
		
		return false;
	}
	
	
	/**
	 * @param str String to check
	 * @return true if str is valid adress, otherwise false
	 */
	public static boolean isCity(String str) {
		if (isEmptyString(str)) return false;
		
		for (char ch: str.toCharArray()) {
			if (!(Character.isAlphabetic(ch) || ch == ' ')) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * @param str String to check
	 * @return true if is only letters numbers and spaces but not blank
	 */
	public static boolean isStreet(String str) {
		if (isEmptyString(str)) return false;
		
		// street city mikud
		for (char ch: str.toCharArray()) {
			if (!(Character.isLetterOrDigit(ch) || ch == ' ')) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * @param str String to check
	 * @return true if only numbers
	 */
	public static boolean isMikud(String str) {
		return isOnlyDigits(str);
	}
	
	/**
	 * @param address String to check
	 * @return true if atleast 3 words, not blank and first word is a numbers and chars (street), and last word is numbers (mikud)
	 */
	public static boolean isAddress(String address) {
		if(isEmptyString(address)) return false;
		
		String[] split = address.split(" ");
		return split.length >= 3 && isStreet(split[0]) && isMikud(split[split.length-1]);
	}
	
	/**
	 * @param phoneNumber String to check
	 * @return true if not empty & 10 digits
	 */
	public static boolean isPhoneNumber(String phoneNumber) {
		return isOnlyDigits(phoneNumber) && phoneNumber.length() == 10;
	}
	
	/**
	 * @param id String to check
	 * @return true if not empty, numbers only & length of 9
	 */
	public static boolean isId(String id) {
		return isOnlyDigits(id) && id.length() == 9;
	}
	
	/**
	 * @param name String to check
	 * @return true if not empty, not only spaces, letters and spaces only
	 */
	public static boolean isName(String name) {
		if (isEmptyString(name)) return false;
		
		for (char ch: name.toCharArray()) {
			if (!(Character.isAlphabetic(ch) || ch == ' ')) return false;
		}
	
		return true;
	}
	
	/**
	 * @param a lower bound
	 * @param b upper bound
	 * @param num value to check
	 * @param valueName String name of value for error printing
	 * @return true if number between a and b including. num in [a,b]
	 */
	public static boolean isNumberInRange(double a, double b, double num) {
		if (!(a <= num && num <= b)) {
			return false;
		}
		return true;
	}
	
	/**
	 * @param username String to check
	 * @return true if only numbers and characters
	 */
	public static boolean isUsername(String username) {
		return Validation.isOnlyNumbersAndChars(username);
	}
	
	/**
	 * @param password String to check
	 * @return true if only numbers and characters and of length minimum 3
	 */
	public static boolean isPassword(String password) {
		return Validation.isOnlyNumbersAndChars(password) && password.length() >= 3;
	}
}
