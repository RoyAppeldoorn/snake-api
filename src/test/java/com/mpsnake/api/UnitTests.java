package com.mpsnake.api;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.google.gson.Gson;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

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
    public void createNewPlayerWithIncorrectData() {
        listAppender.start();
        logger.addAppender(listAppender);

        Player player = new Player("sdfsfsdfs", "");
        playerLogic.createPlayer(player);

        List<ILoggingEvent> logsList = listAppender.list;
        Assert.assertEquals(Level.ERROR, logsList.get(logsList.size() - 1).getLevel());
    }

    @Test
    public void getPlayerWithCorrectId() {
        Player expected = playerLogic.getPlayer("abcd");
        Assert.assertEquals(expected.getPlayer_id(), player.getPlayer_id());
    }
    // </editor-fold>
}
