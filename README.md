# cron-parser

This is a simple cron expression parser. It takes a cron expression as a command-line argument and expands each field to show the times at which it will run. The cron expression will consist of 6 fields separated by a single space. The fields are as follows:
* minute 
* hour 
* day of month 
* month 
* day of week 
* command

Allowed special characters are: 
* `*` - all values
* `/` - used to specify increments
* `,` - used to specify listed values
* `-` - used to specify ranges

For example, the following input argument:
`*/15 0 1,15 * 1-5 /usr/bin/find`

Should print the following output:
```
minute 0 15 30 45
hour 0
day of month 1 15
month 1 2 3 4 5 6 7 8 9 10 11 12
day of week 1 2 3 4 5
command /usr/bin/find
```

This parser ignores fact that some months have 30 days and some 31 days. It also ignores leap years.