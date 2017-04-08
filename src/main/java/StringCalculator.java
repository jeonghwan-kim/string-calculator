import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private List<Splitter> splitters = Arrays.asList(new CustomSplitter(), new DefaultSplitter());

    public int add(String text) {
        if (isBlank(text)) {
            return 0;
        }

        return Arrays.stream(split(text))
                .map(v -> new Positive(v))
                .reduce(new Positive(0), (p1, p2) -> p1.add(p2))
                .getNumber();
    }

    private boolean isBlank(String text) {
        return text == null || text.isEmpty();
    }

    private String [] split(String text) {
        return splitters.stream()
                .filter(s -> s.supports(text))
                .findFirst()
                .map(s -> s.split(text))
                .orElse(new String[0]);
    }
}

class Positive {
    private int number = 0;

    public Positive(int number) {
        if (number < 0) {
            throw new RuntimeException("정수가 아님");
        }
        this.number = number;
    }

    public Positive(String text) {
        this(Integer.parseInt(text));
    }

    public Positive add(Positive num) {
        return new Positive(this.number + num.number);
    }

    public int getNumber() {
        return number;
    }
}

interface Splitter {
    boolean supports(String test);

    String[] split(String test);
}

class DefaultSplitter implements Splitter {
    private final String pattern = ",|:";

    @Override
    public boolean supports(String text) {
        return true;
    }

    @Override
    public String[] split(String text) {
        return text.split(pattern);
    }
}

class CustomSplitter implements Splitter {
    private final Pattern pattern = Pattern.compile("\\/\\/(.+)\\\\n(.*)");

    @Override
    public boolean supports(String text) {
        return pattern.matcher(text).matches();
    }

    @Override
    public String[] split(String text) {
        Matcher m = pattern.matcher(text);
        m.matches();

        String customDelimiter = m.group(1);
        return m.group(2).split(customDelimiter);
    }
}