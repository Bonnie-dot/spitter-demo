package com.example.demo.Controller;

import com.example.demo.Model.Spitter;
import com.example.demo.Service.SpitterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Test
    public void getSpitterById() throws Exception {
        Spitter spitter=new Spitter("5nfgh","5nfgh","5nfgh","5nfgh","2@qq.com");
        spitter.setId(11111L);
        given(spitterService.getSpitterById(122)).willReturn(spitter);
        mockMvc.perform(get("/spitter/122"))
                .andExpect(status().isOk())
                .andExpect(view().name("spitter"))
                .andExpect(model().attribute("spitter",spitter));
    }

    @Test
    public void saveSpitter() throws Exception {
        Spitter spitter=new Spitter("5nfgh","5nfgh","5nfgh","5nfgh","2@qq.com");
        spitter.setId(11111L);
        given(spitterService.saveSpitter(spitter)).willReturn(spitter);
        mockMvc.perform(post("/spitter/122"));
    }

    @Test
    public void getxx(){
        List list=new ArrayList();
        list.stream();
    }
}
