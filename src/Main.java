import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String greetings = "*************** Wellcome to Minesweeper ***************";
        Scanner sc = new Scanner(System.in);
        System.out.println(greetings);

        do{ //initialize the table depending on the level
        }while(!Board.initBoard(sc));

        while(!Board.isLostGame()){
            Board.openBox(sc);
        }
    }

}