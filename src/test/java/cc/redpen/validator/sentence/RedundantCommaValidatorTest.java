package cc.redpen.validator.sentence;

import cc.redpen.RedPen;
import cc.redpen.RedPenException;
import cc.redpen.config.Symbol;
import cc.redpen.config.Configuration;
import cc.redpen.config.ValidatorConfiguration;
import cc.redpen.model.Document;
import cc.redpen.model.Sentence;
import cc.redpen.tokenizer.NeologdJapaneseTokenizer;
import cc.redpen.validator.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static cc.redpen.config.SymbolType.COMMA;
import static cc.redpen.config.SymbolType.RIGHT_SQUARE_BRACKET;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedundantCommaValidatorTest {
    private Configuration getConfiguration() {
        return Configuration.builder("ja")
            .addValidatorConfig(new ValidatorConfiguration("RedundantComma"))
            .addSymbol(new Symbol(COMMA, '、', ","))
            .addSymbol(new Symbol(RIGHT_SQUARE_BRACKET, '」', "]"))
            .build();
    }

    @Test
    public void testDetectRedundantComma() throws RedPenException {
        List<Document> documents = new ArrayList<>();
        documents.add(Document.builder(new NeologdJapaneseTokenizer())
            .addSection(1)
            .addParagraph()
            .addSentence(new Sentence("「よくない、」", 1))
            .build());

        RedPen redPen = new RedPen(this.getConfiguration());
        List<ValidationError> errors = redPen.validate(documents).get(documents.get(0));
        assertEquals(1, errors.size());
    }

    @Test
    public void testWithoutRedundantComma() throws RedPenException {
        List<Document> documents = new ArrayList<>();
        documents.add(Document.builder(new NeologdJapaneseTokenizer())
            .addSection(1)
            .addParagraph()
            .addSentence(new Sentence("「よい」", 1))
            .build());

        RedPen redPen = new RedPen(this.getConfiguration());
        List<ValidationError> errors = redPen.validate(documents).get(documents.get(0));
        assertEquals(0, errors.size());
    }
}
