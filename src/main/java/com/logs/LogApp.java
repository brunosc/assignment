package com.logs;

import com.logs.enums.CommandType;
import com.logs.utils.Logger;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class LogApp {

    private final Clock clock = Clock.systemDefaultZone();

    public static void main(String[] args) {
        new LogApp().execute(args);
    }

    private void execute(String[] args) {
        Instant start = clock.instant();

        CommandType.fromParams(Arrays.asList(args))
                   .execute(args);

        Logger.info("> Execution time: %s in ms", Duration.between(start, clock.instant()).toMillis());
    }

}
