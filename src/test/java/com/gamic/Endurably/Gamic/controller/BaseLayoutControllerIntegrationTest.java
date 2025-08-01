package com.gamic.Endurably.Gamic.controller;

import com.gamic.Endurably.Gamic.Entity.BaseLayout;
import com.gamic.Endurably.Gamic.Entity.Users;
import com.gamic.Endurably.Gamic.repository.BaseLayoutRepository;
import com.gamic.Endurably.Gamic.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc 
@Transactional 
class BaseLayoutControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BaseLayoutRepository baseLayoutRepository; 

    @Autowired
    private UserRepository userRepository;

    private Users testUser;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll(); 
        baseLayoutRepository.deleteAll();

        Users user = new Users();
        user.setName("Test User");
        user.setEmail("testuser@example.com");
        user.setPassword("password");
        user.setRole("USER");
        testUser = userRepository.save(user);
    }

    @Test
    void whenGetBasesWithTownhallFilter_thenReturnsFilteredBases() throws Exception {
        BaseLayout th15Base = new BaseLayout();
        th15Base.setTitle("TH15 Base");
        th15Base.setTownhallLevel(15);
        th15Base.setBaseLink("link1");
        th15Base.setSubmittedBy(testUser);
        baseLayoutRepository.save(th15Base);

        BaseLayout th14Base = new BaseLayout();
        th14Base.setTitle("TH14 Base");
        th14Base.setTownhallLevel(14);
        th14Base.setBaseLink("link2");
        th14Base.setSubmittedBy(testUser);
        baseLayoutRepository.save(th14Base);

        mockMvc.perform(get("/bases") 
                        .param("townhallLevel", "15")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk()) 
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray()) 
                .andExpect(jsonPath("$.content.length()").value(1)) 
                .andExpect(jsonPath("$.content[0].title").value("TH15 Base")); 
    }
}
