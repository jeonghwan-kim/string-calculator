import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private final String regexp = "\\/\\/(.+)\\\\n(.*)";
    private String seperator = ",|:";
    private Parser parser = new Parser(seperator, regexp);

    private int getPositiveNumber(String str) {
        int num = Integer.parseInt(str);
        if (num < 0) {
            throw new RuntimeException("error");
        }

        return num;
    }
    private int sum(String[] tokens) {
        int sum = 0;
        int operatee;
        for (int i = 0; i < tokens.length; i++) {
            operatee = getPositiveNumber(tokens[i]);
            sum += Integer.parseInt(tokens[i]);
        }

        return sum;
    }

    public int calculate(String str){
        if (str == null || str.isEmpty()) return 0;

        if (parser.isCustomeSeperatorIn(str)) {
            parser.setSeperator(parser.getCustomeSeperator(str));
            str = parser.removeCustomeSeperator(str);
        }

        String[] tokens = str.split(seperator);

        return sum(tokens);
    }
}
class Parser {
    private String seperator;
    private String customSperatorPtn;

    public Parser(String seperator, String customSeperatorPtn) {
        this.seperator = seperator;
        this.customSperatorPtn = customSeperatorPtn;
    }

    private Matcher getMacher(String str) {
        return Pattern.compile(seperator).matcher(str);
    }

    public Boolean isCustomeSeperatorIn(String str) {
        return getMacher(str).find();
    }
    public String getCustomeSeperator(String str) {
        Matcher m = getMacher(str);
        m.matches();
        return m.group(1);
    }
    public void setSeperator(String seperator) {
        this.seperator = seperator;
    }
    public String removeCustomeSeperator(String str) {
        Matcher m = getMacher(str);
        m.matches();
        return m.group(2);
    }
}
