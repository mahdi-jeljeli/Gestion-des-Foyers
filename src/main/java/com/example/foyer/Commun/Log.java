package com.example.foyer.Commun;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    // Créez des constantes pour les noms des loggers
    private static final Logger MONITORING_LOGGER = LogManager.getLogger("MonitoringLogger");
    private static final Logger EXCEPTION_LOGGER = LogManager.getLogger("ExceptionLogger");
    private static final Logger FATAL_LOGGER = LogManager.getLogger("FatalLogger");
    private static final Logger SQL_LOGGER = LogManager.getLogger("SqlLogger");

    // Méthodes d'accès aux loggers
    public static Logger getMonitoringLogger() {
        return MONITORING_LOGGER;
    }

    public static Logger getExceptionLogger() {
        return EXCEPTION_LOGGER;
    }

    public static Logger getFatalLogger() {
        return FATAL_LOGGER;
    }

    public static Logger getSqlLogger() {
        return SQL_LOGGER;
    }
}
