class Timer {
  private static Timer instance = null;
  public long elapsed;
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

  public static synchronized Timer getInstrance() {
    if (instance == null) {
      instance = new Timer();
    }
    return instance;
  }
}
