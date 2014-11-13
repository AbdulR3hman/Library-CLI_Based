
/** This Class is a user object, 
 * which Also implements comparable interface.
 * @author b1014963 , Abdul Al-FARAJ
 * @Date 17/10/2012
 *
 */

public class User implements Comparable<User> {

	// Declaring  members/fields of a user.

	private String userFirst , userSurname;
	private int numOfBooksBorrowed;
	private final static int MAXIMUM_BOOKS=3; 
	private final static int INTIAL_NUMBER_OF_BOOKS=0;

	public User()
	{
		userFirst = userSurname = null;
		numOfBooksBorrowed = INTIAL_NUMBER_OF_BOOKS;

	}

	public User(String first, String surname)
	{
		userFirst =first;
		userSurname = surname;
		numOfBooksBorrowed = INTIAL_NUMBER_OF_BOOKS;

	}

	public String getUserName(){ // return user name, ( surname first)

		String temp = this.getLast() + " , "+ this.getFirst();
		return temp;
	}

	public String getFirst(){
		return userFirst;
	}

	public String getLast(){
		return userSurname;
	}

	public boolean borrowBook(){

		if (numOfBooksBorrowed<=MAXIMUM_BOOKS){
			numOfBooksBorrowed++;
			return true;
		}else
			return false;

	}

	public boolean returnBook(){

		if (numOfBooksBorrowed>INTIAL_NUMBER_OF_BOOKS)
		{
			numOfBooksBorrowed--;
			return true;
		}else{
			return false;
		}
	}

	public int getNumOfBooks()
	{
		return numOfBooksBorrowed;
	}


	@Override
	public int compareTo(User obj){

		return this.getLast().compareTo(obj.getLast());
		// The above statements compare the two surnames lexicographically and send +ev,-ev, or 0 accordingly.
	}

	@Override
	public String toString()
	{
		return "\nUsername: " + this.getUserName() + " \nNumber of Books held: " +this.numOfBooksBorrowed;
	}
}
