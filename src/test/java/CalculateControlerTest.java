import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculateControlerTest {

    @Test
    void レベル１例() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("-39", calcControl.exec("1+10/2-3-7*6"));
    }
    @Test
    void レベル２例() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("39", calcControl.exec("5E10/5E9*3.9"));
    }
    @Test
    void レベル３例() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("3", calcControl.exec("((10+3)*3)/(3+(40-5*2)/3)"));
    }
    @Test
    void レベル４例() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("9", calcControl.exec("sqrt(sqrt(6E3+(13*40)+41))"));
    }
    @Test
    void レベル５例() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("54", calcControl.exec("max(10*sqrt(9),6*sqrt(81),20)"));
    }
    @Test
    void 演算子リストから位置取得_足し算() {
        CalculateControler calcControl = new CalculateControler();
        List<String> formulaList = Arrays.asList("+","*","+","*","/");
        assertEquals(0, calcControl.forumlaIndex("+",formulaList));
    }
    @Test
    void 演算子リストから位置取得_引き算() {
        CalculateControler calcControl = new CalculateControler();
        List<String> formulaList = Arrays.asList("*","-","/","*","/");
        assertEquals(1, calcControl.forumlaIndex("-",formulaList));
    }
    @Test
    void 演算子リストから位置取得_掛け算() {
        CalculateControler calcControl = new CalculateControler();
        List<String> formulaList = Arrays.asList("+","-","+","*","-");
        assertEquals(3, calcControl.forumlaIndex("*",formulaList));
    }
    @Test
    void 演算子リストから位置取得_割り算() {
        CalculateControler calcControl = new CalculateControler();
        List<String> formulaList = Arrays.asList("+","-","+","/","/");
        assertEquals(3, calcControl.forumlaIndex("/",formulaList));
    }
    @Test
    void 演算子リストから位置取得_該当なし() {
        CalculateControler calcControl = new CalculateControler();
        List<String> formulaList = Arrays.asList("+","-","+","*");
        assertEquals(-1, calcControl.forumlaIndex("/",formulaList));
    }
    @Test
    void 計算結果格納確認() {
        CalculateControler calcControl = new CalculateControler();
        List<String> numList = new ArrayList<>();
        numList.add("1");
        numList.add("2");
        List<String> formulaList = new ArrayList<>();
        formulaList.add("+");
        calcControl.editNumFormulaList(numList,formulaList);
        assertEquals("3", numList.get(0));
        assertEquals(1, numList.size());
        assertEquals(0, formulaList.size());
    }
    @Test
    void 計算結果小数点以下の成型_1() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("-39", calcControl.editNum("-39.00000000000"));
    }
    @Test
    void 計算結果小数点以下の成型_2() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("39", calcControl.editNum("39.000000000000"));
    }
    @Test
    void 計算結果小数点以下の成型_3() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("3", calcControl.editNum("3.00000000000"));
    }
    @Test
    void 計算結果小数点以下の成型_4() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("9", calcControl.editNum("9.0"));
    }
    @Test
    void 計算結果小数点以下の成型_5() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("54", calcControl.editNum("54.0"));
    }
    @Test
    void 計算結果小数点以下の成型_整数() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("54", calcControl.editNum("54"));
    }
    @Test
    void 計算結果小数点以下の成型_負数() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("-54", calcControl.editNum("-54"));
    }
    @Test
    void 計算結果小数点以下の成型_一桁() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("5", calcControl.editNum("5"));
    }
    @Test
    void 計算結果小数点以下の成型_小数あり() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("5.5", calcControl.editNum("5.5"));
    }
    @Test
    void 計算結果小数点以下の成型_小数あり_2() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("5.5", calcControl.editNum("5.50"));
    }
    @Test
    void 計算結果小数点以下の成型_小数あり_3() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("5.505", calcControl.editNum("5.505"));
    }
    @Test
    void 計算結果小数点以下の成型_小数e() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("5E5", calcControl.editNum("5E5"));
    }
}