package com.logs.enums;

import com.logs.command.CommandExecutor;
import com.logs.command.impl.CommandExecutorDrawHisto;
import com.logs.command.impl.CommandExecutorHelp;
import com.logs.command.impl.CommandExecutorPrintTopN;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public enum CommandType {

    HELP(CommandType::help, new CommandExecutorHelp()),
    PRINT_TOP_N(CommandType::average, new CommandExecutorPrintTopN()),
    DRAW_HISTO(CommandType::histogram, new CommandExecutorDrawHisto());

    private final static String PARAM_HELP = "-h";
    private final static String PARAM_HISTO = "-histo";

    private Predicate<List<String>> paramsPredicate;
    private CommandExecutor executor;

    CommandType(Predicate<List<String>> paramsPredicate, CommandExecutor executor) {
        this.paramsPredicate = paramsPredicate;
        this.executor = executor;
    }

    private static boolean help(List<String> params) {
        return Optional.ofNullable(params).map(p -> p.contains(PARAM_HELP)).orElse(Boolean.FALSE);
    }

    private static boolean histogram(List<String> params) {
        return Optional.ofNullable(params).map(p -> p.contains(PARAM_HISTO)).orElse(Boolean.FALSE);
    }

    private static boolean average(List<String> params) {
        boolean isNotHelpOrHistogram = !(help(params) || histogram(params));
        return isNotHelpOrHistogram && params.size() == 2;
    }

    public static CommandType fromParams(final List<String> params) {
        return Stream.of(values()).filter(p -> p.paramsPredicate.test(params)).findFirst().orElse(HELP);
    }

    public void execute(String[] args) {
        executor.execute(args);
    }
}
