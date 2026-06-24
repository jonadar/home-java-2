package homework2;

import java.util.ArrayList;
import java.util.Collection;

public class SystemReports {
	public static void displayWildcardList(ArrayList<? extends Restaurant> restaurants) {
		restaurants.forEach(System.out::println);
	}
	
	public static double getTotalOrderFinalPriceSum(ArrayList<? extends Order> orders) {
		int total = 0;
		for (Order order: orders) {
			total += order.getFinalPrice();
		}
		return total;
	}
	
	public static void addNewFastfoodRestaurant(ArrayList<? super FastFoodRestaurant> obj) {
		// TODO: ask what are we supposed to add
	}
	
	public static <T extends Comparable<T>> T getGreatestItem(Collection<T> collection) {
		T max = null;
		
		for (T item: collection) {
			if(max == null || item.compareTo(max) < 0) max = item;
		}
		
		return max;
	}
	
	
}
