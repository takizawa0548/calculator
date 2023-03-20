import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.*;

/**
 * 演算実行クラス
 * @author M.Takizawa
 * @version 1.0.0
 */
public class CalculatorExe {
    /**
     * 演算メソッドの呼び出し<BR>
     * 指定された引数に応じて演算メソッドを呼び出す
     * @param operator 演算子
     * @param funcArgList 数値リスト
     * @return 検証結果(boolean)
     * @throws IllegalArgumentException 演算子に対する引数が不正の場合
     */
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
    /**
     * 足し算をする
     * @param numLeft 左側の数値
     * @param numRight 右側の数値
     * @return 演算結果
     */
    private static String addition (String numLeft,String numRight){
        BigDecimal calcLeft = new BigDecimal(numLeft);
        BigDecimal calcRight = new BigDecimal(numRight);
        return calcLeft.add(calcRight).toString().replace("+","");
    }
    /**
     * 引き算をする
     * @param numLeft 左側の数値
     * @param numRight 右側の数値
     * @return 演算結果
     */
    private static String subtraction (String numLeft,String numRight){
        BigDecimal calcLeft = new BigDecimal(numLeft);
        BigDecimal calcRight = new BigDecimal(numRight);
        return calcLeft.subtract(calcRight).toString().replace("+","");
    }
    /**
     * 掛け算をする
     * @param numLeft 左側の数値
     * @param numRight 右側の数値
     * @return 演算結果
     */
    private static String multiplication (String numLeft,String numRight){
        BigDecimal calcLeft = new BigDecimal(numLeft);
        BigDecimal calcRight = new BigDecimal(numRight);
        return calcLeft.multiply(calcRight).toString().replace("+","");
    }
    /**
     * 割り算をする
     * @param numLeft 左側の数値
     * @param numRight 右側の数値
     * @return 演算結果
     * @throws IllegalArgumentException ０除算が発生した場合
     */
    private static String division (String numLeft,String numRight){
        BigDecimal calcLeft = new BigDecimal(numLeft);
        BigDecimal calcRight = new BigDecimal(numRight);
        // ゼロ除算の考慮
        if("0".equals(numRight)){
            //例外処理
            throw new IllegalArgumentException("ゼロ除算が発生しました");
        }
        // 無限小数の考慮
        return calcLeft.divide(calcRight,11, RoundingMode.HALF_UP).toString().replace("+","");
    }
    /**
     * Sqrt関数を実行する
     * @param funcArgStr 数値
     * @return 演算結果
     * @throws IllegalArgumentException 負数が指定された場合
     */
    private static String functionSqrt (String funcArgStr){
        // 負数の考慮
        if("-".equals(funcArgStr.substring(0,1))){
            //例外処理
            throw new IllegalArgumentException("sqrt関数内で負数が使用されました" + funcArgStr);
        }
        return String.valueOf(sqrt(Double.parseDouble(funcArgStr))).replace("+","");
    }
    /**
     * Max関数を実行する
     * @param funcArgList 数値リスト
     * @return 演算結果
     */
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
    /**
     * Min関数を実行する
     * @param funcArgList 数値リスト
     * @return 演算結果
     */
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
