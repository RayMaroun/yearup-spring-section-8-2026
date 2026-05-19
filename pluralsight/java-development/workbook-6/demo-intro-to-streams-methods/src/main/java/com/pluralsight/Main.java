package com.pluralsight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // filter
        System.out.println("Filter Method");
        System.out.println();

        List<String> titles = Arrays.asList(
                "Halloween", "Ghost", "Halloween 2",
                "Friday the 13th", "Twister", "Halloween 3");

        /*List<String> matching = titles
                .stream().filter(title -> title.toLowerCase().contains("halloween"))
                .toList();

        //matching.forEach(matchingTitle -> System.out.println(matchingTitle));
        matching.forEach(System.out::println);*/

        titles
                .stream().filter(title -> title.toLowerCase().contains("halloween"))
                .toList()
                .forEach(System.out::println);

        /*titles
                .stream().filter(title -> {
                    String lower = title.toLowerCase();
                    boolean containsHalloween = lower.contains("halloween");
                    boolean isLongTitle = title.length() > 10;
                    return containsHalloween && isLongTitle;
                } )
                .toList()
                .forEach(System.out::println);*/

        System.out.println("======================================================================================");

        // count
        System.out.println("Count Method");
        System.out.println();

        List<String> titles2 = Arrays.asList(
                "Halloween", "Ghost", "Halloween 2",
                "Friday the 13th", "Twister", "Halloween 3");

        long count = titles2
                .stream().filter(title -> title.toLowerCase().contains("halloween"))
                .count();

        System.out.println("The count is: " + count);

        System.out.println("======================================================================================");

        // forEach()
        System.out.println("forEach Method");
        System.out.println();

        List<String> titles3 = Arrays.asList(
                "Halloween", "Ghost", "Halloween 2",
                "Friday the 13th", "Twister", "Halloween 3");

        // titles3.forEach(System.out::println);

        titles3
                .stream().filter(title -> title.toLowerCase().contains("halloween"))
                .toList()
                .forEach(System.out::println);

        System.out.println("======================================================================================");

        // sorted
        System.out.println("Sorted Method");
        System.out.println();

        List<String> titles4 = Arrays.asList(
                "Halloween 3", "Ghost", "Halloween ",
                "Friday the 13th", "Twister", "Halloween 2");

        titles4
                .stream().filter(title -> title.toLowerCase().contains("halloween"))
                .sorted() // in reverse order: .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);


        System.out.println("======================================================================================");

        // map
        System.out.println("Map Method");
        System.out.println();

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        /*List<Integer> squaresList = new ArrayList<>();

        for (Integer number : numbers) {
            squaresList.add(number * number);
        }*/

        List<Integer> squaresList =
                numbers
                        .stream().map(number -> number * number)
                        .distinct()
                        .toList();


        System.out.println(numbers);
        System.out.println(squaresList);

        System.out.println("-------------------------------------------------------------");

        List<String> names = Arrays.asList("alice", "bob", "charlie", "david");

        List<String> upperCaseNames =
                names
                        .stream().map(name -> name.toUpperCase())
                        .toList();

        System.out.println("Original list: " + names);
        System.out.println("Uppercase list: " + upperCaseNames);

        System.out.println("======================================================================================");

        // reduce
        System.out.println("Reduce Method");
        System.out.println();

        List<Integer> numbers2 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        int sum = numbers2
                .stream().reduce(0, (sumTotal, num) -> sumTotal + num);

        double average = (double) sum / numbers2.size();

        System.out.println("The sum is: " + sum);
        System.out.println("The average is: " + average);

        System.out.println("-------------------------------------------------------------");
        List<Integer> numbers3 = Arrays.asList(1, 2, 3, 4, 5);

        int product = numbers3
                .stream().reduce(1, (sumTotal, num) -> sumTotal * num);

        System.out.println("The product is: " + product);

        System.out.println("-------------------------------------------------------------");

        List<String> words = Arrays.asList("Potatoes", "are", "awesome");

        String sentence = words
                .stream().reduce("", (acc, word) -> acc + word + " ");

        System.out.println("The original words: " + words);
        System.out.println("Combined Sentence: " + sentence.trim());


    }
}
