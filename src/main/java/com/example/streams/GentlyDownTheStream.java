package com.example.streams;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Enhanced coding kata on the Stream API with exception handling, generics, and advanced concepts.
 * All methods include proper validation and can be completed with a single return statement plus validation.
 */
public class GentlyDownTheStream {

    protected List<String> fruits;
    protected List<String> veggies;
    protected List<Integer> integerValues;

    public GentlyDownTheStream() {
        fruits = Arrays.asList("Apple", "Orange", "Banana", "Pear", "Peach", "Tomato");
        veggies = Arrays.asList("Corn", "Potato", "Carrot", "Pea", "Tomato");
        integerValues = new Random().ints(0, 1001)
                .boxed()
                .limit(1000)
                .collect(Collectors.toList());
    }

    /**
     * Example method showing proper exception handling and validation
     * Returns a sorted list of fruits with comprehensive error checking
     */
    public List<String> sortedFruits() throws InvalidDataException {
        try {
            validateCollection(fruits, "Fruits collection");

            return fruits.stream()
                    .filter(Objects::nonNull) // Handle potential null elements
                    .sorted()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new InvalidDataException("Failed to sort fruits: " + e.getMessage());
        }
    }

    /**
     * Enhanced version with custom predicate and exception handling
     */
    public List<String> sortedFruitsException() throws InvalidDataException {
        return sortedFruitsWithFilter(fruit -> !fruit.startsWith("A"));
    }

    // TODO - return a list with the first 2 elements of a sorted list of fruits
    // Add proper validation and exception handling
    public List<String> sortedFruitsFirstTwo() throws InvalidDataException {
        // Implement with validation, null checks, and exception handling
        return null;
    }

    // TODO - return a comma separated String of sorted fruits
    // Handle null values and empty results gracefully
    public String commaSeparatedListOfFruits() throws InvalidDataException {
        // Implement with proper string joining and validation
        return null;
    }

    // TODO - return a list of veggies sorted in reverse (descending) order
    // Use Comparator.reverseOrder() and handle edge cases
    public List<String> reverseSortedVeggies() throws InvalidDataException {
        return null;
    }

    // TODO - return a list of veggies sorted in reverse order, all in upper case
    // Chain multiple stream operations with proper exception handling
    public List<String> reverseSortedVeggiesInUpperCase() throws InvalidDataException {
        return null;
    }

    // TODO - return a list of the top 10 values in the list of random integers
    // Handle cases where list has fewer than 10 elements
    public List<Integer> topTen() throws InvalidDataException {
        return null;
    }

    // TODO - return a list of the top 10 unique values in the list of random integers
    // Use distinct() operation and handle empty results
    public List<Integer> topTenUnique() throws InvalidDataException {
        return null;
    }

    // TODO - return a list of the top 10 unique values that are odd
    // Combine filtering, distinct, and limiting operations
    public List<Integer> topTenUniqueOdd() throws InvalidDataException {
        return null;
    }

    // TODO - return the average of all random numbers
    // Handle potential OptionalDouble and division by zero scenarios
    public Double average() throws InvalidDataException {
        return null;
    }

    // Generic method for safe collection operations
    private <T> void validateCollection(Collection<T> collection, String collectionName) throws EmptyCollectionException {
        if (collection == null) {
            throw new IllegalArgumentException(collectionName + " cannot be null");
        }
        if (collection.isEmpty()) {
            throw new EmptyCollectionException(collectionName + " cannot be empty");
        }
    }

    // Helper method demonstrating advanced generics and functional programming
    private <T> List<T> sortedWithFilter(Collection<T> collection,
                                         Predicate<T> filter,
                                         Comparator<T> comparator) throws InvalidDataException {
        try {
            validateCollection(collection, "Input collection");

            return collection.stream()
                    .filter(Objects::nonNull)
                    .filter(filter)
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new InvalidDataException("Failed to sort and filter collection: " + e.getMessage());
        }
    }

    // Specialized method using the generic helper
    private List<String> sortedFruitsWithFilter(Predicate<String> filter) throws InvalidDataException {
        return sortedWithFilter(fruits, filter, String::compareTo);
    }

    // Utility method for safe integer operations
    private OptionalDouble safeAverage(Collection<Integer> numbers) {
        return numbers.stream()
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .average();
    }
}