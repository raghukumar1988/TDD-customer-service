package com.org.comcast.customerservice.api.contrroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.comcast.customerservice.api.dto.CustomerDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    String URI = "/customers/createCustomer";

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Should successfully create a new customer from Request Body")
    void createConsumerFromRequestBody() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder().customerId(979054L).customerName("RaghuWithBody").activationStatus(true).gender("Male").build();
        String content = new ObjectMapper().writeValueAsString(customerDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)// Ctrl+Alt+V to create variable with correct Type
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())// we can use static import for readability
                .andExpect(MockMvcResultMatchers.jsonPath("customerId").value(979054L))
                .andExpect(MockMvcResultMatchers.jsonPath("customerName").value("RaghuWithBody"))
                .andExpect(MockMvcResultMatchers.jsonPath("activationStatus").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("gender").value("Male"));


    }
}

