import java.util.*;

/** This method extends from LinkedList and implements comparable
 * Also, any object is stored here, will either implement comparable, or is extending from class which implements comparable.
 * @author b1014963, Abdul Al-Faraj
 * @version 12/10/2012
 *
 */

@SuppressWarnings("serial")
public class SortedLinkedList<E extends Comparable<? super E>> extends LinkedList<E> {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	ListIterator<E> iter =  this.listIterator(); 


	public  void insert(E obj)
	{
		ListIterator<E> iter =  this.listIterator(); 

		if (!iter.hasNext()){

			iter.add(obj);
			//THIS IS THE FIRST CASE, WHERE WE DON'T HAVE ANY ELEMENT
		}else {

			E elem=iter.next();	
			int comparison=elem.compareTo(obj); // to save the comparison between the two objects

			while (iter.hasNext() && comparison<0)
			{
				elem=iter.next();
				comparison= elem.compareTo(obj);
			}
			if(comparison>0){

				iter.previous();
			}
			iter.add(obj);
		}

	}
}
