# Java virtual machine

* JVM is stack based abstract machine
* some concepts and vocab carry on from java to JVM
* programs are a collection of class definitions and instructions
* A java compiler compiles a java program into a set of class files that can be understood by the JVM
* a class file contains JVM instructions  (bytecode) and a symbol table  when a JVM reads and executes a class file, it loads the class file into memory and executes the instructions

* even though JVM was designed for java, it can be used to run other languages

## Role of the JVM

* loads and verifies the class file and executes the bytecode they contain
* the JVM is responsible for memory management

## JVM specification

1. A set of instructions and a defintion of the meaning of those instructions called bytecodes
2. a binary format called class file format to convey bytecodes and other information 
3. an algorithm for indentifying and programs that cannot compromise the integrity of the JVM, which is called $ {verification}$

## JVM architecture

* JVM is a stack based abstract machine with special instructions for manipulating objects

* bytecode are stored in a binary format called class file format

* need special programs to display files in a human readable format

Example of a class file:

```java
iconst_2 //push 2 onto the stack
istore_1 //store the value at the top of the stack into local variable 1
iaddd //add the top two values on the stack and push the result onto the stack
```

## Representation of Memory

Typical CPU instruction set views memory as a linear array of bytes
* construct object allocate a contiguous block of memory
* access fields of an object by computing the address of the field from the address of the object
* call a method by computing the address of the method from the address of the class

JVM allows no-byte level access
* prevents corruption of memory state


![picture 1](../images/524c24bf7e362202828642ca846965cfaaf65ae8ae0c65c72487f9afd153abcd.png)  

##  the main loop

* the main loop of the JVM is called the $ {interpreter}$

do {
    fetch the next instruction using the pc and fetch opcode at pc
    if (operands) {
        fetch operands
    }
    execute the instruction at opcode
} while (there are more instructions to execute)


## Class loader

* the class loader is responsible for loading class files into memory
* the class loader is responsible for linking the class files into a single unit called a $ {runtime data area}$
* different subclasses of the class loader are responsible for loading classes from different sources

## the verifier

* endure that certai parts of the machine aer left untouched
* the verifier ensures that the bytecode is valid and that the class file is safe to execute
* programs can try to subvert the security of the JVM  in a variety of ways
* the verifier ensures that the bytecode is valid and that the class file is safe to execute
    - might try to overflow the stack and reach memory that is not supposed to be accessed
    - might try to cast an object to a type that it is not inorder to access private fields
* verfiier traces through code and assures object are not cast to the wrong type    

## internal Architecture of the JVM

there are 5 runtime data areas

1. the ${method area}$ contains the class information, code and constant pool
    - contains the class information, code and constant pool
    - one method area per JVM instance
    - the method area is shared by all threads
    - there is one thread access at a time
2. the ${heap}$ contains all the objects, when you create an array or object using new, object appears in the heap
    - contains all the objects, when you create an array or object using new, object appears in the heap
    - one heap per JVM instance
    - facilitates garbage collection
    - the heap is shared by all threads
    - expansion and contraction of the heap is done by the garbage collector

    Object reperesentation in the heap
    ![picture 2](../images/314c0755f52b9b7e1796b4ac05fca9b0085777c8f3c3b57a75924149c3f2792b.png)  

3. the ${stack}$ contains stackframes where activiation records are stored
    - Each thread has its own stack
    - contains frames: current thread's state
    - pushing and popping frames is done by the JVM

    Stack frame
    ![picture 3](../images/a35c8f6402911054f4eaf47622ebbd6aab908bda3a01d94981a15ac8afbe2b99.png)

    Each stack frame has
    local variables
        - local variables are stored in the local variable array
        - accessed by index of array
    Operand stack
        - organized as an array
        - accessed by pushing and popping
        - always on top of the stack frame
        - work space for operations
        - work space for operations
    Frame data
        - constant pool resovler: dynamic binding
        - normal method return
        - exception handler
        - return values to previous frame
![picture 4](../images/ca0e856c438a3c3f06983fb2a50712e749fca5245d1b674cbd0249136924c785.png)  



4. the ${pc register}$ collection of registers, one per thread each thread program counter is stored in the pc register, program counter is the address of the next instruction to be executed
5. the ${native method stack}$ support methods written in a different language


