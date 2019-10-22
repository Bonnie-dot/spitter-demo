package com.example.demo.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(SpitterController.class)
public class SpitterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getRegister() throws Exception {
        mockMvc.perform(get("/spitter/register"))
                .andExpect(content().string(containsString("Register")));
    }
}
