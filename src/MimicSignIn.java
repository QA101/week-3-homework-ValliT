import java.util.Scanner;

/**
 * This programming challenge requires the use of string methods, for loops, and nested if statements
 * This program is intended to mimic a log-in string in a terminal window and validation of an email address
 * 
 * This is a challenge exercise and will require time to investigate.
 * 
 * @author dustin
 *
 */
public class MimicSignIn {

	/**
	 * Main method should first read arguments from terminal.
	 * first argument -> username
	 * second argument -> password
	 * third argument -> email
	 * If the number of argument is not equal to 3, the program should exit immediately.
	 * 
	 * Then, the program should ask the user to sign in (username and password)
	 * 
	 * They have three attempts to log in, otherwise it should exit
	 * 
	 * Finally, it should tell them if they have a valid email address
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 *  TODO Check if the arguments is the right length.
		 *  If not the right length, exit immediately.
		 */ 
		if(args.length==3) {
			String username = args[0];
			String password = args[1];
			String email = args[2];
			final int MAX_ATTEMPTS = 3;
			String username_attempt = "";
			String password_attempt = "";			
			Scanner console = new Scanner(System.in);
			
			for(int i = 0; i< MAX_ATTEMPTS; i++){
				System.out.println("Username:");
				username_attempt = console.nextLine().trim();
				
				System.out.println("Password:");
				password_attempt = console.nextLine().trim();
				if(username.equals(username_attempt) && password.equals(password_attempt)) {
					if(ValidEmail(email)) {
						System.out.println("You have a valid email address");
					}
					else {
						System.out.println("You do not have a valid email address");
					}
					i = MAX_ATTEMPTS+1;
				}
			}
			console.close();
		}
		else {
			System.out.println("wrong number of elements");
		}
	}
	
	/**
	 * This method will take an email string and validate if it has a valid format.
	 * This only check minimal domain names
	 * 
	 * @param email - email string which needs to be validated for the correct format
	 * @return - returns true if the email is valid, returns false if it is not valid
	 */
	public static boolean ValidEmail(String email) {
		Boolean FoundValid = false;
		String[] emailEnding = {"@hotmail.com", "@gmail.com", "@yahoo.com", "@microsoft.com"};
		
		for(String e : emailEnding) {
			if(email.endsWith(e)) {
				FoundValid = true;
			}
		}
		
		return FoundValid;
	}
}
