package cc.redpen.validator.sentence;

import cc.redpen.model.Sentence;
import cc.redpen.validator.Validator;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EvenSymbolsValidator extends Validator {
    private static String EVEN_SYMBOLS = "…―";

    @Override
    public void validate(Sentence sentence) {
        String content = sentence.getContent();

        for (char symbol : EVEN_SYMBOLS.toCharArray()) {
            Pattern pat = Pattern.compile(String.format("%c+", symbol));
            Matcher re = pat.matcher(content);

            while (re.find()) {
                int length = re.end() - re.start();
                if (length % 2 == 1) {
                    this.addLocalizedErrorWithPosition(
                        sentence, re.start(), re.end(), re.group()
                    );
                }
            }
        }
    }
}
