package org.example;
import java.lang.ProcessBuilder;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;

public class Pipe extends Command {

  @Override
  public boolean execute(String[] args) {
    
    ArrayList<String[]> cmds = splitCmds(args);

    ProcessBuilder pb1 = new ProcessBuilder(cmds.get(0));
    ProcessBuilder pb2 = new ProcessBuilder(cmds.get(1));

    // Use the parent process's I/O channels
    pb1.redirectInput(ProcessBuilder.Redirect.INHERIT);
    pb2.redirectOutput(ProcessBuilder.Redirect.INHERIT);

    try {
      Process p1 = pb1.start();
      Process p2 = pb2.start();

      java.io.InputStream in = p1.getInputStream();
      java.io.OutputStream out = p2.getOutputStream();

      int data;
      while ((data = in.read()) != -1) { 
        out.write(data);
      }

      out.flush();
      out.close();

      p1.waitFor();
      p2.waitFor();
      return true;
    }
    catch (Exception ex) {
      return false;
    }
  }

  private ArrayList<String[]> splitCmds(String[] args) {
    ArrayList<String[]> split = new ArrayList<String[]>();
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("|")) {
        split.add(Arrays.copyOfRange(args, 0, i));
        split.add(Arrays.copyOfRange(args, i+1, args.length));
      }
    }
    return split;
  }
}
