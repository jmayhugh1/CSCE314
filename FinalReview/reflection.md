# Reflection

## Reflection and Meta-Programming
- writing programs that manipulate other programs
- reflection: writing programs that manipulate themselves

## uses of reflection
- configuring programs for specific deployment enviornment
- writing debuggers, class browsers, GUI tools
- optimizing programs fro specific inputs
- analyzing running systems
- extending running systems
- copmilers, iternpreters, and other language tools

## defining reflection
- reflection is the ability of a program to examine and modify the structure and behavior of itself at runtime
- reflection is teh ability of a program to manipulate as data something representing the state of the program during its execution
- there are twi aspects of manipulation
    - introspection: the ability to observe the structure and behavior of a program
    - intercession: the ability to modify the structure and behavior of a program

## language support for reflection
- Metaobjects: objects that represent methods, execution stacks, processor
- ExtremeL Dynamic langugaes such as smalltalk and CLOD
    - langugae defintion are largely just librarires
    - smalltalk
        - class are objects and can be manipulated at runtime

        - Class objects are objects as well, and thus can be manipulated, for example,to modify the properties of Smalltalk’s object model
        - Regular Smalltalk code can access and modify metaobjects
- intermediate: Java, C#
    - Java Reflection API: discover methods and attributes at runtime, create objects of classes whose names discovered only at run time
    - Mostly for introspection, no runtime modification of class’s metaobjects
    - Low: C++ Runtime type identification (RTTI), simple introspection

Why some tasks are delayed until runtime? and why refelction is useful
    -the later in an applications life-cycle the more info is availabe for adapting the program

## Reflection in Java
javas refelction capibilities are offered as a library API

java.lang.Class
java.lang.reflect

Capabilities:
- knowing a name of a class, load the class and create an object
- examine methods fields constructors annotations etc
- invoke constructors methods
- access fields
- create instances of interfaces dynamically

## obtaining class objects

The entry point to reflection operations: java.lang.Class
Various means to obtain an object of Class type
1. From instance (using Object.getClass()):
```java
MyClass mc = new MyClass();
Class c = mc.getClass();
```
2. From type:
```java
c = boolean.class;
```
3. By name:
```java
Class cls = null;
try {
cls = Class.forName("MyClass");
} catch (ClassNotFoundException e) {
. . .
}
// use cls
```

- Class.getSuperclass()
- Class.getClasses()
    - returns an array of all the public classes and
interfaces that are members of the class of this Class
object. Includes inherited members. 
Example:
```java
Class<?>[] c = Character.class.getClasses();
```
- Class.getDeclaredClasses()
    - similar to getClasses() but includes non-public classes
and does not recur to base classes
- Class.getEnclosingClass()
- immediately enclosing class

## class Modifiers
- Javas class modifiers
    - publiv(1), private(2), protected(4), 
    - abstract(1024), static(8), final(16),

example:
```java
class public static MyClass { . . . }
. . .
int mods = MyClass.class.getModifiers();
if (Modifier.isPublic(mods))
System.out.println("public class");

```

## accessing members

Same set of methods for fields, methods, and constructors.
Let X be one of Field, Method, or Constructor:
    - X Class.getDeclaredX(String)
    - X Class.getX(String)
    - X[] Class.getDeclaredXs()
    - X[] Class.getXs()
• “Declared” versions obtain private members too
• “non-Declared” versions obtain inherited members
• Method and constructor versions need the parameter types
as parameters too:
```java
Method getDeclaredMethod(String name,
Class<?> . . . parameterTypes)
throws NoSuchMethodException, SecurityException
```
