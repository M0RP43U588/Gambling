import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class hs {
  static void w(int hs) {
    try {
      File hsf = new File("hs.txt");
      hsf.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      FileWriter hsw = new FileWriter("hs.txt");
      hsw.write(String.valueOf(hs));
      hsw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static int r() {
    int h;
    try {
      File hsf = new File("hs.txt");
      Scanner scnr = new Scanner(hsf);
      h = scnr.nextInt();
      scnr.close();
    } catch (FileNotFoundException e) {
      w(0);
      h = 0;
    }
    return h;
  }
}
