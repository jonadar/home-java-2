package homework2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import MyExceptions.InvalidPropertyException;
import Utils.ConsolePrinter;
import Utils.UserInput;
import Utils.Validation;

public class DeliveryDataBase {
	private Admin systemAdministrator;
	private ArrayList<Customer> customers;
	private ArrayList<RestAdmin> restaurantAdmins;
	private ArrayList<Restaurant> restaurants;
	private ArrayList<Rider> riders;
	private ArrayList<Order> orders;
	
	private HashMap<Integer, ArrayList<Order>> customerOrders;
	private Hashtable<Integer, ArrayList<Restaurant>> customersOrderedRestaurants;
	private HashMap<Integer, Double> customerExpenses;
	
	
	public DeliveryDataBase(){
		try {			
			this.systemAdministrator = new Admin("Steve", "admin", "12345");
		} catch (InvalidPropertyException e) {
			ConsolePrinter.printError(e);
		}
		
		this.customers = new ArrayList<Customer>();
		this.restaurantAdmins = new ArrayList<RestAdmin>();
		this.restaurants = new ArrayList<Restaurant>();
		this.riders = new ArrayList<Rider>();
		this.orders = new ArrayList<Order>();
		
		this.customerOrders = new HashMap<Integer, ArrayList<Order>>();
		this.customersOrderedRestaurants = new Hashtable<Integer, ArrayList<Restaurant>>();
		this.customerExpenses = new HashMap<Integer, Double>();
	}
	
	public Admin getSystemAdministrator() { return systemAdministrator; }
	public ArrayList<Customer> getCustomers() { return customers; }
	public ArrayList<RestAdmin> getRestaurantAdmins() { return restaurantAdmins; }
	public ArrayList<Restaurant> getRestaurants() { return restaurants; }
	public ArrayList<Rider> getRiders() { return riders; }
	public ArrayList<Order> getOrders() { return orders; }
	public HashMap<Integer, ArrayList<Order>> getCustomerOrders() { return customerOrders; }
	public Hashtable<Integer, ArrayList<Restaurant>> getCustomersOrderedRestaurants() { return customersOrderedRestaurants; }
	public HashMap<Integer, Double> getCustomerExpenses() { return customerExpenses; }

	public void addCustomer(Customer customer){
		// check if already in array
		boolean valid = Validation.validateNotInArray(customer, this.customers);
		
		if(valid) {			
			this.customers.add(customer);
		}
		else System.out.println("invalid, customer already registered");
	}
	
	public void addRestaurantAdmin(RestAdmin restaurantAdmin){
		// check if already in array
		boolean valid = Validation.validateNotInArray(restaurantAdmin, this.restaurantAdmins);
		
		if(valid) {
			this.restaurantAdmins.add(restaurantAdmin);
		} 
		else System.out.println("invalid restaurant admin already registered");
	}
	
	public void addRestaurant(Restaurant restaurant){
		// check if already in array
		boolean valid = Validation.validateNotInArray(restaurant, this.restaurants);
		
		if(valid) {
			this.restaurants.add(restaurant);
			
			System.out.println("restaurant with code: " + restaurant.getRestaurantCode() + " has been created.");
		}
		else System.out.println("invalid, restaurant already registered");
	}
	
	public void addRider(Rider rider){
		// check if already in array
		boolean valid = Validation.validateNotInArray(rider, this.riders);

		if(valid) {
			this.riders.add(rider);
		}
		else System.out.println("invalid,  rider already registered");
	}
	
	public void addOrder(Order order){
		// check if already in array
		boolean valid = Validation.validateNotInArray(order, this.orders);
		
		if(!valid) {
			System.out.println("invalid, order already registered");
			return;
		}
		// add order to list and update hash maps
		this.orders.add(order);
		
		// update hashmaps and tables
		this.addOrderToCustomer(order.getCustomerCode(), order);
		this.updateCustomerOrderedRestaurants(order.getCustomerCode(), order.getRest());
		this.updateCustomerExpenses(order.getCustomerCode(), order.getFinalPrice());
	}
	
	// display all system orders
	public void displayAllOrders() {
		if(this.orders == null || this.orders.size() == 0) {
			System.out.println("no orders to display");
			return;
		}
		
		System.out.println("all system orders:");
		Services.displayArrayAsNumberedList(orders);
		
		System.out.println("-----------------------------");
	}
	
	// display all restaurant orders
	public void displayAllOrders(Restaurant restaurant) {
		if(this.orders == null || this.orders.size() == 0) {
			System.out.println("no orders to display");
			return;
		}
		int num = 0;
		
		System.out.println("restaurant orders:");
		for (Order order : this.orders) {
			if(order.getRest().equals(restaurant)) {				
				System.out.println((++num) + ". " + order);
			}
		}
		
		System.out.println("-----------------------------");
	}
	
	// display all restaurant orders
	public ArrayList<Order> getOrdersByRestaurantCode(int code) {
		if(this.orders == null || this.orders.size() == 0) {
			System.out.println("no orders to display");
			return new ArrayList<Order>();
		}
		
		return new ArrayList<>(this.orders.stream().filter(order -> order.getRestaurantCode() == code).collect(Collectors.toList()));
	}
	
	// display all rider orders
	public void displayAllOrders(Rider rider) {
		ArrayList<Order> orders = rider.getOrders();
		
		if(orders == null || orders.size() == 0) {
			System.out.println("no orders to display");
			return;
		}
		
		System.out.println("your orders are: ");
		Services.displayArrayAsNumberedList(orders);
		
		System.out.println("-----------------------------");
	}
	
	// display all customer orders
	public void displayAllOrders(Customer customer) {
		ArrayList<Order> customerOrders = this.customerOrders.get(customer.getCustomerCode());
		
		if(customerOrders == null || customerOrders.size() == 0) {
			System.out.println("Customer has no orders.");
			return;
		}
		
		System.out.println("your orders are: ");
		Services.displayArrayAsNumberedList(customerOrders);
		
		System.out.println("-----------------------------");
	}
	
	// get restaurant code and display its info
	public void displayRestaurantDetailsByCode() { // removeable? not in use, but from first homework
		int restaurantCode = UserInput.getInt("restaurant code");
		
		Restaurant restaurant = null;
		for (Restaurant r: this.restaurants) {
			if(r.getRestaurantCode() == restaurantCode) {
				restaurant = r;
				break;
			}
		}
		
		if(restaurant == null) {
			System.out.println("restaurant not found.");
			return;
		}
		
		System.out.println(restaurant);
	}
	
	/**
	 * @param customerCode of customer to update map
	 * @param Restaurant 
	 */
	public void updateCustomerOrderedRestaurants(int customerCode, Restaurant restaurant) {
		if (!this.customersOrderedRestaurants.containsKey(customerCode)) {
			this.customersOrderedRestaurants.put(customerCode, new ArrayList<Restaurant>());
		}
		
		if (this.customersOrderedRestaurants.get(customerCode).contains(restaurant)) return;
		
		this.customersOrderedRestaurants.get(customerCode).add(restaurant);
	}
	/**
	 * @param customerCode of customer to update expenses of
	 * @param newExpense to add to customer
	 */
	public void updateCustomerExpenses(int customerCode, double newExpense) {
		if (!this.customerExpenses.containsKey(customerCode)) {
			this.customerExpenses.put(customerCode, 0.0);
		}
		
		this.customerExpenses.put(customerCode, this.customerExpenses.get(customerCode) + newExpense);
	}
	
	/**
	 * @param customerCode of customer to add order to
	 * @param Order to add to customer
	 */
	public void addOrderToCustomer(int customerCode, Order order) {
		if (!this.customerOrders.containsKey(customerCode)) {
			this.customerOrders.put(customerCode, new ArrayList<Order>());
		}
		if (this.customerOrders.get(customerCode).contains(order)) {
			System.out.println("order already exists for customer");
			return;
		}
		this.customerOrders.get(customerCode).add(order);
		System.out.println("order was connected to customer");
	}
	
	/**
	 * @param String rider id
	 * @return ArrayList of orders that are not delivered
	 */
	public ArrayList<Order> riderPedningOrders(String riderId){
		ArrayList<Order> ordersToReturn = new ArrayList<Order>();
		ArrayList<Order> listOfOrders = new ArrayList<Order>();
		for (Rider rider : this.riders) {
			if (riderId.equals(rider.getId())) {
				listOfOrders = rider.getOrders();
				break;
			}
		}
		for (Order order : listOfOrders) {
			if (order.getDeliveryStatus().equals("on the way") || order.getDeliveryStatus().equals("sent")) {
				ordersToReturn.add(order);
			}
		}
		return ordersToReturn;
	}
	
	/**
	 * @param customer to check
	 * @return ArrayList of premium restaurants that customer ordered from
	 */
	public ArrayList<Restaurant> customerOrdersFromPremiumRest(Customer customer){
		ArrayList<Restaurant> listToReturn = new ArrayList<Restaurant>();
		
		if (!this.customersOrderedRestaurants.containsKey(customer.getCustomerCode())) {
			return listToReturn;
		}
		
		for (Restaurant r : this.customersOrderedRestaurants.get(customer.getCustomerCode())) {
			if (r instanceof PremiumRestaurant) {
				listToReturn.add(r);
			}
		}
		return listToReturn;
	}
	
	/**
	 * @return Customer who has highest order count
	 */
	public Customer customerWithMostOrders() {
		int currntHighstId = -1;
		int currntHighstOrders = -1;
		for (Integer customerId : customerOrders.keySet()) {
			int cuurntSum = customerOrders.get(customerId).size();
			if (cuurntSum > currntHighstOrders) {
				currntHighstId = customerId;
				currntHighstOrders = cuurntSum;
			}
		}
		for (Customer customer : this.customers) {
			if (currntHighstId == customer.getCustomerCode()) {
				return customer;
			}
		}
		return null; // למקרה ואין בכלל לקוחות
	}
	
	
	/**
	 * @return Rider who has highest order count
	 */
	public Rider riderWithMostOrders() {
		Rider bestRider = null;
		int currntHighstOrders = -1;

		for (Rider rider: this.riders) {
			int ridersOrdersCount = 0;

			for (Order order: rider.getOrders()) {
				if (order.getDeliveryStatus().equals("delivered")) ridersOrdersCount++;
			}
			
			if (ridersOrdersCount > currntHighstOrders) {
				bestRider = rider;
				currntHighstOrders = ridersOrdersCount;
			}
		}

		return bestRider;
	}
	
	
	/**
	 * @param kitchenType type of kitchen to check for
	 * @return ArrayList of open restaurants with given kitchen type
	 */
	public ArrayList<Restaurant> openRestaurantsByKitchenType(String kitchenType){
		ArrayList<Restaurant> openRst = new ArrayList<Restaurant>();
		for (Restaurant restaurant : this.restaurants) {
			if (restaurant.getKitchenType().equals(kitchenType) && restaurant.isOpen()) {
				openRst.add(restaurant);
			}
		}
		return openRst;
	}
	
	// pick and show orders from a restaurant owned by a restaurant admin
	public void showOrdersOfRestaurant(RestAdmin restAdmin) {
		ArrayList<Restaurant> adminRestaurants = restAdmin.getRestaurants();
		
		if(restAdmin.getRestaurants().size() == 0) {
			System.out.println("restaurant admin has no assigned restaurants yet.");
			return;
		}
		//print options
		Services.displayArrayAsNumberedList(adminRestaurants);
		
		// choose restaurant
		int restaurantIndex = UserInput.getIntFromRange(1, adminRestaurants.size(), "restaurant");
		Restaurant restaurant = adminRestaurants.get(restaurantIndex-1);
		
		this.displayAllOrders(restaurant);
	}
	
	public void showOpenRestaurantsByKitchenType(RestAdmin restAdmin) {
		String kitchenType = UserInput.getName("kitchen type");
		ArrayList<Restaurant> restaurantsWithType = this.openRestaurantsByKitchenType(kitchenType);
		ArrayList<Restaurant> filtered = new ArrayList<Restaurant>();
		
		for (Restaurant restaurant: restaurantsWithType) {
			if (restAdmin.getRestaurants().contains(restaurant)) filtered.add(restaurant);
		}
		
		if (filtered.size() == 0) {
			System.out.println("no restaurants with type " + kitchenType + " found for this admin");
			return;
		}
		//print options
		Services.displayArrayAsNumberedList(filtered);
		
		System.out.println("-----------------------------------");
	}
	
	
	
	public void displayOrderedRestaurants(Customer customer) {
		ArrayList<Restaurant> customerRestaurants = this.customersOrderedRestaurants.get(customer.getCustomerCode());
		
		if(customerRestaurants == null || customerRestaurants.size() == 0) {
			System.out.println("customer has no ordered restaurants yet.");
			return;
		}
		
		Services.displayArrayAsNumberedList(customerRestaurants);
	}
	public void displayOrderedPremiumRestaurants(Customer customer) {
		ArrayList<Restaurant> customerRestaurants = this.customerOrdersFromPremiumRest(customer);
		
		if(customerRestaurants == null || customerRestaurants.size() == 0) {
			System.out.println("customer has no ordered premium restaurants yet.");
			return;
		}
		
		Services.displayArrayAsNumberedList(customerRestaurants);
	}
	
	// pick a list to sort then get sort types to pick from
	// currently sorts strings but is case sensitive
	public void sortByOption() {
		final ArrayList<String> options = new ArrayList<>(List.of("1. sort customers by credit",
				"2. sort customers by first name",
				"3. sort riders by number of deliveries",
				"4. sort orders by date",
				"5. sort orders by final price",
				"6. sort restaurants by rating",
				"7. none"));
		
		options.forEach(System.out::println);

		int option = UserInput.getIntFromRange(1, options.size(), "option");
		if(option == options.size()) return;
		
		switch (option) {
			case 1:
				Collections.sort(this.customers); // sorts by remaining credit, customer implements Comparable.
				Services.displayArrayAsNumberedList(this.customers);
				break;
			case 2:
				this.customers.sort((c1, c2) -> c1.getFirstName().compareTo(c2.getFirstName()));
				Services.displayArrayAsNumberedList(this.customers);
				break;
			case 3:
				this.riders.sort((r1, r2) -> {
					int r1deliveries = r1.getOrders().size() - riderPedningOrders(r1.getId()).size();
					int r2deliveries = r2.getOrders().size() - riderPedningOrders(r2.getId()).size();
					return Integer.compare(r2deliveries, r1deliveries); // highest to lowest
				});
				Services.displayArrayAsNumberedList(this.riders);
				break;
			case 4:
				this.orders.sort((o1, o2) -> {
					try {
						String[] split1 = o1.getOrderDate().split("/");
						String[] split2 = o2.getOrderDate().split("/");
						
						int year1 = Integer.parseInt(split1[2]);
						int year2 = Integer.parseInt(split2[2]);
						
						if (year1 != year2) return Integer.compare(year1, year2);
						
						int month1 = Integer.parseInt(split1[1]);
						int month2 = Integer.parseInt(split2[1]);
						
						if (month1 != month2) return Integer.compare(month1, month2);
						
						int day1 = Integer.parseInt(split1[0]);
						int day2 = Integer.parseInt(split2[0]);
						
						return Integer.compare(day1, day2);
					} catch (NumberFormatException e) {
						ConsolePrinter.printError("an order date was invalid");
					} catch (Exception e) {
						ConsolePrinter.printError("an order date was in wrong format");
					}
					
					return 0;
				});
				Services.displayArrayAsNumberedList(this.orders);
				break;
			case 5:
				this.orders.sort(Order.getComparator());
				Services.displayArrayAsNumberedList(this.orders);
				break;
			case 6:
				this.restaurants.sort(Restaurant.getComparator()); // highest to lowest
				Services.displayArrayAsNumberedList(this.restaurants);
				break;

		}
	}
	
	// ======================= bonuses using Stream API ==========================
	
	public void showOpenRestaurants() {
		System.out.println("open restaurants: ");
		this.restaurants.stream().filter(Restaurant::isOpen).collect(Collectors.toList()).forEach(System.out::println); // check if for each okay or if needed map function
	}
	
	public void showPremiumRestaurants() {
		System.out.println("premium restaurants: ");
		this.restaurants.stream().filter(rest -> rest instanceof PremiumRestaurant).collect(Collectors.toList()).forEach(System.out::println); // check if for each okay or if needed map function
	}
	
	public void showAvailableRiders() {
		System.out.println("available riders: ");
		this.riders.stream().filter(Rider.checkAvailable).collect(Collectors.toList()).forEach(System.out::println); // check if for each okay or if needed map function
	}
	
	public double getSumOfAllOrdersPrices() {
		return this.orders.stream().map(Order::getFinalPrice).reduce(0.0, (total, price) -> total + price);
	}
}