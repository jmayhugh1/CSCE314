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
## contravariant method parameter type

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


