public class StringCell extends Cell {
    private String value;
    private int alignment;

    public StringCell(String value) {
        this.value = value;
        alignment = 0;
    }

    public String toString() {
        if(value.length() > 12) {
            return value.substring(0, 11) + ">";
        }
        String spaces = "";
        for(int i=0; i < 12 - value.length(); i++) {
            spaces += " ";
        }
        if(alignment == 1) {
            return spaces + value;
        }
        
        else if(alignment == -1) {
            return value + spaces;
        }

        else {
            double difference = (12.0 - value.length())/2;
            spaces = "";
            for(int i=0; i < Math.floor(difference); i++) {
                spaces += " ";
            }
            String spaces1 = "";
            for(int i=0; i < Math.ceil(difference); i++) {
                spaces1 += " ";
            }
            return spaces + value + spaces1;
        }
    }

    public String getValue() {
        return "\"" + value + "\"";
    }

    public void setAlignment(int value) {
        alignment = value;
    }
}