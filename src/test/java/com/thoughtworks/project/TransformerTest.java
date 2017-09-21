package com.thoughtworks.project;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransformerTest {
    @Test
    public void should_return_sum_of_evens() throws Exception {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int expected = 30;
        int actualResult = new Transformer().sumEvens(integers);
        assertThat(actualResult, is(expected));
    }

    @Test
    public void should_sort_and_count_emails() throws Exception {
        List<String> strings = Arrays.asList("ynpan", "yzqi", "ybowang", "qiqzhao", "yibtan", "abc", "sjyuan");
        Map<String, Integer> expected = new LinkedHashMap<>();
        expected.put("abc", 3);
        expected.put("qiqzhao", 7);
        expected.put("sjyuan", 6);
        expected.put("ybowang", 7);
        expected.put("yibtan", 6);
        expected.put("ynpan", 5);
        expected.put("yzqi", 4);

        Map<String, Integer> actualResult = new Transformer().sortAndCount(strings);
        assertThat(actualResult.keySet(),contains("abc","qiqzhao","sjyuan","ybowang","yibtan","ynpan","yzqi"));
        assertThat(actualResult.values(),contains(3,7,6,7,6,5,4));
    }

    @Test
    public void should_return_sorted_letters() throws Exception {
        String letters = "aababbbcabcdabcde";
        String expected = "a(5) > b(6) > c(3) > d(2) > e(1)";
        String actualResult = new Transformer().sortLetters(letters);
        assertThat(actualResult,is(expected));
    }
}
