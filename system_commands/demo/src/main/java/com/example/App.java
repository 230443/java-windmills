package com.example;


import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // execute python script and print output
        String command = "python3 /home/daniel/Source/java-windmills/system_commands/demo/src/main/resources/mock_turbine.py";
        // String output = executeCommand(command);
        // System.out.println(output);

        // start python script in separate thread and print output continuously
        Thread thread = new Thread(() -> {
            String output = executeCommand(command);
            while (true) {
                System.out.println(output);
                output = executeCommand(command);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        
    }

    public static String executeCommand(String command) {
        StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine())!= null) {
                System.out.println(line);
                output.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}