
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2023, Assignment 5
    Student Name: Joshua Mayhugh
   UIN: 431004527
   Acknowledgements: 
*/

import java.lang.Math;

class Square extends Shape {
  private double side; // side is the side length

  // constructor that accepts a Point (for position) and a double
  // (for the side length).
  public Square(Point p0, double _side)
  {  // implement the constructor
    //assign point 
    super(p0);
    //assign side
    side = _side;
    area = area();
  
  }

  // implement equals(), hashCode(), area(), and toString()
  @Override
  public boolean equals(Object o)
  {  // implement this method and explain your implementation
    //if null return false
    if(o == null){
      return false;
    }
    //if they are the same object return true
    if(o == this){
      return true;
    }
    //if its not an instance of square then it cant be equal
    if(!(o instanceof Square)){
      return false;
    }
    //cast it to a square
    Square s = (Square) o;
    //if the position and side are the same then 
    return (s.position.equals(this.position) && s.side == this.side);
  }

  @Override
  public int hashCode()
  {  // implement this method and explain your implementation

    int result =17;
    //hash the position
    result = 31 * result + position.hashCode();
    //convert to long thenn combine high order and low order bits
    long temp = Double.doubleToLongBits(side);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
    
  }

  @Override
  public double area()
  {  // implement this method 
    //this is the area of a square
    return side * side;
  }

  @Override
  public String toString()
  {  // implement this method and explain your implementation
    //return the string representation of the square
    return "Shape: Square, " + " position = " + position.toString() + ", side = " + side + ", area = " + area();
  }
} // end of class Square

