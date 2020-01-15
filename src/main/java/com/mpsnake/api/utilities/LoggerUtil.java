package com.mpsnake.api.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerUtil {
    private static final Logger logger = LoggerFactory.getLogger(Logger.class);

    public static void errorLogging(String error){
        logger.error(error);
    }

    public static void debugLogging(String msg){
        logger.info(msg);
    }
}
