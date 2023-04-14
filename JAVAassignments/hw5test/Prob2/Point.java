
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2023, Assignment 5
   Student Name: Joshua Mayhugh
   UIN: 431004527
   Acknowledgements: 
*/

public final class Point {
  public double x;
  public double y;

  // constructor that sets the values of x and y
  public Point(double _x, double _y)
  {  // implement the constructor
    x = _x;
    y = _y;
  }

  // implement equals, hashCode(), toString()
  @Override
  public boolean equals(Object s)
  {  // implement the method and explain your implementation 
    if(s == null){ //if its pointing at nothing return false
      return false;
    }
    if(s == this){ //if they are the same object return true, because they are going to be same value
      return true;
    }
    if(!(s instanceof Point)){ // if it isnt an instance of point then it cant be equal
      return false;
    }
    Point p = (Point) s; //cast it to a point
    return (p.x == this.x && p.y == this.y); //if the x and y values are the same then they are equal


  }

  @Override
  public int hashCode()
  {  
    //gor through each value and add it to the hashcode
    int hash = 7; //prime starting value
    hash = 31 * hash + (int) x; //hashes the x value
    hash = 31 * hash + (int) y; //hashes the y value
    return hash; //return the hashcode
  }

  @Override
  public String toString()
  {  // implement the method and explain your implementation 
    return "(" + x + ", " + y + ")"; //return the string representation of the point in paranthesis format
  }
} // end of class Point

