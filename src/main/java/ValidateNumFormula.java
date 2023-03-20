import java.util.List;

public class ValidateNumFormula {
    static boolean validateInputStr(String str){
        if(str == null){
            return false;
        }
        // 使用可能文字を""に置き換え不要文字を洗い出す
        String checkInputStr = str.replaceAll("[0-9+\\-*/E.(),]","");
        checkInputStr = checkInputStr.replaceAll("max","");
        checkInputStr = checkInputStr.replaceAll("min","");
        checkInputStr = checkInputStr.replaceAll("sqrt","");
        // 引数に渡された文字が使用できるものであればtrue
        return "".equals(checkInputStr);
    }
    static boolean checkParentheses(String str){
        if(str == null){
            return false;
        }
        final char[] charArray = str.toCharArray();
        int countParentheses = 0;
        // 括弧の数と入れ子になっているか検証[例：NG-")(","(()"]
        for(final char c : charArray){
            if('('==c){
                countParentheses++;
            }else if(')'==c){
                countParentheses--;
            }
            // 括弧が入れ子になってなければfalse
            if(countParentheses < 0){
                return false;
            }
        }
        // 括弧の数があっていなければfalse
        return countParentheses == 0;
    }
    static boolean checkNum(String str){
        if(str == null){
            return false;
        }
        boolean checkStr = false;
        // 数値チェック(整数、小数点、整数E）
        if(str.matches("^-?[0-9]+[E.]?[0-9]+$")){
            checkStr = true;
        // 数値チェック（一桁の整数）
        } else if (str.matches("^-?[0-9]$")) {
            checkStr = true;
        // 数値チェック（小数E）
        } else if (str.matches("^-?[0-9]+.[0-9]+E?[0-9]+$")) {
            checkStr = true;
        }
        // 数値チェックに当てはまらなければfalse
        return checkStr;
    }
    static boolean checkNumFormulaList(List<String> numList,List<String> formulaList){
        final int numlistCount = numList.size();
        final int formulaListCount = formulaList.size();
        // 数値と演算子の数があっていなければfalse（例：数値（２つ）、演算子（１つ）ならtrue）
        return numlistCount - 1 == formulaListCount;
    }

}
