
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2023, Assignment 5 
    Student Name: Joshua Mayhugh
   UIN: 431004527
   Acknowledgements: 
*/

abstract class Shape implements Comparable<Shape> {
  public Point position;
  public double area;
    
  // constructor that sets position as the Point passed as an argument
  // signature: Shape (Point)
  // implement the constructor
  public Shape(Point p0){
    position = p0;
  }

  // implement equals()
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
    //if its not an instance of shape then it cant be equal
    if(!(o instanceof Shape)){
      return false;
    }
    //cast it to a shape
    Shape s = (Shape) o;
    //if the position and area are the same then they are equal
    return (s.position.equals(this.position) && s.area == this.area);

  }

  // area() should be abstract
  public abstract double area();
 
  // implement compareTo()
		@Override
  public int compareTo(Shape s)
  {  // implement this method and explain your implementation
    //if the area is the same then compare the x values of q and s
    if(this.area ==  s.area){
      return (int) (this.position.x - s.position.x);
    }
    else if(this.area == s.area && this.position.x == s.position.x){
      return (int) (this.position.y - s.position.y);
    }
    //if the area is different then compare the area
    return (int) (this.area - s.area );
    

  }
} // end of class Shape


