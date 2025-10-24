
// TODO - return a list with the first 2 elements of a sorted list of fruits
// Add proper validation and exception handling
public List<String> sortedFruitsFirstTwo() throws InvalidDataException {
// Implement with validation, null checks, and exception handling
return null;
}

identify potential edge cases and exception scenarios and suggestions for robust implementation approaches


________
What if the fruits list is empty []?
________

// TODO - return a comma separated String of sorted fruits
// Handle null values and empty results gracefully
public String commaSeparatedListOfFruits() throws InvalidDataException {
// Implement with proper string joining and validation
return null;
}

identify potential edge cases and exception scenarios and suggestions for robust implementation approaches

--------
// TODO - return a list of veggies sorted in reverse (descending) order
// Use Comparator.reverseOrder() and handle edge cases
public List<String> reverseSortedVeggies() throws InvalidDataException {
return null;
}

identify potential edge cases and exception scenarios and suggestions for robust implementation approaches
________
// TODO - return a list of veggies sorted in reverse order, all in upper case
// Chain multiple stream operations with proper exception handling
public List<String> reverseSortedVeggiesInUpperCase() throws InvalidDataException {
return null;
}

identify potential edge cases and exception scenarios and suggestions for robust implementation approaches
________
// TODO - return a list of the top 10 values in the list of random integers
// Handle cases where list has fewer than 10 elements
public List<Integer> topTen() throws InvalidDataException {
return null;
}


________
try {
GentlyDownTheStream stream = new GentlyDownTheStream();

        System.out.println("1) " + stream.sortedFruits());
        System.out.println("2) " + stream.sortedFruitsException());
        System.out.println("3) " + stream.sortedFruitsFirstTwo(stream.fruits));
        System.out.println("4) " + stream.commaSeparatedListOfFruits(stream.fruits));
        System.out.println("5) " + stream.reverseSortedVeggies(stream.veggies));
        System.out.println("6) " + stream.reverseSortedVeggiesInUpperCase(stream.veggies));
        System.out.println("7) " + stream.topTen(stream.integerValues));
        System.out.println("8) " + stream.topTenUnique());
        System.out.println("9) " + stream.topTenUniqueOdd());
        System.out.println("10) " + stream.average());

    } catch (InvalidDataException e) {
        System.err.println("Data processing error: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Unexpected error: " + e.getMessage());
    }

    System.out.println("\n===== Exception Handling Demo =====");
    demonstrateExceptionHandling();
}

im getting an error and i think its in this section with println 7 im not sure what to put in the stream
________
public GentlyDownTheStream() {
fruits = Arrays.asList("Apple", "Orange", "Banana", "Pear", "Peach", "Tomato");
veggies = Arrays.asList("Corn", "Potato", "Carrot", "Pea", "Tomato");
integerValues = new Random().ints(0, 1001)
.boxed()
.limit(1000)
.collect(Collectors.toList());
}

This is what my contructor looks like -------- Found answer with this prompt 
________
// TODO - return a list of the top 10 unique values in the list of random integers
// Use distinct() operation and handle empty results
public List<Integer> topTenUnique() throws InvalidDataException {
return null;
}

identify potential edge cases and exception scenarios and suggestions for robust implementation approaches
_________
// TODO - return a list of the top 10 unique values that are odd
// Combine filtering, distinct, and limiting operations
public List<Integer> topTenUniqueOdd() throws InvalidDataException {
return null;
}

identify potential edge cases and exception scenarios and suggestions for robust implementation approaches
________
// TODO - return the average of all random numbers
// Handle potential OptionalDouble and division by zero scenarios
public Double average() throws InvalidDataException {
return null;
}

identify potential edge cases and exception scenarios and suggestions for robust implementation approaches
________
@Nested
@DisplayName("Exception Handling Tests")
class ExceptionHandlingTests {

        @Test
        @DisplayName("Should handle null collections gracefully")
        void shouldHandleNullCollections() {
            // Test would require creating instance with null collections
            // This demonstrates the type of exception testing to implement
            assertThatThrownBy(() -> {
                GentlyDownTheStream nullStream = new GentlyDownTheStream();
                // Force null state for testing
                nullStream.fruits = null;
                nullStream.sortedFruits();
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("cannot be null");
        }

        @Test
        @DisplayName("Should handle empty collections appropriately")
        void shouldHandleEmptyCollections() {
            // Test empty collection behavior
            assertThatThrownBy(() -> {
                GentlyDownTheStream emptyStream = new GentlyDownTheStream();
                emptyStream.fruits = List.of(); // Empty list
                emptyStream.sortedFruits();
            }).isInstanceOf(EmptyCollectionException.class)
                    .hasMessageContaining("cannot be empty");
        }

heres my exceptionsHandlingTest  but im currently getting errors

java.lang.AssertionError:
Expecting actual throwable to be an instance of:
com.example.streams.EmptyCollectionException
but was:
com.example.streams.InvalidDataException: Failed to sort fruits: Fruits collection cannot be empty
at com.example.streams.GentlyDownTheStream.sortedFruits(GentlyDownTheStream.java:39)
at com.example.streams.GentlyDownTheStreamTest$ExceptionHandlingTests.lambda$shouldHandleEmptyCollections$1(GentlyDownTheStreamTest.java:196)
at org.assertj.core.api.ThrowableAssert.catchThrowable(ThrowableAssert.java:63)
...(82 remaining lines not displayed - this can be changed with Assertions.setMaxStackTraceElementsDisplayed)
at com.example.streams.GentlyDownTheStreamTest$ExceptionHandlingTests.shouldHandleEmptyCollections(GentlyDownTheStreamTest.java:197)
at java.base/java.lang.reflect.Method.invoke(Method.java:580)
at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
java.lang.AssertionError:
Expecting actual throwable to be an instance of:
java.lang.IllegalArgumentException
but was:
com.example.streams.InvalidDataException: Failed to sort fruits: Fruits collection cannot be null
at com.example.streams.GentlyDownTheStream.sortedFruits(GentlyDownTheStream.java:39)
at com.example.streams.GentlyDownTheStreamTest$ExceptionHandlingTests.lambda$shouldHandleNullCollections$0(GentlyDownTheStreamTest.java:184)
at org.assertj.core.api.ThrowableAssert.catchThrowable(ThrowableAssert.java:63)
...(82 remaining lines not displayed - this can be changed with Assertions.setMaxStackTraceElementsDisplayed)
at com.example.streams.GentlyDownTheStreamTest$ExceptionHandlingTests.shouldHandleNullCollections(GentlyDownTheStreamTest.java:185)
at java.base/java.lang.reflect.Method.invoke(Method.java:580)
at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
Process finished with exit code -1
this is the error it says 2 test failed and 2 passed what do i do

--The issue is that your tests expect different exception types than what your code is actually throwing. Your code throws InvalidDataException, but the tests expect IllegalArgumentException and EmptyCollectionException. -- fixed it by changing the exceptions thrown in the methods to match the test expectations.
________
