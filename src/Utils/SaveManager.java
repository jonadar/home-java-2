package Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import MyExceptions.RestaurantNotFoundException;
import homework2.Customer;
import homework2.DeliveryDataBase;
import homework2.FastFoodRestaurant;
import homework2.Order;
import homework2.PremiumRestaurant;
import homework2.RestAdmin;
import homework2.Restaurant;
import homework2.Rider;
import homework2.Services;

public class SaveManager {
	
	public static void save(DeliveryDataBase DDB) {
		saveCustomers(DDB.getCustomers());
		saveOrders(DDB.getOrders());
		saveRiders(DDB.getRiders());
		saveRestaurants(DDB.getRestaurants());
		saveRestaurantAdmins(DDB.getRestaurantAdmins());
	}
	
	public static void load(DeliveryDataBase DDB) {
		loadCustomers(DDB);
		loadRestaurants(DDB);
		loadOrders(DDB);
		loadRiders(DDB);
		loadRestaurantAdmins(DDB);
	}
	
	/*
	 *  ================== save functions = ===========================
	 */
	public static void saveCustomers(ArrayList<Customer> customers) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("data/saves/customers.txt"));
			for (Customer c : customers) {
				writer.write(c.getfirstName() + ";" + c.getLastName()+ ";" + c.getAddress()+ ";" + c.getPhoneNumber()+ ";" + c.getEmail()+ ";" + c.getRemainingCredit());
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			ConsolePrinter.printError("could not find customer save");
		}
	}
	
	public static void saveOrders(ArrayList<Order> orders) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("data/saves/orders.txt"));
			for (Order o : orders) {
				writer.write(o.getCustomerCode()+ ";" + o.getRestaurantCode()+ ";" + o.getBasePrice()+ ";" + o.getOrderDate()+ ";" + o.getDriverId()+ ";" + o.getDeliveryStatus()+ ";" + o.getDeliveryDate());
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			ConsolePrinter.printError("could not find customer save");
		}
	}
	
	public static void saveRiders(ArrayList<Rider> riders) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("data/saves/riders.txt"));
			for (Rider r : riders) {
				writer.write(r.getId()+ ";" + r.getFullName()+ ";" + r.getPhoneNumber()+ ";" + r.getVehicle()+ ";" + r.getAvailable());
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			ConsolePrinter.printError("could not find customer save");
		}
	}
	
	public static void saveRestaurants(ArrayList<Restaurant> restaurants) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("data/saves/restaurants.txt"));
			for (Restaurant r : restaurants) {
				String restProperties = r.getName() + ";" + r.getKitchenType()+ ";" + r.getRating()+ ";" + r.isOpen()+ ";" + r.getDeliveryFee();
				
				if (r instanceof FastFoodRestaurant) {
					FastFoodRestaurant rest = (FastFoodRestaurant) r;
					writer.write("Fst;" + restProperties + ";" + rest.getAverageCookTime() + ";" + rest.getFastDeliveryFee());
				} else if (r instanceof PremiumRestaurant) {
					PremiumRestaurant rest = (PremiumRestaurant) r;
					writer.write("Prm;" + restProperties + ";" + rest.getMinOrderValue() + ";" + rest.getOrderFeePercentage());
				} else writer.write("Reg;" + restProperties);
				
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			ConsolePrinter.printError("could not find customer save");
		}
	}
	
	public static void saveRestaurantAdmins(ArrayList<RestAdmin> restaurantAdmins) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("data/saves/restaurantAdmins.txt"));
			for (RestAdmin a : restaurantAdmins) {
				writer.write(a.getName()+ ";" + a.getUsername()+ ";" + a.getPassword()+ ";");
				String restaurantCodes = "";
				
				for(int i = 0; i < a.getRestaurants().size(); i++) {
					restaurantCodes += a.getRestaurants().get(i).getRestaurantCode();
					if(i != a.getRestaurants().size() - 1) { // for all but last item
						restaurantCodes += "|";  //seperate them by none ';'
					}
				}
				writer.write(restaurantCodes);
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			ConsolePrinter.printError("could not find customer save");
		}
	}
	
	/*
	 *  ================== load functions = ===========================
	 */
	
	public static void loadCustomers(DeliveryDataBase DDB) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/saves/customers.txt"));
			String line;
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ";");
	
				if (st.countTokens() == 6) {					
					String fname = st.nextToken();
					String lname = st.nextToken();
					String address = st.nextToken();
					String phone = st.nextToken();
					String email = st.nextToken();
					Double balance = Double.parseDouble(st.nextToken());
					
					Customer customer = new Customer(fname, lname, address, phone, email, balance);
					DDB.addCustomer(customer);
				}
			}
			
			reader.close();
		} catch (IOException e) {
			ConsolePrinter.printError("could not find customer save");
		}
	}
	
	public static void loadOrders(DeliveryDataBase DDB) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/saves/orders.txt"));
			String line;
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ";");
	
				if (st.countTokens() == 7) {				
					int code = Integer.parseInt(st.nextToken());
					int restCode = Integer.parseInt(st.nextToken());
					double basePrice = Double.parseDouble(st.nextToken());
					String orderDate = st.nextToken();
					String riderId = st.nextToken();
					String status = st.nextToken();
					String deliveryDate = st.nextToken();

					try {
						Restaurant rest = Services.findRestaurant(restCode, DDB.getRestaurants());
						
						Order order = new Order(code, rest, basePrice, orderDate);
						
						if(!riderId.equals("null")) order.setDriverId(riderId);
						
						if(Validation.isDate(deliveryDate)) order.setDeliveryDate(deliveryDate);
						
						order.setDeliveryStatus(status);
					
						DDB.addOrder(order);
					} catch (RestaurantNotFoundException e) {
						ConsolePrinter.printError(e);
					}
					
				}
			}
			
			reader.close();
		} catch (IOException e) {
			ConsolePrinter.printError("could not find order save");
		}
	}
	
	public static void loadRiders(DeliveryDataBase DDB) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/saves/riders.txt"));
			String line;
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ";");
				
				if (st.countTokens() == 5) {					
					String id = st.nextToken();
					String name = st.nextToken();
					String phone = st.nextToken();
					String vehicle = st.nextToken();
					boolean available = Boolean.parseBoolean(st.nextToken());
					
					Rider rider = new Rider(id, name, phone, vehicle, available);
					DDB.addRider(rider);
				}
			}
			
			reader.close();
		} catch (IOException e) {
			ConsolePrinter.printError("could not find rider save");
		}
	}
	
	public static void loadRestaurants(DeliveryDataBase DDB) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/saves/restaurants.txt"));
			String line;
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ";");
				
				if (st.countTokens() >= 6) {
					String restaurantType = st.nextToken(); 
					String name = st.nextToken();
					String kitchenType = st.nextToken();
					double rating = Double.parseDouble(st.nextToken());
					boolean open = Boolean.parseBoolean(st.nextToken());
					double fee = Double.parseDouble(st.nextToken());
					
					Restaurant rest;
					
					if(restaurantType.equals("Fst") && st.countTokens() == 2) {
						rest = new FastFoodRestaurant(name, kitchenType, rating, open, fee, Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
					} else if (restaurantType.equals("Prm") && st.countTokens() == 2) {
						rest = new PremiumRestaurant(name, kitchenType, rating, open, fee, Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
					} else {
						rest = new Restaurant(name, kitchenType, rating, open, fee);
					}
					
					DDB.addRestaurant(rest);
				}
			}
			
			reader.close();
		} catch (IOException e) {
			ConsolePrinter.printError("could not find restaurant save");
		}
	}
	
	public static void loadRestaurantAdmins(DeliveryDataBase DDB) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/saves/restaurantAdmins.txt"));
			String line;
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ";");
				
				if (st.countTokens() >= 3) { // atleast name username and password					
					String name = st.nextToken();
					String username = st.nextToken();
					String password = st.nextToken();
				
					RestAdmin restAdmin = new RestAdmin(name, username, password);
					
					if (st.countTokens() == 1) {
						StringTokenizer codes = new StringTokenizer(st.nextToken(), "|");
						
						while(codes.hasMoreTokens()) {
							try {	
								int code = Integer.parseInt(codes.nextToken());
								Restaurant rest = Services.findRestaurant(code, DDB.getRestaurants());
								restAdmin.addRestaurant(rest);
							} catch (Exception e) {
								ConsolePrinter.printError(e);
							}
						}
					}
					
					DDB.addRestaurantAdmin(restAdmin);
				}
			}
			
			reader.close();
		} catch (IOException e) {
			ConsolePrinter.printError("could not find restaurantAdmins save");
		}
	}
	
}
