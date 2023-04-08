
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2023, Assignment 5
   Student Name:
   UIN:
   Acknowledgements:
*/

import java.lang.Math;

class Circle extends Shape {
  private double radius;

  // constructor that accepts a Point (for position) and a double
  // (for the radius).
  public Circle(Point p0, double r)
  {  // implement the constructor
    //assign point 
    super(p0);
    //assign radius
    radius = r;
    //assign area
    area = area();
  }

  // implement equals(), hashCode(), area(), and toString()
  @Override
  public boolean equals(Object o)
  {  // implement this method and explain your implementation
    //if null return false
    if(o == null)
    {
      return false;
    }
    //if they are the same object return true
    if(o == this)
    {
      return true;
    }
    //if its not an instance of circle then it cant be equal
    if(!(o instanceof Circle))
    {
      return false;
    }
    //cast it to cicle
    Circle c = (Circle) o;

    //if the position and radius are the same then 
    return (c.position.equals(this.position) &&  c.radius == this.radius);
  }

  @Override
  public int hashCode()
  {  // implement this method and explain your implementation
    int result =17;
    //hash the position
    result = 31 * result + position.hashCode();
    //convert to long thenn combine high order and low order bits
    long temp = Double.doubleToLongBits(radius);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public double area()
  {  // implement this method 
    //area of a circle is pi * r^2
    return Math.PI * radius * radius;
  }

  @Override
  public String toString()
  {  // implement this method and explain your implementation
    //return the position and radius
    return "Shape: Circle, Position: " + position.toString() + ", Radius: " + radius + ", Area: " + area();
  }
} // end of class Circle

