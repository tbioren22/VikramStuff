import java.util.Scanner;

public class Runner {
    private static final int    WIDTH = 10;
    private static final int    HEIGHT = 7;
    private static final char[] VALIDCOLUMNS = {'A','B','C','D','E','F','G','H','I','J'};
    private static Spreadsheet spreadSheet;
    public static void main(String args[]) {
        spreadSheet = new Spreadsheet(WIDTH, HEIGHT);
        boolean quit = false;
        Scanner scan = new Scanner(System.in);

        while(!quit) {
            System.out.println("Enter a command...");
            String userInput = scan.nextLine();
            String[] userInputArray = userInput.split(" ");
            switch(userInputArray[0].toUpperCase()) {
                case "QUIT":
                    quit = true;
                    break;

                case "PRINT":
                    spreadSheet.print();
                    break;

                case "CLEAR":
                    if(userInputArray.length == 4) {
                        String cellLoc1 = userInputArray[1].toUpperCase();
                        String cellLoc2 = userInputArray[3].toUpperCase();
                        char cellLetter1 = cellLoc1.charAt(0);
                        char cellLetter2 = cellLoc2.charAt(0);
                        String tempCellNum = cellLoc1.substring(1);
                        int cellNumber1 = Integer.parseInt(tempCellNum);
                        tempCellNum = cellLoc2.substring(1);
                        int cellNumber2 = Integer.parseInt(tempCellNum);
                        spreadSheet.clearRect(cellLetter1, cellNumber1, cellLetter2, cellNumber2);
                    }
                    else {
                        spreadSheet.clearSpreadsheet();
                    }
                    break;
                
                case "SORTA":
                    if(userInputArray.length == 4) {
                        String cellLoc1 = userInputArray[1].toUpperCase();
                        String cellLoc2 = userInputArray[3].toUpperCase();
                        char cellLetter = cellLoc1.charAt(0);
                        String tempCellNum = cellLoc1.substring(1);
                        int cellNumber1 = Integer.parseInt(tempCellNum);
                        tempCellNum = cellLoc2.substring(1);
                        int cellNumber2 = Integer.parseInt(tempCellNum);
                        spreadSheet.sorta(cellLetter, cellNumber1, cellNumber2);
                    }
                    else {
                        System.out.println("I'm sorry, I didn't understand your input.\nCould you try again?");
                    }
                    break;

                    case "SORTD":
                    if(userInputArray.length == 4) {
                        String cellLoc1 = userInputArray[1].toUpperCase();
                        String cellLoc2 = userInputArray[3].toUpperCase();
                        char cellLetter = cellLoc1.charAt(0);
                        String tempCellNum = cellLoc1.substring(1);
                        int cellNumber1 = Integer.parseInt(tempCellNum);
                        tempCellNum = cellLoc2.substring(1);
                        int cellNumber2 = Integer.parseInt(tempCellNum);
                        spreadSheet.sortd(cellLetter, cellNumber1, cellNumber2);
                    }
                    else {
                        System.out.println("I'm sorry, I didn't understand your input.\nCould you try again?");
                    }
                    break;

                default:
                    if(isValid(userInputArray[0].toUpperCase())) {
                        System.out.println(userInputArray[0].toUpperCase());
                        char[] cellLoc = userInputArray[0].toUpperCase().toCharArray();
                        char cellLetter = cellLoc[0];
                        int cellNumber = 0;
                        for(int i=1; i < cellLoc.length; i++) {
                            cellNumber += Integer.parseInt(Character.toString(cellLoc[i])) * Math.pow(10, i-1);
                        }

                        if(Assignments.assignmentInput(userInputArray)) {
                            if(Assignments.isStringAssignment(userInput)) {
                                String assignment = Assignments.getStringAssignment(userInput);
                                spreadSheet.assignStringCell(cellLetter, cellNumber, assignment);
                            }

                            else if(Assignments.isNumericAssignment(userInput)) {
                                double assignment = Assignments.getNumericAssignment(userInput);
                                spreadSheet.assignNumberCell(cellLetter, cellNumber, assignment);
                            }

                            else if(Assignments.isFormulaicAssignment(userInput)) {
                                String assignment = Assignments.getFormulaicAssignment(userInput);
                                spreadSheet.assignFormulaicCell(cellLetter, cellNumber, assignment);
                            }

                            else {
                                System.out.println("I'm sorry, I didn't understand your input.\nCould you try again?");
                            }
                        }

                        else {
                            spreadSheet.printCellValue(cellLetter, cellNumber);
                        }
                    }

                    else {
                        System.out.println("I'm sorry, I didn't understand your input.\nCould you try again?");
                    }
                    break;
            }
        }
        scan.close();
    }

    private static boolean isValid(String cellInput) {
        char[] cellLoc = cellInput.toCharArray();
        for(char temp : VALIDCOLUMNS) {
            if(temp == cellLoc[0]) {
                return true;
            }
        }
        return false;
    }

    public static double requestCellValue(char cellLetter, int cellNumber) {
        return spreadSheet.getCellValue(cellLetter, cellNumber);
    }
}