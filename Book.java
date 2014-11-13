
/** 	This class hold information about book object.
		which also implements comparable interface.
 * 		@author b1014963 , Abdul Al-FARAJ
 * 		@Date 17/10/2012
 */

public class Book implements Comparable<Book> {

	/* Declaring all members of book**/

	private String bookTitle, authorFirst, authoerSurname;
	private boolean borrowed; 
	private User theBorrower; 

	public Book ()
	{
		bookTitle= authorFirst = authoerSurname = null;
		borrowed=false;

	}

	public Book (String title, String first,String surname)
	{
		bookTitle=title;
		authorFirst=first;
		authoerSurname=surname;

		borrowed=false; 
		/** I assume that the books are initially not borrowed, because they are new to the library */ 

	}

	// The following methods is to get the members, and because we get our members from a file, 
	// I do not make any set methods for the members.
	// The only set method, created, is for 'borrowed/borrower', for future changes when users borrow a book.

	public String getAuthorName(){ // return surname first
		String temp = getSurname() + ", " +getFirstName() ;
		return temp;
	}

	public String getFirstName(){
		return authorFirst;
	}

	public String getSurname(){
		return authoerSurname;
	}

	public String getBookTitle(){
		return bookTitle;
	}

	public void setBorrowed(boolean state){
		borrowed=state; 
	}

	public boolean isBorrowed(){
		return borrowed;
	}

	@Override
	public int compareTo(Book obj){

		return this.authoerSurname.compareTo(obj.authoerSurname);
		// The above statements compare the two surnames lexicographically and send 1,-1, or 0 accordingly.

	}

	@Override
	public String toString()
	{
		return "\nTitle: " + this.getBookTitle()+ "\nAuthor: "+ this.getAuthorName() + "\nBorrowed\f: "+ isBorrowed()
				+"\n------------------------------------------------";

	}

	public void setTheBorrower(User usr)
	{
		theBorrower= usr;
	}

	public User getTheBorrower()
	{
		return theBorrower;
	}
}
