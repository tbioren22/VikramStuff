public class NumberCell extends Cell {
    private double value;
    private int alignment;

    public NumberCell(double value) {
        this.value = value;
        alignment = 0;
    }

    public String toString() {
        String txtValue = "";
        if(value == Math.floor(value)) {
            txtValue = Integer.toString((int)value);
        }

        else {
            txtValue = Double.toString(value);
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
    public String getValue() {
        String output = "";
        if(value == Math.floor(value)) {
            output = String.format("%f", (int)value);
        }
        else
            output = String.format("%f",value);
        return output;
    }

    public double getNumericValue() {
        return value;
    }
}