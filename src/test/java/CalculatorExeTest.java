import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorExeTest {

    @Test
    void callAddition() {
        CalculatorExe exec = new CalculatorExe();
        assertEquals(15, exec.call("+",new String[]{"3","12"}));
    }
    @Test
    void callSubtraction() {
        CalculatorExe exec = new CalculatorExe();
        assertEquals(9, exec.call("-",new String[]{"12","3"}));
    }
    @Test
    void callMultiplication() {
        CalculatorExe exec = new CalculatorExe();
        assertEquals(36, exec.call("*",new String[]{"12","3"}));
    }
    @Test
    void callDivision() {
        CalculatorExe exec = new CalculatorExe();
        assertEquals(4, exec.call("/",new String[]{"12","3"}));
    }
    @Test
    void callRemainder() {
        CalculatorExe exec = new CalculatorExe();
        assertEquals(1, exec.call("%",new String[]{"16","3"}));
    }
    @Test
    void callSqrt() {
        CalculatorExe validate = new CalculatorExe();
        assertEquals("1.414213562", validate.call("sqrt",new String[]{"2"}));
    }
    @Test
    void callMax() {
        CalculatorExe validate = new CalculatorExe();
        assertEquals("2", validate.call("max",new String[]{"2","1"}));
    }
    @Test
    void callMin() {
        CalculatorExe validate = new CalculatorExe();
        assertEquals("1", validate.call("min",new String[]{"2","1"}));
    }
}