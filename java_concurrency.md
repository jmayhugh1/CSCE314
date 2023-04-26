# Concurrent programs
    - more than one activities execute
    simultaneously (concurrently)
    - no interference between activities, unless
    specially programmed to communicate
A big portion of software we use is concurrent
    - OS: IO, user interaction, many processes, . . .
    - Web browser, mail client, mail server, . . .
    - Think about the Internet!
    
---------------------------------------------------------


# Why should we care?
    • Several application areas necessitate
    concurrent software
    • Concurrency can help in software
    construction:
    • organize programs into independent parts
    • Concurrent programs can run faster on
    parallel machines
    • Concurrent programs promote throughput
    computing on CMT/CMP machines
    3
---------------------------------------------------------


## Language Support for Concurrency
How languages provide means to program concurrent
programs varies:
• C, C++: concurrency features not part of the
language, but rather provided in (standard) libraries
• In Java, concurrency features partially part of the
language and partially defined in its standard
libraries (Java concurrency API)
• In Erlang, Oz, threads, futures, etc. integral part of
the language
Focus: mechanics of Java’s low level concurrency
feature - threads
6

---------------------------------------------------------

## Threads
• Thread is an independently executed unit of a
program
• The JVM takes care of scheduling threads,
typically each active thread gets a small amount
of processing time in its turn, with rapid switching
between threads
• In other words: Programmer does not control how
much of which thread gets executed when
(preemptive scheduling)
• In a system with more than one processing units,
threads may execute in parallel

---------------------------------------------------------


## process vs threads

### process
1. self-contained execution
environment
2. own memory space, such as JVM or runtime memory
3. one Java application is
typically associated with
one process (but not
always)
### Thread
1. at least one per process, once you start runnong program main method starts a new thread
2. shares resources with other
threads in the process,
including memory, open files
3. every Java program starts
with one thread (+ some
system threads for GC etc.)
4. concurrency is attained by
starting new threads from the
main thread (recursively)
---------------------------------------------------------


## Running Threads

```java

public interface Runnable {
void run();
}
public class MyRunnable implements Runnable {
public void run() {
// task here . . .
}
}
// in main()
Runnable r = new MyRunnable();
Thread t = new Thread(r);
t.start();
```

---------------------------------------------------------

## extending thread 

Task for a thread can be specified also in a subclass of Thread

```java
public class MyThread extends Thread {
public void run() { . . . // task here
}
}
Thread t = new MyThread();
t.start();
```

Benefits of using Runnable instead:
• It does not identify a task (that can be executed in parallel) with a
thread object
• Since Runnable is an interface, the class implements Runnable
could extend another class
• Thread object typically bound with the OS’s thread
• Many runnables can be executed in a single thread for better
efficiency, e.g., with thread pools

---------------------------------------------------------
runnable is much more light, than fullblown thread class
- since a class can extend only onw class, but can implement many interfaces you ca have a class that implements runnable and extends another class
- by seperating runnables from operating system threads you can have many runnables executed in a single thread for better efficiency, e.g., with thread pools

---------------------------------------------------------
## Examples
GreetingRunnable.java, PingPong.java
Key points of the examples:
• In presence of side-effects, different interleavings of tasks
may produce different results
• A situation where the result of a computation may vary
based on the order of the execution of tasks of the
computation is called a race condition (or race hazard).
• A race hazard exists when two threads can potentially modify the
same piece of data in an interleaved way that can corrupt data.
• One of the sources of difficulty of concurrent programming
• Absence of side-effects means that race conditions cannot
occur (makes “purity” of a language a desirable property)
---------------------------------------------------------
the examples are harmless becaue it doesnt modify variables and doesnt have any side effects


## Aside: Thread Pools
• Thread pools launch a fixed number of OS threads and
keep them alive
• Runnable objects executed in a thread pool executes in
one of those threads (in the first idle one)
• Thread pools commonly used to improve efficiency of
various server applications (web servers, data base
engines, etc.)

```java
GreetingRunnable r1 = new GreetingRunnable("Hi!");
GreetingRunnable r2 = new GreetingRunnable("Bye!");
ExecutorService pool = Executors.newFixedThreadPool(MAX_THREADS);
pool.execute(r1);
pool.execute(r2);
```

---------------------------------------------------------

one of the benefits of having a class implement runnable interface is that by seoerating the runnables from a bpool of threads the remaining OS threads can be used for other tasks

threads take a lot of memory and causes heavy tolls on the JVM if it is heavily threaded.

in a multithreaded sub application you can keep a pool of ac certain numner of threads and when clients put in a request it will put them in a queue and the threads will take them out of the queue and execute them

the thread pool needs to be declared somwhere in a program.

the maximum number of threads is determined by the operating system

---------------------------------------------------------

## stopping threads

• Threads stop when the run method returns
• They can also be stopped via interrupting them
E.g., new HTTP GET request on a web server, while several threads are still
processing the previous request from the same client
• Call to the interrupt() method of a thread sets the interrupted flag of
the thread (Examining the flag with Thread.interrupted() clears it)
• Thread itself decides how to (and whether it should) stop - typically
stopping is preceded by a clean-up (releasing resources etc.)
• Convention: entire body of run method protected by try-catch
• Note: Thread.stop(), Thread.suspend() and Thread.resume() are
deprecated as too dangerous

---------------------------------------------------------

the isalive method is used to check if a thread is still running

a thread is alive until it is terminates

a thread can terminate in 3 ways

1. the run method returns normally, best case scenario where cleanup is performed and resources are released

2. the run method completes abruptile by interrupting the thread. this is the worst case scenario where the thread is terminated abruptly and resources are not released, cathing the exception is not enough to release the resources

3. application terminates

deadlocks can result in frozen processes




## Thread States

A thread can be in one of the following states:
• new: just created, not yet started
• runnable: after invoking start(). Not scheduled to run yet
• running: executing, not an explicit state defined
• blocked: waiting for a resource, sleeping for some set
period of time. When condition met, returns back to
runnable state
• dead: after return of run method. Cannot be restarted.
15

---------------------------------------------------------

## Thread Safety
• Some software element is thread-safe if it is
guaranteed to exhibit correct behavior while executed
concurrently by more than one thread
• A definition geared towards OO, and the ideology of
design of Java concurrency features:
• Fields of an object or class always maintain a valid
state (class invariant), as observed by other
objects and classes, even when used concurrently
by multiple threads.
• Postconditions of methods are always satisfied for
valid preconditions.

---------------------------------------------------------
[picture 1](images/c44f35ad0f84aea65eec2eefb8b3602e57e5328b101d59f75590fa3dd084d88a.png) 
# synchronization

want concurrent multithreaded program to be thread state

## synchonizarion with locks

• Lock object guards a shared resource
• Commonly a lock object is an instance variable of a
class that needs to modify a shared resource:

```java
public class BankAccount {
public BankAccount() {
balanceChangeLock = new ReentrantLock();
. . .
}
. . .
private Lock balanceChangeLock;
}
```

---------------------------------------------------------
the parts where thread interference can occur is called critical section

in the bank account example where you acess and modify balance it is a critical section

lock objects are used to protect critical sections by restricting access to them, only the thread that owns the lock can access the critical section

common practice is to create a lock object for each part of the code that needs to be protected
## Example
    
```java
public void deposit(double amount) {
balanceChangeLock.lock() //any thread that wants to access this critical section must first acquire the lock by calling lock()
try {
System.out.println("Depositing " + amount);
double nb = balance + amount;
System.out.println("New balance is " + nb);
balance = nb;
} finally {
balanceChangeLock.unlock(); //after the critical section is done the thread must release the lock by calling unlock()

}
}

//better version
balanceChangeLock.lock();
try {
    //make changes to balance
} finally {
    balanceChangeLock.unlock();
}
```


Above could be improved - critical sections should be
as short as possible.

---------------------------------------------------------

## Lock Ownership
Thread owns the lock after calling lock(), if another
thread does not own it already
• If lock owned by another thread, scheduler
deactivates thread that tries to lock, and
reactivates periodically to see if lock not owned
anymore
• Ownership lasts until unlock() called
• “Reentrant” lock means the thread owning a lock
can lock again (e.g., calling another method using
the same lock to protect its critical section)

---------------------------------------------------------
ownership of a lock lasts from the invocation of lock() to the invocation of unlock() reenetrant means that the thread that owns the lock can lock again

## Per Method SYnchronization

• Java ties locks and synchronization: object locks and
synchronized methods
• The granularity may not always be desirable. Example:
```java
public class BankAccount {
public synchronized void deposit(double amount) {
System.out.println("Depositing " + amount);
double nb = balance + amount;
System.out.println("New balance is " + nb);
balance = nb;
}
public synchronized void withdraw(double amount) { . . . }
}
//Synchronized methods automatically wraps the body into
lock; try {body} finally { unlock }
```
---------------------------------------------------------
synchonized methods xan be thought of as a wrapper around the body of the method that acquires the lock and releases it after the method is done

## Deadlock
Our bank account allows overdraft. Attempts to remedy:
if (account.getBalance() >= amount) account.withdraw(amount);

• Does not work, thread may be preempted between test of balance
and withdrawing

• Next attempt
```java
public void withdraw(double amount) {
balanceChangeLock.lock();
try {
while (balance < amount) {} // wait balance to grow
double nb = balance - amount;
balance = nb;
} finally {
balanceChangeLock.unlock();
}
}
```

---------------------------------------------------------
the while loop keeps the thread from releasing the lock and the thread will never be able to acquire the lock again

here no thread can make any progress

## condition Objects

Condition object allows a temporary release of a lock
```java
public class BankAccount {
public BankAccount() {
balance = 0;
balanceChangeLock = new ReentrantLock();
sufficientFundsCondition = balanceChangeLock.newCondition();
}
public void withdraw(double amount) {
balanceChangeLock.lock()
try {
while (balance < amount) sufficientFundsCondition.await();
. . .
} finally { balanceChangeLock.unlock(); }
}
private Lock balanceChangeLock;
private Condition sufficientFundsCondition;
}
```
Current thread unblocked by a call to signalAll(), a notification to all threads
blocked with a particular condition object

---------------------------------------------------------

## signalAll()
```java
public void deposit(double amount) {
balanceChangeLock.lock();
try {
. . .
sufficientFundsCondition.signalAll();
} finally { balanceChangeLock.unlock(); }
}
```
• Notification with signalAll() means: something has
changed, it is worthwhile to check if it can proceed
• signalAll() must be called while owning the lock
bound to the condition object

## Memory Consistency Errors

Processors have various levels of caches: different
processors may have a different view of memory at a given
time
This may lead to an absurd situation:
```java
int x = 0; // shared between threads A and B
x++; // A does this
System.out.println(x); // Afterwards, B observes x
// x can be either 0 or 1
```
Java memory model defines the set of allowed values that
can be returned when a variable is read.

## Java Memory Model (1)
Program order is the order the statements are put in a program.
Basic synchronization actions:
• Monitor lock, synchronized methods and blocks, or volatile variables (see below)
• Yields a synchronization order.
• Synchronization order is always consistent with the program order.
• Establish a happens-before relationship, which determines what values can be
returned by a read of a variable.
Volatile variables with the volatile modifier.
• A write to a volatile variable synchronizes with all subsequent reads of that variable.
• Common use of volatile: a flag to indicate something happened, or lock-free code
together with atomic variable.
From earlier example:
```java
volatile int x = 0; // shared between threads A and B
x++; // A does this
System.out.println(x); // Afterwards, B observes x; it can only be 1
```
## Java Memory Model (2)
A multiprocessor system is sequentially consistent if the result of any execution is
the same as if the operations of all the processors were executed in some
sequential order, and the operations of each individual processor appear in this
sequence in the order specified by its program.
Java (or C++) memory model does not guarantee sequential consistency
Program order only respected within a thread.
Special rules by “Event A happens-before event B” relation are the
guarantees of ordering of events between threads:
• Action A followed by action B in the same thread,
• actions before start of a thread happen before actions in the thread,
• unlock/lock,
• write of volatile field happens-before subsequent reads

## summary so Far

Concurrent programming is very difficult when mutable state is
in place
• A ton of idioms, “best practices”, but still many problems
• Does not easily scale beyond a few collaborating threads
• In particular, lock-based programs do not compose
E.g., assume a container class that has thread-safe insert and delete. It is not
possible to write a thread-safe “delete item x from container c1 and add it to
container c2” (see Harris et al.: “Composable Memory Transactions”)
• We do not yet know how to best program concurrent programs
• Several alternative approaches to lock-based concurrency
exist
• Next: brief look at non-blocking concurrency and software
transactional memory