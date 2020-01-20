package com.mpsnake.api;

import com.mpsnake.api.logic.PlayerLogic;
import com.mpsnake.api.logic.StatisticsLogic;
import com.mpsnake.api.model.Player;
import com.mpsnake.api.model.Statistic;
import com.mpsnake.api.repositories.PlayerRepository;
import com.mpsnake.api.repositories.StatisticsRepository;
import com.mpsnake.api.utilities.LoggerUtil;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class UnitTests {
    // <editor-fold defaultstate="collapsed" desc="Setup">
    private Player player = new Player("abcd", "test");
    private Statistic statistic = new Statistic("abcd");

    @Autowired
    PlayerLogic playerLogic;
    @Autowired
    StatisticsLogic statisticsLogic;
    @Autowired
    LoggerUtil loggerUtil;

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private StatisticsRepository statisticsRepository;

    @BeforeEach
    public void setup() {
        playerRepository.save(player);
        statisticsRepository.save(statistic);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="PlayerLogic Unit tests">
    @Test
    public void createNewPlayerWithCorrectData() {
        // arrange
        Player player = new Player("test", "test");
        String expected = player.getPlayer_id();

        // act
        playerLogic.createPlayer(player);
        String actual = playerRepository.findById("test").get().getPlayer_id();

        // assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createNewStatisticWhenNewPlayerGetsCreated() {
        // arrange
        Player player = new Player("test", "test");
        String expected = player.getPlayer_id();

        // act
        playerLogic.createPlayer(player);
        String actual = statisticsRepository.findById("test").get().getPlayer_id();

        // assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPlayerWithCorrectData() {
        // arrange
        Player expected = playerLogic.getPlayer("abcd");

        // assert
        Assert.assertEquals(expected.getPlayer_id(), player.getPlayer_id());
    }

    @Test
    public void getPlayerWithIncorrectData() {
        assertThrows(ResponseStatusException.class, () -> {
            playerLogic.getPlayer("ab");
        });
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="StatisticsLogic Unit tests">

    @Test
    public void getStatisticFromPlayerByIdWithCorrectData() {
        // expected
        String expected = statistic.getPlayer_id();

        // act
        Statistic actual = statisticsLogic.getUserStatistics("abcd");

        // assert
        Assert.assertEquals(expected, actual.getPlayer_id());
    }

    @Test
    public void getStatisticFromPlayerByIdWithIncorrectData() {
        assertThrows(ResponseStatusException.class, () -> {
            statisticsLogic.getUserStatistics("wrong_id");
        });
    }

    @Test
    public void increaseKillCountForPlayerWithCorrectData() {
        // act
        int expected = 1;
        String correctId = statistic.getPlayer_id();

        // act
        statisticsLogic.increaseKillCountForPlayer(correctId);
        int actual = statisticsRepository.findById(correctId).get().getKills();

        // assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void increaseDeadCountForPlayerWithCorrectData() {
        // act
        int expected = 1;
        String correctId = statistic.getPlayer_id();

        // act
        statisticsLogic.increaseDeadCountForPlayer(correctId);
        int actual = statisticsRepository.findById(correctId).get().getDeads();

        // assert
        Assert.assertEquals(expected, actual);
    }

    // </editor-fold>
}
