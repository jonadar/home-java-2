package homework2;

import java.util.ArrayList;

import Utils.UserInput;
import Utils.Validation;

public class Rider {
	private String id;
	private String fullName;
	private String phoneNumber;
	private String vehicle;
	private boolean isAvailable;
	private ArrayList<Order> orders;
	
	public Rider(String id, String fullName, String phoneNumber, String vehicle, boolean isAvailable) {
		this.id = id;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.vehicle = vehicle;
		this.isAvailable = isAvailable;
		this.orders = new ArrayList<Order>();
		
		System.out.println("rider with id: " + this.id + " has been created.");
	}
	
	public String getId() { return id; }
	public String getFullName() { return fullName; }
	public String getPhoneNumber() { return phoneNumber; }
	public String getVehicle() { return vehicle; }
	public ArrayList<Order> getOrders() { return orders; }
	public boolean getAvailable() { return isAvailable; }

	public void setId(String id) {
		if (Validation.isId(id)) this.id = id;
		else System.out.println("invalid id");
	}
	
	public void setFullName(String fullName) {
		if (Validation.isName(fullName)) this.fullName = fullName;
		else System.out.println("invalid full name");
	}

	public void setPhoneNumber(String phoneNumber) {
		if (Validation.isPhoneNumber(phoneNumber)) this.phoneNumber = phoneNumber;
		else System.out.println("invalid phone number");
	}

	public void setVehicle(String vehicle) {
		if (Validation.isName(vehicle)) this.vehicle = vehicle;
		else System.out.println("invalid vehicle");
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

//	public void setOrders(ArrayList<Order> orders) {
//		if (orders == null) {
//			System.out.println("cant set null orders");
//			return;
//		}
//
//		this.orders = orders;
//	}
	
	public boolean addOrder(Order order) {
		if(order == null) return false;
		if(!Validation.validateNotInArray(order, this.orders)) {
			System.out.println("order already in riders list");
			return false;
		}
		
		this.orders.add(order);
		this.setAvailable(false);
		
		return true;
	}
	
	// look for active order show it
	public void showActiveOrder() {
		if(isAvailable) {
			System.out.println("rider is not currently working on an order");
			return;
		}
		for (Order order : this.orders) {
			if(order.getDeliveryStatus().equals("on the way")) {
				System.out.println("active order: " + order);
				return;
			}
		}
		System.out.println("no active order found");
	}
	
	public void menu(DeliveryDataBase DDB) {
		System.out.println("welcome rider " + this.fullName + ". what would you like to do?");
		while(true) {
			System.out.println("1. update order status");
			System.out.println("2. view active order");
			System.out.println("3. view order history");
			System.out.println("4. logout");
			int option = UserInput.getIntFromRange(1, 4, "option");
			if(option == 4) break;
			
			switch (option) {
				case 1:
					Services.updateOrderStatus(this);
					break;
				case 2:
					this.showActiveOrder();
					break;
				case 3:
					DDB.displayAllOrders(this); // TODO: check if this does correctly
					break;
			}
		}
	}
	
	
	@Override
	public String toString() {
		return "Rider [id=" + id + ", fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", vehicle=" + vehicle
				+ ", isAvailable=" + isAvailable + ", orders=" + this.orders + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Rider) {
			Rider other = (Rider) obj;
			return other.id.equals(this.id);
		}
		return false;
	}
}