# Java Concurrent Collections Demos

This repository contains demonstrations of various concurrent collections in Java, showcasing their usage in multi-threaded environments.

## Demos

### 1. BlockingQueueDemo

Demonstrates the usage of `ArrayBlockingQueue`, an implementation of [`BlockingQueue`](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/BlockingQueue.html).

- Illustrates a producer-consumer scenario.
- Shows how the queue automatically blocks when full (producer) or empty (consumer).
- Highlights thread-safety and built-in flow control.

### 2. SimpleConcurrentHashMapDemo

Showcases the [`ConcurrentHashMap`](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ConcurrentHashMap.html) class, a thread-safe variant of `HashMap`.

- Demonstrates concurrent read and write operations.
- Shows how multiple threads can safely access and modify the map simultaneously.

### 3. SimpleCopyOnWriteArrayListDemo

Illustrates the use of [`CopyOnWriteArrayList`](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/CopyOnWriteArrayList.html), a thread-safe variant of [`ArrayList`](https://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html).

- Demonstrates how write operations create a new copy of the underlying array.
- Shows how read operations can happen concurrently without synchronization.

## Key Features

- **Thread Safety**: All demos showcase collections that are safe for use in concurrent environments.
- **Performance**: Each collection is optimized for different use cases in multi-threaded scenarios.
- **Ease of Use**: These collections simplify concurrent programming by handling synchronization internally.

## Running the Demos

To run each demo:

1. Compile the Java file:
2. Run the compiled class:


## Notes

- The `BlockingQueueDemo` uses `ArrayBlockingQueue` with a capacity of 5 elements.
- The `SimpleConcurrentHashMapDemo` shows simultaneous read and write operations on a shared map.
- The `SimpleCopyOnWriteArrayListDemo` demonstrates how writes create a new copy while reads can occur without locking.

These demos are designed to illustrate basic concepts and are not optimized for production use.

