package com.insight.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class BackendApplicationTests {
	@Autowired
	private Calculator calculator;
	private MockMvc mockMvc;
	
    @Test
    public void testAdd() {
        // Act
        int result = calculator.add(25, 32);

        // Assert
        assertEquals(57, result);
    }

	@Test
	public void testEndpointAdd() throws Exception {
		mockMvc.perform(get("/api/add")
                .param("zahl1", "2")
                .param("zahl2", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
	}

}
