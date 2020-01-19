package com.mpsnake.api;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.mpsnake.api.logic.PlayerLogic;
import com.mpsnake.api.logic.StatisticsLogic;
import com.mpsnake.api.model.Player;
import com.mpsnake.api.model.Statistic;
import com.mpsnake.api.repositories.PlayerRepository;
import com.mpsnake.api.repositories.StatisticsRepository;
import com.mpsnake.api.utilities.LoggerUtil;
import org.junit.Assert;
import ch.qos.logback.classic.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@TestPropertySource(locations="classpath:application-test.properties")
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

    Logger logger = (Logger) LoggerFactory.getLogger(LoggerUtil.class);

    ListAppender<ILoggingEvent> listAppender = new ListAppender<>();

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private StatisticsRepository statisticsRepository;

    @Before
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

//    @Test
//    public void createNewPlayerWithIncorrectData() {
//        // arrange
//        listAppender.start();
//        logger.addAppender(listAppender);
//        List<ILoggingEvent> logsList = listAppender.list;
//        Player player = new Player("", "");
//
//        // act
//        playerLogic.createPlayer(player);
//
//        // arrange
//        Assert.assertEquals(Level.ERROR, logsList.get(logsList.size() - 1).getLevel());
//    }

    @Test
    public void getPlayerWithCorrectData() {
        // arrange
        Player expected;

        // act
        expected = playerLogic.getPlayer("abcd");

        // assert
        Assert.assertEquals(expected.getPlayer_id(), player.getPlayer_id());
    }

    @Test(expected = ResponseStatusException.class)
    public void getPlayerWithIncorrectData() {
        // act
        playerLogic.getPlayer("ab");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="StatisticsLogic Unit tests">
    @Test
    public void createNewStatisticWithCorrectData() {
        // arrange
        Statistic stat = new Statistic("test");

        // act
        statisticsLogic.createNewStatistic(stat);
        String actual = statisticsRepository.findById("test").get().getPlayer_id();

        // assert
        Assert.assertEquals("test", actual);
    }

    @Test
    public void getStatisticFromPlayerByIdWithCorrectData() {
        // expected
        String expected = statistic.getPlayer_id();

        // act
        Statistic actual = statisticsLogic.getUserStatistics("abcd");

        // assert
        Assert.assertEquals(expected, actual.getPlayer_id());
    }

    @Test(expected = ResponseStatusException.class)
    public void getStatisticFromPlayerByIdWithIncorrectData() {
        statisticsLogic.getUserStatistics("wrong_id");
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
