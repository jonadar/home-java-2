package homework2;

import java.util.Comparator;

import Utils.Validation;

public class Order {
	private int orderCode;
	private int customerCode;
	private Restaurant restaurant;
	private int restaurantCode;
	private String driverId;
	private String orderDate;
	private String deliveryDate;
	private double basePrice;
	private double finalPrice;
	private String deliveryStatus;
	
	private static int orderCount = 1;
	
	private static final String[] VALID_STATUSES = {"sent", "on the way", "delivered"};  // order sent - sent  to system, order on the way - rider is delivering, delivered - order arrived
	private static final Comparator<Order> comparator = (c1, c2) -> Double.compare(c2.finalPrice, c1.finalPrice);
	
	public int getOrderCode() {return orderCode;}
	public int getCustomerCode() {return customerCode;}
	public Restaurant getRest() {return restaurant;}
	public int getRestaurantCode() {return restaurantCode;}
	public String getDriverId() {return driverId;}
	public String getOrderDate() {return orderDate;}
	public String getDeliveryDate() {return deliveryDate;}
	public double getBasePrice() {return basePrice;}
	public double getFinalPrice() {return finalPrice;}
	public String getDeliveryStatus() {return deliveryStatus;}
	public static String[] getValidStatuses() {return VALID_STATUSES;}
	
	public Order(int customerCode, Restaurant restaurant, double basePrice, String orderDate) {
		this.orderCode = orderCount++;
		this.customerCode = customerCode;
		this.restaurant = restaurant;
		this.restaurantCode = restaurant.getRestaurantCode();
		this.driverId = null;
		this.orderDate = orderDate;
		this.basePrice = basePrice;
		this.deliveryDate = "0";
		this.deliveryStatus = VALID_STATUSES[0];
		this.finalPrice = restaurant.calculatePrice(basePrice);
		
		System.out.println("Order with code: " + this.orderCode + " has been created.");
	}
	
	public void setCustomerCode(int customerCode) {
		boolean valid = Validation.validate(customerCode, "invalid customer code");
		if(valid) this.customerCode = customerCode;
	}
	
	public void setRestaurant(Restaurant rest) {
		if (rest != null) {
			this.restaurant = rest;
		}
		else {
			System.out.println("invalid field");
		}
	}
	
	public void setRestaurantCode(int restaurantCode) {
		boolean valid = Validation.validate(restaurantCode, "invalid restaurant code");
		if(valid) this.restaurantCode = restaurantCode;
	}
	
	public void setDriverId(String driverId) {
		if(Validation.isId(driverId)) this.driverId = driverId;
		else System.out.println("invalid driver id");
	}
	
	public void setOrderDate(String orderDate) {
		if(Validation.isDate(orderDate)) this.orderDate = orderDate;
		else System.out.println("invalid order date");
	}
	
	public void setDeliveryDate(String deliveryDate) {
		if(Validation.isDate(deliveryDate)) this.deliveryDate = deliveryDate;
		else System.out.println("invalid delivery date");
	}
	
	public void setBasePrice(double basePrice) {
		boolean valid = Validation.validate(basePrice, "invalid base price");
		if(valid) {
			this.basePrice = basePrice;
			this.finalPrice = restaurant.calculatePrice(basePrice);
		} 
	}
	
	public void setDeliveryStatus(String deliveryStatus) {
		boolean inArray = !Validation.validateNotInArray(deliveryStatus, VALID_STATUSES);
		
		if(inArray) this.deliveryStatus = deliveryStatus;
		else System.out.println("invalid delivery status");
	}
	
	@Override
	public String toString() {
		return "Order [orderCode=" + orderCode + ", customerCode=" + customerCode + ", restaurant=" + restaurant
				+ ", restaurantCode=" + restaurantCode + ", driverId=" + driverId + ", orderDate=" + orderDate
				+ ", deliveryDate=" + deliveryDate + ", basePrice=" + basePrice + ", finalPrice=" + finalPrice
				+ ", deliveryStatus=" + deliveryStatus + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Order) {
			Order other = (Order) obj;
			return other.orderCode == this.orderCode;
		}
		return false;
	}
	
	public static int compare(Order o1, Order o2) {
		return comparator.compare(o1, o2);
	}
}
