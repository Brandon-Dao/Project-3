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

    // - return a list with the first 2 elements of a sorted list of fruits -- done
    // Add proper validation and exception handling
    public List<String> sortedFruitsFirstTwo(List<String> fruits) throws InvalidDataException {
        // Validate input
        if (fruits == null) {
            throw new InvalidDataException("Fruits list cannot be null");
        }

        // Filter out invalid entries
        List<String> validFruits = fruits.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        // Validate we have data after filtering
        if (validFruits.isEmpty()) {
            throw new InvalidDataException("No valid fruit entries found");
        }

        // Sort and return first two (or fewer if less than 2 valid entries)
        return validFruits.stream()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .limit(2)
                .collect(Collectors.toUnmodifiableList()); // Immutable result
    }

    //  - return a comma separated String of sorted fruits -- done
    // Handle null values and empty results gracefully
    public String commaSeparatedListOfFruits(List<String> fruits) throws InvalidDataException {
        // Validate input
        if (fruits == null) {
            throw new InvalidDataException("Fruits list cannot be null");
        }

        // Filter invalid entries and normalize
        List<String> validFruits = fruits.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .distinct() // Remove duplicates
                .collect(Collectors.toList());

        // Return empty string for no valid data (graceful)
        if (validFruits.isEmpty()) {
            return "";
        }

        // Sort case-insensitively and join with comma-space for readability
        return validFruits.stream()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.joining(", "));
    }

    //  - return a list of veggies sorted in reverse (descending) order -- done
    // Use Comparator.reverseOrder() and handle edge cases
    public List<String> reverseSortedVeggies(List<String> veggies) throws InvalidDataException {
        // Validate input
        if (veggies == null) {
            throw new InvalidDataException("Veggies list cannot be null");
        }

        // Handle empty list gracefully
        if (veggies.isEmpty()) {
            return Collections.emptyList();
        }

        // Filter and normalize
        List<String> validVeggies = veggies.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .distinct() // Optional: remove duplicates
                .collect(Collectors.toList());

        // Return empty if all data was invalid
        if (validVeggies.isEmpty()) {
            return Collections.emptyList();
        }

        // Sort in reverse order (case-insensitive for natural sorting)
        return validVeggies.stream()
                .sorted(String.CASE_INSENSITIVE_ORDER.reversed())
                .collect(Collectors.toUnmodifiableList());
    }

    //  - return a list of veggies sorted in reverse order, all in upper case -- done
    // Chain multiple stream operations with proper exception handling
    public List<String> reverseSortedVeggiesInUpperCase(List<String> veggies) throws InvalidDataException {
        // Validate input
        if (this.veggies == null) {
            throw new InvalidDataException("Veggies list cannot be null");
        }

        // Handle empty list gracefully
        if (this.veggies.isEmpty()) {
            return Collections.emptyList();
        }

        // Chain operations efficiently
        List<String> result = this.veggies.stream()
                .filter(Objects::nonNull)           // 1. Remove nulls
                .map(String::trim)                  // 2. Trim whitespace
                .filter(s -> !s.isEmpty())          // 3. Remove empty strings
                .map(s -> s.toUpperCase(Locale.ROOT)) // 4. Convert to uppercase (locale-safe)
                .distinct()                         // 5. Remove duplicates (optional)
                .sorted(Comparator.reverseOrder())  // 6. Sort in reverse
                .collect(Collectors.toList());

        // Return empty if all data was filtered out
        return result.isEmpty() ? Collections.emptyList() : result;
    }

     // - return a list of the top 10 values in the list of random integers -- done
    // Handle cases where list has fewer than 10 elements
    public List<Integer> topTen() throws InvalidDataException {
        // Validate input

        if (integerValues == null) {
            throw new InvalidDataException("Random integers list cannot be null");
        }

        // Handle empty list gracefully
        if (integerValues.isEmpty()) {
            return Collections.emptyList();
        }

        // Filter nulls, sort descending, take up to 10
        List<Integer> result = integerValues.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.reverseOrder())
                .limit(10)
                .collect(Collectors.toList());

        // Return result (handles < 10 elements automatically)
        return result;
    }

    //  - return a list of the top 10 unique values in the list of random integers -- done
    // Use distinct() operation and handle empty results
    public List<Integer> topTenUnique() throws InvalidDataException {
        if (integerValues == null) {
            throw new InvalidDataException("Integer values list cannot be null");
        }

        if (integerValues.isEmpty()) {
            return Collections.emptyList();
        }

        // Filter nulls, remove duplicates, sort descending, take top 10
        return integerValues.stream()
                .filter(Objects::nonNull)
                .distinct()  // Remove duplicates
                .sorted(Comparator.reverseOrder())
                .limit(10)
                .collect(Collectors.toList());
    }

    // - return a list of the top 10 unique values that are odd -- done
    // Combine filtering, distinct, and limiting operations
    public List<Integer> topTenUniqueOdd() throws InvalidDataException {
        if (integerValues == null) {
            throw new InvalidDataException("Integer values list cannot be null");
        }

        if (integerValues.isEmpty()) {
            return Collections.emptyList();
        }

        // Filter nulls, filter odds, remove duplicates, sort descending, take top 10
        return integerValues.stream()
                .filter(Objects::nonNull)
                .filter(n -> n % 2 != 0)          // Keep only odd numbers
                .distinct()                        // Remove duplicates
                .sorted(Comparator.reverseOrder()) // Sort descending
                .limit(10)                         // Take top 10
                .collect(Collectors.toList());
    }

    //  - return the average of all random numbers -- done
    // Handle potential OptionalDouble and division by zero scenarios
    public Double average() throws InvalidDataException {
        if (integerValues == null) {
            throw new InvalidDataException("Integer values list cannot be null");
        }

        if (integerValues.isEmpty()) {
            throw new InvalidDataException("Cannot calculate average of empty list");
        }

        // Filter nulls and calculate average
        return integerValues.stream()
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)  // Convert to IntStream for efficiency
                .average()                     // Returns OptionalDouble
                .orElseThrow(() -> new InvalidDataException("No valid values to average"));
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