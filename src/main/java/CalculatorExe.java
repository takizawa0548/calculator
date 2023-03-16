public class CalculatorExe {
    // 演算メソッドの呼び出し
    long call (String operator, String[] funcArgList){
        switch (operator) {
            case "+" :
                return addition(funcArgList);
            case "-" :
                return subtraction(funcArgList);
            case "*" :
                return multiplication(funcArgList);
            case "/" :
                return division(funcArgList);
            case "%" :
                return remainder(funcArgList);
            case "sqrt" :
                return sqrt(funcArgList);
            case "max" :
                return max(funcArgList);
            case "min" :
                return min(funcArgList);
            default:
                return 0;
        }
    }
    // 足し算
    private long addition (String[] funcArgList){

        return 0;
    }
    // 引き算
    private long subtraction (String[] funcArgList){

        return 0;
    }
    // 掛け算
    private long multiplication (String[] funcArgList){

        return 0;
    }
    // 割り算
    private long division (String[] funcArgList){
        // 0除算の考慮
        return 0;
    }
    // あまり算
    private long remainder (String[] funcArgList){
        // 0除算の考慮
        return 0;
    }
    // 平方根
    private long sqrt (String[] funcArgList){

        return 0;
    }
    // 最大値の選択
    private long max (String[] funcArgList){

        return 0;
    }
    // 最小値の選択
    private long min (String[] funcArgList){

        return 0;
    }
}
