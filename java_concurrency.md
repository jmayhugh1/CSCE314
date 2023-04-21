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
2. own memory space
3. one Java application is
typically associated with
one process (but not
always)
### Thread
1. at least one per process
2. shares resources with other
threads in the process,
including memory, open files
3. every Java program starts
with one thread (+ some
system threads for GC etc.)
4. concurrency is attained by
starting new threads from the
main thread (recursively)

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

## Thread States

A thread can be in one of the following states:
• new: just created, not yet started
• runnable: after invoking start(). Not scheduled to run yet
• running: executing
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

## Example
    
    ```java
public void deposit(double amount) {
balanceChangeLock.lock()
try {
System.out.println("Depositing " + amount);
double nb = balance + amount;
System.out.println("New balance is " + nb);
balance = nb;
} finally {
balanceChangeLock.unlock();
}
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