public class Assignments {
    public static boolean assignmentInput(String[] input) {
        boolean result = false;
        for(String i : input) {
            if(i.equals("=")) {
                result = true;
            }
        }
        return result;
    }

    public static boolean isStringAssignment(String input) {
        return input.indexOf("\"") != -1 && input.indexOf("\"", input.indexOf("\"")+1) != -1;
    }

    public static String getStringAssignment(String input) {
        int beginIndex = input.indexOf("\"") + 1;
        int endIndex = input.indexOf("\"", beginIndex);
        return input.substring(beginIndex, endIndex);
    }

    public static boolean isNumericAssignment(String input) {
        int beginIndex = input.lastIndexOf(" ") + 1;
        String cutInput = input.substring(beginIndex);
        char[] inputChars = cutInput.toCharArray();
        for(char temp : inputChars) {
            if(!Character.isDigit(temp) && temp != '.' && temp != '-') {
                return false;
            }
        }
        return true;
    }

    public static double getNumericAssignment(String input) {
        int beginIndex = input.lastIndexOf(" ") + 1;
        String cutInput = input.substring(beginIndex);
        return Double.parseDouble(cutInput);
    }

    public static boolean isFormulaicAssignment(String input) {
        int beginIndex = input.lastIndexOf("=") + 2;
        String cutInput = input.substring(beginIndex);
        return cutInput.startsWith("(") && cutInput.endsWith(")") && (cutInput.contains("+") || cutInput.contains("-") || cutInput.contains("*") || cutInput.contains("/"));
    }

    public static String getFormulaicAssignment(String input) {
        return input.substring(input.indexOf("(") + 1, input.length() - 1);
    }
}