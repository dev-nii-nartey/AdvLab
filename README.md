# Java Concurrency Examples

This repository contains Java code examples demonstrating various concurrency concepts, including deadlock scenarios and thread interruption. These examples are intended for educational purposes to help developers understand and practice concurrent programming in Java.

## Contents

1. [DeadLock Example](#deadlock-example)
2. [Thread Interruption Example](#thread-interruption-example)

## DeadLock Example

The `ResolveDeadLockTest` class demonstrates a classic deadlock scenario where two threads are competing for shared resources.

### Key Components:

- `ResourceA` and `ResourceB`: Inner classes representing shared resources.
- `block1` and `block2`: Runnable implementations showcasing the deadlock scenario.

### How it works:

1. Two threads are created, each trying to acquire locks on both ResourceA and ResourceB.
2. Thread-1 acquires ResourceB and then tries to acquire ResourceA.
3. Thread-2 acquires ResourceA and then tries to acquire ResourceB.
4. This leads to a deadlock where each thread is waiting for a resource held by the other.

### Running the example:

```java
public static void main(String[] args) {
    ResolveDeadLockTest test = new ResolveDeadLockTest();
    // ... (code to create and start threads)
}
```

## Thread Interruption Example

The `ThreadInterruptDemo` class demonstrates how to handle thread interruption in Java.

### Key Components:

- `ThreadInterruptDemo`: A custom Thread class that checks for interruption.
- `main()`: Method to start and interrupt the thread.

### How it works:

1. The `run()` method continuously checks if the thread has been interrupted.
2. If interrupted, it throws an InterruptedException.
3. The main method starts the thread, waits for 5 seconds, then interrupts it.
4. The thread catches the InterruptedException and performs a graceful shutdown.

### Running the example:

```java
public void main() {
    ThreadInterruptDemo t1 = new ThreadInterruptDemo();
    t1.start();
    // ... (code to sleep and interrupt the thread)
}
```

## Usage

To run these examples:

1. Clone this repository.
2. Open the project in your favorite Java IDE.
3. Run the `main` method in each example class.

## Learning Objectives

- Understand how deadlocks can occur in multi-threaded applications.
- Learn about thread synchronization and resource locking.
- Explore techniques for thread interruption and handling interrupted exceptions.
- Practice graceful shutdown of threads.

## Thread Count

The examples in this repository demonstrate the use of multiple threads:

- DeadLock Example: 3 threads (1 main thread + 2 worker threads)
- Thread Interruption Example: 2 threads (1 main thread + 1 worker thread)

Total: 5 threads across both examples.

## Contributing

Feel free to contribute to this project by submitting pull requests or opening issues for any bugs or improvements.


