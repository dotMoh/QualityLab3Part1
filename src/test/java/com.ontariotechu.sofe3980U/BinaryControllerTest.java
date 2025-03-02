package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }

    @Test
public void getDefaultWithInvalidParameter() throws Exception {
    this.mvc.perform(get("/").param("operand1", "invalidValue"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
            .andExpect(model().attribute("operand1", "invalidValue"))
            .andExpect(model().attribute("operand1Focused", true));
}

@Test
public void getParameter2() throws Exception {
    this.mvc.perform(get("/").param("operand1","0"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
            .andExpect(model().attribute("operand1", "0"))
            .andExpect(model().attribute("operand1Focused", true));
}

@Test
public void postParameter2() throws Exception {
    this.mvc.perform(post("/").param("operand1","001").param("operator","+").param("operand2","110"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "111"))
            .andExpect(model().attribute("operand1", "001"));
}

@Test
public void postParameterMulti1() throws Exception {
    this.mvc.perform(post("/").param("operand1","001").param("operator","*").param("operand2","110"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "110"))
            .andExpect(model().attribute("operand1", "001"));
}

@Test
public void postParameterMulti2() throws Exception {
    this.mvc.perform(post("/").param("operand1","00").param("operator","*").param("operand2","0"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", "00"));
}

@Test
public void postParameterMulti3() throws Exception {
    this.mvc.perform(post("/").param("operand1","11011").param("operator","*").param("operand2","110"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "10100010"))
            .andExpect(model().attribute("operand1", "11011"));
}

@Test
public void postParameterand1() throws Exception {
    this.mvc.perform(post("/").param("operand1","1010").param("operator","&").param("operand2","1100"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1000"))
            .andExpect(model().attribute("operand1", "1010"));
}

@Test
public void postParameterand2() throws Exception {
    this.mvc.perform(post("/").param("operand1","00").param("operator","&").param("operand2","0"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", "00"));
}

@Test
public void postParameterand3() throws Exception {
    this.mvc.perform(post("/").param("operand1","00100").param("operator","&").param("operand2","0000000000100"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "100"))
            .andExpect(model().attribute("operand1", "00100"));
}

@Test
public void postParameteror1() throws Exception {
    this.mvc.perform(post("/").param("operand1","001").param("operator","|").param("operand2","110"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "111"))
            .andExpect(model().attribute("operand1", "001"));
}

@Test
public void postParameteror2() throws Exception {
    this.mvc.perform(post("/").param("operand1","00").param("operator","|").param("operand2","0"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", "00"));
}

@Test
public void postParameteror3() throws Exception {
    this.mvc.perform(post("/").param("operand1","11011").param("operator","|").param("operand2","100"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "11111"))
            .andExpect(model().attribute("operand1", "11011"));
}



}