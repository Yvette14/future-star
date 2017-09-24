package com.thoughtworks.api;

import com.thoughtworks.project.Sorter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SorterController {

    @GetMapping(value = "words-length", params = "letters")
    public String sortLetters(@RequestParam String letters) {
        return Sorter.sortLetters(letters);
    }
}
