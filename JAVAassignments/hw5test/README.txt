Title: Homework 5 README
Name: Joshua Mayhugh
UIN: 431004527
----------
Problem 2
----------
type this first: javac Main.java
then type this: java Main S input.txt

//all the tasks are tested in the main
This should output the following:
First it output everything in input.txt
Then it prints out how many shapes are in the file then how many shapes have been created
Then it prints out every shape with a detailed description.
then it sorts the shapes by area
then it calculates the total area of all the shapes

s ( 4.5, 1.0 ) 10.5
c ( 43, 37.5 ) 9
s ( 6.5, 11.1 ) 25
c ( 30, 50 ) 3
c ( 50, 50 ) 4.1
s ( 30, 60 ) 12
s ( 73.5, 35 ) 16
c ( 16.5, 10.5 ) 52
s ( 16.5, 10.5 ) 52
c ( 6.5, 11.1 ) 25
s ( 26.5, 41.1 ) 15
The input file contains 11 shapes.
11 shapes have been created

//for task 4 this is the toString
Shape: Square,  position = (4.5, 1.0), side = 10.5, area = 110.25
Shape: Circle, Position: (43.0, 37.5), Radius: 9.0, Area: 254.46900494077323
Shape: Square,  position = (6.5, 11.1), side = 25.0, area = 625.0
Shape: Circle, Position: (30.0, 50.0), Radius: 3.0, Area: 28.274333882308138
Shape: Circle, Position: (50.0, 50.0), Radius: 4.1, Area: 52.81017250684441
Shape: Square,  position = (73.5, 35.0), side = 16.0, area = 256.0
Shape: Circle, Position: (16.5, 10.5), Radius: 52.0, Area: 8494.8665353068       
Shape: Square,  position = (16.5, 10.5), side = 52.0, area = 2704.0
Shape: Circle, Position: (6.5, 11.1), Radius: 25.0, Area: 1963.4954084936207     
Shape: Square,  position = (26.5, 41.1), side = 15.0, area = 225.0

//for task 6 this is the sorted by area

The shapes sorted by area are:
Shape: Circle, Position: (30.0, 50.0), Radius: 3.0, Area: 28.274333882308138     
Shape: Circle, Position: (50.0, 50.0), Radius: 4.1, Area: 52.81017250684441      
Shape: Square,  position = (4.5, 1.0), side = 10.5, area = 110.25
Shape: Square,  position = (30.0, 60.0), side = 12.0, area = 144.0
Shape: Square,  position = (26.5, 41.1), side = 15.0, area = 225.0
Shape: Circle, Position: (43.0, 37.5), Radius: 9.0, Area: 254.46900494077323     
Shape: Square,  position = (73.5, 35.0), side = 16.0, area = 256.0
Shape: Square,  position = (6.5, 11.1), side = 25.0, area = 625.0
Shape: Circle, Position: (6.5, 11.1), Radius: 25.0, Area: 1963.4954084936207     
Shape: Square,  position = (16.5, 10.5), side = 52.0, area = 2704.0
Shape: Circle, Position: (16.5, 10.5), Radius: 52.0, Area: 8494.8665353068       
//this is for task 5 the total shape area calc 
The total area of  shapes is: 14858.165455130347


----------
Problem 3
----------
type in javac CellTest.java
then type in java CellTest
then it should output this

===
//task 3 is done here
//print function and constructor tested here
1 22 21 12 24 17 
sum of intlist is 97 //int_sum tested here 
sum of null list is 0
===
===
1.0 16.0 13.72 5.0 22.0 7.1
sum ints = 97.0  //num_sum tested here
sum doubles = 64.82
===



----------
Problem EC
-----------

type in javac CellListTest.java
then type java CellListTest 

//tests constructor with empty list and with Lists and with clone

stringlist = [(head: A) -> (the) -> (the) -> (dove)]
stringlist2 = [(head: A) -> (dove) -> (the) -> (the)]
stringlist3 = [(head: A) -> (dove) -> (dove) -> (the)]

//test .equals method


stringlist equals to stringlist2 ? true
stringlist equals to stringlist3 ? false
CellList<Integer> equals to CellList<String> ? false
list  = [(head: 1) -> (2) -> (3) -> (4)]
list1 = [(head: 2) -> (4) -> (3) -> (1)]

//tests the ==


list == list1 is false
list.equals(list1) = true
list3 = [(head: 1) -> (2) -> (3) -> (1)]
list4 = [(head: 1) -> (2) -> (3) -> (1) -> (4)]

//tests compareTo and equals

list1.equals(list3) = false
list1.equals(list4) = false
list.compareTo(list1) = 0
list.compareTo(list4) = -1

//test toString

[(head: )]
[(head: 1) -> (2) -> (3) -> (4)]
1
[(head: 22) -> (21) -> (2) -> (3) -> (4)]
22
[(head: 22) -> (21) -> (2) -> (3) -> (4)]
22 22
21 21
2 2
3 3
4 4
[(head: )]
list1 = [(head: 2) -> (4) -> (3) -> (1)]
list2 = [(head: 4) -> (3) -> (2) -> (21) -> (22) -> (1) -> (2) -> (3) -> (4)]    
list2.compareTo(list1) = 1
=== end of test
