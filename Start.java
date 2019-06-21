import java.util.Scanner;
import java.lang.Character;
public class Start {
   public static void main(String args[]) {
      printStart();
      System.out.println();
      makeBoard();
      System.out.println("Would you like to play?");
      System.out.println("If not type 'Done' at the end of any turn or press any letter and enter.");
      Scanner reader = new Scanner(System.in);  // Reading from System.in
      String q = reader.nextLine();
      int whosTurn = 0;
      while(!(q.equals("Done"))) {
         print();
         System.out.println("Enter an x cordinate: ");
         int x1 = reader.nextInt();
         System.out.println("Enter a y cordinate: ");
         int y1 = reader.nextInt();
         System.out.println("Enter the x cordinate you wish to move to: ");
         int x2 = reader.nextInt();
         System.out.println("Enter the y cordinate you wish to move to: ");
         int y2 = reader.nextInt();
         boolean legal = checkLegal(whosTurn, x1, y1, x2, y2);
         if(legal == false) {
            continue;
         }
         move(x1, y1,x2, y2, whosTurn);
         String k = reader.next();
         q = k;
      //0 is capitals turn and 1 is lower case turn
         if(whosTurn == 0) {
            whosTurn++;
         }
         else {
            whosTurn--;
         }
      }
      reader.close();
   }
   public static boolean checkLegal(int whosTurn, int x1, int y1, int x2, int y2) {
      boolean isEachPartTrue = false;
      int numTrue = 0; 
   //Checks to make sure your numbers are in the range
      if(x1+x2+y1+y2 <= 28 && x1+x2+y1+y2 >= 0) {
         numTrue++;
      }
      if(!(x1+x2+y1+y2 <= 28 && x1+x2+y1+y2 >= 0)) {
         System.out.println("The numbers you entered were too big or small.");
         return false;
      }
   //Checks if you are moving your own piece
      char c = board[y1][x1].charAt(0);
      if((Character.isUpperCase(c) && whosTurn == 0) || (!(Character.isUpperCase(c)) && whosTurn == 1)) {
         numTrue++;
      }
      if((Character.isUpperCase(c) && whosTurn == 1) || (!(Character.isUpperCase(c)) && whosTurn == 0)) {
         System.out.println("You are trying to move someone elses piece.");
         System.out.println("Nice Try.");
         return false;
      }
   //Checks if you are moving a piece in a legal direction 
      String a = board[y1][x1];
   //This is the method that needs work and it checks for a legal pawn move 
      int legalPieceMove = 0;
   
   //Pawns
      if(a.equals("P")) {
         if(legalP(x1, y1, x2, y2) == true) {
            legalPieceMove++;
         }
      }
      if(a.equals("p")) {
         if(legalp(x1, y1, x2, y2) == true) {
            legalPieceMove++;
         }
      }
   //Kings
      if(a.equals("K")) {
         if(legalK(x1, y1, x2, y2) == true) {
            legalPieceMove++;
         }
      }
      if(a.equals("k")) {
         if(legalk(x1, y1, x2, y2) == true) {
            legalPieceMove++;
         }
      }
   //Queens
      if(a.equals("Q")) {
         if(legalQ(x1, y1, x2, y2) == true) {
            legalPieceMove++;
         }
      }
      if(a.equals("q")) {
         if(legalq(x1, y1, x2, y2) == true) {
            legalPieceMove++;
         }
      }
   //Rooks
      if(a.equals("R")) {
         if(legalR(x1, y1, x2, y2) == true) {
            legalPieceMove++;
         }
      }  
      if(a.equals("r")) {
         if(legalr(x1, y1, x2, y2) == true) {
            legalPieceMove++;
         }
      }
   //Knights
      if(a.equals("N")) {
         if(legalN(x1, y1, x2, y2) == true) {
            legalPieceMove++;
         }
      }
      if(a.equals("n")) {
         if(legaln (x1, y1, x2, y2) == true) {
            legalPieceMove++;
         }
      }
   //Bishops
      if(a.equals("B")) {
         if(legalB(x1, y1, x2, y2) == true) {
            legalPieceMove++;
         }
      }
      if(a.equals("b")) {
         if(legalb(x1, y1, x2, y2) == true) {
            legalPieceMove++;
         }
      }
   
      if((legalPieceMove == 1) && (numTrue == 2)) {
      
         isEachPartTrue = true;
      }
      System.out.println( "a= " + a + " legalPieceMove= " + legalPieceMove + " numTrue= " +  numTrue +" isEachPartTrue= " + isEachPartTrue + " whosTurn= " + whosTurn);
      return isEachPartTrue;
   }
//The X and Y is switched in the top but it still works the same way it is supposed to 
   public static void move(int y1, int x1, int y2, int x2, int whosTurn){
   //if(board[y2][x2] == "k") {
   //	
   //}
      if(!(board[x2][y2] == " ")) {
         board[x2][y2] = " ";
      }
      String temp = board[x1][y1];
      board[x1][y1] = board[x2][y2];
      board[x2][y2] = temp;
   }
   public static void printStart() {//intro text for user
      System.out.println("This is a chess game");
      System.out.println("The Best Chess in the NorthWest");
      System.out.println("");
      System.out.println("To move your piece you will");
      System.out.println("need to enter the cordinates");
      System.out.println("of the piece you want to move and");
      System.out.println("the cordinates of the place you");
      System.out.println("want to move the piece too.");
      System.out.println("");
      System.out.println("You enter cordinates with the");
      System.out.println("x, y form");
      System.out.println("");
      System.out.println("If you enter a cordinate that is already");
      System.out.println("occupied you will be prompted");
      System.out.println("to reenter the cordinates.");
      System.out.println("");
   }
   public static void printNumbers() {
      System.out.print("   ");
      for(int i = 0; i < 8; i++) {
         System.out.print(i + " ");
      }
   }
   public static String[][] board = new String[8][8];//starting positions of the pieces
   public static void makeBoard() {	
      board[1][0] = "P";
      board[1][1] = "P";
      board[1][2] = "P";
      board[1][3] = "P";
      board[1][4] = "P";
      board[1][5] = "P";
      board[1][6] = "P";
      board[1][7] = "P";
      board[6][0] = "p";
      board[6][1] = "p";
      board[6][2] = "p";
      board[6][3] = "p";
      board[6][4] = "p";
      board[6][5] = "p";
      board[6][6] = "p";
      board[6][7] = "p";
      board[0][0] = "R";
      board[0][7] = "R";
      board[7][0] = "r";
      board[7][7] = "r";
      board[0][1] = "N";
      board[0][6] = "N";
      board[7][1] = "n";
      board[7][6] = "n";
      board[0][2] = "B";
      board[0][5] = "B";
      board[7][2] = "b";
      board[7][5] = "b";
      board[0][3] = "K";
      board[0][4] = "Q";
      board[7][4] = "k";
      board[7][3] = "q";
      for(int i = 2; i <= 5; i++) {
         for(int j = 0; j <=7; j++) {
            board[i][j] = " "; 
         }
      }
   }
   public static void print() {//makes number grid next to board
      printNumbers();
      System.out.println();
      for(int i = 0; i < 8; i++) {
         System.out.print(i + "  ");
         for(int j = 0; j < board.length;j++) {
            System.out.print(board[i][j] + " ");
         }
         System.out.println();
      }
   }

   public static boolean legalP(int x1, int y1, int x2, int y2) {	//a rare chess move thats illegal
      if(y1 == 1) {
         if((y2 == y1 + 2) || (y2 == y1 + 1)) {
            if(x2 == x1) {
               return true;
            }
            else if((x2 == x1 + 1) || (x2 == x1 - 1)){
               if(!(board[y2][x2] == " ")) {
                  if(y2 == y1 + 2) {
                     System.out.println("I almost didn't prohibit this move");
                     System.out.println("thinking no one would do it but YOU");
                     System.out.println("tried to do it so, NICE TRY!");
                  }
                  else {
                     return true;
                  }
               }
            }
         }
      }
      if(!(y1 == 1)) {
         if(y2 == y1 + 1) {
            if(x2 == x1) {
               if(board[y1 + 1][x1] == " ") {
                  return true;
               }
            }
            else if((x2 == x1 + 1) || (x2 == x1 - 1)){
               if(!(board[y2][x2] == " ")) {
                  return true;
               }
            }
         }
      }
      return false;
   }
   public static boolean legalp(int x1, int y1, int x2, int y2) {//creating the X,Y grid
      if(y1 == 6) {
         if((y2 == y1 - 2) || (y2 == y1 - 1)) {
            if(x2 == x1) {
               if(board[y1 - 1][x1] == " ") {
                  return true;
               }
            }
            else if((x2 == x1 + 1) || (x2 == x1 - 1)){//a rare chess move thats illegal
               if(!(board[y2][x2] == " ")) {
                  if(y2 == y1 - 2) {
                     System.out.println("I almost didn't prohibit this move");
                     System.out.println("thinking no one would do it but YOU");
                     System.out.println("tried to do it so, NICE TRY!");
                  }
                  else {
                     return true;
                  }
               }
            }
         }
      }
      if(!(y1 == 6)) {
         if(y2 == y1 - 1) {
            if(x2 == x1) {
               return true;
            }
            else if((x2 == x1 + 1) || (x2 == x1 - 1)){//X,Y grid
               if(!(board[y2][x2] == " ")) {
                  return true;
               }
            }
         }
      }
      return false;
   }
   public static boolean legalR(int x1, int y1, int x2, int y2) {//X,Y grid
      if(x1 == x2) {
         if(y2>y1) {
            for(int  i = y1;i < y2; i++) {
               if(!(board[i][x1] == " ")) {
                  return false;
               }
            }
         }
         if(y1>y2) {
         
         } 
      }
      if(y1 == y2) {
      
      }
      return true;
   }
   public static boolean legalr(int x1, int y1, int x2, int y2) {//checking how each piece moves
   
      return false;
   }
   public static boolean legalN(int x1, int y1, int x2, int y2) {
      return false;
   }
   public static boolean legaln(int x1, int y1, int x2, int y2) {
      return false;
   }
   public static boolean legalB(int x1, int y1, int x2, int y2) {
      return false;
   }
   public static boolean legalb(int x1, int y1, int x2, int y2) {
      return false;
   }
   public static boolean legalK(int x1, int y1, int x2, int y2) {
      return false;
   }
   public static boolean legalk(int x1, int y1, int x2, int y2) {
      return false;
   }
   public static boolean legalQ(int x1, int y1, int x2, int y2) {
      return false;
   }
   public static boolean legalq(int x1, int y1, int x2, int y2) {
      return false;
   }
}