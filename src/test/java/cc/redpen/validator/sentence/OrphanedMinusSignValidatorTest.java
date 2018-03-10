package cc.redpen.validator.sentence;

import cc.redpen.model.Sentence;
import cc.redpen.validator.ValidationError;
import cc.redpen.validator.Validator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrphanedMinusSignValidatorTest {
    @Test
    public void testDetectOrphanedSign() {
        Validator validator = new OrphanedMinusSignValidator();
        List<ValidationError> errors = new ArrayList<>();
        validator.setErrorList(errors);
        validator.validate(new Sentence("\u2212ほげ", 0));
        assertEquals(1, errors.size());
    }

    @Test
    public void testWithoutOrphanedSign() {
        Validator validator = new OrphanedMinusSignValidator();
        List<ValidationError> errors = new ArrayList<>();
        validator.setErrorList(errors);
        validator.validate(new Sentence("\u2212123", 0));
        assertEquals(0, errors.size());
    }
}
