package com.mpsnake.api;

import com.google.gson.Gson;
import com.mpsnake.api.model.Player;
import com.mpsnake.api.model.Statistic;
import com.mpsnake.api.repositories.PlayerRepository;
import com.mpsnake.api.repositories.StatisticsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest
public class IntegrationTests {
    // <editor-fold defaultstate="collapsed" desc="Setup">
    private Player player = new Player("abcd", "test");
    private Statistic statistic = new Statistic("abcd");

    private MockMvc mock;

    @Autowired
    private WebApplicationContext wac;

    @Resource
    private PlayerRepository playerRepository;
    @Resource
    private StatisticsRepository statisticsRepository;

    @Before
    public void setup() {
        mock = MockMvcBuilders.webAppContextSetup(wac).build();
        playerRepository.save(player);
        statisticsRepository.save(statistic);
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
    public void getPlayerWithCorrectData() throws Exception{
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
    public void getPlayerWithIncorrectData() throws Exception{
        mock.perform(MockMvcRequestBuilders.get("/player/ab")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tests for get /statistics/{id}">
    @Test
    public void getStatisticsFromPlayerWithCorrectData() throws Exception{
        MvcResult result = mock.perform(MockMvcRequestBuilders.get("/statistics/abcd")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(statistic))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn();

        String expected = "{\"kills\":0,\"deads\":0,\"player_id\":\"abcd\"}";

        Assert.assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    public void getStatisticsFromPlayerWithIncorrectData() throws Exception{
        mock.perform(MockMvcRequestBuilders.get("/statistics/ab")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tests for update /statistics/{id}/kill">
    @Test
    public void increaseKillCountForPlayer() throws Exception{
        mock.perform(MockMvcRequestBuilders.post("/statistics/abcd/kill")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        MvcResult result = mock.perform(MockMvcRequestBuilders.get("/statistics/abcd")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(statistic))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn();

        String expected = "{\"kills\":1,\"deads\":0,\"player_id\":\"abcd\"}";

        Assert.assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    public void increaseDeadCountForPlayer() throws Exception{
        mock.perform(MockMvcRequestBuilders.post("/statistics/abcd/dead")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        MvcResult result = mock.perform(MockMvcRequestBuilders.get("/statistics/abcd")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(statistic))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn();

        String expected = "{\"kills\":0,\"deads\":1,\"player_id\":\"abcd\"}";

        Assert.assertEquals(expected, result.getResponse().getContentAsString());
    }
    // </editor-fold>
}
