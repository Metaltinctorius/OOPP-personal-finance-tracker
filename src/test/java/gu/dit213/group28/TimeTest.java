package gu.dit213.group28;

import static org.junit.jupiter.api.Assertions.*;

import gu.dit213.group28.model.Time;
import gu.dit213.group28.model.enums.Speed;
import gu.dit213.group28.model.interfaces.Itimer;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;

public class TimeTest {

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
    assertEquals(3, time.getCurrentTick());
  }

  @Test
  public void timePauseTest() throws InterruptedException {
    assertEquals(0, time.getCurrentTick());
    time.start();
    TimeUnit.MILLISECONDS.sleep(119);
    time.pause();
    TimeUnit.MILLISECONDS.sleep(119);
    assertEquals(3, time.getCurrentTick());
  }

  @Test
  public void timeUpdateTest() throws InterruptedException {
    assertEquals(0, time.getCurrentTick());
    assertTrue(time.isNextReady());
    assertFalse(time.isNextReady());
    time.start();
    TimeUnit.MILLISECONDS.sleep(3320);
    assertTrue(time.isNextReady());
    assertFalse(time.isNextReady());
  }

  @Test
  public void timeSlowTest() throws InterruptedException {
    time.setThreshold(Speed.SLOW);
    time.isNextReady();
    time.start();
    assertFalse(time.isNextReady());
    TimeUnit.MILLISECONDS.sleep(9920);
    assertTrue(time.isNextReady());
  }

  @Test
  public void timeNormalTest() throws InterruptedException {
    time.setThreshold(Speed.NORMAL);
    time.isNextReady();
    time.start();
    assertFalse(time.isNextReady());
    TimeUnit.MILLISECONDS.sleep(3320);
    assertTrue(time.isNextReady());
  }

  @Test
  public void timeFastTest() throws InterruptedException {
    time.setThreshold(Speed.FAST);
    time.isNextReady();
    time.start();
    assertFalse(time.isNextReady());
    TimeUnit.MILLISECONDS.sleep(1010);
    assertTrue(time.isNextReady());
  }

  @Test
  public void timeChangeFastToSlowTest() throws InterruptedException {
    time.setThreshold(Speed.FAST);
    time.isNextReady();
    time.start();
    assertFalse(time.isNextReady());
    TimeUnit.MILLISECONDS.sleep(515);
    assertFalse(time.isNextReady());
    time.setThreshold(Speed.SLOW);
    assertFalse(time.isNextReady());
    TimeUnit.MILLISECONDS.sleep(5010);
    assertTrue(time.isNextReady());
  }

  @Test
  public void timeChangeSlowToFastTest() throws InterruptedException {
    time.setThreshold(Speed.SLOW);
    time.isNextReady();
    time.start();
    assertFalse(time.isNextReady());
    TimeUnit.MILLISECONDS.sleep(5010);
    assertFalse(time.isNextReady());
    time.setThreshold(Speed.FAST);
    assertFalse(time.isNextReady());
    TimeUnit.MILLISECONDS.sleep(515);
    assertTrue(time.isNextReady());
  }

  @BeforeEach
  public void setUp() {
    time = new Time();
    time.initTime();
  }
}
