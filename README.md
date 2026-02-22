# ? Dual-Pivot QuickSort — JVM-Style Optimized Implementation

## ?? Inspired by Java’s Internal `Arrays.sort()` for Primitive Types

This repository contains a highly optimized implementation of **Dual-Pivot QuickSort**, inspired by the algorithm used internally by the Java Virtual Machine (since Java 7) for sorting primitive arrays.

Dual-Pivot QuickSort is a performance-engineered evolution of classical QuickSort, designed to improve real-world efficiency through smarter partitioning and reduced overhead.

This implementation focuses not only on correctness, but on performance-aware design.

---

## ?? Why Dual Pivot?

Unlike traditional QuickSort (which uses a single pivot), Dual-Pivot QuickSort:

- Uses two pivots
- Splits the array into three regions in one pass
- Reduces unnecessary swaps
- Improves cache efficiency
- Performs better in many real-world scenarios

It is the strategy adopted by the JDK for primitive array sorting.

---

## ?? Optimization Techniques Implemented

This is not a basic academic implementation. It includes:

- Dual-pivot partitioning
- Insertion sort fallback for small subarrays
- Sampling-based pivot selection
- Tail recursion reduction (stack optimization)
- Recursion depth tracking
- Comparison counting
- Stack space estimation
- Clean partition logic with reduced overhead

These are the same categories of optimizations found in production-grade sorting systems.

---

## ?? Algorithm Complexity

- Best Case: O(n log n)
- Average Case: O(n log n)
- Worst Case: O(n²)
- Space Complexity: O(log n) due to recursion

---

## ?? Engineering Highlights

- Smaller partitions are processed first to minimize stack growth.
- Insertion sort is used for small ranges to exploit CPU cache locality.
- Pivot sampling reduces probability of unbalanced partitions.
- Recursion depth is tracked to estimate memory behavior.

This project demonstrates how theoretical algorithms are refined into high-performance implementations.

---

## ?? How To Compile

```
javac DualPivotQuickSort.java
```

## ? How To Run

```
java DualPivotQuickSort
```

---

## ?? Purpose

This project is intended for:

- Algorithm study and analysis
- Performance benchmarking
- Understanding JVM-level sorting strategies
- Interview preparation
- Systems and performance engineering exploration

---

## ?? Technical Note

Java’s `Arrays.sort()` uses Dual-Pivot QuickSort for primitive arrays and TimSort for object arrays.

This implementation follows the same dual-pivot philosophy and incorporates similar optimization principles for academic and analytical purposes.

---

## ?? Final Words

Sorting is simple.

Optimized sorting is engineering.

This implementation reflects the difference.