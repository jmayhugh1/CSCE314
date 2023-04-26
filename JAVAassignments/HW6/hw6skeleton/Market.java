
/* Written by Hyunyoung Lee for CSCE 314 Students Homework 6 Problem 2 

   Student Name: Joshua Mayhugh
   UIN: 431004527
   Acknowledgements:
*/

import java.util.*;

public class Market<T> {
  Collection<T> stock; // stock of the market

  public Market() { stock = new java.util.LinkedList<T>(); }

  void sell(T item) {
    // implement this method
    // add the item to the end of the stock
    stock.add(item);
  }
  public T buy() {
    // implement this method
    //remove the first item from the stock and return it
    //remove the first item from the stock and return it
    if(stock.isEmpty()) {// return null if the stock is empty
      System.out.println("The stock is empty");
      return null; 
    }
    T item = stock.iterator().next(); // get the first item
    stock.remove(item); // remove the item from the stock

    return item;
  }
  void sell(Collection<? extends T> items) { // modify the parameter type
    // implement this method
    // add all the items to the end of the stock
    stock.addAll(items);


  }
  void buy(int n, Collection<? super T> items) { // modify the parameter type
    // implement this method
    int amnt = n; // set the amount to n
    int size = stock.size(); // get the size of the stock
    //iterate through the stock and add the first n elements to items
    Iterator<T> iter = stock.iterator();
    for(int i = 0; i < n && iter.hasNext(); i++)
    {
      T item = iter.next(); // get the next item
      iter.remove(); // remove the item from the stock
      items.add(item); // add the item to the collection


    }
    if(amnt > size) 
    {
      System.out.println("stock is out"); // print out a message if the stock is out
    }

  } // end of class Market

}
// Study class Main. You don't need to modify class Main
class Main {
  // three static nested classes expressing example subclass hierarchy
  // Gala <: Apple <: Fruit
  static class Fruit { public String toString () { return "Fruit"; } }
  static class Apple extends Fruit {
                       public String toString () { return "Apple"; }
  }
  static class Gala extends Apple {
                       public String toString () { return "Gala"; }
  }

  public static void main(String args[]) {
    Market<Fruit> farmersmarket = new Market<Fruit> ();

    Deque<Fruit> fruits = new ArrayDeque<Fruit>();
    fruits.addFirst(new Gala());
    fruits.addFirst(new Apple());
    //Fruit a = fruits.remove();
    //if (a instanceof Apple) System.out.println("a is Apple");

    Vector<Apple> apples = new Vector<Apple>();
    apples.addElement(new Apple());
    apples.addElement(new Apple());
    apples.addElement(new Gala());

    farmersmarket.sell(fruits);
    farmersmarket.sell(apples);
    farmersmarket.sell(new Fruit());
    farmersmarket.sell(new Gala());

    ArrayList<Fruit> mybasket = new ArrayList<Fruit>();

    farmersmarket.buy(6, mybasket);

    // print out what you bought
    System.out.println("Here's what I bought");
    for (Fruit e : mybasket) System.out.println(e);
    System.out.println("Enjoy!");
  } // end of main


} // end of class Main

