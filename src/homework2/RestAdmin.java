package homework2;

import java.util.ArrayList;

import Utils.UserInput;
import Utils.Validation;

public class RestAdmin extends Admin{
	private ArrayList<Restaurant> restaurants;
	
	public RestAdmin(String name, String username, String password) {
		super(name, username, password);
		this.restaurants = new ArrayList<Restaurant>();
		
		System.out.println("restaurant admin with username: " + this.username + " has been created.");
	}

	public ArrayList<Restaurant> getRestaurants() { return restaurants; }
	
	public boolean addRestaurant(Restaurant rest) {
		if(rest == null) return false;
		
		boolean isNew = Validation.validateNotInArray(rest, this.restaurants);
		
		if(!isNew) {
			System.out.println("restaurant already in array");
			return false;
		}
		
		this.restaurants.add(rest);

		return true;
	}
	
	@Override
	public void menu(DeliveryDataBase DDB) {
		while(true) {
			System.out.println("1. add customer");
			System.out.println("2. create new order");
			System.out.println("3. add rider");
			System.out.println("4. assign rider to order");
			System.out.println("5. view restaurant orders");
			System.out.println("6. view restaurants by kitchen type");
			System.out.println("7. logout");
			
			int option = UserInput.getIntFromRange(1, 7, "option");
			if(option == 7) break;
			
			switch (option) {
				case 1:
					Customer c = Services.addCustomer();
					DDB.addCustomer(c);
					break;
				case 2:
					Order o = Services.createNewOrderByRestAdmin(this, DDB.getCustomers());
					DDB.addOrder(o);
					break;
				case 3:
					Rider r = Services.addRider(); // does not add to DS
					DDB.addRider(r);
					break;
				case 4:
					Services.assignOrderToRider(DDB.getRiders(), DDB.getOrders());
					break;
				case 5:
					DDB.showOrdersOfRestaurant(this);
					break;
				case 6:
					DDB.showOpenRestaurantsByKitchenType(this);
					break;
			}
		}
	}
	
}
