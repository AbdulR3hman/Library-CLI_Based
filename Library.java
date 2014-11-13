import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ListIterator;
import java.util.Scanner;

/** 	Structure of the library is set here
 * 		all methods needed to be implemented in the main are created here.
 *		
 * 		@author b1014963 , Abdul Al-FARAJ
 * 		@Date 30/10/2012
 */
public class Library {

	private SortedLinkedList<Book> booksLibrary;
	private SortedLinkedList<User> usersLibrary;
	File log;

	public Library() throws IOException
	{
		booksLibrary = new SortedLinkedList<Book>();
		usersLibrary = new SortedLinkedList<User>();
		log= new File ("H:\\ECE Year 2012-2013\\Semester 1\\CSC2011 - Advance Programming\\Practicle\\CourseWork ( week 3 & 4 )\\src\\outputs.txt");

	}

	//Load books from txt file.
	public void loadBooksUsers() throws FileNotFoundException

	{
		File inputFile = new File("H:\\ECE Year 2012-2013\\Semester 1\\CSC2011 - Advance Programming\\Practicle\\CourseWork ( week 3 & 4 )\\src\\inputLibrary.txt");
		Scanner bookUsersScanner = new Scanner (inputFile);

		/** The Following is to save the information about the Books and their authors.*/
		int noOfBooks= bookUsersScanner.nextInt();
		bookUsersScanner.nextLine(); // to skip the first empty line.
		int i=0; // to increment.
		while (bookUsersScanner.hasNext() && i<noOfBooks)
		{

			String TempBook=bookUsersScanner.nextLine();
			String TempAuther= bookUsersScanner.nextLine();
			String[] splits = TempAuther.split(" ");
			Book tempBook = new Book (TempBook, splits[0],splits[1]); // assuming there is only one surname
			booksLibrary.insert(tempBook);		
			i++;

		}

		int noOfUsers= bookUsersScanner.nextInt();
		bookUsersScanner.nextLine(); // to skip the first empty line.
		i=0;
		while (bookUsersScanner.hasNext() && i<noOfUsers)
		{

			String tempUserName = bookUsersScanner.nextLine();
			String[] splits = tempUserName.split(" ");
			User tempUser = new User (splits[0],splits[1]); // assuming there is only one surname
			usersLibrary.insert(tempUser);		
			i++;
		}
		bookUsersScanner.close();
	}

	public SortedLinkedList<Book> getBookList()
	{
		return this.booksLibrary;

	}

	public SortedLinkedList<User> getUserList()
	{
		return this.usersLibrary;
	}

	public  char getChar(){

		Scanner charScan = new Scanner(System.in);
		String  input = charScan.next();
		while(input.length()!=1)
		{

			System.out.println("\nInvalid instruction, please try again: \n");
			input=charScan.next();
		}

		char[] split = input.toCharArray();
		return split[0];

	}

	// case b- books display
	public void printBooks()
	{
		ListIterator<Book> bookIter =  this.getBookList().listIterator();
		while (bookIter.hasNext())
		{
			System.out.print(bookIter.next());
		}
	}

	//case u- users display
	public void printUsers()
	{
		ListIterator<User> bookIter =  this.getUserList().listIterator();
		while (bookIter.hasNext())
		{
			System.out.print(bookIter.next());
		}
	}

	//case i- issue books
	public void issueBook() throws IOException
	{

		PrintWriter outputLibrary =new PrintWriter(new FileWriter(log, true)); // This won't overwrite the content of the text file, it will add upon the file.
		Scanner bookSearch = new Scanner (System.in);
		System.out.println("\n To issue a book, please type the user surname:   "); // we assume no user have similar names, as the assignment specification indicates
		String wantedUser = bookSearch.nextLine();
		boolean bookFound=false, userFound = false; 


		// 1st: Find User
		User tempUser=null; 
		String tempUserSurname;
		ListIterator<User> userIter =  this.getUserList().listIterator();

		while (userIter.hasNext() && !userFound){

			tempUser=userIter.next();
			tempUserSurname= tempUser.getLast();
			if(tempUserSurname.compareToIgnoreCase(wantedUser)==0){
				userFound=true;
			}

		}

		//2nd: Find book, userFound=true
		if (userFound)
		{
			System.out.println("\n User has been found, please type in the book name that you would like to issue:  ");
			String wantedBook = bookSearch.nextLine();
			Book tempElem;
			String tempTitle;
			ListIterator<Book> bookIter =  this.getBookList().listIterator();

			while (bookIter.hasNext() && !bookFound)
			{
				tempElem = bookIter.next();
				tempTitle = tempElem.getBookTitle();
				if (tempTitle.compareToIgnoreCase(wantedBook)==0)
				{
					bookFound=true;
					//3rd: is book borrowed?
					if(!tempElem.isBorrowed()){

						/**Update starts here*/
						//4th: maximum number of books?
						if (tempUser.getNumOfBooks()<3){
							tempUser.borrowBook();
							tempElem.setBorrowed(true);
							tempElem.setTheBorrower(tempUser);
							System.out.println("\nBook is found, and it is not borrowed"
									+"\nThe following Book: " + tempElem.getBookTitle() + " Been issued to: "+tempElem.getTheBorrower());
						}else{
							System.out.println("User: " + tempUser.getUserName() + " has maximum number of book, " +  tempUser.getFirst()+" can't borrow any more books.");
						}
					}else{
						//Book is found but borrowed:
						System.out.println("Book: " + tempElem.getBookTitle() + " is borrowed. \nAn appropriate letter will be generated for: " + tempElem.getTheBorrower());
						outputLibrary.println("\n------------------------------------------------------------------------------");
						outputLibrary.println("\n");
						outputLibrary.println("\nDear:"+tempElem.getTheBorrower().getLast() );
						outputLibrary.println("Please return the following books as soon as possible. It has been requested by a different user.");
						outputLibrary.println("\nBook title:" + tempElem.getBookTitle()+"\n");
						outputLibrary.println("\n");
						outputLibrary.println("\n");
						outputLibrary.println("\nThank you very much, Newcastle CS Library" );
						outputLibrary.println("\n------------------------------------------------------------------------------");
					}
				}
			}
			//if book not found
			if (!bookFound){
				System.out.println("Book: " + wantedBook+ " is not found!");
			}
			//if user isn't found	
		}else{
			System.out.println("User: " + wantedUser + " has not been found");
		}
		outputLibrary.close();
	}

	//case r- return books
	public void returnBook()
	{


		boolean bookFound = false, userFound=false;
		User tempUser = null; Book tempBook = null;
		String tempBorrowerSurname = null, tempTitle = null;

		Scanner returnedBook = new Scanner (System.in);
		System.out.println("Please type  in user's surname who is returning the book: ");
		String returnerSurname = returnedBook.nextLine();

		/**is user found?*/
		ListIterator<User> userIter =  this.getUserList().listIterator();
		while (userIter.hasNext() && !userFound)
		{
			tempUser=userIter.next();
			if(tempUser.getLast().compareToIgnoreCase(returnerSurname)==0){
				userFound=true;
			}
		}

		/**If user found, find book!*/
		if(userFound){
			ListIterator<Book> bookIter =  this.getBookList().listIterator();
			System.out.println("Please type  in book title which you would like to return: ");
			tempTitle= returnedBook.nextLine();

			while (bookIter.hasNext() && !bookFound){

				tempBook=bookIter.next();
				if (tempBook.getBookTitle().compareToIgnoreCase(tempTitle)==0){

					bookFound=true;
					//check if its borrowed
					if(tempBook.isBorrowed()){
						tempBorrowerSurname=tempBook.getTheBorrower().getLast();
						//check if they are the same as who is returning the book.
						if ( tempBorrowerSurname.compareToIgnoreCase(returnerSurname)==0 ){
							//update the library
							tempBook.setBorrowed(false);
							tempBook.setTheBorrower(null);
							tempUser.returnBook();
							System.out.println("Book is successfuly returned.");
						}else{
							System.out.println("User who borrowed the book is not the same as the user returing it....");
						}
					}else{
						System.out.println("\nThe current book is not borrowed, please try again.");
					}
				}
			}
			/**if book isn't found*/
			if(!bookFound){
				System.out.println("The requried book is not found, please try again from the main menu, thanks....");
			}

			/**if user isn't found*/
		} else{
			System.out.println("User is not found");
		}
	}
}
