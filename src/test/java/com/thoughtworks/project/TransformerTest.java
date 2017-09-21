package com.thoughtworks.project;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
}
