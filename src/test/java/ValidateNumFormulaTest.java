import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidateNumFormulaTest {

    @Test
    void 入力可能文字の検証成功() {
        assertTrue(ValidateNumFormula.validateInputStr("0123456789.E+-*/()sqrtmax,"));
    }

    @Test
    void 入力可能文字の検証失敗() {
        assertEquals(false, ValidateNumFormula.validateInputStr("abcdfghijklmnopqrstuvwxyz@!?"));
    }

    @Test
    void 括弧の数確認成功() {
        assertTrue(ValidateNumFormula.checkParentheses("(())"));
    }
    @Test
    void 括弧なし成功() {
        assertTrue(ValidateNumFormula.checkParentheses("1+2"));
    }
    @Test
    void 括弧の数確認失敗() {
        assertEquals(false, ValidateNumFormula.checkParentheses("(()"));
    }
    @Test
    void 括弧の入れ子確認失敗() {
        assertEquals(false, ValidateNumFormula.checkParentheses("())()"));
    }
    @Test
    void 数値チェック成功_整数_複数桁() {
        assertTrue(ValidateNumFormula.checkNum("12345"));
    }
    @Test
    void 数値チェック成功_負数_複数桁() {
        assertTrue(ValidateNumFormula.checkNum("-12345"));
    }
    @Test
    void 数値チェック成功_整数_一桁() {
        assertTrue(ValidateNumFormula.checkNum("1"));
    }
    @Test
    void 数値チェック成功_負数_一桁() {
        assertTrue(ValidateNumFormula.checkNum("-1"));
    }
    @Test
    void 数値チェック成功_小数() {
        assertTrue(ValidateNumFormula.checkNum("1.5"));
    }
    @Test
    void 数値チェック成功_小数E() {
        assertTrue(ValidateNumFormula.checkNum("1E5"));
    }
    @Test
    void 数値チェック失敗() {
        assertEquals(false, ValidateNumFormula.checkNum("-1.e5"));
    }
    @Test
    void 数値リストと演算子リストのカウント確認成功() {
        List<String> numList = Arrays.asList("1","2");
        List<String> formulaList = Arrays.asList("+");
        assertTrue(ValidateNumFormula.checkNumFormulaList(numList,formulaList));
    }
    @Test
    void 数値リストと演算子リストのカウント確認失敗_演算子が多い() {
        List<String> numList = Arrays.asList("1","2");
        List<String> formulaList = Arrays.asList("+","+");
        assertEquals(false, ValidateNumFormula.checkNumFormulaList(numList,formulaList));
    }
    @Test
    void 数値リストと演算子リストのカウント確認失敗_数値が多い() {
        List<String> numList = Arrays.asList("1","2","2");
        List<String> formulaList = Arrays.asList("+");
        assertEquals(false, ValidateNumFormula.checkNumFormulaList(numList,formulaList));
    }
}