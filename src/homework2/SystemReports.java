package homework2;

import java.util.ArrayList;
import java.util.Collection;

public class SystemReports {
	public static void displayWildcardList(ArrayList<? extends Restaurant> restaurants) {
		if (restaurants == null || restaurants.size() == 0) System.out.println("no restaurants to display");
		restaurants.forEach(System.out::println);
	}
	
	public static double getTotalOrderFinalPriceSum(ArrayList<? extends Order> orders) {
		int total = 0;
		for (Order order: orders) {
			if(order != null) total += order.getFinalPrice();
		}
		return total;
	}
	
	public static void addNewFastfoodRestaurant(ArrayList<? super FastFoodRestaurant> obj) {
		if (obj == null) return;
		
		FastFoodRestaurant res = Services.createFastFoodRestaurant();
		
		obj.add(res);
		System.out.println("added " + res);
	}
	
	public static <T extends Comparable<T>> T getGreatestItem(Collection<T> collection) {
		T max = null;
		
		for (T item: collection) {
			if (item != null) {		
				if(max == null || item.compareTo(max) < 0) max = item;
			}
		}
		
		return max;
	}
}
