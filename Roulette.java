import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.Thread;

public class Roulette {
  static final double VERSION=1.00;
  // constants for text manipulation in terminal
  static final String RESET = "\033[0m";
  static final String BOLD = "\033[1m";
  static final String UNDERLINE = "\033[4m";
  static final String REVERSED = "\033[7m";

  static final String BLACK = "\033[0;30m";
  static final String RED = "\033[0;31m";
  static final String GREEN = "\033[0;32m";
  static final String YELLOW = "\033[0;33m";
  static final String BLUE = "\033[0;34m";
  static final String PURPLE = "\033[0;35m";
  static final String CYAN = "\033[0;36m";
  static final String WHITE = "\033[0;37m";

  static final String BLACK_BACKGROUND = "\033[40m";
  static final String RED_BACKGROUND = "\033[41m";
  static final String GREEN_BACKGROUND = "\033[42m";
  static final String YELLOW_BACKGROUND = "\033[43m";
  static final String BLUE_BACKGROUND = "\033[44m";
  static final String PURPLE_BACKGROUND = "\033[45m";
  static final String CYAN_BACKGROUND = "\033[46m";
  static final String WHITE_BACKGROUND = "\033[47m";

  static final String CLAER = "\033[H\033[2J";

  // 50%
  static final int[] REDNUMS             = { 1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36 };
  static final int[] BLACKNUMS           = { 2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35 };
  static final int[] ODDNUMS             = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35 };
  static final int[] EVENNUMS            = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36 };
  static final int[] ONE2EIGHTEEN        = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 ,13, 14, 15, 16, 17, 18 };
  static final int[] NINETEEN2THIRTYSIX  = { 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36 };

  // 33.3%
  static final int[] FIRSTTWELVE         = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
  static final int[] SECONDTWELVE        = { 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24 };
  static final int[] THIRDTWELVE         = { 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36 };
  static final int[] FIRSTCOL            = { 1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34 };
  static final int[] SECONDCOL           = { 2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35 };
  static final int[] THIRDCOL            = { 3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36 };
  static final int[] ROWONE              = { 1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34 };
  static final int[] ROWTWO              = { 2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35 };
  static final int[] ROWTHREE            = { 3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36 };
  static final String[] AC               = { "first 12", "second 12", "third 12", "1-18", "even", "red", "black", "odd", "19-36", "z", "row 1",  "row 2", "row 3"};

  // start money
  static int z = 0;
  static int umoney = 1000;

  public static int highscore = 0;
  static int multiplyer = 0;
  static String istr = """
    Red or Black
    You bet on the color of the number.
    The "0" is green and automatically means a loss for a color bet.
    Win: Double your stake (payout 2 ×).

    Even or Odd
    You bet on whether the number is even or odd.
    The "0" is also considered a loss here.
    Win: Double your stake (2 ×).

    Low (1–18) or High (19–36)
    You bet on which of the two blocks the number falls into.
    The "0" results in a loss.
    Win: Double your stake (2 ×).

    Rows (1st, 2nd, or 3rd Row)
    1st Row: Numbers 1, 4, 7, …, 34
    2nd Row: Numbers 2, 5, 8, …, 35
    3rd Row: Numbers 3, 6, 9, …, 36
    The "0" does not belong to any row.
    Win: Triple your stake (3 ×).

    Dozen (1–12, 13–24, or 25–36)
    You choose one of the three dozens.
    The "0" results in a loss.
    Win: Triple your stake (3 ×).

    Single Number (Straight Up)
    You bet on exactly one number (1–36).
    If your number hits exactly, you win 36 times your stake.
      """;
  
  // create scanner object
  static Scanner scnr = new Scanner(System.in);

  static void start() { // method where it all starts
    if (z == 0) {
      claer();
      z++;
      highscore = hs.r();
      System.out.printf("Roulette version: %.02f\n", VERSION);
      System.out.println("/--------------------------\\");
      System.out.println("|   Type \"go\" to start     | ");
      System.out.println("|--------------------------|");
      System.out.println("|  Type \"info\" for info    | ");
      System.out.println("\\--------------------------/ ");

      // get user answer
      String stansw = scnr.nextLine().trim();
      if (stansw.toLowerCase().equals("info")) {
        info();
      }
    }
  }

  static void pm(int ms) { // delay method that takes ms
    try {
      Thread.sleep(ms);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  static String[] uir = new String[2];
  static String uc;
  static int  ucs2;
  static int uba;

  static String[] userinput() { // method that gets the betting target and amount of the user
    claer();
    System.out.println("-------------------------|" + GREEN_BACKGROUND + " 0 " + RESET + "|----------------------------");
    System.out.println("|" + RED_BACKGROUND + " 3 " + RESET + "|" + BLACK_BACKGROUND + " 6 " + RESET + "|"+ RED_BACKGROUND + " 9 " + RESET + "|" + RED_BACKGROUND + " 12 " + RESET + "|" + BLACK_BACKGROUND+ " 15 " + RESET + "|" + RED_BACKGROUND + " 18 " + RESET + "|" + RED_BACKGROUND + " 21 " + RESET + "|"+ BLACK_BACKGROUND + " 24 " + RESET + "|" + RED_BACKGROUND + " 27 " + RESET + "|" + RED_BACKGROUND+ " 30 " + RESET + "|" + BLACK_BACKGROUND + " 33 " + RESET + "|" + RED_BACKGROUND + " 36 " + RESET+ "| <- Row 3");
    System.out.println("|" + BLACK_BACKGROUND + " 2 " + RESET + "|" + RED_BACKGROUND + " 5 " + RESET + "|"+ BLACK_BACKGROUND + " 8 " + RESET + "|" + BLACK_BACKGROUND + " 11 " + RESET + "|" + RED_BACKGROUND+ " 14 " + RESET + "|" + BLACK_BACKGROUND + " 17 " + RESET + "|" + BLACK_BACKGROUND + " 20 " + RESET+ "|" + RED_BACKGROUND + " 23 " + RESET + "|" + BLACK_BACKGROUND + " 26 " + RESET + "|"+ BLACK_BACKGROUND + " 29 " + RESET + "|" + RED_BACKGROUND + " 32 " + RESET + "|" + BLACK_BACKGROUND+ " 35 " + RESET + "| <- Row 2");
    System.out.println("|" + RED_BACKGROUND + " 1 " + RESET + "|" + BLACK_BACKGROUND + " 4 " + RESET + "|"+ RED_BACKGROUND + " 7 " + RESET + "|" + BLACK_BACKGROUND + " 10 " + RESET + "|" + BLACK_BACKGROUND+ " 13 " + RESET + "|" + RED_BACKGROUND + " 16 " + RESET + "|" + RED_BACKGROUND + " 19 " + RESET + "|"+ BLACK_BACKGROUND + " 22 " + RESET + "|" + RED_BACKGROUND + " 25 " + RESET + "|" + BLACK_BACKGROUND+ " 28 " + RESET + "|" + BLACK_BACKGROUND + " 31 " + RESET + "|" + RED_BACKGROUND + " 34 " + RESET+ "| <- Row 1");
    System.out.println("----------------------------------------------------------");
    System.out.println("|     First 12    |     Second 12     |     Third 12     |");
    System.out.println("----------------------------------------------------------");
    System.out.println("| 1-18  |   Even  |" + RED_BACKGROUND + "   Red   " + RESET + "|" + BLACK_BACKGROUND+ " Black " + RESET + "|   Odd   |   19-36  |");
    System.out.println("----------------------------------------------------------");
    System.out.println("|                  Specific number ('z')                 |");
    System.out.println("----------------------------------------------------------");
    System.out.println("You have: " + YELLOW + umoney + " CHF" + RESET);
    System.out.print("On what do you want to bet: ");

    do { // get user target
      uc = scnr.nextLine().trim().toLowerCase(); 
      if (!(Arrays.asList(AC).contains(uc))) {System.out.print(uc + " is invalid, try again: ");}
    } while (!(Arrays.asList(AC).contains(uc)));

    if (uc.equals("z")) { // get exact number if the target is z
      do {
        System.out.print("Enter a number from 0-36: "); 
        while (!scnr.hasNextInt()) {
          System.out.print("Please enter a number: ");
          scnr.next();
        }
        ucs2 = scnr.nextInt(); 
      } while (ucs2 < 0 || ucs2 > 36);
      uir[0] = String.valueOf(ucs2);
    } else {
      uir[0] = uc;
    }

    do {
      System.out.print("How much do you want to be on " + uc + " ? ");

      while (!scnr.hasNextInt()) {
        System.out.print("Please enter a valid number: ");
        scnr.next();
      }

      uba = scnr.nextInt();

      if (uba > umoney) {
        System.out.println("Your bet is too high. " + umoney + " is your current balance, try again.");
      } else if (uba < 1) {
        System.out.println("Your bet must be at least 1, try again.");
      }

    } while (uba > umoney || uba < 1);
    uir[1] = String.valueOf(uba); // insert into array
    return uir;
  }

  static int rand() { // method that generates a random integer
    Random randint = new Random();
    return randint.nextInt(0, 37);
  }

  static void info() { // method that prints information
    claer();
    System.out.println(istr);
    System.out.println("/----------------------------------------------------\\");
    System.out.println("|                Are you Ready?: ('y')               |");
    System.out.println("\\---------------------------------------------------/");
    if (scnr.nextLine().trim().trim().toLowerCase().contentEquals("y")) {
      start();
    }
    else {
      info();
    }
  }

  static void claer() { // method that clears terminal screen using the CLAER constant
    System.out.println(CLAER);
  }

    static void anim(int antagonist) { // animation powered by ansi codes
        int i = 0;
        String texture1 = "   ";                    
        String texture2 = "" + WHITE_BACKGROUND + " o " + RESET + "";
        while (i <= (antagonist + 111)) {
            String[] slots = new String[37];
            int target = i % 37;

            for (int idx = 0; idx < 37; idx++) {
                slots[idx] = (idx == target)
                 ? texture2
                 : texture1;
            }
            claer();
            System.out.println("                       ________________________________                       ");
            System.out.println("                     /      " + slots[36] + RED_BACKGROUND + " 36 "    + RESET + "    " + GREEN_BACKGROUND + " 0 " + RESET + slots[0] + "  " + RED_BACKGROUND + " 1 "    + RESET + slots[1] + "   \\");
            System.out.println("                  / " + slots[35] + BLACK_BACKGROUND + " 35 " + RESET    + "                           " + BLACK_BACKGROUND + " 2 " + RESET + slots[2]    + " \\");
            System.out.println("              /  " + slots[34] + RED_BACKGROUND + " 34 " + RESET    + "                                 " + RED_BACKGROUND + " 3 " + RESET + slots[3]    + "  \\");
            System.out.println("          /   " + slots[33] + BLACK_BACKGROUND + " 33 " + RESET    + "                                       " + BLACK_BACKGROUND + " 4 " + RESET + slots[4]    + "  \\");
            System.out.println("         /   " + slots[32] + RED_BACKGROUND + " 32 " + RESET    + "                                         " + RED_BACKGROUND + " 5 " + RESET + slots[5]    + "   \\");
            System.out.println("      /   " + slots[31] + BLACK_BACKGROUND + " 31 " + RESET    + "                                              " + BLACK_BACKGROUND + " 6 " + RESET    + slots[6] + "  \\");
            System.out.println("     /  " + slots[30] + RED_BACKGROUND + " 30 " + RESET    + "                                                 " + RED_BACKGROUND + " 7 " + RESET    + slots[7] + "  \\");
            System.out.println("    /  " + slots[29] + BLACK_BACKGROUND + " 29 " + RESET    + "                                                   " + BLACK_BACKGROUND + " 8 " + RESET    + slots[8] + "  \\");
            System.out.println("    |  " + slots[28] + BLACK_BACKGROUND + " 28 " + RESET    + "                                                   " + RED_BACKGROUND + " 9 " + RESET    + slots[9] + "  |");
            System.out.println("    \\  " + slots[27] + RED_BACKGROUND + " 27 " + RESET    + "                                                  " + BLACK_BACKGROUND + " 10 " + RESET    + slots[10] + " /");
            System.out.println("     \\  " + slots[26] + BLACK_BACKGROUND + " 26 " + RESET    + "                                                " + BLACK_BACKGROUND + " 11 " + RESET    + slots[11] + " /");
            System.out.println("      \\  " + slots[25] + RED_BACKGROUND + " 25 " + RESET    + "                                              " + RED_BACKGROUND + " 12 " + RESET    + slots[12] + " /");
            System.out.println("        \\  " + slots[24] + BLACK_BACKGROUND + " 24 " + RESET    + "                                          " + BLACK_BACKGROUND + " 13 " + RESET + slots[13]    + " /  ");
            System.out.println("         \\  " + slots[23] + RED_BACKGROUND + " 23 " + RESET    + "                                        " + RED_BACKGROUND + " 14 " + RESET + slots[14]    + " /");
            System.out.println("            \\  " + slots[22] + BLACK_BACKGROUND + " 22 " + RESET    + "                                  " + BLACK_BACKGROUND + " 15 " + RESET + slots[15]    + "  /");
            System.out.println("                \\ " + slots[21] + RED_BACKGROUND + " 21 " + RESET    + "                            " + RED_BACKGROUND + " 16 " + RESET + slots[16]    + "  /");
            System.out.println("                   \\  " + slots[20] + BLACK_BACKGROUND + " 20 "    + RESET + "                     " + BLACK_BACKGROUND + " 17 " + RESET + slots[17]    + " /");
            System.out.println("                          \\   " + slots[19] + RED_BACKGROUND + " 19 "    + RESET + "     " + RED_BACKGROUND + " 18 " + RESET + slots[18]    + "       /");
            System.out.println("                           -----------------------------             "                   + "                "); // 20
            i++;
            pm(25); 
        }
    }
  
  static int checker(String[] x, int rnum) { // returns the multiplyer
    String ucc = x[0];
    int arr[] = {0};
    multiplyer = 0;

    try {
      Integer.parseInt(ucc);
      if (rnum == Integer.valueOf(ucc)) {
        return 36;
      } else {
        return 0;
      }
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }

    switch (ucc) { // optimized switch statement
      case "even":
        if (rnum % 2 == 0) return 1;
        else return 0;
      case "odd":
        if (rnum % 2 == 1) return 1;
        else return 0;
      case "red":
        arr = REDNUMS;
        break;
      case "black":
        arr = BLACKNUMS;
        break;
      case "1-18":
        arr = ONE2EIGHTEEN;
        break;
      case "19-36":
        arr = NINETEEN2THIRTYSIX;
        break;
      case "first 12":
        arr = FIRSTTWELVE;
        break;
      case "second 12":
        arr = SECONDTWELVE;
        break;
      case "third 12":
        arr = THIRDTWELVE;
        break;
      case "row 1":
        arr = ROWONE;
        break;
      case "row 2":
        arr = ROWTWO;
        break;
      case "row 3":
        arr = ROWTHREE;
        break;
      default:
        System.out.println("hacker"); // this case is impossible to reach under normal circustances
        break;
    }

    for (int i : arr) { // iterate over array
      if (i == rnum) {
        if (arr == REDNUMS || arr == BLACKNUMS || arr == ONE2EIGHTEEN || arr == NINETEEN2THIRTYSIX) {multiplyer = 1;} else {multiplyer = 2;}
      }
    }

    return multiplyer;
  }

  static void worl(int m) { // method that carries out the win or loose actions
    if (m == 0) {
      System.out.println("You have... " + RED + "lost" + RESET + " :/ ");
      umoney = (umoney - (Integer.valueOf(uir[1])));
    } else {
      System.out.println("You have won: + " + (Integer.valueOf(uir[1]) * multiplyer) + GREEN + "  \\^o^/" + RESET);
      umoney = (umoney + (Integer.valueOf(uir[1]) * multiplyer));
      if (umoney > highscore) {highscore = umoney;
      System.out.println("Your score is: " + YELLOW + umoney + RESET + GREEN+"You beat the highscore, good job"+RESET);
      }
    }
  }

  static int xit() { // method for end of round and termination of program
    System.out.println("/----------------------------------------------------\\");        
    System.out.println("|          Do you want to play again? (y/N)           |");
    System.out.println("\\---------------------------------------------------/");

    String string = scnr.nextLine().trim();
    if (! string.equalsIgnoreCase("y")) {
    System.out.println("/----------------------------------------------------\\");        
    System.out.println("|               You played " +z+ " times              |");
    System.out.println("\\---------------------------------------------------/");
    hs.w(highscore);
    return 0;
    } else {
      hs.w(highscore);
      umoney = 1000;
      main(null);
      return 0;
    }
  }

  public static void main(String args[]) { // main method
    start();
    int rnum = rand();
    multiplyer = checker(userinput(), rnum);
    anim(rnum);
    pm(1000);
    worl(multiplyer);
    pm(2000);
    if (umoney == 0) {
      scnr.nextLine().trim();
      xit();
    } else {
      scnr.nextLine().trim();
      main(null);
    }
  }
}
