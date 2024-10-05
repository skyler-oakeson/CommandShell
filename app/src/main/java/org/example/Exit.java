package org.example;

public class Exit extends Command {

  @Override
  public boolean execute(String[] args) {
    ShellEnv env = ShellEnv.getInstance();
    env.shutDown();
    return true;
  }
}
