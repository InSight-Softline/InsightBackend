package com.insight.backend.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for testing purposes.
 * Enables component scanning within the 'com.insight.backend' package.
 */
@Configuration
@ComponentScan(basePackages = "com.insight.backend")
public class TestConfig {

}
