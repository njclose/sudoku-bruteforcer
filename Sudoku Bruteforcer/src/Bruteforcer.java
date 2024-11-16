import java.util.Scanner;

public class Bruteforcer {
    static Node[] row1 = new Node[9];
    static Node[] row2 = new Node[9];
    static Node[] row3 = new Node[9];
    static Node[] row4 = new Node[9];
    static Node[] row5 = new Node[9];
    static Node[] row6 = new Node[9];
    static Node[] row7 = new Node[9];
    static Node[] row8 = new Node[9];
    static Node[] row9 = new Node[9];
    static int currentSearchRow = 1;
    static int currentSearchColumn = 1;
    static boolean lastMovementRight;
    static boolean validPuzzle = false;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        setupNodes();
        System.out.println();
        displayMenu();
    }

    public static void setupNodes(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                Node node = new Node();
                switch(i){
                    case 0 -> row1[j] = node;
                    case 1 -> row2[j] = node;
                    case 2 -> row3[j] = node;
                    case 3 -> row4[j] = node;
                    case 4 -> row5[j] = node;
                    case 5 -> row6[j] = node;
                    case 6 -> row7[j] = node;
                    case 7 -> row8[j] = node;
                    case 8 -> row9[j] = node;
                }
            }
        }
    }

    public static void displayMenu(){
        clearTerminal();
        displayCurrentInput();
        System.out.println("++======== MAIN MENU ========++");
        System.out.println("||    1. Show Solved Board   ||");
        System.out.println("||        2. Edit Row        ||");
        System.out.println("||      3. Solve Puzzle      ||");
        System.out.println("||     4. Generate Puzzle    ||");
        System.out.println("||          5. Quit          ||");
        System.out.println("++===========================++");
        System.out.println();
        System.out.print("#:> ");
        String stringSelection = input.nextLine();
        int selection = Integer.parseInt(stringSelection);
        switch(selection){
            case 1 -> {
                if (validPuzzle){

                    //COMPLETE BEFORE FINISHING PROJECT

                }else{
                    String exit = "";
                    while(!exit.equals("exit")){
                        clearTerminal();
                        System.out.println("There is currently not a solved board in memory");
                        System.out.println();
                        System.out.println("| Type 'exit' to exit page |");
                        System.out.print("#:> ");
                        exit = input.nextLine();
                    }
                    displayMenu();
                }
            }
            case 2 -> {
                clearTerminal();
                System.out.println("What row would you like to edit?");
                System.out.print("#:> ");
                String rowString = input.nextLine();
                int rowSelected = Integer.parseInt(rowString);
                while (rowSelected < 1 || rowSelected > 9){
                    clearTerminal();
                    System.out.println("Ensure that the number typed is between 1 and 9");
                    System.out.println();
                    System.out.println("What row would you like to edit?");
                    System.out.print("#:> ");
                    rowString = input.nextLine();
                    rowSelected = Integer.parseInt(rowString);
                }
                addRow(rowSelected);
            }
            case 3 -> {
                lastMovementRight = true;
                search();
            }
            case 4 -> {
                String exit = "";
                while(!exit.equals("exit")){
                    clearTerminal();
                    System.out.println("This feature is currently in development");
                    System.out.println();
                    System.out.println("| Type 'exit' to exit page |");
                    System.out.print("#:> ");
                    exit = input.nextLine();    
                }
                displayMenu();
            }
            case 5 -> {
                System.out.println("Thank you for using Sudoku Bruteforcer");
                System.exit(0);
            }
        }
    }

    public static void addRow(int currentRows){
        System.out.println("Enter row using spaces to seperate each number (use 0 if space is unknown)");
        System.out.print("#:> ");
        String rowInputString = input.nextLine();
        String[] rowString = rowInputString.split("\\s+");
        int[] rowInt = new int[9];
        if (rowString.length != 9){
            System.out.println("Invalid number of values in row (must input 9)");
            addRow(currentRows);
        }
        for (int i = 0; i < 9; i++){
            rowInt[i] = Integer.parseInt(rowString[i]);
            switch (currentRows){
                case 1 -> row1[i].editValue(rowInt[i]);
                case 2 -> row2[i].editValue(rowInt[i]);
                case 3 -> row3[i].editValue(rowInt[i]);
                case 4 -> row4[i].editValue(rowInt[i]);
                case 5 -> row5[i].editValue(rowInt[i]);
                case 6 -> row6[i].editValue(rowInt[i]);
                case 7 -> row7[i].editValue(rowInt[i]);
                case 8 -> row8[i].editValue(rowInt[i]);
                case 9 -> row9[i].editValue(rowInt[i]);
            }
            if (rowInt[i] < 0 || rowInt[i] > 9){
                System.out.println("Values in row must be within 0 and 9");
                addRow(currentRows);
            }
        }
        displayMenu();
    }

    public static boolean search(){
        while(true){
            boolean valueWorksInRow = false;
            boolean valueWorksInBoth = false;
            int workingValue = 0;
            if (currentSearchRow == 0){
                validPuzzle = false;
                return false;
            }else if (currentSearchRow == 10){
                validPuzzle = true;
                return true;
            }
            Node[] row = new Node[9];
            switch(currentSearchRow){
                case 1 -> row = row1;
                case 2 -> row = row2;
                case 3 -> row = row3;
                case 4 -> row = row4;
                case 5 -> row = row5;
                case 6 -> row = row6;
                case 7 -> row = row7;
                case 8 -> row = row8;
                case 9 -> row = row9;
            }
            Node[] column = new Node[9];
            switch(currentSearchRow){
                case 1 -> {
                    column[0] = row1[0];
                    column[1] = row2[0];
                    column[2] = row3[0];
                    column[3] = row4[0];
                    column[4] = row5[0];
                    column[5] = row6[0];
                    column[6] = row7[0];
                    column[7] = row8[0];
                    column[8] = row9[0];
                }
                case 2 -> {
                    column[0] = row1[1];
                    column[1] = row2[1];
                    column[2] = row3[1];
                    column[3] = row4[1];
                    column[4] = row5[1];
                    column[5] = row6[1];
                    column[6] = row7[1];
                    column[7] = row8[1];
                    column[8] = row9[1];
                }
                case 3 -> {
                    column[0] = row1[2];
                    column[1] = row2[2];
                    column[2] = row3[2];
                    column[3] = row4[2];
                    column[4] = row5[2];
                    column[5] = row6[2];
                    column[6] = row7[2];
                    column[7] = row8[2];
                    column[8] = row9[2];
                }
                case 4 -> {
                    column[0] = row1[3];
                    column[1] = row2[3];
                    column[2] = row3[3];
                    column[3] = row4[3];
                    column[4] = row5[3];
                    column[5] = row6[3];
                    column[6] = row7[3];
                    column[7] = row8[3];
                    column[8] = row9[3];
                }
                case 5 -> {
                    column[0] = row1[4];
                    column[1] = row2[4];
                    column[2] = row3[4];
                    column[3] = row4[4];
                    column[4] = row5[4];
                    column[5] = row6[4];
                    column[6] = row7[4];
                    column[7] = row8[4];
                    column[8] = row9[4];
                }
                case 6 -> {
                    column[0] = row1[5];
                    column[1] = row2[5];
                    column[2] = row3[5];
                    column[3] = row4[5];
                    column[4] = row5[5];
                    column[5] = row6[5];
                    column[6] = row7[5];
                    column[7] = row8[5];
                    column[8] = row9[5];
                }
                case 7 -> {
                    column[0] = row1[6];
                    column[1] = row2[6];
                    column[2] = row3[6];
                    column[3] = row4[6];
                    column[4] = row5[6];
                    column[5] = row6[6];
                    column[6] = row7[6];
                    column[7] = row8[6];
                    column[8] = row9[6];
                }
                case 8 -> {
                    column[0] = row1[7];
                    column[1] = row2[7];
                    column[2] = row3[7];
                    column[3] = row4[7];
                    column[4] = row5[7];
                    column[5] = row6[7];
                    column[6] = row7[7];
                    column[7] = row8[7];
                    column[8] = row9[7];
                }
                case 9 -> {
                    column[0] = row1[8];
                    column[1] = row2[8];
                    column[2] = row3[8];
                    column[3] = row4[8];
                    column[4] = row5[8];
                    column[5] = row6[8];
                    column[6] = row7[8];
                    column[7] = row8[8];
                    column[8] = row9[8];
                }
            }
                if (row[currentSearchColumn - 1].isSearchable()){
                    for (int i = 0; i < 9; i++){
                        if (!(locateNode(currentSearchRow, currentSearchColumn).getCheckedValues()[0] == workingValue && locateNode(currentSearchRow, currentSearchColumn).getCheckedValues()[1] == workingValue && locateNode(currentSearchRow, currentSearchColumn).getCheckedValues()[2] == workingValue && locateNode(currentSearchRow, currentSearchColumn).getCheckedValues()[3] == workingValue && locateNode(currentSearchRow, currentSearchColumn).getCheckedValues()[4] == workingValue && locateNode(currentSearchRow, currentSearchColumn).getCheckedValues()[5] == workingValue && locateNode(currentSearchRow, currentSearchColumn).getCheckedValues()[6] == workingValue && locateNode(currentSearchRow, currentSearchColumn).getCheckedValues()[7] == workingValue && locateNode(currentSearchRow, currentSearchColumn).getCheckedValues()[8] == workingValue)){
                            if (!(row[0].getValue() == i && row[1].getValue() == i && row[2].getValue() == i && row[3].getValue() == i && row[4].getValue() == i && row[5].getValue() == i && row[6].getValue() == i && row[7].getValue() == i && row[8].getValue() == i)){
                                valueWorksInRow = true;
                            }
                        }                    
                        if (!(valueWorksInRow == true)){
                            if (!(column[0].getValue() == i && column[1].getValue() == i && column[2].getValue() == i && column[3].getValue() == i && column[4].getValue() == i && column[5].getValue() == i && column[6].getValue() == i && column[7].getValue() == i && column[8].getValue() == i)){
                                valueWorksInBoth = true;
                            }
                        }else{
                            break;
                        }
                    }

                    if (valueWorksInBoth == true){
                        lastMovementRight = true;
                        currentSearchColumn++;
                    }else{
                        lastMovementRight = false;
                        currentSearchColumn--;
                    }

                }else{
                    if (lastMovementRight){
                        currentSearchColumn++;
                    }else{
                        currentSearchColumn--;
                    }
                }


            // THE METHOD FOR COMPLETING THE SEARCH
            
            if (currentSearchColumn == 0){
                currentSearchColumn = 9;
                currentSearchRow--;
            }else if (currentSearchColumn == 10){
                currentSearchColumn = 1;
                currentSearchRow++;
            }
        }
    }

    public static void displayCurrentInput(){
        System.out.println("CURRENT PUZZLE:");
        System.out.println();
        System.out.println(stylize(row1[0].getValue(), true) + " " + stylize(row1[1].getValue(), true) + " " + stylize(row1[2].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row1[3].getValue(), true) + " " + stylize(row1[4].getValue(), true) + " " + stylize(row1[5].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row1[6].getValue(), true) + " " + stylize(row1[7].getValue(), true) + " " + stylize(row1[8].getValue(), true));
        System.out.println(stylize(row2[0].getValue(), true) + " " + stylize(row2[1].getValue(), true) + " " + stylize(row2[2].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row2[3].getValue(), true) + " " + stylize(row2[4].getValue(), true) + " " + stylize(row2[5].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row2[6].getValue(), true) + " " + stylize(row2[7].getValue(), true) + " " + stylize(row2[8].getValue(), true));
        System.out.println(stylize(row3[0].getValue(), true) + " " + stylize(row3[1].getValue(), true) + " " + stylize(row3[2].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row3[3].getValue(), true) + " " + stylize(row3[4].getValue(), true) + " " + stylize(row3[5].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row3[6].getValue(), true) + " " + stylize(row3[7].getValue(), true) + " " + stylize(row3[8].getValue(), true));
        System.out.println("\u001b[30m------+-------+------\u001b[0m");
        System.out.println(stylize(row4[0].getValue(), true) + " " + stylize(row4[1].getValue(), true) + " " + stylize(row4[2].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row4[3].getValue(), true) + " " + stylize(row4[4].getValue(), true) + " " + stylize(row4[5].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row4[6].getValue(), true) + " " + stylize(row4[7].getValue(), true) + " " + stylize(row4[8].getValue(), true));
        System.out.println(stylize(row5[0].getValue(), true) + " " + stylize(row5[1].getValue(), true) + " " + stylize(row5[2].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row5[3].getValue(), true) + " " + stylize(row5[4].getValue(), true) + " " + stylize(row5[5].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row5[6].getValue(), true) + " " + stylize(row5[7].getValue(), true) + " " + stylize(row5[8].getValue(), true));
        System.out.println(stylize(row6[0].getValue(), true) + " " + stylize(row6[1].getValue(), true) + " " + stylize(row6[2].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row6[3].getValue(), true) + " " + stylize(row6[4].getValue(), true) + " " + stylize(row6[5].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row6[6].getValue(), true) + " " + stylize(row6[7].getValue(), true) + " " + stylize(row6[8].getValue(), true));
        System.out.println("\u001b[30m------+-------+------\u001b[0m");
        System.out.println(stylize(row7[0].getValue(), true) + " " + stylize(row7[1].getValue(), true) + " " + stylize(row7[2].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row7[3].getValue(), true) + " " + stylize(row7[4].getValue(), true) + " " + stylize(row7[5].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row7[6].getValue(), true) + " " + stylize(row7[7].getValue(), true) + " " + stylize(row7[8].getValue(), true));
        System.out.println(stylize(row8[0].getValue(), true) + " " + stylize(row8[1].getValue(), true) + " " + stylize(row8[2].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row8[3].getValue(), true) + " " + stylize(row8[4].getValue(), true) + " " + stylize(row8[5].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row8[6].getValue(), true) + " " + stylize(row8[7].getValue(), true) + " " + stylize(row8[8].getValue(), true));
        System.out.println(stylize(row9[0].getValue(), true) + " " + stylize(row9[1].getValue(), true) + " " + stylize(row9[2].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row9[3].getValue(), true) + " " + stylize(row9[4].getValue(), true) + " " + stylize(row9[5].getValue(), true) + " \u001b[30m|\u001b[0m " + stylize(row9[6].getValue(), true) + " " + stylize(row9[7].getValue(), true) + " " + stylize(row9[8].getValue(), true));
        System.out.println();
        System.out.println();
    }

    public static void displaySearch(){
        validPuzzle = true;
        System.out.println("SEARCHING...");
        System.out.println();
        System.out.println(row1[0] + " " + row1[1] + " " + row1[2] + " \u001b[30m|\u001b[0m " + row1[3] + " " + row1[4] + " " + row1[5] + " \u001b[30m|\u001b[0m " + row1[6] + " " + row1[7] + " " + row1[8]);
        System.out.println(row2[0] + " " + row2[1] + " " + row2[2] + " \u001b[30m|\u001b[0m " + row2[3] + " " + row2[4] + " " + row2[5] + " \u001b[30m|\u001b[0m " + row2[6] + " " + row2[7] + " " + row2[8]);
        System.out.println(row3[0] + " " + row3[1] + " " + row3[2] + " \u001b[30m|\u001b[0m " + row3[3] + " " + row3[4] + " " + row3[5] + " \u001b[30m|\u001b[0m " + row3[6] + " " + row3[7] + " " + row3[8]);
        System.out.println("\u001b[30m------+-------+------\u001b[0m");
        System.out.println(row4[0] + " " + row4[1] + " " + row4[2] + " \u001b[30m|\u001b[0m " + row4[3] + " " + row4[4] + " " + row4[5] + " \u001b[30m|\u001b[0m " + row4[6] + " " + row4[7] + " " + row4[8]);
        System.out.println(row5[0] + " " + row5[1] + " " + row5[2] + " \u001b[30m|\u001b[0m " + row5[3] + " " + row5[4] + " " + row5[5] + " \u001b[30m|\u001b[0m " + row5[6] + " " + row5[7] + " " + row5[8]);
        System.out.println(row6[0] + " " + row6[1] + " " + row6[2] + " \u001b[30m|\u001b[0m " + row6[3] + " " + row6[4] + " " + row6[5] + " \u001b[30m|\u001b[0m " + row6[6] + " " + row6[7] + " " + row6[8]);
        System.out.println("\u001b[30m------+-------+------\u001b[0m");
        System.out.println(row7[0] + " " + row7[1] + " " + row7[2] + " \u001b[30m|\u001b[0m " + row7[3] + " " + row7[4] + " " + row7[5] + " \u001b[30m|\u001b[0m " + row7[6] + " " + row7[7] + " " + row7[8]);
        System.out.println(row8[0] + " " + row8[1] + " " + row8[2] + " \u001b[30m|\u001b[0m " + row8[3] + " " + row8[4] + " " + row8[5] + " \u001b[30m|\u001b[0m " + row8[6] + " " + row8[7] + " " + row8[8]);
        System.out.println(row9[0] + " " + row9[1] + " " + row9[2] + " \u001b[30m|\u001b[0m " + row9[3] + " " + row9[4] + " " + row9[5] + " \u001b[30m|\u001b[0m " + row9[6] + " " + row9[7] + " " + row9[8]);
        System.out.println();
        System.out.println();
    }

    public static String stylize(int value, boolean current){
        if (current == true){
            switch (value){
                case 0 -> {
                    return "" + value;
                }
                case 1, 2 -> {
                    return "\u001b[31m" + value + "\u001b[0m";
                }
                case 3, 4 -> {
                    return "\u001b[33m" + value + "\u001b[0m";
                }
                case 5, 6 -> {
                    return "\u001b[32m" + value + "\u001b[0m";
                }
                case 7, 8 -> {
                    return "\u001b[34m" + value + "\u001b[0m";
                }
                case 9 -> {
                    return "\u001b[35m" + value + "\u001b[0m";
                }
            }
        }else{

        }
        return "";
    }

    public static Node locateNode(int row, int column){
        switch(row){
            case 1 -> {
                return row1[column];
            }
            case 2 -> {
                return row2[column];
            }
            case 3 -> {
                return row3[column];
            }
            case 4 -> {
                return row4[column];
            }
            case 5 -> {
                return row5[column];
            }
            case 6 -> {
                return row6[column];
            }
            case 7 -> {
                return row7[column];
            }
            case 8 -> {
                return row8[column];
            }
            case 9 -> {
                return row9[column];
            }
        }
        return null;
    }

    public static void clearTerminal(){
        System.out.print("\033[H\033[2J"); 
    }
}

