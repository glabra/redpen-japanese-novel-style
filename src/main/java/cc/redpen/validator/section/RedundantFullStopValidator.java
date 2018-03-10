package cc.redpen.validator.section;

import java.lang.Character;
import java.util.List;
import cc.redpen.model.Section;
import cc.redpen.model.Paragraph;
import cc.redpen.model.Sentence;
import cc.redpen.validator.Validator;
import cc.redpen.config.Symbol;
import cc.redpen.config.SymbolType;
import cc.redpen.tokenizer.TokenElement;

public class RedundantFullStopValidator extends Validator {
    private char fullStop;
    private char rightBracket;

    private void validateSentence(Sentence left, Sentence right) {
        List<TokenElement> leftTokens = left.getTokens();
        TokenElement leftToken = leftTokens.get(leftTokens.size() - 1);
        TokenElement rightToken = right.getTokens().get(0);

        boolean isFstop = leftToken.getSurface().indexOf(this.fullStop) != -1;
        boolean isRight = rightToken.getSurface().indexOf(this.rightBracket) != -1;

        if (isFstop && isRight) {
            this.addLocalizedErrorFromToken(left, leftToken);
        }
    }

    private void validateParagraph(Paragraph paragraph) {
        Sentence prevSentence = null;

        for (Sentence sentence : paragraph.getSentences()) {
            if (prevSentence != null) {
                this.validateSentence(prevSentence, sentence);
            }

            prevSentence = sentence;
        }
    }

    @Override
    public void validate(Section section) {
        this.fullStop = this.getSymbolTable().getSymbol(SymbolType.FULL_STOP).getValue();
        this.rightBracket = this.getSymbolTable().getSymbol(SymbolType.RIGHT_SQUARE_BRACKET).getValue();

        for (Paragraph paragraph : section.getParagraphs()) {
            this.validateParagraph(paragraph);
        }
    }
}
