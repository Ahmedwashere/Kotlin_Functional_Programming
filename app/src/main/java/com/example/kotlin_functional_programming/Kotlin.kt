package com.example.kotlin_functional_programming

import java.io.File.separator

data class Student(val name: String, val age: Int, val gpa: Double, val creditHours: Int)

fun main() {
    println("-".repeat(50))
    println("\n\tWelcome to functional programming in Kotlin!\n")
    println("-".repeat(50))

    val students = listOf(
        Student("Alice", 20, 3.5, 61),
        Student("Bob", 22, 3.8, 110),
        Student("Charlie", 21, 3.2, 88),
        Student("Dave", 23, 2.9, 120)
    )

    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    println("-".repeat(50) + "\n")
    println("Imperative Programming -> You describe HOW you want something to be done.")
    println(
        "Original List: $numbers" +
                "\nFilter for evens only ${getEvenImperative(numbers)}"
    )

    println("\n" + "-".repeat(50))

    println("-".repeat(50) + "\n")
    println("Functional Programming -> You describe WHAT should be done.")
    println(
        "Original List: $numbers" +
                "\nFilter for evens only ${getEvenImperative(numbers)}"
    )

    println("\n" + "-".repeat(50))

    // Functions as first class citizens
    var evenFilter = { it: Int -> it % 2 == 0 }
    var oddFilter = { it: Int -> it % 2 == 1 }

    // The function below takes in another function as an argument!
    println("Even numbers from numbers: ${filterNumbersAndReturnList(numbers, evenFilter)}")
    println("Odd numbers from numbers: ${filterNumbersAndReturnList(numbers, oddFilter)}")

    println("\n" + "-".repeat(50))
    println("\n" + "-".repeat(50))
    println("\nINTERMEDIATE OPERATIONS\n")
    //Intermediate Operations
    // filter, map
    println("Map the student objects to their name and then filter for names that contain 'e'" + students.map { it.name }
        .filter { it.contains("e") }.toCollection(ArrayList()))

    // sortedBy
    println("\nSort the students in ascending order by GPA:\n" + students.sortedBy { it.gpa })

    // sortedByDescending
    println("\nSort the students in descending order by GPA:\n" + students.sortedByDescending { it.gpa })

    // Distinct
    println(
        "\nGetting distinct Elements: " + listOf(1, 2, 3, 4, 5, 5, 5, 5).distinct()
            .toCollection(ArrayList())
    )

    // take
    println("Take two numbers from the stream: " + numbers.take(2).toCollection(ArrayList()))

    println("\n" + "-".repeat(50))
    println("\n" + "-".repeat(50))

    println("\n TERMINAL OPERATIONS\n")
    println(
        "Terminal operations are called only once in each functional programming statement. " +
                "Once called, the intermediate operations will be executed " +
                "and you are returned a result."
    )

    // Types of terminal Operations

    println("\n\nBelow we will print out each students name using the forEach terminal operation.")
    students.forEach { println(it.name) }

    // So the question is we know that streams can change all the elements in a list.
    // But how do we actually go ahead and aggregate our results???
    // WE USE REDUCE!!!

    // Reduce is extremely important

    // In this exercise lets sums all the numbers in our numbers list
    println("The sum of the numbers list is: " + numbers.reduce { acc, i -> acc + i })

    // Sum all multiples of 3 in the numbers list
    println("The sum of the the multiples of 3: " +
            numbers
                .filter { number -> number % 3 == 0 }
                .reduce { accumulator, number -> accumulator + number })

    // Make a single String that has the names of all the students seperated as a comma-delimmited list
    println(
        "All the student names as one string '${
            students.map { student -> student.name }
                .reduce { finalString, name -> finalString + " " + name }
        }'."
    )

    println(
        "All the student names as one string '${
            students.map { student -> student.name }
                .joinToString(separator=" ")
        }'."
    )



}

fun filterNumbersAndReturnList(
    numbers: List<Int>,
    operatorFunction: (Int) -> Boolean
): ArrayList<Int> {
    return numbers.filter(operatorFunction).toCollection(ArrayList())
}

fun getEvenImperative(numbers: List<Int>): ArrayList<Int> {
    var evenNumbers = arrayListOf<Int>()
    for (number in numbers) {
        if (number % 2 == 0) {
            evenNumbers.add(number)
        }
    }
    return evenNumbers
}

fun getEvensFunctional(numbers: List<Int>): ArrayList<Int> {
    return numbers.filter { it -> it % 2 == 0 }.toCollection(ArrayList())
}
