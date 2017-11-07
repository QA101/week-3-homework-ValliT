import java.util.Random;
import java.util.Scanner;

/**
 * The intent of the pig game is to roll a dice to 100 before your opponents.
 * 
 * Each turn, a player repeatedly rolls a die until either a 1 is rolled or the player decides to "hold"
 * If the player rolls a 1, they score nothing and it becomes the next player's turn.
 * If the player rolls any other number, it is added to their turn total and the player's turn continues.
 * If a player chooses to "hold", their turn total is added to their score, and it becomes the next player's turn. 
 * 			
 * @author dustin
 *
 */
public class PigGame {

	int[] scores;
	Boolean playing = true;
	Boolean isWon = false;
	final int MAX_ATTEMPTS = 3;
	final int MIN_PLAYERS = 2;
	final int MAX_PLAYERS = 4;
	String IncorrectPlayers = "You can't play with that many people, it wouldn't be any fun";
	String NotAnInt = "That's not an int. Com'on";
	int DiceSides = 6;
	Random rand = new Random();
	final int WIN_CONDITION = 100;
	final String INSTRUCTIONS = "Type: ROLL or R to roll the dice, HOLD to stop rolling, QUIT to end the game, or PRINT to print the scores";
	
	
	/**
	 * Start location for the program. The program will create a pig object and provide minimal instructions to the players
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner console = new Scanner(System.in);
		PigGame game = new PigGame();
		
		game.ClearScreen();
		System.out.println("Would you like to play a game???");
		System.out.println();
		game.PrintHelp();
		System.out.println("Press any key to continue...");
		console.nextLine();
		game.ClearScreen();
		
		while(game.playing) {
			game.Reset(console);
			game.Play(console);
			if (game.isWon) {
				System.out.println("Do you want to play again?");
				if (console.nextLine().trim().toUpperCase() == "YES") {
					game.playing = true;
				}
			}
		}
	}
	
	/**
	 * Resets the parameters of the game and asks the user how many players
	 * The user must put in a valid set of player, otherwise the program will exit
	 * 
	 * @param console
	 */
	public void Reset(Scanner console) {
		int players = 0;
		playing = false;
		
		for (int i = 0; i < MAX_ATTEMPTS; i++) {
			System.out.println("How many players?");
			if(console.hasNextInt()) {
				players = Integer.parseInt(console.nextLine());
				if(players >= MIN_PLAYERS && players <= MAX_PLAYERS ) {
					scores = new int[players];
					i = MAX_ATTEMPTS +1;
					playing = true;
				}
				else {
					System.out.println(IncorrectPlayers);
				}
			}
			else {
				System.out.println(NotAnInt);
			}
		}
	}
	
	public void PrintInstructions() {
		System.out.println(INSTRUCTIONS);
	}
	
	public void Play(Scanner console) {
		while(playing) {
			for (int i =0; i < scores.length; i++) {
				System.out.println("Press any key to continue...");
				console.nextLine();
				ClearScreen();
				
				System.out.printf("It is player's %d turn\n", i);
				Boolean playingRound = true;
				int result = 0;
				int roundTotal = 0;
				String command = "";
				
				while(playingRound) {
					PrintInstructions();
					command = CleanInput(console.nextLine());
					
					switch(command) {
						case "R":
						case "ROLL":
							result = roll();
							System.out.printf("You got a: %d\n", result);
							if(result != 1) {
								roundTotal+= result;
								System.out.println("Current Round Total: "+roundTotal);
								if ((roundTotal+scores[i])>=WIN_CONDITION){
									System.out.printf("Player %d has won with a score of %d", i, scores[i]);
									playingRound = false;
									isWon = true;
								}
							}
							else {
								playingRound = false;
							}
							break;
						
						case "EXIT":
						case "QUIT":
							playing = false;
							playingRound = false;
							i = scores.length+1;
							break;
						
						case "PRINT":
							PrintScores();
							break;
						
						case "HOLD":
							scores[i] += roundTotal;
							playingRound = false;
							break;
							
						case "HELP":
							ClearScreen();
							PrintHelp();
							break;
							
						case "CLEAR":
							ClearScreen();
							break;
							
						default:
							System.out.println("Invalid Command");
							break;
					}
				}
			}
		}
	}
	
	/**
	 * Clearing the terminal for most unix based system. This clear command should be OS dependent.
	 */
	public void ClearScreen(){
		//TODO build OS dependent function for clearing terminal
		System.out.print(String.format("\033[2J"));
	}
	
	/**
	 * Print the help instructions for the player
	 */
	public void PrintHelp() {
		System.out.println("How to play:");
		System.out.println("Each turn, a player repeatedly rolls a die until either a 1 is rolled or the player decides to \"hold\":\r\n" + 
			"If the player rolls a 1, they score nothing and it becomes the next player's turn.\r\n" + 
			"If the player rolls any other number, it is added to their turn total and the player's turn continues.\r\n" + 
			"If a player chooses to \"hold\", their turn total is added to their score, and it becomes the next player's turn.\r\n" + 
			"\r\n" + 
			"The first player to score 100 or more points wins.");
		System.out.println();
	}
	
	/**
	 * create a random number between 1 and the number of sides of the die.
	 *
	 * @return - sudo-random number
	 */
	public int roll() {
		return rand.nextInt(DiceSides)+1;
	}
	
	/**
	 * Prints the scored banked by each player
	 */
	public void PrintScores() {
		for(int i = 0; i < scores.length; i ++) {
			System.out.printf("Player %d has a banked score of: %d\n", i, scores[i]);
		}
	}
	
	/**
	 * cleans the input string from terminal then returns the cleaned up string to the caller
	 * 
	 * @param input
	 * @return
	 */
	public String CleanInput(String input) {
		return input.trim().toUpperCase();
	}
}
