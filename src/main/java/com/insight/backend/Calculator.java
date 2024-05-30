package com.insight.backend;

import org.springframework.stereotype.Component;

@Component
public class Calculator {

    public int add(int zahl1, int zahl2) {
		return zahl1 + zahl2;
	}

}