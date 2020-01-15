package com.mpsnake.api;

import com.google.gson.Gson;
import com.mpsnake.api.model.Player;
import com.mpsnake.api.repositories.PlayerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class IntegrationTests {
    // <editor-fold defaultstate="collapsed" desc="Setup">
    private Player player = new Player("abcd", "test");

    private MockMvc mock;

    @Autowired
    private WebApplicationContext wac;

    @Resource
    private PlayerRepository playerRepository;

    @Before
    public void setup() {
        mock = MockMvcBuilders.webAppContextSetup(wac).build();
        playerRepository.save(player);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Error handling">
    @Test
    public void get404Error() throws Exception {
        MvcResult result = mock.perform(MockMvcRequestBuilders.get("/oopsie")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is(404))
                .andReturn();

        int expected = 404;

        Assert.assertEquals(expected, result.getResponse().getStatus());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tests for post /player/create">
    @Test
    public void insertCorrectPlayer() throws Exception{
        mock.perform(MockMvcRequestBuilders.post("/player/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(player))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void insertIncorrectPlayer() throws Exception{
        mock.perform(MockMvcRequestBuilders.post("/player/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tests for get /player/{id}">
    @Test
    public void getCorrectPlayer() throws Exception{
        MvcResult result = mock.perform(MockMvcRequestBuilders.get("/player/abcd")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(player))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn();

        String expected = "{\"player_id\":\"abcd\",\"nickname\":\"test\"}";

        Assert.assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    public void getPlayerWithIncorrect() throws Exception{
        MvcResult result = mock.perform(MockMvcRequestBuilders.get("/player/abcd")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(player))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn();

        String expected = "{\"player_id\":\"abcd\",\"nickname\":\"test\"}";

        Assert.assertEquals(expected, result.getResponse().getContentAsString());
    }
    // </editor-fold>
}
