package cc.redpen.validator.sentence;

import java.lang.Character;
import cc.redpen.model.Sentence;
import cc.redpen.validator.Validator;
import cc.redpen.config.Symbol;
import cc.redpen.config.SymbolType;
import cc.redpen.tokenizer.TokenElement;

public class RedundantCommaValidator extends Validator {
    @Override
    public void validate(Sentence sentence) {
        char comma = this.getSymbolTable().getSymbol(SymbolType.COMMA).getValue();
        char right = this.getSymbolTable().getSymbol(SymbolType.RIGHT_SQUARE_BRACKET).getValue();

        TokenElement prevToken = null;
        for (TokenElement token : sentence.getTokens()) {
            if (prevToken != null) {
                boolean isComma = prevToken.getSurface().indexOf(comma) != -1;
                boolean isRight = token.getSurface().indexOf(right) != -1;
                if (isComma && isRight) {
                    this.addLocalizedErrorFromToken(sentence, prevToken);
                }
            }

            prevToken = token;
        }
    }
}
