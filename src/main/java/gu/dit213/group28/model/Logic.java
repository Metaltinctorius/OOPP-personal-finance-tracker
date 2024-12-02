package gu.dit213.group28.model;

import gu.dit213.group28.model.interfaces.Icontrollable;
import gu.dit213.group28.model.interfaces.Iobserver;
import java.util.List;

public class Logic extends Observable implements Icontrollable {

  /**
   * Very temporary model, only intended to show relationship between the different components of
   * MVC.
   */
  private String bee = "";

  public void beeOne() {
    bee =
        "                       \\     /\n"
            + "               \\      o ^ o     /\n"
            + "                 \\ (           ) /\n"
            + " ______(%%%%%%%)______\n"
            + "(     /   /  )%%%%%%%(  \\   \\     )\n"
            + "(___/___/__/           \\__\\___\\___)\n"
            + "   (     /  /(%%%%%%%)\\  \\     )\n"
            + "   (_/__/ (%%%%%%%) \\__\\_)\n"
            + "            /(                      )\\\n"
            + "          /     (%%%%%)     \\\n"
            + "                   (%%%)\n"
            + "                         !";
    notifyObservers();
  }

  public void beeTwo() {
    bee =
        "                .-~~~-.\n"
            + "              /        }\n"
            + "             /      .-~\n"
            + "            |        }\n"
            + "___\\.~~-.-~|     .-~_\n"
            + "    { O  |  ` .-~.   ; ~-.__\n"
            + "     ~--~/-|_\\|  :   : .-~\n"
            + "        /  |  \\~ - - ~\n"
            + "       /   |   \\";
    notifyObservers();
  }

  /**
   * Method for updating the view, there can be more than one of these methods It is possible that
   * we will only ever have one view, in which case the for-loop would be unnecessary. But it
   * shouldn't matter for now.
   */
  private void notifyObservers() {
    for (Iobserver observer : observers) {
      observer.update(bee);
    }
  }

  @Override
  public void insertTransaction(List<String> args) {}
}
