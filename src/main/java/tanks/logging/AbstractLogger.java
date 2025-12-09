package tanks.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractLogger
{
    protected Level level = Level.INFO;

    public enum Level
    {          // levels:
        TRACE, // 0
        DEBUG, // 1
        INFO,  // 2
        WARN,  // 3
        ERROR, // 4
        FATAL  // 5
    }

    protected abstract void write(String message, Level level);

    private void log(Level msgLevel, String format, Object... args)
    {
        if (msgLevel.ordinal() >= level.ordinal())
        {
            String message = String.format(format, args);
            String formatted = formatMessage(message, msgLevel);
            write(formatted, msgLevel);
        }
    }

    private String formatMessage(String message, Level level)
    {
        return String.format("[%s] [%s] %s",
            LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME),
            level,
            message);
    }

    public void trace(String message)
    {
        log(Level.TRACE, message);
    }

    public void debug(String message)
    {
        log(Level.DEBUG, message);
    }

    public void info(String message)
    {
        log(Level.INFO, message);
    }

    public void warn(String message)
    {
        log(Level.WARN, message);
    }

    public void error(String message)
    {
        log(Level.ERROR, message);
    }

    public void error(String message, Throwable throwable)
    {
        log(Level.ERROR, message);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        write(stringWriter.toString(), Level.ERROR);
    }

    public void fatal(String message)
    {
        log(Level.FATAL, message);
    }

    public void fatal(String message, Throwable throwable)
    {
        log(Level.FATAL, message);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        write(stringWriter.toString(), Level.FATAL);
    }

    public void setLevel(Level level)
    {
        this.level = level;
    }
}
