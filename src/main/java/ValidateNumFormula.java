import java.util.List;

public class ValidateNumFormula {
    // 引数に渡された文字が使用できるものであればtrueを返す
    static boolean validateInputStr(String str){
        if(str == null){
            return false;
        }
        String checkInputStr = str.replaceAll("[0-9+\\-*/E.(),]","");
        checkInputStr = checkInputStr.replaceAll("max","");
        checkInputStr = checkInputStr.replaceAll("sqrt","");
        if("".equals(checkInputStr)){
            return true;
        }
        return false;
    }
    static boolean checkParentheses(String str){
        if(str == null){
            return false;
        }
        final char[] charArray = str.toCharArray();
        int countParentheses = 0;
        for(final char c : charArray){
            if('('==c){
                countParentheses++;
            }else if(')'==c){
                countParentheses--;
            }
            // 括弧が入れ子になっているか
            if(countParentheses < 0){
                return false;
            }
        }
        // 括弧の数があっていなければfalseを返す
        if(countParentheses != 0){
            return false;
        }
        return true;
    }
    static boolean checkNum(String str){
        if(str == null){
            return false;
        }
        // 数値チェック
        boolean checkStr = false;
        if(str.matches("^\\-?[0-9]+[E\\.]?[0-9]+$")){
            checkStr = true;
        } else if (str.matches("^\\-?[0-9]$")) {
            checkStr = true;
        }
        return checkStr;
    }
    static boolean checkNumFormulaList(List<String> numList,List<String> formulaList){
        final int numlistCount = numList.size();
        final int formulaListCount = formulaList.size();
        if(numlistCount-1 == formulaListCount){
            return true;
        }
        // 数値と演算子の数があっていない
        return false;
    }

}
