package com.thoughtworks.project;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Transformer {

    static Map<String, Integer> sortAndCount(List<String> emailNames) {
        Collections.sort(emailNames);
        Map<String, Integer> result = new LinkedHashMap<>();
        emailNames.forEach(item -> result.put(item, item.length()));

        return result;
    }

    static String sortLetters(String letters) {
        Map<String, Integer> result = new HashMap<>();
        Arrays.asList(letters.split(""))
                .forEach(item -> result.put(item, result.containsKey(item) ? result.get(item) + 1 : 1));

        Stream<Map.Entry<String, Integer>> sortedLetters = result.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()));

        return formatAsString(sortedLetters);
    }

    private static String formatAsString(Stream<Map.Entry<String, Integer>> sorted) {
        return sorted.map(item -> item.getValue() + "(" + item.getKey() + ")")
                .collect(Collectors.joining(" < "));
    }
}
