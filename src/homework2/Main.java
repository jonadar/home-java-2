package homework2;

import MyExceptions.CustomerNotFoundException;
import MyExceptions.RiderNotFoundException;
import Utils.ConsolePrinter;
import Utils.UserInput;

public class Main {

	public static void main(String[] args) {
		DeliveryDataBase DDB = new DeliveryDataBase();
		
		// 5 riders
		DDB.addRider(new Rider("116591425","Jhon man", "0116591325", "car", true));
		DDB.addRider(new Rider("026521423","miriam", "0026321423", "car", false));
		DDB.addRider(new Rider("424591424","eden", "0424391424", "skateboard", true));
		DDB.addRider(new Rider("426591428","gregor", "0423591428", "car", true));
		DDB.addRider(new Rider("826591429","michelle", "0823531439", "corkinet", false));
		
		// 10 customers
		DDB.addCustomer(new Customer("Jonathan", "Mil", "Modiin 1 green place 4415", "3981292512", "cafsaf@gmacom",500));
		DDB.addCustomer(new Customer("Moulda", "lol", "Modiin 2 green place 4415", "5981292512", "safsaf@gmail", 2040));
		DDB.addCustomer(new Customer("Jack", "Popler", "Modiin 3 green place 4415", "7981292512", "ra521af@gmail", 10000));
		DDB.addCustomer(new Customer("iris", "cook", "Modiin 4 green place 4415", "2981262512", "eafssfaf@gmail", 8923));
		DDB.addCustomer(new Customer("jalta", "katz", "Modiin 5 fajksf 4415", "2981292542", "v2af@gmail",1000));
		DDB.addCustomer(new Customer("david", "haddad", "Modiin 6 green place 4415", "2588292312", "jafsaf@gmail",5224));
		DDB.addCustomer(new Customer("ron", "ald", "Modiin 7 green place 4415", "2981232562", "xafsaf@gmaicom",8000));
		DDB.addCustomer(new Customer("bob", "dirichle", "Modiin 8 laf kkfwao 4415", "2983792512", "jafsykjaf@gmaicom",521));
		DDB.addCustomer(new Customer("greg", "leibnitz", "Modiin 9 green place 4415", "2951232512", "uabfsaf@gmaom",3324));
		DDB.addCustomer(new Customer("some", "guy", "Modiin 10 green place 4415", "2981294512", "oyaftsaf@gmaim",5210));
		
		//10 restaurants of each type
		DDB.addRestaurant(new Restaurant("the big fat whale", "mexican", 2, true, 10));
		DDB.addRestaurant(new Restaurant("flying dutch people", "big", 5, true, 10));
		DDB.addRestaurant(new Restaurant("kuu & kang", "small", 5, true, 10));
		DDB.addRestaurant(new Restaurant("the small not fat whale", "kosher", 5, true, 10));
		DDB.addRestaurant(new Restaurant("faxxxutch people", "mexican", 5, true, 10));
		DDB.addRestaurant(new Restaurant("kvasnt & kang", "small", 5, true, 10));
		DDB.addRestaurant(new Restaurant("fasf big fat whale", "big", 5, true, 10));
		DDB.addRestaurant(new Restaurant("tttlying dutch people", "big", 5, true, 10));
		DDB.addRestaurant(new Restaurant("yyklint & kang", "small", 5, true, 10));
		DDB.addRestaurant(new Restaurant("xxlint & kang", "small", 5, true, 10));
		
		DDB.addRestaurant(new FastFoodRestaurant("bob's burgers", "big", 5, true, 10, 15, 20));
		DDB.addRestaurant(new FastFoodRestaurant("greg's ultra delux shwarma", "big", 5, true, 10, 3, 40));
		DDB.addRestaurant(new FastFoodRestaurant("krusty burger", "big", 5, true, 10, 10, 23));
		DDB.addRestaurant(new FastFoodRestaurant("kaflsflas", "small", 7, true, 10, 15, 20));
		DDB.addRestaurant(new FastFoodRestaurant("blao xis", "big", 4, true, 10, 3, 40));
		DDB.addRestaurant(new FastFoodRestaurant("dinmpa", "kosher", 5, true, 10, 10, 23));
		DDB.addRestaurant(new FastFoodRestaurant("doom doom dum dum", "small", 2, true, 10, 15, 20));
		DDB.addRestaurant(new FastFoodRestaurant("lary sucks", "kosher", 5, true, 10, 3, 40));
		DDB.addRestaurant(new FastFoodRestaurant("michelle enjoyers", "big", 5, true, 10, 10, 23));
		DDB.addRestaurant(new FastFoodRestaurant("big mac", "big", 5, true, 10, 10, 23));
		
		DDB.addRestaurant(new PremiumRestaurant("cavern on the green", "big", 5, true, 10, 120, 3));
		DDB.addRestaurant(new PremiumRestaurant("little garden", "big", 5, true, 10, 150, 10));
		DDB.addRestaurant(new PremiumRestaurant("canary blue", "small", 5, true, 10, 200, 5));
		DDB.addRestaurant(new PremiumRestaurant("sparrow", "big", 5, true, 10, 100, 10));
		DDB.addRestaurant(new PremiumRestaurant("creme pao", "kosher", 5, true, 10, 120, 3));
		DDB.addRestaurant(new PremiumRestaurant("poxter", "mexican", 5.4, true, 10, 150, 10));
		DDB.addRestaurant(new PremiumRestaurant("noding", "big", 10, true, 10, 200, 5));
		DDB.addRestaurant(new PremiumRestaurant("bill and jills dill grill", "big", 5, true, 10, 100, 10));
		DDB.addRestaurant(new PremiumRestaurant("cda", "small", 5, true, 10, 200, 5));
		DDB.addRestaurant(new PremiumRestaurant("BBA", "big", 9.2, true, 10, 100, 10));
		
		// 10 Orders
		DDB.addOrder(new Order(1, DDB.getRestaurants().get(15), 120, "6/3/2026"));
		DDB.addOrder(new Order(2, DDB.getRestaurants().get(17), 20, "8/3/2023"));
		DDB.addOrder(new Order(3, DDB.getRestaurants().get(11), 50, "16/3/2021"));
		DDB.addOrder(new Order(4, DDB.getRestaurants().get(4), 200, "6/7/2005"));
		DDB.addOrder(new Order(5, DDB.getRestaurants().get(1), 30, "15/3/2004"));
		DDB.addOrder(new Order(6, DDB.getRestaurants().get(20), 44, "22/2/2026"));
		DDB.addOrder(new Order(7, DDB.getRestaurants().get(21), 88, "6/4/2026"));
		DDB.addOrder(new Order(8, DDB.getRestaurants().get(25), 152, "1/3/2026"));
		DDB.addOrder(new Order(9, DDB.getRestaurants().get(29), 260, "2/3/2026"));
		DDB.addOrder(new Order(10, DDB.getRestaurants().get(28), 785, "6/6/2026"));
		
		//3 restaurant admins
		DDB.addRestaurantAdmin(new RestAdmin("jonathan", "jon", "123"));
		DDB.addRestaurantAdmin(new RestAdmin("eden", "michelle", "michelle123"));
		DDB.addRestaurantAdmin(new RestAdmin("bill", "lapdaz", "sec51pass52"));
		
		//Admin admin = new Admin("Steve", "admin", "12345");
		
		// תפריט
		while(true) {
			System.out.println("1. admin login");
			System.out.println("2. restaurant admin login");
			System.out.println("3. rider login");
			System.out.println("4. customer login");
			
			int option = UserInput.getIntFromRange(1, 4, "option");
			System.out.println(option);
			
			switch(option) {
				case(1):
					String adminUsername = UserInput.getUsername(); // doesnt have to be get username, can be any string and just fail
					String adminPassword = UserInput.getPassword(); // same here
					// login as admin
					if(DDB.getSystemAdministrator().login(adminUsername, adminPassword)) {
						System.out.println("you are admin");
						DDB.getSystemAdministrator().menu(DDB);
					} else {
						System.out.println("username or password incorrect.");
					}
					
					break;
				case(2):
					// restAdmin login using password and username
					String username = UserInput.getUsername(); // doesnt have to be get username, can be any string and just fail
					String password = UserInput.getPassword(); // same here
				
					RestAdmin restaurantAdmin = null;
					for (RestAdmin ra: DDB.getRestaurantAdmins()) {
						if(ra.getUsername().equals(username)) {
							restaurantAdmin = ra;
							break;
						}
					}
					
					if(restaurantAdmin == null) {
						System.out.println("restaurant admin not found.");
						break;
					}
					
					
					if(restaurantAdmin.login(username, password)) {
						System.out.println("you are restaurant admin");
						restaurantAdmin.menu(DDB);
					} else {
						System.out.println("username or password incorrect.");
					}
					
					break;
				case(3):
					// rider login using id
					String id = UserInput.getId();
				
					try {
						Rider rider = Services.findRider(id, DDB.getRiders());
						
						if(rider == null) { // this line might be useless now
							System.out.println("rider not found.");
							break;
						}
						
						rider.menu(DDB);
					} catch (RiderNotFoundException e) {
						ConsolePrinter.printError(e);
					}
					
					break;
				case(4):
					// customer login with customer code
					int code = UserInput.getInt("customer code");
				
					try {
						Customer customer = Services.findCustomer(code, DDB.getCustomers());
						
						if(customer == null) { // might not be relevant anymore
							System.out.println("customer not found.");
							break;
						}
						
						customer.menu(DDB);
					} catch (CustomerNotFoundException e) {
						ConsolePrinter.printError(e);
					}
					
				
					break;
				default:
					break;
			}
		}
		
//		input.close();
		
	}
}
