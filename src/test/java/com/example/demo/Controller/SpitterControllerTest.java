package com.example.demo.Controller;

import com.example.demo.Model.Spitter;
import com.example.demo.Repository.SpitterRepository;
import com.example.demo.Service.SpitterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(SpitterController.class)
public class SpitterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SpitterService spitterService;

    @Test
    public void getRegister() throws Exception {
        mockMvc.perform(get("/spitter/register"))
                .andExpect(content().string(containsString("Register")));
    }

    @Test
    public void getSpitters() throws Exception {
        Spitter spitter=new Spitter("5nfgh","5nfgh","5nfgh","5nfgh","2@qq.com");
        List<Spitter> spitterList=new ArrayList<>();
        spitterList.add(spitter);
        //mock service return value
        given(spitterService.getAllSpitters()).willReturn(spitterList);
        mockMvc.perform(get("/spitter/spitters"))
                .andExpect(content().string(containsString("5nfgh")));
    }


}
