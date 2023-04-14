
/* For the Extra Credit Problem (Problem EC) 
   CellList.java skeleton written by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2023, Assignment 5 

 Student Name: Joshua Mayhugh
   UIN: 431004527
   Acknowledgements: 
*/

import java.util.Arrays;
import java.util.Iterator;

// Total 40 points for the CellList class

public class CellList<E> implements Iterable<E>, Cloneable, Comparable<CellList<E>> {   
  private Cell<E> n;
  private int length;

  @Override
  public Iterator<E> iterator() { return n.iterator(); }

  // Task 1: override clone() (5 points)
  @Override
  public CellList<E> clone() {
		// Implement this method and explain
    //loop through the list and add the elements to a new list
    CellList<E> newList = new CellList<E>();
    for(E element : this){
    newList.length++;
      //go to the end of the list and add it
      Cell<E> newCell = new Cell<E>(element,null);
      if(newList.n == null) newList.n = newCell;
      else{
        Cell<E> temp = newList.n;
        while(temp.getNext() != null)//use while to iterate until emort
        {
          temp = temp.getNext();// finds last
        }
        temp.setNext(newCell); //make the last the new cell
      }
    }
    
   return newList;
  }



	 @Override
  public int compareTo(CellList<E> list) { 
    if (this.length < list.length) return -1;
    if (this.length == list.length) return 0;
    return 1; 
  }

  // Task 2: override equals() (10 points) 
	 @Override
  public boolean equals(Object o) {
		// Implement this method and explain (read the equality criteria in the
		// problem statement carefully!)                                          
    //they are equal if they have the same length and the same elements but order is not important
    if(o == null) return false; //if the object is nothing return false
    if(o == this) return true; //if they point to the same object return true
    if(!(o instanceof CellList)) return false; //if its not a cell list return false
    @SuppressWarnings("unchecked")
    CellList<E> list = (CellList<E>) o; //cast it to a cell list
    CellList<E> list1 = list.clone();  //clone the list
    CellList<E> list2 = this.clone(); //clone the list
    //create two new arrays to store the elements

    @SuppressWarnings("unchecked")
    E[] arr1 = (E[]) new Object[list1.length];
    @SuppressWarnings("unchecked")
    E[] arr2 = (E[]) new Object[list2.length];

    int i = 0;
    //loop through the list1 and add the elements to the array
    for(E element : list1){
      arr1[i] = element;
      i++;
    }
    i = 0;
    //loop through list2 and add the elements to the array
    for(E element : list2){
      arr2[i] = element;
      i++;
    }
    //sort the arrays
    Arrays.sort(arr1);
    Arrays.sort(arr2);
    for(int j = 0; j < arr1.length; j++){
      if(!arr1[j].equals(arr2[j])) 
      return false; //if the elements are not equal return false
    }
    
    return true;

		}

                                                            

  @Override
  public int hashCode() {
    return length;
  }

  // no-arg constructor - given
  public CellList() { n = null; length = 0; }
    
  // Task 3: one-arg constructor (5 points)
  public CellList(Iterable<E> iterable) {
		// implement this constructor

   //iterate through the iterable backwards and add the elements to the list
    for(E element : iterable){
      length++;
      //go to the end of the list and add it
      Cell<E> newCell = new Cell<E>(element,null);
      if(n == null) n = newCell;
      else{
        Cell<E> temp = n;
        while(temp.getNext() != null)//use while to iterate until emort
        {
          temp = temp.getNext();// finds last
        }
        temp.setNext(newCell); //make the last the new cell
      }
    }
		}

				
  // Task 4: total 20 points for toString(), push() and pop()
  // 8 points
  public String toString() {
		// implement this method
    //IF THIS IS NULL RETURN JUST AN EMPTY LIST
    if(n == null) return "[(head: )]";
    int i = 0;
    String str = "[";
    for(E element : this){
      if(i == length -1) 
      str += "(" + element + ")"; //if it is the last element, the arrow is not needed
      else if(i == 0)
      str += "(head: " + element + ") -> "; //if it is the first element, the arrow is not needed
      else if(i == length - 1 && i == 0)
      str += "(head: " + element + ")"; //if it is the only element, the arrow is not needed
      else str += "(" + element + ") -> "; //if it is not the last element the arrow will be used
      i++;
    }
    
    str += "]";
    return str;
  }

  // 5 points
  public void push(E item) {
  // implement this method
  //add to the front
    Cell<E> newCell = new Cell<E>(item,n);
    n = newCell;
    length++;
		}

  // 7 points
  public E pop() {
  // implement this method
  //remove from the front
    if(n == null) 
    return null; //if empyt return null
    E item = n.getVal(); //get item to return
    n = n.getNext();
    length--;
    return item;
		}

  // given 
  public E peek() { return n.getVal(); }

  // given 
  public int getLength() { return length; }
}

