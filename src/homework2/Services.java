package homework2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import MyExceptions.*;
import Utils.*;

public class Services {
	private static Predicate<Collection<?>> isEmptyCollection = (n) -> n.size() == 0; // bonus, creating predicate
	
	public static void updateOrderStatus(Rider rider) {
		ArrayList<Order> riderOrders = rider.getOrders();
		
		if(riderOrders == null || isEmptyCollection.test(riderOrders)) { // using predicate
			System.out.println("no orders to update");
			return;
		}
		
		// pick order from list
		displayArrayAsNumberedList(riderOrders);
		
		int orderCode = UserInput.getInt("order code");
		Order order = null ;
		for (Order o: riderOrders) {
			if(o.getOrderCode() == orderCode) {
				order = o;
				break;
			}
		}
		
		if(order == null) {
			System.out.println("order not found.");
			return;
		}

		// choose order status (on the way), (delivered)
		final String[] options = {"on the way", "delivered"};
		String deliveryOption = UserInput.getStringFromOptions(options);
		
		try {
			order.setDeliveryStatus(deliveryOption);
			
			// if chose (delivered) update order delivery date and rider is available
			if (deliveryOption.equals("delivered")) {
				String deliveryDate = UserInput.getDate("delivery date");
				order.setDeliveryDate(deliveryDate);
				rider.setAvailable(true);
			} else if (deliveryOption.equals("on the way")) {
				rider.setAvailable(false);
			}
			
			System.out.println("updated order.");
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
	}
	
	// offer customer to update phone, address or email details
	public static void updatePersonalInfo(Customer customer) {
		System.out.println("what would you like to update?");
		final String[] options = {"phone number", "adress", "email", "none"};
		String userSelection = UserInput.getStringFromOptions(options);
		try {
			if(userSelection.equals(options[0])) { // phone number
				String phoneNumber = UserInput.getPhoneNumber();
				customer.setPhoneNumber(phoneNumber);
				System.out.println("updated phone number");
			}
			
			else if (userSelection.equals(options[1])){ // address
				String adress = UserInput.getAddress();
				customer.setAddress(adress);
				System.out.println("updated address");
			}
			
			else if (userSelection.equals(options[2])){ // email
				String email = UserInput.getEmail();
				customer.setEmail(email);
				System.out.println("updated email address");
			}
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
	}
	
	/**
	 * @param customer who's order it is
	 * @param restaurants array of available restaurants
	 * @return Order if created successfully otherwise null
	 */
	public static Order createNewOrder(Customer customer, ArrayList<Restaurant> restaurants) {
		if(restaurants.size() == 0) {
			System.out.println("no restaurants to order from");
			return null;
		}
		
		displayArrayAsNumberedList(restaurants);
		
		// choose restaurant
		int restaurantIndex = UserInput.getIntFromRange(1, restaurants.size(), "restaurant");
		Restaurant restaurant = restaurants.get(restaurantIndex-1);
		
		// get base amount
		double baseCost = UserInput.getDouble("base cost");
		
		// check customer has enough money
		if(customer.getRemainingCredit() < restaurant.calculatePrice(baseCost)) {
			System.out.println("customer does not have enough remaining credit to place order. cost is " + restaurant.calculatePrice(baseCost));
			return null;
		}
		
		if (restaurant instanceof PremiumRestaurant) {
			if(baseCost < ((PremiumRestaurant) restaurant).getMinOrderValue()) {				
				System.out.println("cost too low for order, must be more than " + ((PremiumRestaurant) restaurant).getMinOrderValue());
				return null;	
			}
		}
		
		// get date
		String date = UserInput.getDate("todays date");
		try {
			Order newOrder = new Order(customer.getCustomerCode(), restaurant, baseCost, date);
			
			customer.setRemainingCredit(customer.getRemainingCredit() - restaurant.calculatePrice(baseCost)); // pay order cost
			
			return newOrder;
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
		return null;
	}
	
	// prompt user for customer details and create and return instance
	public static Customer addCustomer() {
		String name = UserInput.getName("name");
		
		String famillyName = UserInput.getName("familly name");
		
		String adress = UserInput.getAddress();
		
		String phoneNumber = UserInput.getPhoneNumber();
		
		String email = UserInput.getEmail();
		
		double remainingCredit = UserInput.getDouble("remaining credit");
		try {
			return new Customer(name, famillyName, adress, phoneNumber, email, remainingCredit);			
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
		return null;
	}
	
	// prompt user for restaurant admin details and create and return instance
	public static RestAdmin addRestAdmin() {
		String username = UserInput.getUsername();
	
		String restAdminName = UserInput.getName("resturnt admin name");
		
		String password = UserInput.getPassword();
		
		try {
			return new RestAdmin(restAdminName, username, password);
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
		return null;
	}
		
		
	// find restaurant admin from array using their username, returns admin or null if not found
	public static RestAdmin findRestAdmin(String username, ArrayList<RestAdmin> restaurantAdmins) throws RestAdminNotFoundException {
		for (int i = 0 ; i<restaurantAdmins.size() ; i++) {
			if (restaurantAdmins.get(i) != null) {
				if (username.equals(restaurantAdmins.get(i).getUsername())) {
					return restaurantAdmins.get(i);
				}
			}
		}
		throw new RestAdminNotFoundException();
	}
	
	// find restaurant from array using their code, returns restaurant or null if not found
	public static Restaurant findRestaurant(int restCode, ArrayList<Restaurant> restaurants) throws RestaurantNotFoundException {
		for (int i = 0 ; i<restaurants.size() ; i++) {
			if (restaurants.get(i) != null) {
				if (restCode == restaurants.get(i).getRestaurantCode()) {
					return restaurants.get(i);
				}
			}
		}
		throw new RestaurantNotFoundException();
	}
	
	// prompt user for restaurant and restaurant admin and assign that admin to the restaurant
	public static boolean assignRestAdminToRestaurant(ArrayList<RestAdmin> restaurantAdmins, ArrayList<Restaurant> restaurants, String username, int restcode) {
		try {			
			RestAdmin restAdmin = findRestAdmin(username, restaurantAdmins);
			Restaurant restaurant = findRestaurant(restcode, restaurants);
			if (restAdmin!= null && restaurant!=null) {
				boolean success = restAdmin.addRestaurant(restaurant);
				if (success) {
					System.out.println("assigned admin " + restAdmin.getUsername() + " to restaurant " + restaurant.getName());
					return true;
				} else return false;
			}
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		} 
		return false;
	}
	
	// prompt user for type and create appropriate restaurant to return
	public static Restaurant addRestaurant() {
		System.out.println("choose 1 for restaurant");
		System.out.println("choose 2 for fast food restaurant");
		System.out.println("choose 3 for premium restaurant");
		
		int option = UserInput.getIntFromRange(1, 3, "restaurant type");
		
		Restaurant restaurant = null;
		
		switch (option) {
			case 1: // הוספת מסעדה רגילה
				System.out.println("you choose restaurant");
				restaurant = createRestaurant();
				break;
			case 2: // הוספת מסעדה מהירה
				System.out.println("you choose fast food restaurant");
				restaurant = createFastFoodRestaurant();
				break;
			case 3: // הוספת מסעדת יוקרה
				System.out.println("you choose premium restaurant");
				restaurant = createPremiumRestaurant();
				break;
			default:
				System.out.println("invalid number");
		}
		
		return restaurant;
	}
	
	// prompt for normal restaurant details and return instance of restaurant
	public static Restaurant createRestaurant() {
		String restName = UserInput.getName("resaurant name");
		
		String kitchenType = UserInput.getName("kitchen type");
		
		double rating = UserInput.getDoubleFromRange(0, 10, "rating");

		boolean isOpen = UserInput.getBoolean("is restaurant open?");
		
		double deliveryFee = UserInput.getDouble("delivery fee");

		try {
			return new Restaurant(restName, kitchenType, rating, isOpen, deliveryFee);			
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
		return null;
	}
	
	// prompt for premium restaurant details and return instance of restaurant
	public static PremiumRestaurant createPremiumRestaurant() {
		Restaurant basicRest = createRestaurant();
		
		double minOrderValue = UserInput.getDouble("minimum order value");
		
		double orderFeePercentage = UserInput.getDouble("order fee percentage");
		try {
			return new PremiumRestaurant(basicRest.getName(), basicRest.getKitchenType(), basicRest.getRating(), basicRest.isOpen(), basicRest.getDeliveryFee(), minOrderValue, orderFeePercentage);			
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
		return null;
	}
	
	// prompt for fast food restaurant details and return instance of restaurant
	public static FastFoodRestaurant createFastFoodRestaurant() {
		Restaurant basicRest = createRestaurant();
		double averageCookTime = UserInput.getDouble("average cook time");
		
		double fastDeliveryFee = UserInput.getDouble("fast delivery fee");
		
		try {			
			return new FastFoodRestaurant(basicRest.getName(), basicRest.getKitchenType(), basicRest.getRating(), basicRest.isOpen(), basicRest.getDeliveryFee(), averageCookTime, fastDeliveryFee);
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
		return null;
	}
	
	// prompt for rider details and return instance
	public static Rider addRider() {
		String id = UserInput.getId();
	
		String fullName = UserInput.getName("full name");
		
		String phoneNumber = UserInput.getPhoneNumber();
		
		String vehicle = UserInput.getName("vehicle");
		
		boolean isAvailable = UserInput.getBoolean("is is available");
		
		try {
			return new Rider(id, fullName, phoneNumber, vehicle, isAvailable);			
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
		return null;
	}
	
	// find rider from array using their id, returns rider or null if not found
	public static Rider findRider(String id, ArrayList<Rider> riders) throws RiderNotFoundException{
		if(!Validation.isId(id)) { // no point in looking if not valid id
			throw new RiderNotFoundException();
		}
		
		for (Rider rider :riders) {
			if (rider != null && id.equals(rider.getId())) {
				return rider;
			}
		}
		
		throw new RiderNotFoundException();
	}
	
	// find order from array using their code, returns order or null if not found
	public static Order findOrder(int orderCode, ArrayList<Order> orders) {
		for (int i = 0; i < orders.size() ; i++) {
			if (orders.get(i) != null) {
				if (orderCode == orders.get(i).getOrderCode()) {
					return orders.get(i);
				}
			}
		}
		return null;
	}
	
//	// prompt for rider and order and attempt to assign them to eachother, returns true if succeded
//	public static boolean assignOrderToRider(ArrayList<Rider> riders, ArrayList<Order> orders) {
//		
//		try {
//			Rider rider = findRider(UserInput.getId(), riders);
//			Order order = findOrder(UserInput.getInt("order"), orders);
//			
//			if (rider == null || order == null) {
//				throw new Exception("rider or order could not be found");
//			}
//			
//			// rider is available and order does not have a rider assigned yet
//			if (rider.getAvailable() && order.getDriverId() == null){
//				boolean added = rider.addOrder(order);
//				if(added) {
//					System.out.println("assigned rider " + rider.getId() + " to order " + order.getOrderCode());
//					// update order
//					order.setDriverId(rider.getId());
//					order.setDeliveryStatus("on the way");
//					return true;
//				} else {
//					throw new Exception("failed to add order to rider");
//				}
//			}
//			
//			if(!rider.getAvailable()) System.out.println("rider not available");
//			else if(order.getDriverId() != null) System.out.println("order already has rider assigned");
//			
//		} catch (RiderNotFoundException e) {
//			ConsolePrinter.printError(e);
//		} catch (Exception e) {
//			ConsolePrinter.printError(e);
//		}
//		
//		return false;
//	}
	
	// for a rider and order, attempt to assign them to eachother, returns true if succeded
	public static boolean assignOrderToRider(Rider rider, Order order) {
		try {
			if (rider == null || order == null) {
				throw new Exception("rider or order could not be found");
			}
			
			// rider is available and order does not have a rider assigned yet
			if (rider.getAvailable() && order.getDriverId() == null){
				boolean added = rider.addOrder(order);
				if(added) {
					// update order
					order.setDriverId(rider.getId());
					order.setDeliveryStatus("on the way");
					
					return true;
				} else {
					throw new Exception("failed to add order to rider");
				}
			}
			
			if(!rider.getAvailable()) throw new Exception("rider not available");
			else if(order.getDriverId() != null) throw new Exception("order already has rider assigned");
			
		} catch (RiderNotFoundException e) {
			ConsolePrinter.printError(e);
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
		return false;
	}
	
	// find customer from array using their code, returns customer or null if not found
	public static Customer findCustomer(int customerCode, ArrayList<Customer> customers) throws CustomerNotFoundException {
		for (int i = 0 ; i<customers.size() ; i++) {
			if (customers.get(i) != null) {
				if (customerCode == customers.get(i).getCustomerCode()) {
					return customers.get(i);
				}
			}
		}
		throw new CustomerNotFoundException();
	}
	
	// creates new order from a restaurant admin is in charge of
	public static Order createNewOrderByRestAdmin(RestAdmin restAdmin, ArrayList<Customer> customers) {
		Order newOrder = null;
		
		ArrayList<Restaurant> adminRestaurants = restAdmin.getRestaurants();
		
		if(adminRestaurants.size() == 0) {
			System.out.println("no restaurants found for admin, cant add order");
			return null;
		}

		displayArrayAsNumberedList(adminRestaurants);
		
		// choose restaurant
		int restaurantIndex = UserInput.getIntFromRange(1, adminRestaurants.size(), "restaurant");
		Restaurant restaurant = adminRestaurants.get(restaurantIndex-1);
		
		try {
			// choose customer code if it not exsist it breaks out of the func
			Customer customer = findCustomer(UserInput.getInt("customer code"), customers);
			if (customer == null) {
				throw new Exception("could not find customer");
			}
			
			// get base amount
			double baseCost = UserInput.getDouble("base cost");
			
			if(customer.getRemainingCredit() < restaurant.calculatePrice(baseCost)) {
				throw new Exception("customer does not have enough remaining credit to place order. cost is " + restaurant.calculatePrice(baseCost));
			}
			
			if (restaurant instanceof PremiumRestaurant) {
				if(baseCost < ((PremiumRestaurant) restaurant).getMinOrderValue()) {				
					throw new Exception("cost too low for order, must be more than " + ((PremiumRestaurant) restaurant).getMinOrderValue());
				}
			}
			
			// get date
			String date = UserInput.getDate("todays date");
			
			newOrder = new Order(customer.getCustomerCode(), restaurant, baseCost, date);
			
			customer.setRemainingCredit(customer.getRemainingCredit() - restaurant.calculatePrice(baseCost)); // customer needs to pay for the order
		} catch (CustomerNotFoundException e) {
			ConsolePrinter.printError(e);
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		} finally {
			System.out.println("done trying to add to restaurant " + restaurantIndex + "."); // not usefull, but need to use finally block for assignment :(
		}
		
		return newOrder;
	}
	
	// get restaurant code and decide if open or closed
	public static void updateRestaurantStatus(ArrayList<Restaurant> restaurants) {
		try {
			Restaurant restaurant = findRestaurant(UserInput.getInt("restaurant code"), restaurants);
			
			if(restaurant == null) {
				System.out.println("restaurant not found");
				return;
			}
			
			// tell user current state, and offer to change
			String currently = restaurant.isOpen() ? "open" : "closed";
			String canBe = restaurant.isOpen() ? "closed" : "open";
			
			System.out.println("restaurant is currently " + currently);
			boolean shouldChange = UserInput.getBoolean("set restaurant as " + canBe + "?");
			
			if(shouldChange) restaurant.setOpen(!restaurant.isOpen()); // if user asks to change, change to opposit of what it has
		} catch (RestaurantNotFoundException e) {
			ConsolePrinter.printError(e);
		}
	}
	
	/**
	 * @param <T>
	 * @param items array to print with numbers
	 */
	public static <T> void displayArrayAsNumberedList(ArrayList<T> items) {
		for (int i = 0; i < items.size(); i++) {
			System.out.println((i+1) + ". " + items.get(i));
		}
	}
	
	/**
	 * @param customer to add to balance
	 */
	public static void chargeCustomerBalance(Customer customer) {
		double chargeAmount = UserInput.getDouble("amount to add");
		try {			
			customer.setRemainingCredit(customer.getRemainingCredit() + chargeAmount);
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
	}
	
	/**
	 * @param customer to withdraw from balance
	 */
	public static void withdrawCustomerBalance(Customer customer) {
		double withdrawAmount = UserInput.getDoubleFromRange(0, customer.getRemainingCredit(), "amount to withdraw");
		try {			
			customer.setRemainingCredit(customer.getRemainingCredit() - withdrawAmount);
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
	}
	
}