package gu.dit213.group28;

public class TimeTest {

  /*
   * These tests take a long time so we've commented them out
   */
  /*
  private Itimer time;

  @Test
  public void timeInitTest() throws InterruptedException {
    assertEquals(0, time.getCurrentTick());
    TimeUnit.MILLISECONDS.sleep(119);
    assertEquals(0, time.getCurrentTick());
  }

  @Test
  public void timeStartTest() throws InterruptedException {
    assertEquals(0, time.getCurrentTick());
    time.start();
    TimeUnit.MILLISECONDS.sleep(119);
    assertEquals(7, time.getCurrentTick());
  }

  @Test
  public void timePauseTest() throws InterruptedException {
    assertEquals(0, time.getCurrentTick());
    time.start();
    TimeUnit.MILLISECONDS.sleep(119);
    time.pause();
    assertEquals(7, time.getCurrentTick(), 1);
    TimeUnit.MILLISECONDS.sleep(119);
    assertEquals(7, time.getCurrentTick(), 1);
  }

  @Test
  public void timeUpdateTest() throws InterruptedException {
    assertEquals(0, time.getCurrentTick());
    time.start();
    assertTrue(time.next());
  }

  @Test
  public void timeSlowTest() throws InterruptedException {
    time.setThreshold(Speed.SLOW);
    time.start();
    assertTrue(time.next());
  }

  @Test
  public void timeNormalTest() throws InterruptedException {
    time.setThreshold(Speed.NORMAL);
    time.start();
    assertTrue(time.next());
  }

  @Test
  public void timeFastTest() throws InterruptedException {
    time.setThreshold(Speed.FAST);
    time.start();
    assertTrue(time.next());
  }

  @Test
  public void timeChangeFastToSlowTest() throws InterruptedException {
    time.setThreshold(Speed.FAST);

    time.start();
    TimeUnit.MILLISECONDS.sleep(1650);
    System.out.println(time.getCurrentTick());
    time.setThreshold(Speed.SLOW);
    System.out.println(time.getCurrentTick());
    assertTrue(time.next());
  }

  @Test
  public void timeChangeSlowToFastTest() throws InterruptedException {
    time.setThreshold(Speed.SLOW);
    time.start();
    TimeUnit.MILLISECONDS.sleep(16500);
    // System.out.println(time.getCurrentTick());
    time.setThreshold(Speed.FAST);
    // System.out.println(time.getCurrentTick());
    assertTrue(time.next());
  }

  @BeforeEach
  public void setUp() {
    time = new Time();
    time.initTime();
  }

  @AfterEach
  public void tearDown() {
    time = null;
  }*/
}
