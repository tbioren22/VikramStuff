public class FormulaCell extends Cell {
    private final boolean ISFUNCTION;
    private String formula, operator, startingCell, endingCell;
    private double num1, num2;
    private int alignment;
    
    public FormulaCell(String formula) {
        this.formula = formula;
        alignment = 0;
        ISFUNCTION = checkFunction();
        formatFormula();
    }

    private boolean checkFunction() {
        if(formula.split(" ")[0].contentEquals("SUM")
        || formula.split(" ")[0].contentEquals("AVG")) {
            return true;
        }
        return false;
    }

    private void formatFormula() {
        String[] splitFormula = formula.split(" ");
        if(ISFUNCTION) {
            operator = splitFormula[0];
            startingCell = splitFormula[1];
            endingCell = splitFormula[3];
        }

        else {
            num1 = Double.parseDouble(splitFormula[0]);
            operator = splitFormula[1];
            num2 = Double.parseDouble(splitFormula[2]);
        }
    }

    public String getValue() {
        return formula;
    }

    public String toString() {
        String txtValue = "";
        if(getNumericValue() == Math.floor(getNumericValue())) {
            txtValue = Integer.toString((int)getNumericValue());
        }

        else {
            txtValue = Double.toString(getNumericValue());
        }

        if(txtValue.length() > 12) {
            return txtValue.substring(0, 11) + ">";
        }
        String spaces = "";
        for(int i=0; i < 12 - txtValue.length(); i++) {
            spaces += " ";
        }
        if(alignment == 1) {
            return spaces + txtValue;
        }
        
        else if(alignment == -1) {
            return txtValue + spaces;
        }

        else {
            double difference = (12.0 - txtValue.length())/2;
            spaces = "";
            for(int i=0; i < Math.floor(difference); i++) {
                spaces += " ";
            }
            String spaces1 = "";
            for(int i=0; i < Math.ceil(difference); i++) {
                spaces1 += " ";
            }
            return spaces + txtValue + spaces1;
        }
    }

    public double getNumericValue() {
        double value = 0;
        if(ISFUNCTION) {
            char sumColumn = startingCell.charAt(0);
            int startRow = 0;
            for(int i=1; i < startingCell.length(); i++) {
                startRow += Integer.parseInt(Character.toString(startingCell.charAt(i))) * Math.pow(10, i-1);
            }
            int endRow = 0;
            for(int i=1; i < endingCell.length(); i++) {
                endRow += Integer.parseInt(Character.toString(endingCell.charAt(i))) * Math.pow(10, i-1);
            }

            for(int i=startRow; i <= endRow; i++) {
                value += Runner.requestCellValue(sumColumn, i);
            }
            if(operator.contentEquals("AVG")) {
                value = value / (endRow - startRow + 1);
            }
        }
        
        else {
            switch(operator) {
                case "+":
                    value = num1 + num2;
                    break;
                
                case "-":
                    value = num1 - num2;
                    break;
                
                case "*":
                    value = num1 * num2;
                    break;

                case "/":
                    value = num1 / num2;
                    break;
            }
        }
        return value;
    }
}