package tanks.logging;

import java.io.PrintStream;

public class ConsoleLogger extends AbstractLogger
{
    private final PrintStream out;
    private final PrintStream err;

    public ConsoleLogger()
    {
        this(System.out, System.err);
    }

    public ConsoleLogger(PrintStream out, PrintStream err)
    {
        this.out = out;
        this.err = err;
    }

    @Override
    protected void write(String message, Level level)
    {
        PrintStream stream = (level == Level.ERROR || level == Level.FATAL) ? err : out; // if level is error or fatal, use System.err, or use System.out
        stream.println(message);
        stream.flush();
    }
}
