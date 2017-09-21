package com.thoughtworks.project;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Transformer {
    public int sumEvens(List<Integer> integers) {
        return integers.stream().filter(n -> n % 2 == 0).reduce(0, (a, b) -> a + b);
    }

    public Map<String, Integer> sortAndCount(List<String> strings) {
        Collections.sort(strings);
        Map<String, Integer> result = new LinkedHashMap<>();
        strings.forEach(item-> result.put(item,item.length()));
        return result;
    }

    public String sortLetters(String letters) {
        List<String> strings = Arrays.asList(letters.split(""));
        Map<String, Integer> result = new HashMap<>();
        strings.forEach(item->{
            if(result.get(item) != null){
                result.put(item, result.get(item) + 1);
            }else {
                result.put(item, 1);
            }
        });

        Stream<Map.Entry<String, Integer>> sorted = result.entrySet().stream().sorted(Map.Entry.comparingByKey());

        return sorted.map(item->item.getKey()+"("+item.getValue()+")").collect(Collectors.joining(" > "));
    }
}
