package org.example;

import java.util.ArrayList;


public class History extends Command {

  @Override
  public boolean execute(String[] args) {
    ArrayList<String> history = ShellEnv.getInstance().getHistory();
    for (int i = 0; i < history.size(); i++) {
      System.out.printf("%d. %s\n", i, history.get(i));
    }
    return true;
  }
}


