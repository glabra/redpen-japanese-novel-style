package cc.redpen.validator.sentence;

import cc.redpen.model.Sentence;
import cc.redpen.validator.ValidationError;
import cc.redpen.validator.Validator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EvenSymbolsValidatorTest {
    @Test
    public void testDetectOddSymbols() {
        Validator validator = new EvenSymbolsValidator();
        List<ValidationError> errors = new ArrayList<>();
        validator.setErrorList(errors);
        validator.validate(new Sentence("これは…だめ……", 0));
        assertEquals(1, errors.size());
    }

    @Test
    public void testWithoutOddSymbols() {
        Validator validator = new EvenSymbolsValidator();
        List<ValidationError> errors = new ArrayList<>();
        validator.setErrorList(errors);
        validator.validate(new Sentence("これは……いい……", 0));
        assertEquals(0, errors.size());
    }
}
