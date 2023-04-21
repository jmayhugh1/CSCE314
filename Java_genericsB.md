## Type system and variance

within the type system of a programming language, variance refers to the subtyping relationship between two complex types.
- ex: List of Cats vs List of Animals and a func returning cat vs animal

## Covariance and contravariance

- a typing rule of complex types or a type constructor is 
- covariant if it preserves subtyping and ordering of types 
- contravariant if it reverses the subtyping and ordering of types
- invariant if it does not preserve subtyping and ordering of types (neither covariant nor contravariant)


## covariant method return type

Return type of a method is covariant if the return type of the method is a subtype of the return type of the overridden method

```java
class Animal {
    public Animal clone() {
        return new Animal();
    }
}
Class Panda extends Animal {
    public Panda clone() {
        return new Panda();
    }
}
```
- this is safe, when we call Animal's clone methid we get at least an animak back, but we could get an animal subtype

```java
Animal p = new Panda()

Animal a = p.clone() // rwturns a panda
```
## covariant method parameter type

would this be okay?

```java
class Animal {
    public bool compare(Animal a) {
        return true;
    }

}
class Panda extends Animal {
    public bool compare(Panda p) {
        return true;
    }
}
```
covariant paramter types are not safe and thus not supported in mist mainstream languages
 ```java
Animal p = new Panda()
Animal a = new Animal()

p.compare(a) // type error

```

## contravariant Parameter type

contravariant paramter types would be safe but they have not been found useful and are not supported in most mainstream languages

```java
class Animal {
    public bool compare(Panda) {
        return true;
    }

}
class Panda extends Animal {
    public bool compare(Animal a) {
        return true;
    }
}
Animal p = new Panda()
Animal a = new Animal()
p.compare(a) // not useful

``` 
- when overidding method in java: invariant parameter type, covariant return type 

![picture 1](../images/c374f1d45cf6dda44ea84e052e7004ec385f66fb336e2c6b6abf3adb721071c2.png)  


B<T> is a subtype of A<T> and Integer is a subtype of Number

onlt the first is safe because you are invoking the generic tyoe A with type Integer

# another example 

```java 
String <: Object and ArrayList <: List
(1) ArrayList<String> <: List<String> O.K.
(2) ArrayList<String> <: ArrayList<Object> Not O.K.
(3) ArrayList<String> <: List<Object> Not O.K.
```


assinginh list of object with list of string is not safe because you can add a string to a list of object

## Covariance and contravariance of a generic class

Generic class C<T> is covariant with respect to T 
if A <: B implies C<A> <: C<B>
- if A is a subtype of B then C<A> is a subtype of C<B>

Generic class C<T> is contravariant with respect to T 
if A <: B implies C<B> <: C<A>
- if A is a subtype of B then C<B> is a subtype of C<A>


Generic class C<T> is invariant with respect to T 
if C<A> <: C<B> holds only if A = B
- if C<A> is a subtype of C<B> then A and B must be the same type

## Special Case: JAVA arrays
```java
Object[] objA = new String[]{"1", "2", "3"}; // OK
objA[0] = new Integer(1); // throws here

```

First statement OK since Java arrays are (unsafely) covariant:
String <: Object ⇒ String[] <: Object[] 

- since string is a subtype of object, string array is allowed to be a subtype of object array
- if programmer accidentally tries to add an integer to the array where string is stored, it will throw an runtime error
You can think of String[] as Array<String>
From the type checker’s perspective, second OK
Integer <: Object 


However, the second statement is an attempt to assign an 
Integer in place of String. 


This works (very limited tasks can be performed though):
 Object[] objN = new Number[]{(float)(3.0/2), 5, Math.PI};
 for (Object o : objN) out.print(o + “ ”);

