package lab21;
/**
 * @author meng
 */
import java.io.File;
import java.util.Scanner;

public class AEIOU {
	static String sequences = ""; // all to store matched sequences
	
	// load the file, store everything in one string
	public static String loadData(String fileName) {
		String str = "";
		Scanner fileScanner = null;
		
		try {
			fileScanner = new Scanner(new File(fileName));
			while (fileScanner.hasNextLine()) {
				str += fileScanner.nextLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fileScanner != null) {
				fileScanner.close();
			}
		}
		
		return str;
	}
	
	// count the appearance of AEIOU in order
	public static int countSeq(String longString) {
		int count = 0; // count of appearance
		// bits represent matching A/E/I/O/U respectively,
		// 0 - not matched yet; 1 - matched
		// e.g., 11000 represents that I have got "AE" matched in order.
		String state = "00000";
		String sequence = ""; // current matched sequence
		char pointer; //  current char in given string
		
		for (int i = 0; i < longString.length(); i++) {
			pointer = longString.toLowerCase().charAt(i);
			sequence += longString.charAt(i);
			
			if ('a' == pointer) { // every time when get an "A", the seq needs to start over
					state = "10000"; // matching "A"
			} else if ('1' == state.charAt(0)) { // matched "A"
				if ('0' == state.charAt(1)) { // matched "A"
					if ('e' == pointer) { // get an "E"
						state = "11000"; // matching "AE"
					} else if ('i' == pointer ||
							'o' == pointer ||
							'u' == pointer) { // get {A, E, I, O, U} - {A, E}
						// if an "A" comes in, seq state should stay "10000" since "A" is matched!!!
						state = "00000"; // disordered, reset
						sequence = ""; // reset
					}
				} else if ('0' == state.charAt(2)) { //  matched "AE"
					if ('i' == pointer) { // get an "I"
						state = "11100"; // matching "AEI"
					} else if ('e' == pointer ||
							'o' == pointer ||
							'u' == pointer) { // get {A, E, I, O, U} - {A, I}
						state = "00000"; // disordered, reset
						sequence = ""; // reset
					}
				} else if ('0' == state.charAt(3)) { // matched "AEI"
					if ('o' == pointer) { // get an "O"
						state = "11110"; // matching "AEIO"
					} else if ('e' == pointer ||
							'i' == pointer ||
							'u' == pointer) { // get {A, E, I, O, U} - {A, O}
						state = "00000"; // disordered, reset
						sequence = ""; // reset
					}
				} else if ('0' == state.charAt(4)) { // matched "AEIO"
					if ('u' == pointer) { // finally ...
						// get the whole sequence in order ...
						count++; // increase counter
						sequences += count + ": " + sequence + "\n"; // append current sequence to the list
						sequence = ""; // reset
						state = "00000"; // reset to start over the long journey ...
					} else if ('e' == pointer ||
							'i' == pointer ||
							'o' == pointer) { // get {A, E, I, O, U} - {A, U}
						state = "00000"; // disordered, reset. man! I was this close!
						sequence = ""; // reset
					}
				}
			}
			
		}
		
		return count;
	}
	
	public static void main(String[] args) {
		String fileName = "src/lab21/blah.txt";
		String str = loadData(fileName);
		System.out.println("The file blah.txt has \"AEIOU\" in order " + countSeq(str) + " times:");
		System.out.println(sequences);
	}
}
