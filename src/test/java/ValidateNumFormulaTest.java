import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateNumFormulaTest {

    @Test
    void validateInputStrSuccess() {
        ValidateNumFormula validate = new ValidateNumFormula();
        assertEquals(true, validate.validateInputStr("1+2"));
    }

    @Test
    void validateInputStrFailure() {
        ValidateNumFormula validate = new ValidateNumFormula();
        assertEquals(false, validate.validateInputStr("1a2"));
    }

    @Test
    void validateFormulaSuccess() {
        ValidateNumFormula validate = new ValidateNumFormula();
        assertEquals(true, validate.validateFormula("1+2"));
    }
    @Test
    void validateFormulaFailure() {
        ValidateNumFormula validate = new ValidateNumFormula();
        assertEquals(false, validate.validateFormula("12++"));
    }
}