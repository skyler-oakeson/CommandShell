package org.example;

class Timer {
  private static Timer instance = null;
  private long elapsed;
  private long start;

  private Timer() {
    elapsed = 0;
    start = 0;
  }

  public void startTimer() {
    start = System.currentTimeMillis();
  }

  public void endTimer() {
    elapsed += System.currentTimeMillis() - start;
  }

  public long getElapsed() {
    return elapsed;
  }

  public static synchronized Timer getInstance() {
    if (instance == null) {
      instance = new Timer();
    }
    return instance;
  }
}
