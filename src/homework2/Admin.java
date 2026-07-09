package homework2;

import MyExceptions.InvalidPropertyException;
import Utils.UserInput;
import Utils.Validation;

public class Admin {
	protected String name;
	protected String username;
	protected String password;
	
	public Admin(String name, String username, String password) throws InvalidPropertyException  {
		setName(name);
		setUsername(username);
		setPassword(password);
	}
	
	public String getName() { return name; }
	public String getUsername() { return username; }
	
	
	public void setName(String name) throws InvalidPropertyException{
		if (Validation.isName(name)) this.name = name;
		else throw new InvalidPropertyException("invalid name, needs to be not empty, not only spaces, letters and spaces only");
	}

	public void setUsername(String username) throws InvalidPropertyException{
		if (Validation.isUsername(username)) this.username = username;
		else throw new InvalidPropertyException("invalid username, none empty numbers and characters");
	}

	public void setPassword(String password) throws InvalidPropertyException{
		if (Validation.isPassword(password)) this.password = password;
		else throw new InvalidPropertyException("invalid password, none empty numbers and characters with length of atleast 3");
	}

	public boolean login(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
	}
	
	public void menu(DeliveryDataBase DDB) {
		while(true) {
			System.out.println("1. add customer");
			System.out.println("2. add restaurant admin");
			System.out.println("3. assign restaurant admin to restaurant");
			System.out.println("4. add restaurant");
			System.out.println("5. add rider");
			System.out.println("6. assign rider to order");
			System.out.println("7. view all system orders");
			System.out.println("8. show customer with most orders");
			System.out.println("9. show rider with most deliveries");
			System.out.println("10. update restaurant status");
			System.out.println("11. sort lists");
			System.out.println("12. show system orders total price");
			System.out.println("13. show system report");
			System.out.println("14. add fastfood restaurant (wildcard func)");
			System.out.println("15. show premium restaurants");
			System.out.println("16. logout");
			
			int option = UserInput.getIntFromRange(1, 16, "option");
			if(option == 16) break;
			
			switch (option) {
				case 1:
					Customer c = Services.addCustomer(); // should add customer in function
					DDB.addCustomer(c);
					break;
				case 2:
					RestAdmin rs = Services.addRestAdmin(); // doesnt actually add yet, just creates
					DDB.addRestaurantAdmin(rs);
					break;
				case 3:
					//Services.assignRestAdminToRestaurant(DDB.getRestaurantAdmins(), DDB.getRestaurants());
					break;
				case 4:
					Restaurant rest = Services.addRestaurant(); // need to update code slightly for 3 options of rest type
					DDB.addRestaurant(rest);
					break;
				case 5:
					Rider rider = Services.addRider(); // doesnt add yet to Delivery System
					DDB.addRider(rider);
					break;
				case 6:
//					Services.assignOrderToRider(DDB.getRiders(), DDB.getOrders());
					break;
				case 7:
					DDB.displayAllOrders(); // show all system orders
					break;
				case 8:
					System.out.println(DDB.customerWithMostOrders());
					break;
				case 9:
					System.out.println(DDB.riderWithMostOrders()); 
					break;
				case 10:
					Services.updateRestaurantStatus(DDB.getRestaurants());
					break;
				case 11:
					// TODO: check this works
					// currently sorts strings but is case sensitive
					DDB.sortByOption();
					break;
				case 12:
					System.out.println("total order prices in system are: " + DDB.getSumOfAllOrdersPrices()); // using stream api
					break;
				case 13:
					System.out.println("=====================   system report  ====================");
					System.out.println("system restaurants: ");
					SystemReports.displayWildcardList(DDB.getRestaurants());
					System.out.println("system has " + DDB.getOrders().size() + " orders.");
					System.out.println("system has " + DDB.getRiders().size() + " riders.");
					System.out.println("system has " + DDB.getRestaurantAdmins().size() + " restaurant admins.");
					System.out.println("total orders value is " + SystemReports.getTotalOrderFinalPriceSum(DDB.getOrders()));
					Customer bestCustomer = SystemReports.getGreatestItem(DDB.getCustomers());
					if(bestCustomer != null) {
						System.out.println("the customer with most money to spend is " + bestCustomer);
					}
					System.out.println("============================================================");
					break;
					
				case 14:
					SystemReports.addNewFastfoodRestaurant(DDB.getRestaurants()); // not good, but they asked for it :(
					break;
				case 15:
//					DDB.showPremiumRestaurants();
					break;
			}
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Admin) {
			Admin other = (Admin) obj;
			return other.username.equals(this.username);
		}
		return false;
	}
}
