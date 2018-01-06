package com.arivan.amin.widget.applications.usage;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class LinuxUsageMonitor implements UsageMonitor
{
    private static final Pattern EXTRA_SPACES = Pattern.compile(" {2,}");
    private final Logger logger = Logger.getLogger(getClass().getName());
    
    private LinuxUsageMonitor ()
    {
        super();
    }
    
    @NotNull
    public static LinuxUsageMonitor newInstance ()
    {
        return new LinuxUsageMonitor();
    }
    
    @Override
    public List<UsageItem> getProcessesUsage ()
    {
        List<UsageItem> list = new ArrayList<>(20);
        String output = getCommandOutput();
        String str = "TIME+ COMMAND";
        output = output.substring(output.lastIndexOf(str) + str.length() + 1);
        output = EXTRA_SPACES.matcher(output).replaceAll(" ");
        output = output.replaceAll(" ", ":");
        System.out.println(output);
        String[] itemRows = output.split("\n");
        for (int i = 0; i < 10; i++)
        {
            String[] row = itemRows[i].split(":");
            System.out.println("making the list");
            System.out.println(Arrays.toString(row));
            list.add(UsageItem.newInstance(row[1], row[8], row[9], row[11]));
        }
        return list;
    }
    
    private String getCommandOutput ()
    {
        String data;
        try (InputStream stream = new ProcessBuilder("top", "-d", "0.01", "-bn", "2").start()
                .getInputStream())
        {
            data = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            data = "";
            logger.warning(e.getMessage());
        }
        return data;
    }
}