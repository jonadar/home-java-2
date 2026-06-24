package homework2;

import java.util.function.Predicate;

import Utils.UserInput;
import Utils.Validation;

public class Customer implements Comparable<Customer> {
	private int customerCode;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private String email;
	private double remainingCredit;
	
	private static int customerCount = 1;
	
	public int getCustomerCode() { return customerCode; }
	public String getfirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getAddress() { return address; }
	public String getPhoneNumber() { return phoneNumber; }
	public String getEmail() { return email; }
	public double getRemainingCredit() { return remainingCredit; }

	
	public Customer(String firstName, String lastName, String address, String phoneNumber, String email, double remainingCredit) {
		this.customerCode = customerCount++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.remainingCredit = remainingCredit;
		
		System.out.println("customer with code: " + this.customerCode + " has been created.");
	}
	
	public void setfirstName(String firstName) {
		if (Validation.isName(firstName)) this.firstName = firstName;
		else System.out.println("inavlid first name");
	}
	
	public void setLastName(String lastName) {
		if (Validation.isName(lastName)) this.lastName = lastName;
		else System.out.println("invalid last name");
	}
	
	public void setAddress(String address) {
		if (Validation.isAddress(address)) this.address = address;
		else System.out.println("invalid adress");
	}
	
	public void setPhoneNumber(String phoneNumber) {
		if (Validation.isPhoneNumber(phoneNumber)) this.phoneNumber = phoneNumber;
		else System.out.println("invalid phone number entered");
	}
	
	public void setEmail(String email) {
		if (Validation.isEmail(email)) this.email = email;
		else System.out.println("invalid email");
	}
	
	public void setRemainingCredit(double remainingCredit) {
		boolean valid = Validation.validate(remainingCredit, "invalid remaining credit");
		
		if (valid) this.remainingCredit = remainingCredit;
	}
	
	public void menu(DeliveryDataBase DDB) {
		System.out.println("you are customer");
		
		while(true) {
			System.out.println("1. create new order");
			System.out.println("2. view my orders");
			System.out.println("3. update my personal info");
			System.out.println("4. view restaurants ordered from");
			System.out.println("5. view premium restaurants ordered from");
			System.out.println("6. view my current balance");
			System.out.println("7. add to my balance");
			System.out.println("8. take money out of balance");
			System.out.println("9. logout");
			
			int option = UserInput.getIntFromRange(1,9, "option");
			if(option == 9) break;
			
			switch (option) {
				case 1:
					Order o = Services.createNewOrder(this, DDB.getRestaurants());
					DDB.addOrder(o);
					break;
				case 2:
					DDB.displayAllOrders(this);
					break;
				case 3:
					Services.updatePersonalInfo(this);
					break;
				case 4:
					DDB.displayOrderedRestaurants(this);
					break;
				case 5:
					DDB.displayOrderedPremiumRestaurants(this);
					break;
				case 6:
					System.out.println("customer balance is: " + this.getRemainingCredit());
					break;
				case 7:
					Services.chargeCustomerBalance(this);
					break;
				case 8:
					Services.withdrawCustomerBalance(this);
					break;
			}
		}
	}
	
	
	@Override
	public String toString() {
		return "Customer [customerCode=" + customerCode + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + ", email=" + email + ", remainingCredit="
				+ remainingCredit + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Customer) {
			Customer other = (Customer) obj;
			return other.customerCode == this.customerCode;
		}
		return false;
	}
	
	@Override
	public int compareTo(Customer other) {
		return Double.compare(this.remainingCredit, other.remainingCredit);
	}
}
