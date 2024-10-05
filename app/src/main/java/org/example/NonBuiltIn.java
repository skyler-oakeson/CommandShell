package org.example;
import java.lang.ProcessBuilder;
import java.io.File;
import java.io.IOException;

public class NonBuiltIn extends Command {
  @Override
  public boolean execute(String[] args) {
    Timer timer = Timer.getInstance();
    ProcessBuilder pb = new ProcessBuilder(args);

    pb.directory(new File(System.getProperty("user.dir"))); // Toggle this
    pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
    pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
    try {
      timer.startTimer();
      Process p = pb.start();
      p.waitFor();
      timer.endTimer();
      return true;
    }
    catch (IOException ex) {
        System.out.println("Illegal command");
        return false;
    }
    catch (Exception ex) {
        System.out.println("Something else bad happened");
        return false;
    }
  }
}
