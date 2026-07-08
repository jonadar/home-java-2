package homework2;

import MyExceptions.InvalidPropertyException;
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
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getAddress() { return address; }
	public String getPhoneNumber() { return phoneNumber; }
	public String getEmail() { return email; }
	public double getRemainingCredit() { return remainingCredit; }

	
	public Customer(String firstName, String lastName, String address, String phoneNumber, String email, double remainingCredit) throws InvalidPropertyException{
		setFirstName(firstName);
		setLastName(lastName);
		setAddress(address);
		setPhoneNumber(phoneNumber);
		setEmail(email);
		setRemainingCredit(remainingCredit);
		this.customerCode = customerCount++;
		
		System.out.println("customer with code: " + this.customerCode + " has been created.");
	}
	
	public void setFirstName(String firstName) throws InvalidPropertyException {
		if (Validation.isName(firstName)) this.firstName = firstName;
		else throw new InvalidPropertyException("inavlid first name must be not empty, not only spaces, letters and spaces only");
	}
	
	public void setLastName(String lastName) throws InvalidPropertyException {
		if (Validation.isName(lastName)) this.lastName = lastName;
		else throw new InvalidPropertyException("invalid last name must be not empty, not only spaces, letters and spaces only");
	}
	
	public void setAddress(String address) throws InvalidPropertyException {
		if (Validation.isAddress(address)) this.address = address;
		else throw new InvalidPropertyException("invalid adress needs to be atleast 3 words, not blank and first word is a numbers and chars (street), and last word is numbers (mikud)");
	}
	
	public void setPhoneNumber(String phoneNumber) throws InvalidPropertyException {
		if (Validation.isPhoneNumber(phoneNumber)) this.phoneNumber = phoneNumber;
		else throw new InvalidPropertyException("invalid phone number entered must be  not empty & 10 digits");
	}
	
	public void setEmail(String email) throws InvalidPropertyException {
		if (Validation.isEmail(email)) this.email = email;
		else throw new InvalidPropertyException("invalid email must contain atleast 1 '@' and only alphabetic chars and '.'");
	}
	
	public void setRemainingCredit(double remainingCredit) throws InvalidPropertyException{
		if (Validation.validate(remainingCredit)) this.remainingCredit = remainingCredit;
		else throw new InvalidPropertyException("invalid remaining credit, must be positive value");
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
		return Double.compare(other.remainingCredit, this.remainingCredit); // high to low
	}
}
