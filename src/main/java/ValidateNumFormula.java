import java.util.List;
/**
 * 計算機検証クラス
 * @author M.Takizawa
 * @version 1.0.0
 */
public class ValidateNumFormula {
    /**
     * 入力値検証<BR>
     * 許可されていない文字が入っていればfalseを返す
     * @param str 数式
     * @return 検証結果(boolean)
     */
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
    /**
     * 括弧検証<BR>
     * 括弧の数と入れ子になっているか検証[例：NG-")(","(()"]
     * @param str 数式
     * @return 検証結果(boolean)
     */
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
            // 括弧が入れ子になってなければfalse
            if(countParentheses < 0){
                return false;
            }
        }
        // 括弧の数があっていなければfalse
        return countParentheses == 0;
    }
    /**
     * 数値検証<BR>
     * 演算に使用できる数値か検証する
     * @param str 数式
     * @return 検証結果(boolean)
     */
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
    /**
     * 演算リストと数値リストの整合性検証<BR>
     * 数値リストのサイズが演算子リストより１大きければtrue<BR>
     * （例：数値リスト（２つ）、演算子リスト（１つ）ならtrue）
     * @param numList 数値リスト
     * @param formulaList 演算子リスト
     * @return 検証結果(boolean)
     */
    static boolean checkNumFormulaList(List<String> numList,List<String> formulaList){
        final int numlistCount = numList.size();
        final int formulaListCount = formulaList.size();
        // 数値と演算子の数があっていなければfalse
        return numlistCount - 1 == formulaListCount;
    }

    /**
     * 入力文字列から数値作成時の検証（最初の文字）
     * @param c 検証文字
     * @return 検証結果(boolean)
     */
    static boolean checkCharFirstNumber(char c){
        // 最初の文字検証。マイナス記号または数値ならtrue
        return '-' == c || String.valueOf(c).matches("[0-9]");
    }
    /**
     * 入力文字列から数値作成時の検証（二文字目以降）
     * @param c 検証文字
     * @return 検証結果(boolean)
     */
    static boolean checkCharLoopNumber(char c){
        // ２文字目以降検証。数値または"E"か"."ならtrue
        return String.valueOf(c).matches("[0-9E.]");
    }
    /**
     * 入力文字列から演算子取得時の検証
     * @param c 検証文字
     * @return 検証結果(boolean)
     */
    static boolean checkCharFomula(char c){
        // 演算子文字検証。
        return String.valueOf(c).matches("[+\\-*/]");
    }
    /**
     * 入力文字列から関数作成時の検証（最初の文字）
     * @param c 検証文字
     * @return 検証結果(boolean)
     */
    static boolean checkCharFirstFunction(char c){
        // 最初の関数文字検証。(Sqrt,max,min)
        return String.valueOf(c).matches("[sm]");
    }
    /**
     * 入力文字列から関数作成時の検証（二文字目以降）
     * @param str 検証文字列
     * @return 検証結果(boolean)
     */
    static boolean checkCharLoopFunction(String str){
        // 最初の関数文字列検証。(Sqrt,max,min)
        return "sqrt".equals(str) || "max".equals(str)|| "min".equals(str);
    }

}
