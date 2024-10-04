package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.io.IOException;

public class ShellEnv {
    private static ShellEnv instance = null;
    private ArrayList<String> history;
    private boolean running;
    private HashMap<String, Command> cmds;

    private ShellEnv() {
      running = true;
      cmds = new HashMap<String, Command>();
      history = new ArrayList<String>();

      cmds.put("ls", new Ls());
      cmds.put("list", new Ls());
      cmds.put("cd", new Cd());
      cmds.put("mdir", new Mdir());
      cmds.put("rdir", new Rdir());
      cmds.put("history", new History());
      cmds.put("^", new RunHist());
      //cmds.put("ptime", new Ptime());
      //cmds.put("exit", new Exit());
    }

    public static synchronized ShellEnv getInstance() {
      if (instance == null) {
        instance = new ShellEnv();
      }
      return instance;
    }
    
    public String getHistoryVal(int index) throws IOException {
      if (index > history.size()) {
        throw new IOException();
      }
      return history.get(index);
    }

    public boolean isRunning() {
      return running;
    }

    public void toggleRunning() {
      running = !running;
    }

    public ArrayList<String> getHistory() {
      return history;
    }

    public void executeCmd(String input) {
      String[] args = splitCommand(input);
      if (cmds.containsKey(args[0])) {
        boolean res = cmds.get(args[0]).execute(args);
        if (res) history.add(input);
      } else {
      }
    }

    /**
     * Split the user command by spaces, but preserving them when inside
     * double-quotes.
     * Code Adapted from:
     * https://stackoverflow.com/questions/366202/regex-for-splitting-a-string-using-space-when-not-surrounded-by-single-or-double
     */
    public static String[] splitCommand(String command) {
      java.util.List<String> matchList = new java.util.ArrayList<>();

      Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
      Matcher regexMatcher = regex.matcher(command);
      while (regexMatcher.find()) {
        if (regexMatcher.group(1) != null) {
          // Add double-quoted string without the quotes
          matchList.add(regexMatcher.group(1));
        } else if (regexMatcher.group(2) != null) {
          // Add single-quoted string without the quotes
          matchList.add(regexMatcher.group(2));
        } else {
          // Add unquoted word
          matchList.add(regexMatcher.group());
        }
      }

      return matchList.toArray(new String[matchList.size()]);
    }
}
