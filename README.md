# Java Threading and Thread Pool Demo

This project demonstrates basic concepts of threading and thread pools in Java, suitable for beginners learning about concurrent programming.

## Overview

The demo includes examples of:
1. Thread creation
2. Thread management
3. Basic synchronization
4. Using a thread pool

The scenario simulates a simple task of counting numbers, which is divided among multiple threads to demonstrate concurrent execution.

## Code Structure

The main components of this demo are:

- `CounterTask`: A Runnable that counts numbers within a given range.
- `Main`: The main class that orchestrates the threading demonstrations.

## Features Demonstrated

### 1. Thread Creation and Management

- Creating threads using both [Thread](https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html) class and [Runnable](https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html) interface.
- Starting and joining threads.

### 2. Synchronization

- Using synchronized methods to ensure thread-safe operations on shared resources.

### 3. Thread Pool

- Utilizing [ExecutorService](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorService.html) to manage a pool of worker threads.
- Submitting tasks to the thread pool and shutting it down properly.

## How to Run

1. Ensure you have Java Development Kit (JDK) installed on your system.
2. Clone this repository to your local machine.
3. Navigate to the project directory in your terminal.
4. Compile the Java files:
5. Run the main class:


## Expected Output

The program will display the results of counting operations performed by different threads and the thread pool. You'll see output demonstrating:

- Individual threads counting numbers
- Synchronized counting across threads
- Thread pool executing multiple counting tasks

## Learning Objectives

This demo is designed to help beginners understand:

- How to create and manage threads in Java
- The basics of thread synchronization
- The concept and usage of thread pools

Feel free to experiment with the code, adjust the number of threads or the counting ranges to see how it affects the execution.

## Contributing

Contributions to improve or expand this demo are welcome. Please feel free to submit pull requests or open issues for discussion.

## License

[MIT License](LICENSE)
