package com.insight.backend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @Autowired
    private Calculator calculator;

    /**
     *
     * @param zahl1
     * @param zahl2
     * @return
     */
    @GetMapping("api/add")
    public ResponseEntity<Object> add(@RequestParam int zahl1, int zahl2) {
            Map<String, Integer> response = new HashMap<>();
            response.put("result", calculator.add(zahl1, zahl2));
            return ResponseEntity.ok(response);
    }

}


