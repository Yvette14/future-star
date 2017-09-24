package com.thoughtworks.project;


import org.junit.gen5.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SorterTest {

    @Test
    public void should_sort_and_count_emails() throws Exception {
        List<String> emailNames = Arrays.asList("ynpan", "yzqi", "ybowang", "qiqzhao", "yibtan", "abc", "sjyuan");

        Map<String, Integer> sortedEmailMap = Sorter.sortAndCount(emailNames);

        assertThat(sortedEmailMap.keySet(), contains("abc", "qiqzhao", "sjyuan", "ybowang", "yibtan", "ynpan", "yzqi"));
        assertThat(sortedEmailMap.values(), contains(3, 7, 6, 7, 6, 5, 4));
    }

    @Test
    public void should_return_sorted_letters() throws Exception {
        String letters = "aababbbcabcdabcde";

        String sortedAndFormattedLetterString = Sorter.sortLetters(letters);

        assertThat(sortedAndFormattedLetterString, is("1(e) < 2(d) < 3(c) < 6(b) < 5(a)"));
    }
}
