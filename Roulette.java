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
  static final String[] AC               = { "erste 12", "zweite 12", "dritte 12", "1-18", "gerade", "rot", "schwarz", "ungerade", "19-36", "z", "reihe 1",  "reihe 2", "reihe 3"};

  // start money
  static int z = 0;
  static int umoney = 1000;

  public static int highscore = 0;
  static int multiplyer = 0;
  static String istr = """
  Rot oder Schwarz
      Du wettest auf die Farbe der Zahl.
      Die “0” ist grün und bedeutet bei einer Farb­wette automatisch Verlust.
      Gewinn: Verdoppelung deines Einsatzes (Auszahlung 2 ×).

  Gerade oder Ungerade
      Du wettest darauf, ob die Zahl gerade oder ungerade ist.
      Die “0” gilt hier ebenfalls als Verlust.
      Gewinn: Verdoppelung deines Einsatzes (2 ×).

  Niedrig (1–18) oder Hoch (19–36)
      Du wettest, in welchem der beiden Blöcke die Zahl liegt.
      Die “0” führt zum Verlust.
      Gewinn: Verdoppelung deines Einsatzes (2 ×).

  Reihen (1., 2. oder 3. Reihe)
          Reihe: Zahlen 1, 4, 7, …, 34
          Reihe: Zahlen 2, 5, 8, …, 35
          Reihe: Zahlen 3, 6, 9, …, 36
      Die “0” gehört zu keiner Reihe.
      Gewinn: Verdreifachung deines Einsatzes (3 ×).

  Dutzend (1.–12., 13.–24. oder 25.–36.)
      Du wählst eines der drei Dutzende aus.
      Die “0” führt zum Verlust.
      Gewinn: Verdreifachung deines Einsatzes (3 ×).

  Einzelzahl (Straight Up)
      Du wettest auf genau eine Zahl (1–36).
      Trifft genau deine Zahl, gewinnst du das 36-Fache deines Einsatzes.

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
      System.out.println("|  Type \"info\" for into    | ");
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
    System.out.println("|" + RED_BACKGROUND + " 3 " + RESET + "|" + BLACK_BACKGROUND + " 6 " + RESET + "|"+ RED_BACKGROUND + " 9 " + RESET + "|" + RED_BACKGROUND + " 12 " + RESET + "|" + BLACK_BACKGROUND+ " 15 " + RESET + "|" + RED_BACKGROUND + " 18 " + RESET + "|" + RED_BACKGROUND + " 21 " + RESET + "|"+ BLACK_BACKGROUND + " 24 " + RESET + "|" + RED_BACKGROUND + " 27 " + RESET + "|" + RED_BACKGROUND+ " 30 " + RESET + "|" + BLACK_BACKGROUND + " 33 " + RESET + "|" + RED_BACKGROUND + " 36 " + RESET+ "| <- Reihe 3");
    System.out.println("|" + BLACK_BACKGROUND + " 2 " + RESET + "|" + RED_BACKGROUND + " 5 " + RESET + "|"+ BLACK_BACKGROUND + " 8 " + RESET + "|" + BLACK_BACKGROUND + " 11 " + RESET + "|" + RED_BACKGROUND+ " 14 " + RESET + "|" + BLACK_BACKGROUND + " 17 " + RESET + "|" + BLACK_BACKGROUND + " 20 " + RESET+ "|" + RED_BACKGROUND + " 23 " + RESET + "|" + BLACK_BACKGROUND + " 26 " + RESET + "|"+ BLACK_BACKGROUND + " 29 " + RESET + "|" + RED_BACKGROUND + " 32 " + RESET + "|" + BLACK_BACKGROUND+ " 35 " + RESET + "| <- Reihe 2");
    System.out.println("|" + RED_BACKGROUND + " 1 " + RESET + "|" + BLACK_BACKGROUND + " 4 " + RESET + "|"+ RED_BACKGROUND + " 7 " + RESET + "|" + BLACK_BACKGROUND + " 10 " + RESET + "|" + BLACK_BACKGROUND+ " 13 " + RESET + "|" + RED_BACKGROUND + " 16 " + RESET + "|" + RED_BACKGROUND + " 19 " + RESET + "|"+ BLACK_BACKGROUND + " 22 " + RESET + "|" + RED_BACKGROUND + " 25 " + RESET + "|" + BLACK_BACKGROUND+ " 28 " + RESET + "|" + BLACK_BACKGROUND + " 31 " + RESET + "|" + RED_BACKGROUND + " 34 " + RESET+ "| <- Reihe 1");
    System.out.println("----------------------------------------------------------");
    System.out.println("|    Erste 12    |     Zweite 12     |     Dritte 12     |");
    System.out.println("----------------------------------------------------------");
    System.out.println("| 1-18  | Gerade |" + RED_BACKGROUND + "   Rot   " + RESET + "|" + BLACK_BACKGROUND+ " Schwarz " + RESET + "|Ungerade|   19-36  |");
    System.out.println("----------------------------------------------------------");
    System.out.println("|                   Belibige Zahl ('z')                  |");
    System.out.println("----------------------------------------------------------");
    System.out.println("Sie haben " + YELLOW + umoney + " CHF" + RESET);
    System.out.println("Auf was wollen Sie Wetten? ");

    do { // get user target
      uc = scnr.nextLine().trim().toLowerCase(); 
      if (!(Arrays.asList(AC).contains(uc))) {System.out.print(uc + " ist ungültig, versuchen Sie es noch einmal: ");}
    } while (!(Arrays.asList(AC).contains(uc)));

    if (uc.equals("z")) { // get exact number if the target is z
      do {
        System.out.print("Geben sie eine Zahl zwischen 0 - 36 ein: "); 
        while (!scnr.hasNextInt()) {
          System.out.print("Bitte geben sie eine Zahl ein: ");
          scnr.next();
        }
        ucs2 = scnr.nextInt(); 
      } while (ucs2 < 0 || ucs2 > 36);
      uir[0] = String.valueOf(ucs2);
    } else {
      uir[0] =uc;
    }

    System.out.print("Wieviel möchten sie einsetzen? ");
    do { // get amount user wants to bet
      while (!scnr.hasNextInt()) {
        System.out.print("Bitte geben sie eine Gültige Zahl an: ");
        scnr.next();
      }
      if (uba > umoney || uba < 1) {
        System.out.print("Ihre eingabe ist zu hoch, Ihr Kontostand beträgt." + umoney + "Geben Sie einen gültigen Betrag ein: ");
      }
      uba = scnr.nextInt();
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
    System.out.println("|                Sind sie bereit?: ('j')             |");
    System.out.println("\\---------------------------------------------------/");
    if (scnr.nextLine().trim().trim().toLowerCase().contentEquals("j")) {
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
      case "gerade":
        if (rnum % 2 == 0) return 1;
        else return 0;
      case "ungerade":
        if (rnum % 2 == 1) return 1;
        else return 0;
      case "rot":
        arr = REDNUMS;
        break;
      case "schwarz":
        arr = BLACKNUMS;
        break;
      case "1-18":
        arr = ONE2EIGHTEEN;
        break;
      case "19-36":
        arr = NINETEEN2THIRTYSIX;
        break;
      case "erste 12":
        arr = FIRSTTWELVE;
        break;
      case "zweite 12":
        arr = SECONDTWELVE;
        break;
      case "dritte 12":
        arr = THIRDTWELVE;
        break;
      case "reihe 1":
        arr = ROWONE;
        break;
      case "reihe 2":
        arr = ROWTWO;
        break;
      case "reihe 3":
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
      System.out.println("Sie haben ihre Wette " + RED + "verloren" + RESET + " :/ ");
      umoney = (umoney - (Integer.valueOf(uir[1])));
    } else {
      System.out.println("Sie haben + " + (Integer.valueOf(uir[1]) * multiplyer) + GREEN + " gewonnen" + RESET + " \\^o^/");
      umoney = (umoney + (Integer.valueOf(uir[1]) * multiplyer));
      if (umoney > highscore) {highscore = umoney;
      System.out.println("Ihr Score ist: " + YELLOW + umoney + RESET + GREEN+" Sie haben eine neue Gesamt-Highscore gesetzt, gut gemacht!"+RESET);
      }
    }
  }

  static int xit() { // method for end of round and termination of program
    System.out.println("/----------------------------------------------------\\");        
    System.out.println("|Geben sie an ob sie weiter spielen wollen: ('j'/'n')|");
    System.out.println("\\---------------------------------------------------/");

    String string = scnr.nextLine().trim();
    if (! string.equalsIgnoreCase("j")) {
    System.out.println("/----------------------------------------------------\\");        
    System.out.println("|           Sie haben "+z+" mal gespielt!              |");
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
