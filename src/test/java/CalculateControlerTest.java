import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculateControlerTest {

    @Test
    void 呼び出し成功() {
        CalculateControler calcControl = new CalculateControler();
        assertEquals("2", calcControl.exec("1+1"));
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
}