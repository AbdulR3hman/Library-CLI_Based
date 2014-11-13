import java.io.IOException;
/** 	This  is the main of the program. 
 *		Librarians will be interacting with the programme form here.
 *		
 * 		@author b1014963 , Abdul Al-FARAJ
 * 		@Date 30/10/2012
 */

public class Driver {
	public static void main(String[] args) throws IOException {


		Library library = new Library();
		library.loadBooksUsers();
		boolean finished = false;
		char uniqueChar;

		while (!finished)
		{	
			printMenu();
			uniqueChar =  library.getChar();

			switch (uniqueChar)
			{
			case 'f':
				System.out.println("Library is terminated");
				finished=true;
				System.exit(0);
			case 'b':
				library.printBooks();
				break;
			case 'u':
				library.printUsers();
				break;
			case 'i':
				library.issueBook();
				break;
			case 'r':
				library.returnBook();
				break;
			default:
				System.out.println("Invalid instruction, please try again.");
			}
		}
	}

	private static void printMenu()
	{
		System.out.println("\n***************************************************" +
				"\nWelcome To The Library"+
				"\nPlease choosing one of the following:" +
				"\n f - to finish running the program" +
				"\n b - Display Books"+
				"\n u - Display Users" +
				"\n i - Issue books and update the library" +
				"\n r - Return books and update the library"
				+"\n***************************************************");
	}
}

