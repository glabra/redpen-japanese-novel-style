package cc.redpen.validator.sentence;

import java.lang.Character;
import cc.redpen.model.Sentence;
import cc.redpen.validator.Validator;

public class OrphanedMinusSignValidator extends Validator {
    private final char MINUS_SIGN = '\u2212';

    @Override
    public void validate(Sentence sentence) {
        String content = sentence.getContent();

        int toffset = content.indexOf(MINUS_SIGN);
        while (toffset >= 0 && toffset < content.length() - 1) {
            if (!Character.isDigit(content.charAt(toffset + 1))) {
                this.addLocalizedErrorWithPosition(
                    sentence, toffset, toffset, MINUS_SIGN
                );
            }

            toffset = content.indexOf(MINUS_SIGN, toffset + 1);
        }
    }
}
