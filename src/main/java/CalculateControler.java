import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.min;

public class CalculateControler {
    public String exec(String numFormulaStr){
        List<String> numList = new ArrayList<>();
        List<String> formulaList = new ArrayList<>();
        int index = 0;

        // 次に数値がくる状態=0,次に演算子が来る状態=1,演算子が連続で来ている状態=-1
        int numFormulaBalance = 0;
        StringBuilder insertNumStr = new StringBuilder();
        StringBuilder insertFuctionStr = new StringBuilder();
        // 数値と演算子を仕分ける。関数や括弧が出てきたら括弧の範囲内を引数に再帰処理
        final char[] CHAR_ARRAY = numFormulaStr.toCharArray();
        while(index < CHAR_ARRAY.length) {
            // 演算子が続いた場合処理中止
            if (numFormulaBalance == -1) {
                // 例外処理
                throw new IllegalArgumentException("演算子が連続しています");
            }
            // 数値の格納
            if (numFormulaBalance == 0 && ('-' == CHAR_ARRAY[index] || String.valueOf(CHAR_ARRAY[index]).matches("[0-9E\\.]"))) {
                insertNumStr.append(CHAR_ARRAY[index]);
                index++;
                // 格納する数値を取得
                while (index < CHAR_ARRAY.length) {
                    if (!String.valueOf(CHAR_ARRAY[index]).matches("[0-9E\\.]")) {
                        break;
                    }
                    insertNumStr.append(CHAR_ARRAY[index]);
                    index++;
                }
                // 不正値の検証
                if (!ValidateNumFormula.checkNum(insertNumStr.toString())) {
                    // 例外処理
                    throw new IllegalArgumentException("不正値です");
                }
                // 数値リストへ格納
                numList.add(insertNumStr.toString());
                // 数値変数初期化
                insertNumStr = new StringBuilder();
                // インクリメント
                numFormulaBalance++;
                continue;
            }
            // 演算子の格納
            else if (numFormulaBalance == 1 && String.valueOf(CHAR_ARRAY[index]).matches("[+\\-*/]")) {
                // 演算子リストへ格納
                formulaList.add(String.valueOf(CHAR_ARRAY[index]));
                index++;
                // デクリメント
                numFormulaBalance--;
                continue;
            }
            // 関数が出た場合(演算子の後に来るものとする）
            else if (numFormulaBalance == 0 && String.valueOf(CHAR_ARRAY[index]).matches("[sqrtmax]")) {
                String addStr = "";
                insertFuctionStr.append(CHAR_ARRAY[index]);
                index++;
                // 関数名の取得
                while (index < CHAR_ARRAY.length) {
                    if ("sqrt".equals(insertFuctionStr.toString()) || "max".equals(insertFuctionStr.toString())) {
                        // 括弧内情報取得
                        int parenthesesBalance = 0;
                        if ('(' == CHAR_ARRAY[index]) {
                            parenthesesBalance++;
                            index++;
                            StringBuilder functionArgsStr = new StringBuilder();
                            while (index < CHAR_ARRAY.length) {
                                if ('(' == CHAR_ARRAY[index]) {
                                    parenthesesBalance++;
                                } else if (')' == CHAR_ARRAY[index]) {
                                    parenthesesBalance--;
                                }
                                if (parenthesesBalance == 0) {
                                    index++;
                                    break;
                                }
                                // 引数格納
                                functionArgsStr.append(CHAR_ARRAY[index]);
                                index++;
                            }
                            // 配列に格納
                            String[] argsStrList = functionArgsStr.toString().split(",");
                            // 引数が数値になるように再帰呼び出し
                            for (int i = 0; i < argsStrList.length; i++) {
                                final String argsStr = argsStrList[i];
                                // 数値でなければ呼び出し
                                if (!ValidateNumFormula.checkNum(argsStr)) {
                                    argsStrList[i] = new CalculateControler().exec(argsStr);
                                }
                            }
                            // 関数メソッド呼び出し
                            addStr = CalculatorExe.call(insertFuctionStr.toString(), argsStrList);
                            break;
                        } else {
                            //例外処理
                            throw new IllegalArgumentException(insertFuctionStr + "関数の後に括弧がありません");
                        }
                    }
                    //関数名を取得するまで
                    insertFuctionStr.append(CHAR_ARRAY[index]);
                    index++;
                }
                // 数値リストへ格納
                numList.add(addStr);
                // インクリメント
                numFormulaBalance++;
                continue;
            }
            // 括弧が出た場合(演算子の後に来るものとする&関数の括弧ではないこと）
            else if (numFormulaBalance == 0 && '('==CHAR_ARRAY[index]) {
                // 括弧内情報取得
                int parenthesesBalance = 0;
                parenthesesBalance++;
                index++;
                StringBuilder functionArgsStr = new StringBuilder();
                while (index < CHAR_ARRAY.length) {
                    if ('(' == CHAR_ARRAY[index]) {
                        parenthesesBalance++;
                    } else if (')' == CHAR_ARRAY[index]) {
                        parenthesesBalance--;
                    }
                    if (parenthesesBalance == 0) {
                        index++;
                        break;
                    }
                    // 引数格納
                    functionArgsStr.append(CHAR_ARRAY[index]);
                    index++;
                }
                // 再帰呼び出し
                final String numStr = new CalculateControler().exec(functionArgsStr.toString());
                // 数値リストへ格納
                numList.add(numStr);
                // インクリメント
                numFormulaBalance++;
                continue;
            }else{
                throw new IllegalArgumentException("不正な式です");
            }
        }
        // 数値と演算子の数があっていなければ処理終了(1+1+,1++1のような不正な式を排除)
        if(!ValidateNumFormula.checkNumFormulaList(numList,formulaList)){
            // 例外処理
            throw new IllegalArgumentException("演算子と数値の数があっていません");
        }

        // 本計算(四則演算）
        while(numList.size() > 1){
            editNumFormulaList(numList,formulaList);
        }

        // 不要な小数点以下の数値を成型しリターン
        return editNum(numList.get(0));
    }

    public void editNumFormulaList(List<String> numList,List<String> formulaList){
        int additionIndex = forumlaIndex("+",formulaList);
        int subtractionIndex = forumlaIndex("-",formulaList);
        int multiplicationIndex = forumlaIndex("*",formulaList);
        int divisionIndex = forumlaIndex("/",formulaList);
        if(multiplicationIndex != -1 || divisionIndex != -1){
            int index = 0;
            if(multiplicationIndex != -1 && divisionIndex != -1){
                index = min(multiplicationIndex,divisionIndex);
            }else if(multiplicationIndex != -1){
                index = multiplicationIndex;
            }else if(divisionIndex != -1){
                index = divisionIndex;
            }

            String[] list = new String[]{numList.get(index),numList.get(index+1)};
            String str = CalculatorExe.call(formulaList.get(index),list);
            numList.set(index,str);
            numList.remove(index+1);
            formulaList.remove(index);
            return;
        }
        if(additionIndex != -1 || subtractionIndex != -1){
            int index = 0;
            if(additionIndex != -1 && subtractionIndex != -1){
                index = min(additionIndex,subtractionIndex);
            }else if(additionIndex != -1){
                index = additionIndex;
            }else if(subtractionIndex != -1){
                index = subtractionIndex;
            }
            String[] list = new String[]{numList.get(index),numList.get(index+1)};
            String str = CalculatorExe.call(formulaList.get(index),list);
            numList.set(index,str);
            numList.remove(index+1);
            formulaList.remove(index);
        }
    }

    public int forumlaIndex(String forumulaStr,List<String> formulaList){
        return formulaList.indexOf(forumulaStr);
    }

    public String editNum(String numStr){
        BigDecimal value = new BigDecimal(numStr);
        return value.stripTrailingZeros().toString();
    }
}
