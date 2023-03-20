import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidateNumFormulaTest {

    @Test
    void 入力可能文字の検証成功() {
        assertTrue(ValidateNumFormula.validateInputStr("0123456789.E+-*/()sqrtmaxmin,"));
    }

    @Test
    void 入力可能文字の検証失敗() {
        assertFalse(ValidateNumFormula.validateInputStr("abcdfghijklmnopqrstuvwxyz@!?"));
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
        assertFalse(ValidateNumFormula.checkParentheses("(()"));
    }
    @Test
    void 括弧の入れ子確認失敗() {
        assertFalse(ValidateNumFormula.checkParentheses("())()"));
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
        assertFalse(ValidateNumFormula.checkNum("-1.e5"));
    }
    @Test
    void 数値リストと演算子リストのカウント確認成功() {
        List<String> numList = Arrays.asList("1","2");
        List<String> formulaList = List.of("+");
        assertTrue(ValidateNumFormula.checkNumFormulaList(numList,formulaList));
    }
    @Test
    void 数値リストと演算子リストのカウント確認失敗_演算子が多い() {
        List<String> numList = Arrays.asList("1","2");
        List<String> formulaList = Arrays.asList("+","+");
        assertFalse(ValidateNumFormula.checkNumFormulaList(numList, formulaList));
    }
    @Test
    void 数値リストと演算子リストのカウント確認失敗_数値が多い() {
        List<String> numList = Arrays.asList("1","2","2");
        List<String> formulaList = List.of("+");
        assertFalse(ValidateNumFormula.checkNumFormulaList(numList, formulaList));
    }
    @Test
    void 数値配列一番目の判定_数値_成功() {
        assertTrue(ValidateNumFormula.checkCharFirstNumber('0'));
    }
    @Test
    void 数値配列一番目の判定_マイナス記号_成功() {
        assertTrue(ValidateNumFormula.checkCharFirstNumber('-'));
    }
    @Test
    void 数値配列一番目の判定_失敗() {
        assertFalse(ValidateNumFormula.checkCharFirstNumber('E'));
    }
    @Test
    void 数値配列二番目以降の判定_成功() {
        assertTrue(ValidateNumFormula.checkCharLoopNumber('0'));
    }
    @Test
    void 数値配列二番目以降の判定_失敗() {
        assertFalse(ValidateNumFormula.checkCharLoopNumber('a'));
    }
    @Test
    void 関数配列一番目以降の判定_成功() {
        assertTrue(ValidateNumFormula.checkCharFirstFunction('s'));
    }
    @Test
    void 関数配列一番目以降の判定_失敗() {
        assertFalse(ValidateNumFormula.checkCharFirstFunction('S'));
    }
    @Test
    void 関数配列二番目以降の判定_成功() {
        assertTrue(ValidateNumFormula.checkCharLoopFunction("sqrt"));
    }
    @Test
    void 数値配列一番目以降の判定_失敗() {
        assertFalse(ValidateNumFormula.checkCharLoopFunction("wwwwwww"));
    }
}