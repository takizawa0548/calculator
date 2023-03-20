import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorExeTest {

    @Test
    void 足し算成功_正の整数() {
        assertEquals("15", CalculatorExe.call("+",new String[]{"3","12"}));
    }
    @Test
    void 足し算成功_負数() {
        assertEquals("-7", CalculatorExe.call("+",new String[]{"-1","-6"}));
    }
    @Test
    void 足し算成功_小数() {
        assertEquals("15.80", CalculatorExe.call("+",new String[]{"3.15","12.65"}));
    }
    @Test
    void 足し算成功_小数e() {
        assertEquals("5.5E10", CalculatorExe.call("+",new String[]{"5E10","5E9"}));
    }
    @Test
    void 足し算成功_小数e_2() {
        assertEquals("5.5E10", CalculatorExe.call("+",new String[]{"5E10","5E9"}));
    }
    @Test
    void 引き算成功_正の整数() {
        assertEquals("9", CalculatorExe.call("-",new String[]{"12","3"}));
    }
    @Test
    void 引き算成功_負数() {
        assertEquals("5", CalculatorExe.call("-",new String[]{"-1","-6"}));
    }
    @Test
    void 引き算成功_左側が小さい整数() {
        assertEquals("-9", CalculatorExe.call("-",new String[]{"3","12"}));
    }
    @Test
    void 引き算成功_小数() {
        assertEquals("9.50", CalculatorExe.call("-",new String[]{"12.65","3.15"}));
    }
    @Test
    void 引き算成功_小数e() {
        assertEquals("4.5E10", CalculatorExe.call("-",new String[]{"5E10","5E9"}));
    }
    @Test
    void 引き算成功_小数e_2() {
        assertEquals("4.5E10", CalculatorExe.call("-",new String[]{"5E10","5E9"}));
    }
    @Test
    void 掛け算成功_正の整数() {
        assertEquals("36", CalculatorExe.call("*",new String[]{"12","3"}));
    }
    @Test
    void 掛け算成功_負数() {
        assertEquals("-36", CalculatorExe.call("*",new String[]{"-12","3"}));
    }
    @Test
    void 掛け算成功_小数() {
        assertEquals("39.8475", CalculatorExe.call("*",new String[]{"12.65","3.15"}));
    }
    @Test
    void 掛け算成功_小数e() {
        assertEquals("2.5E4", CalculatorExe.call("*",new String[]{"5E1","5E2"}));
    }
    @Test
    void 掛け算成功_小数e_2() {
        assertEquals("2.5E4", CalculatorExe.call("*",new String[]{"5E1","5E2"}));
    }
    @Test
    void 割り算成功_正の整数() {
        assertEquals("4.00000000000", CalculatorExe.call("/",new String[]{"12","3"}));
    }
    @Test
    void 割り算成功_負数() {
        assertEquals("-4.00000000000", CalculatorExe.call("/",new String[]{"12","-3"}));
    }
    @Test
    void 割り算成功_小数() {
        assertEquals("4.01587301587", CalculatorExe.call("/",new String[]{"12.65","3.15"}));
    }
    @Test
    void 割り算成功_小数e() {
        assertEquals("10.00000000000", CalculatorExe.call("/",new String[]{"5E10","5E9"}));
    }
    @Test
    void 割り算成功_小数e_2() {
        assertEquals("10.00000000000", CalculatorExe.call("/",new String[]{"5E10","5E9"}));
    }
    @Test
    void 割り算失敗_ゼロ除算() {
        Assertions.assertThrows(Exception.class, () -> {
            CalculatorExe.call("/",new String[]{"2","0"});
        });
    }
    @Test
    void 平方根引数() {
        assertEquals("1.4142135623730951", CalculatorExe.call("sqrt",new String[]{"2"}));
    }
    @Test
    void 平方根関数成功() {
        assertEquals("1.4142135623730951", CalculatorExe.call("sqrt",new String[]{"2"}));
    }
    @Test
    void 平方根関数失敗() {
        Assertions.assertThrows(Exception.class, () -> {
            CalculatorExe.call("sqrt",new String[]{"-2"});
        });
    }
    @Test
    void 最大値関数成功_正の整数() {
        assertEquals("2", CalculatorExe.call("max",new String[]{"2","1"}));
    }
    @Test
    void 最大値関数成功_負の整数() {
        assertEquals("-1", CalculatorExe.call("max",new String[]{"-2","-1"}));
    }
    @Test
    void 最大値関数成功_小数() {
        assertEquals("2.1", CalculatorExe.call("max",new String[]{"2.1","1.5"}));
    }
    @Test
    void 最大値関数成功_複数配列() {
        assertEquals("2", CalculatorExe.call("max",new String[]{"2","1","-2"}));
    }
    @Test
    void 最小値関数成功_正の整数() {
        assertEquals("1", CalculatorExe.call("min",new String[]{"2","1"}));
    }
    @Test
    void 最小値関数成功_負の整数() {
        assertEquals("-2", CalculatorExe.call("min",new String[]{"-2","-1"}));
    }
    @Test
    void 最小値関数成功_小数() {
        assertEquals("1.5", CalculatorExe.call("min",new String[]{"2.1","1.5"}));
    }
    @Test
    void 最小値関数成功_複数配列() {
        assertEquals("1", CalculatorExe.call("min",new String[]{"555555","2","1"}));
    }
    @Test
    void 引数不正値() {
        Assertions.assertThrows(Exception.class, () -> {
            CalculatorExe.call("+",new String[]{"aiueo","2"});
        });
    }
    @Test
    void 引数不正値_2() {
        Assertions.assertThrows(Exception.class, () -> {
            CalculatorExe.call("+",new String[]{"1","1..5"});
        });
    }
    @Test
    void 引数不正値_3() {
        Assertions.assertThrows(Exception.class, () -> {
            CalculatorExe.call("+",new String[]{"1","+"});
        });
    }
    @Test
    void 不正な引数_足し算() {
        Assertions.assertThrows(Exception.class, () -> {
            CalculatorExe.call("+",new String[]{"555555","2","1"});
        });
    }
    @Test
    void 不正な引数_引き算() {
        Assertions.assertThrows(Exception.class, () -> {
            CalculatorExe.call("-",new String[]{"555555","2","1"});
        });
    }
    @Test
    void 不正な引数_掛け算() {
        Assertions.assertThrows(Exception.class, () -> {
            CalculatorExe.call("*",new String[]{"555555","2","1"});
        });
    }
    @Test
    void 不正な引数_割り算() {
        Assertions.assertThrows(Exception.class, () -> {
            CalculatorExe.call("/",new String[]{"555555","2","1"});
        });
    }
    @Test
    void 不正な引数_平方根() {
        Assertions.assertThrows(Exception.class, () -> {
            CalculatorExe.call("sqrt",new String[]{"555555","2","1"});
        });
    }
    @Test
    void 不正な引数_最大値() {
        Assertions.assertThrows(Exception.class, () -> {
            CalculatorExe.call("max",new String[0]);
        });
    }
    @Test
    void 不正な引数_最小値() {
        Assertions.assertThrows(Exception.class, () -> {
            CalculatorExe.call("min",new String[0]);
        });
    }
    @Test
    void 使用不可な演算子の呼び出し() {
        Assertions.assertThrows(Exception.class, () -> {
            CalculatorExe.call("sum",new String[]{"555555","2","1"});
        });
    }
}