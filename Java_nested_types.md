# Nested classes
## Purposes

-way to group classes that are only used in one place (helper classes)

-allows classes to be structured and scoped into logicallty related groups, increasing readability and maintainability

-encapsulation of implementation details, connect logically related classes together, and provide a namespace for related classes

-Think subtasks that may access main task's private variables
```java
public class OuterClass {
    public class InnerClass {
        // ...
    }
}
```
## static vs non-static nested classes
-Static nested classes are not associated with an instance of the outer class, and do not have access to the outer class's instance variables. They are accessed using the outer class name.

-inner classes: non-static nested classes, have acces to private memeber of the outer class

```java
class Enclosing {
    static class StaticNested { //Enclosing.StaticNested
        private int price=6;
    }
    class Inner { //non-static nested class
        private int price=5;
    }
}
```
### static nested classes
-associated with the outer class

-similar to static class members, cannot refer directly to instance variables or methods defined in the outer class: it can only use them through an object reference

-static nested classes behave like any other top-level class

-to create an instance of the static nested 
class, you must use the name of the outer class, followed by the dot operator, followed by the name of the static nested class
```java
Enclosing.StaticNested nested = new Enclosing.StaticNested();
```
### non-static nested classes(inner classes)

-are associated with an instance of the outer class

-has access to all the members of the outer class, including private members

-cant def a static member in a non-static nested class

-objects that are insrances of an inner class exit within an instance of the outer class and have direct access to the methods and fields of the outer class

-to instantiate an inner class, you must first instantiate the outer class. Then, you can create an object of the inner class by using the object reference of the outer class, followed by the dot operator, followed by the new operator, followed by the name of the inner class, followed by the argument list
```java
Enclosing enclosing = new Enclosing();
Enclosing.Inner inner = enclosing.new Inner();
```

### local inner classes

-are classes that are defined within a block, constructor, or if-then-else statement

-they are not members of the class, and therefore cannot have any access modifiers

-declerations of a type (such as a method parameter or a local variable) in a local classs shadow declerations of in the enclosing scope that hace the same name

-can access local variables and parameters of the enclosing block that are final or effectively final

```java
Class Greetings {...
    interface Hello {
        ...
    }
    public void Hey(){
        class helloGreeter implements Hello {
            ...
        }
    }
}
```
### anonymous inner classes

-if you do not need to reuse a local class, you can declare and instantiate it at the same time

-are defined with the new operator, followed by the name of the class or interface, followed by an argument list, followed by a class body

-declare and instantiate an anonymous inner class in a single statement

-cant extend a class and implement an interface at the same time

-declaring and instantiating are done in a single statement

```java
Class Greetings {...
    interface Hello {
        ...
    }
    public void Hey(){
        Hello helloGreeter = new Hello() {
            ...
        }
    }
}
```

## Nesting in Interfaces

-nested classes and interfaces allow one to associate types that are logically related, to an interface inside an interface

-Use 1: to return multiple values from an interface method

```java
public interface Changeable {
    class Record {
        public Object changer;
        public String changeDesc;
    }
   Record getLastCHange();
}
```

-Use 2: define a partial or complete implementation of an interface

```java

interface SharedData{
    class Data{
        private int value;
        public Data(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }
    }
    Data data = new Data(0);
}
