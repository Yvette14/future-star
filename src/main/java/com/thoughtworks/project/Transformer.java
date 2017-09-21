package com.thoughtworks.project;

import java.util.List;

public class Transformer {
    public int sumEvens(List<Integer> integers) {
        return integers.stream().filter(n -> n % 2 == 0).reduce(0, (a, b) -> a + b);
    }
}
