## Test assignment
> A program that parses the log file and extracts, aggregates and presents the data from it. The log file contains request durations.

## How to use

It is necessary run by command line. Examples below:

> Help
```
$ java -jar assignment.jar -h
```

> Print out top 'n' resources with highest average request duration.
```
$ java -jar assignment.jar /path/to/timing.log 10
```

> Draw histogram of hourly number of requests.
```
$ java -jar assignment.jar /path/to/timing.log -histo
```
