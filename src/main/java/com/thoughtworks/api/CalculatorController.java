package com.thoughtworks.api;

import com.thoughtworks.project.Calculator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    @GetMapping(value = "/evens-sum", params = "numbers")
    public int calculateEvensSum(@RequestParam List<Integer> numbers) {
        return Calculator.sumEvens(numbers);
    }

}
