import java.util.Scanner;

public class Board {
    private static int level, mines;
    static final int LOW = 5, MEDIUM = 10, HARD=15;
    static Box[][] table;
    private static boolean lostGame;

    public static boolean isLostGame() {
        return lostGame;
    }

    private static void setLostGame(boolean lostGame) {
        Board.lostGame = lostGame;
    }

    public static boolean initBoard(Scanner sc){
        System.out.println("Enter level:\n1--Low\n2--Medium\n3--Hard");
        int option = sc.nextInt();
        switch(option){
            case 1 -> {
                level = LOW;
                mines = 3;
                table = new Box[LOW][LOW];
                fillBoard();
                return true;
            }
            case 2 -> {
                level = MEDIUM;
                mines = 10;
                table = new Box[MEDIUM][MEDIUM];
                fillBoard();
                return true;
            }
            case 3 ->{
                level = HARD;
                mines = 20;
                table = new Box[HARD][HARD];
                fillBoard();
                return true;
            }
            default -> {
                System.out.println("\n----Error-> try again\n");
                return false;
            }
        }
    }

    public static void showBoard(){
        for(Box[] row: table){
            System.out.print("\n|");
            for(Box box: row){
                System.out.print(box + "|");
            }
        }
    }

    private static void fillBoard(){
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                table[i][j] = new Box();
            }
        }
        System.out.println("En fillBoard:\n");
        int createdMines = 0, row, column;
        while(createdMines < mines) {
            row = getRandomPosition();
            column = getRandomPosition();
            System.out.println(createdMines + "--mina en pos:" + row + '-' + column);
            if(table[row][column].isMine()){ //to check if it is already a mine
                continue;
            }
            createdMines++;
            table[row][column].setMine(true);
        }
        showBoard();
    }

    private static int getRandomPosition(){
        return (int)(Math.random() * level) ;
    }

    public static void openBox(Scanner sc){
        int row, column;
        do {
            System.out.println("\n***Enter positions to open a box (BETWEEN 1 AND "+ (table.length) +"):\nRow: ");
            row = sc.nextInt() -1 ;
            System.out.println("Column: ");
            column = sc.nextInt() - 1;
            if (!isInBoard(row, column) || table[row][column].isOpen()) {
                System.out.println("\n--ERROR: Wrong position");
            }else{
                lostGame = table[row][column].setOpen(true);
                if (!lostGame) {
                    checkAround(row, column);
                }
            }
            showBoard();
        }while(!lostGame && !isFinished());

    }

    private static void checkAround(int row, int column){
        if(isInBoard(row-1, column) && !table[row-1][column].isOpen() && !table[row-1][column].isMine()){ //up
            table[row-1][column].setOpen(true);
            checkAround(row-1, column);
        }else if(isInBoard(row-1, column) && !table[row-1][column].isOpen()){
            table[row][column].increaseValue();
        }

        if(isInBoard( row-1, column-1) && !table[row-1][column-1].isOpen() && !table[row-1][column-1].isMine()){ //up-left
            table[row-1][column-1].setOpen(true);
            checkAround(row-1, column-1);
        }else if(isInBoard(row-1, column-1) && !table[row-1][column-1].isOpen()){
            table[row][column].increaseValue();
        }
        
        if(isInBoard( row, column-1) && !table[row][column-1].isOpen() && !table[row][column-1].isMine()){ //left
            table[row][column-1].setOpen(true);
            checkAround(row, column-1);
        }else if(isInBoard(row, column-1) && !table[row][column-1].isOpen()){
            table[row][column].increaseValue();
        }

        if(isInBoard( row+1, column-1) && !table[row+1][column-1].isOpen() && !table[row+1][column-1].isMine()){ //down-left
            table[row+1][column-1].setOpen(true);
            checkAround(row+1, column-1);
        }else if(isInBoard(row+1, column-1)  && !table[row+1][column-1].isOpen()){
            table[row][column].increaseValue();
        }

        if(isInBoard(row+1, column)  && !table[row+1][column].isOpen() && !table[row+1][column].isMine()){ //down
            table[row+1][column].setOpen(true);
            checkAround(row+1, column);
        }else if(isInBoard(row+1, column) && !table[row+1][column].isOpen()){
            table[row][column].increaseValue();
        }

        if(isInBoard( row+1, column+1) && !table[row+1][column+1].isOpen() && !table[row+1][column+1].isMine()){ //down-right
            table[row+1][column+1].setOpen(true);
            checkAround(row+1, column+1);
        }else if(isInBoard(row+1, column+1) && !table[row+1][column+1].isOpen()){
            table[row][column].increaseValue();
        }

        if(isInBoard( row, column+1) && !table[row][column+1].isOpen() && !table[row][column+1].isMine()){ //right
            table[row][column+1].setOpen(true);
            checkAround(row, column+1);
        }else if(isInBoard(row, column+1) && !table[row][column+1].isOpen()){
            table[row][column].increaseValue();
        }

        if(isInBoard( row-1, column +1) && !table[row-1][column+1].isOpen() && !table[row-1][column+1].isMine()){ //up-right
            table[row-1][column+1].setOpen(true);
            checkAround(row-1, column+1);
        }else if(isInBoard(row-1, column +1)){ 
            table[row][column].increaseValue();
        }
    }
    
    private static boolean isInBoard(int row, int column){
        if(row > -1 && row < table.length && column > -1 && column < table.length){
            return true;
        }
        return false;
    }

    private static boolean isFinished(){
        for(Box[] row: table){
            for(Box box: row){
                if(!box.isMine() && !box.isOpen()){
                    return false;
                }
            }
        }
        return true;
    }
}
