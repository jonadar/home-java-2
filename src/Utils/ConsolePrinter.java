package Utils;

public class ConsolePrinter {

	public static void printError(Exception e) {
		System.out.print("\u001B[31m");
		System.out.println("Error: " + e.getMessage());
		System.out.print("\u001B[0m");
	}
	
	public static void printError(String message) {
		System.out.print("\u001B[31m");
		System.out.println("Error: " + message);
		System.out.print("\u001B[0m");
	}
}
