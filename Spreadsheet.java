public class Spreadsheet {
    private final int WIDTH;
    private final int HEIGHT;
    Cell[][] sheet;
    private static final char[] VALIDCOLUMNS = {'A','B','C','D','E','F','G','H','I','J'};
    
    /**
     * Minimum constructor for the Spreadsheet class
     * @param width The width of the spreadsheet
     * @param height The height of the spreadsheet
     */
    public Spreadsheet(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.initSheet();
    }

    public void clearSpreadsheet() {
        initSheet();
    }

    public void clearRect(char cellLetter1, int cellNumber1, char cellLetter2, int cellNumber2) {
        int columnStart = 0;
        for(int i=0; i < VALIDCOLUMNS.length; i++) {
            if(VALIDCOLUMNS[i] == cellLetter1) {
                columnStart = i;
            }
        }
        int columnEnd = 0;
        for(int i=0; i < VALIDCOLUMNS.length; i++) {
            if(VALIDCOLUMNS[i] == cellLetter2) {
                columnEnd = i;
            }
        }
        for(int column=columnStart; column <= columnEnd; column++) {
            for(int row=cellNumber1; row <= cellNumber2; row++) {
                sheet[row-1][column] = new Cell();
            }
        }
    }

    public void sorta(char cellLetter, int cellNumber1, int cellNumber2) {
        cellNumber1 --;
        Cell[] values = new Cell[cellNumber2 - cellNumber1];
        int column = 0;
        for(int i = 0; i < VALIDCOLUMNS.length; i ++) {
            if(VALIDCOLUMNS[i] == cellLetter) {
                column = i;
            }
        }
        for(int i=0; i < values.length; i++) {
            values[i] = sheet[cellNumber1 + i][column];
        }
        int index = 0;
        while(index < values.length-1) {
            if(values[index].getNumericValue() > values[index+1].getNumericValue()) {
                Cell temp = values[index];
                values[index] = values[index+1];
                values[index+1] = temp;
                index = 0;
            }
            else {
                index ++;
            }
        }
        for(int i=0; i < values.length; i++) {
            sheet[cellNumber1 + i][column] = values[i];
        }
    }

    public void sortd(char cellLetter, int cellNumber1, int cellNumber2) {
        cellNumber1 --;
        Cell[] values = new Cell[cellNumber2 - cellNumber1];
        int column = 0;
        for(int i = 0; i < VALIDCOLUMNS.length; i ++) {
            if(VALIDCOLUMNS[i] == cellLetter) {
                column = i;
            }
        }
        for(int i=0; i < values.length; i++) {
            values[i] = sheet[cellNumber1 + i][column];
        }
        int index = 0;
        while(index < values.length-1) {
            if(values[index].getNumericValue() < values[index+1].getNumericValue()) {
                Cell temp = values[index];
                values[index] = values[index+1];
                values[index+1] = temp;
                index = 0;
            }
            else {
                index ++;
            }
        }
        for(int i=0; i < values.length; i++) {
            sheet[cellNumber1 + i][column] = values[i];
        }
    }

    /**
     * Set a certain cell in the spreadsheet to a cell of type double.
     * @param cellLetter The column of the spreadsheet in letter form
     * @param cellNumber The row of the cell in number form
     * @param cellValue The value of the cell in type double
     */
    public void assignNumberCell(char cellLetter, int cellNumber, double cellValue) {
        for(int i = 0; i < VALIDCOLUMNS.length; i ++) {
            if(VALIDCOLUMNS[i] == cellLetter) {
                sheet[cellNumber-1][i] = new NumberCell(cellValue);
            }
        }
    }
    
    /**
     * Set a certain cell in the spreadsheet to a cell of type String.
     * @param cellLetter The column of the spreadsheet in letter form
     * @param cellNumber The row of the cell in number form
     * @param cellValue The value in String form
     */
    public void assignStringCell(char cellLetter, int cellNumber, String cellValue) {
        for(int i = 0; i < VALIDCOLUMNS.length; i ++) {
            if(VALIDCOLUMNS[i] == cellLetter) {
                sheet[cellNumber-1][i] = new StringCell(cellValue);
            }
        }
    }

    public void assignFormulaicCell(char cellLetter, int cellNumber, String cellValue) {
        for(int i = 0; i < VALIDCOLUMNS.length; i ++) {
            if(VALIDCOLUMNS[i] == cellLetter) {
                sheet[cellNumber-1][i] = new FormulaCell(cellValue);
            }
        }
    }

    private void initSheet() {
        sheet = new Cell[HEIGHT][WIDTH];
        for(int row = 0; row < sheet.length; row ++) {
            for(int column = 0; column < sheet[row].length; column ++) {
                sheet[row][column] = new Cell();
            }
        }
    }

    /**
     * Prints out the value of a given cell.
     * @param cellLetter The letter of the cell (the column / X-Value)
     * @param cellNumber The number of the cell (the row / Y-Value)
     */
    public void printCellValue(char cellLetter, int cellNumber) {
        System.out.print(cellLetter + "" + cellNumber + " = ");
        for(int i = 0; i < VALIDCOLUMNS.length; i ++) {
            if(VALIDCOLUMNS[i] == cellLetter) {
                System.out.println(sheet[cellNumber-1][i].getValue());
            }
        }
    }

    public double getCellValue(char cellLetter, int cellNumber) {
        for(int i = 0; i < VALIDCOLUMNS.length; i ++) {
            if(VALIDCOLUMNS[i] == cellLetter) {
                return sheet[cellNumber-1][i].getNumericValue();
            }
        }
        return 0;
    }

    /**
     * Prints out the spreadsheet. It does it in a neatly organized table.
     */
    public void print() {
        for(int i=0; i < (WIDTH+1)*13; i++) {
            if(i%13 == 0) {
                System.out.print("+");
            }
            else {
                System.out.print("-");
            }
        }
        System.out.print("+\n|            |");
        for(int i=0; i < (WIDTH); i++) {
            System.out.print("      " + VALIDCOLUMNS[i] + "     |");
        }
        for(int column=0; column < HEIGHT; column++) {
            System.out.println();
            for(int i=0; i < (WIDTH+1)*13; i++) {
                if(i%13 == 0) {
                    System.out.print("+");
                }
                else {
                    System.out.print("-");
                }
            }
            System.out.println("+");
            for(int row=-1; row < WIDTH; row++) {
                if(row == -1) {
                    System.out.print("|      " + (column+1) + "     |");
                }
                else {
                    System.out.print(sheet[column][row] + "|");
                }
            }
        }
        System.out.println();
        for(int i=0; i < (WIDTH+1)*13; i++) {
            if(i%13 == 0) {
                System.out.print("+");
            }
            else {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }
}