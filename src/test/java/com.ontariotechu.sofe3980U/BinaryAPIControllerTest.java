package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }
	@Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    @Test
    public void add3() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "101").param("operand2", "1001"))
                .andExpect(status().isOk())
                .andExpect(content().string("1110"));
    }
    
    @Test
    public void add4() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","10").param("operand2","1101"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }
    
    @Test
    public void add5() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","111"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1110))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }
    
    @Test
    public void or1() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","101").param("operand2","110"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(110))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }
    
    @Test
    public void or2() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "001").param("operand2", "101"))
                .andExpect(status().isOk())
                .andExpect(content().string("101"));
    }
    
    @Test
    public void or3() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","111").param("operand2","110"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(110))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }
    
    @Test
    public void and1() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","101").param("operand2","111"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }
    
    @Test
    public void and2() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "110").param("operand2", "101"))
                .andExpect(status().isOk())
                .andExpect(content().string("100"));
    }
    
    @Test
    public void and3() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","111").param("operand2","110"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(110))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(110))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }
    
    @Test
    public void multi1() throws Exception {
        this.mvc.perform(get("/multi_json").param("operand1","10").param("operand2","11"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(11))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(110))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multi"));
    }
    
    @Test
    public void multi2() throws Exception {
        this.mvc.perform(get("/multi").param("operand1", "100").param("operand2", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string("1000"));
    }
    
    @Test
    public void multi3() throws Exception {
        this.mvc.perform(get("/multi_json").param("operand1","1").param("operand2","101"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multi"));
    }
    

    
}