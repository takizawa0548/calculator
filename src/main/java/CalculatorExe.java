import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.*;

public class CalculatorExe {
    // 演算メソッドの呼び出し
    static String call (String operator, String[] funcArgList){
        // 数値チェック
        for(String checkArg : funcArgList){
            // 当てはまらなければ例外処理
            if(!ValidateNumFormula.checkNum(checkArg)){
                throw new IllegalArgumentException("数値が不正です");
            }
        }
        switch (operator) {
            case "+" :
                if(funcArgList.length==2){
                    return addition(funcArgList[0], funcArgList[1]);
                }else{
                    throw new IllegalArgumentException("引数が不正です");
                }
            case "-" :
                if(funcArgList.length==2){
                    return subtraction(funcArgList[0], funcArgList[1]);
                }else{
                    throw new IllegalArgumentException("引数が不正です");
                }
            case "*" :
                if(funcArgList.length==2){
                    return multiplication(funcArgList[0], funcArgList[1]);
                }else{
                    throw new IllegalArgumentException("引数が不正です");
                }
            case "/" :
                if(funcArgList.length==2){
                    return division(funcArgList[0], funcArgList[1]);
                }else{
                    throw new IllegalArgumentException("引数が不正です");
                }
            case "sqrt" :
                if(funcArgList.length==1){
                    return functionSqrt(funcArgList[0]);
                }else{
                    throw new IllegalArgumentException("引数が不正です");
                }
            case "max" :
                if(funcArgList.length!=0){
                    return functionMax(funcArgList);
                }else{
                    throw new IllegalArgumentException("引数が不正です");
                }
            case "min" :
                if(funcArgList.length!=0){
                    return functionMin(funcArgList);
                }else{
                    throw new IllegalArgumentException("引数が不正です");
                }
            default :
                throw new IllegalArgumentException("使用不可能な演算子です");
        }
    }
    // 足し算
    private static String addition (String numLeft,String numRight){
        BigDecimal calcLeft = new BigDecimal(numLeft);
        BigDecimal calcRight = new BigDecimal(numRight);
        return calcLeft.add(calcRight).toString().replace("+","");
    }
    // 引き算
    private static String subtraction (String numLeft,String numRight){
        BigDecimal calcLeft = new BigDecimal(numLeft);
        BigDecimal calcRight = new BigDecimal(numRight);
        return calcLeft.subtract(calcRight).toString().replace("+","");
    }
    // 掛け算
    private static String multiplication (String numLeft,String numRight){
        BigDecimal calcLeft = new BigDecimal(numLeft);
        BigDecimal calcRight = new BigDecimal(numRight);
        return calcLeft.multiply(calcRight).toString().replace("+","");
    }
    // 割り算
    private static String division (String numLeft,String numRight){
        BigDecimal calcLeft = new BigDecimal(numLeft);
        BigDecimal calcRight = new BigDecimal(numRight);
        // ゼロ除算の考慮
        if("0".equals(numRight)){
            //例外処理
            throw new IllegalArgumentException("0除算が発生しました");
        }
        // 無限小数の考慮
        return calcLeft.divide(calcRight,11, RoundingMode.HALF_UP).toString().replace("+","");
    }
    // 平方根
    private static String functionSqrt (String funcArgStr){
        // 負数の考慮
        if("-".equals(funcArgStr.substring(0,1))){
            //例外処理
            throw new IllegalArgumentException("sqrt関数内で負数が使用されました" + funcArgStr);
        }
        return String.valueOf(sqrt(Double.parseDouble(funcArgStr))).replace("+","");
    }
    // 最大値の選択
    private static String functionMax (String[] funcArgList){
        BigDecimal compare = new BigDecimal(funcArgList[0]);
        for(int i = 1; i < funcArgList.length; i++){
            BigDecimal compareR = new BigDecimal(funcArgList[i]);
            if(compare.compareTo(compareR) < 0){
                compare = new BigDecimal(funcArgList[i]);
            }
        }
        return String.valueOf(compare.toString()).replace("+","");
    }
    // 最小値の選択
    private static String functionMin (String[] funcArgList){
        BigDecimal compare = new BigDecimal(funcArgList[0]);
        for(int i = 1; i < funcArgList.length; i++){
            BigDecimal compareR = new BigDecimal(funcArgList[i]);
            if(compare.compareTo(compareR) > 0){
                compare = new BigDecimal(funcArgList[i]);
            }
        }
        return String.valueOf(compare.toString()).replace("+","");
    }
}
