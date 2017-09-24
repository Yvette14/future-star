package com.thoughtworks.project;

import java.util.List;

public class Calculator {
    public static int sumEvens(List<Integer> numbers) {
        return numbers.stream().filter(n -> n % 2 == 0).reduce(0, (a, b) -> a + b);
    }
}
