
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2023, Assignment 5 
   Student Name:
   UIN:
   Acknowledgements:
*/

class TotalAreaCalculator {
  public static double calculate(Shape[] shapes) {
    double totalArea = 0;
  // for each shape in the shapes array,   
  // invoke the object's area() method,
  // summing up the areas
  // and finally returns the total area
    for(Shape s : shapes){
      totalArea += s.area();
    }
    return totalArea;
  }
}

