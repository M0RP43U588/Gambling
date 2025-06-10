import java.util.Random;
import java.util.Scanner;

/* TODO:
 * - Function that gets previous score
 *   Function for animation
 *   Other functions
 *   Maybe find out how to clear console */

public class Roulette {
  public static final double VERSION=0.22;
  // constants for text manipulation in terminal
  public static final String RESET = "\033[0m";
  public static final String BOLD = "\033[1m";
  public static final String UNDERLINE = "\033[4m";
  public static final String REVERSED = "\033[7m";

  public static final String BLACK = "\033[0;30m";
  public static final String RED = "\033[0;31m";
  public static final String GREEN = "\033[0;32m";
  public static final String YELLOW = "\033[0;33m";
  public static final String BLUE = "\033[0;34m";
  public static final String PURPLE = "\033[0;35m";
  public static final String CYAN = "\033[0;36m";
  public static final String WHITE = "\033[0;37m";

  public static final String BLACK_BACKGROUND = "\033[40m";
  public static final String RED_BACKGROUND = "\033[41m";
  public static final String GREEN_BACKGROUND = "\033[42m";
  public static final String YELLOW_BACKGROUND = "\033[43m";
  public static final String BLUE_BACKGROUND = "\033[44m";
  public static final String PURPLE_BACKGROUND = "\033[45m";
  public static final String CYAN_BACKGROUND = "\033[46m";
  public static final String WHITE_BACKGROUND = "\033[47m";

  // define arrays with numbers
  public static final int[] REDNUMS             = { 1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36 };
  public static final int[] BLACKNUMS           = { 2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35 };
  public static final int[] ODDNUMS             = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35 };
  public static final int[] EVENNUMS            = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36 };
  public static final int[] FIRSTTWELVE         = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
  public static final int[] SECONDTWELVE        = { 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24 };
  public static final int[] THIRDTWELVE         = { 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36 };
  public static final int[] FIRSTCOL            = { 1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34 };
  public static final int[] SECONDCOL           = { 2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35 };
  public static final int[] THIRDCOL            = { 3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36 };
  public static final int[] ONE2EIGHTEEN        = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 ,13, 14, 15, 16, 17, 18 };
  public static final int[] NINETEEN2THIRTYSIX  = {19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36 };

  // start money
  public static int umoney = 1000;

  // create scanner object
  static Scanner scnr = new Scanner(System.in);

  public static void start() {
    System.out.printf("Roulette version: %.02f\n", VERSION);
    System.out.println("/--------------------------\\");
    System.out.println("|   Type \"go\" to start     | ");
    System.out.println("|--------------------------|");
    System.out.println("|  Type \"info\" for into    | ");
    System.out.println("\\--------------------------/ ");

    // get user answer
    String stansw = scnr.nextLine();
    if (stansw.equals("info")) {
      info();
    }
    // TODO: Add function call for function that gets previous highscore
}

// random int function
public static int rand() {
    Random randint = new Random();
    return randint.nextInt(37);
}

// information function
public static void info() {
    System.out.println("placeholder for game information");
}

// main function
public static void main(String args[]) {
    start();
    scnr.close();
  }

  // close scanner
} 
