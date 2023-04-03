import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.min;
/**
 * 計算機実行クラス
 * @author M.Takizawa
 * @version 1.0.0
 */
public class CalculateControler {
    /**
     * 実行メソッド<BR>
     * 与えられた式を再帰的に処理し計算結果を返す
     * @param numFormulaStr 数式
     * @return 演算結果
     * @throws IllegalArgumentException 不正な式の場合
     */
    public String exec(String numFormulaStr){
        List<String> numList = new ArrayList<>();
        List<String> formulaList = new ArrayList<>();
        int index = 0;

        // 次に数値がくる状態=0,次に演算子が来る状態=1,演算子が連続で来ている状態=-1
        int numFormulaBalance = 0;
        StringBuilder insertNumStr = new StringBuilder();
        // 数値と演算子を仕分ける。関数や括弧が出てきたら括弧の範囲内を引数に再帰処理
        final char[] CHAR_ARRAY = numFormulaStr.toCharArray();
        while(index < CHAR_ARRAY.length) {
            // 演算子が続いた場合処理中止
            if (numFormulaBalance == -1) {
                // 例外処理
                throw new IllegalArgumentException("演算子が連続しています");
            }
            // 数値の格納
            if (numFormulaBalance == 0 && ValidateNumFormula.checkCharFirstNumber(CHAR_ARRAY[index])) {
                // 数値配列へ格納
                insertNumStr.append(CHAR_ARRAY[index]);
                index++;
                // 格納する数値を取得
                while (index < CHAR_ARRAY.length) {
                    // ２文字目以降で数値が取得できなくなったらbreak
                    if (!ValidateNumFormula.checkCharLoopNumber(CHAR_ARRAY[index])) {
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
            }
            // 演算子の格納
            else if (numFormulaBalance == 1 && ValidateNumFormula.checkCharFomula(CHAR_ARRAY[index])) {
                // 演算子リストへ格納
                formulaList.add(String.valueOf(CHAR_ARRAY[index]));
                index++;
                // デクリメント
                numFormulaBalance--;
            }
            // 関数が出た場合(演算子の後に来るものとする）
            else if (numFormulaBalance == 0 && ValidateNumFormula.checkCharFirstFunction(CHAR_ARRAY[index])) {
                String addStr = "";
                StringBuilder insertFuctionStr = new StringBuilder();
                // 関数文字列格納
                insertFuctionStr.append(CHAR_ARRAY[index]);
                index++;
                // 関数名の取得
                while (index < CHAR_ARRAY.length) {
                    // 該当する関数文字列があれば関数メソッド呼び出しの処理に入る
                    if (ValidateNumFormula.checkCharLoopFunction(insertFuctionStr.toString())) {
                        // 括弧内情報取得
                        int parenthesesBalance = 0;
                        if ('(' == CHAR_ARRAY[index]) {
                            parenthesesBalance++;
                            index++;
                            List<String> splitList = new ArrayList<>();
                            StringBuilder functionArgsStr = new StringBuilder();
                            // 関数内の引数取得
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
                                // 最初の関数括弧のカンマで格納していく　max(max(1,2),2)の場合：配列[max(1,2),2】で分解する
                                if(parenthesesBalance==1 && ',' == CHAR_ARRAY[index]){
                                    splitList.add(functionArgsStr.toString());
                                    functionArgsStr = new StringBuilder();
                                    index++;
                                    continue;
                                }
                                // 引数格納
                                functionArgsStr.append(CHAR_ARRAY[index]);
                                index++;
                            }
                            // カンマがなかった場合または、カンマ区切りの最終文字列を追加
                            splitList.add(functionArgsStr.toString());
                            // 配列に格納
                            String[] argsStrList = splitList.toArray(new String[0]);
                            // 引数が数値になるように再帰呼び出し
                            for (int i = 0; i < argsStrList.length; i++) {
                                final String argsStr = argsStrList[i];
                                // 数値でなければ呼び出す
                                if (!ValidateNumFormula.checkNum(argsStr)) {
                                    argsStrList[i] = new CalculateControler().exec(argsStr);
                                }
                            }
                            // 関数メソッド呼び出し
                            addStr = CalculatorExe.call(insertFuctionStr.toString(), argsStrList);
                            break;
                        } else {
                            // 例外処理
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
            }
            // 括弧が出た場合(演算子の後に来るものとする&関数の括弧ではないこと）
            else if (numFormulaBalance == 0 && '('==CHAR_ARRAY[index]) {
                // 括弧内情報取得
                int parenthesesBalance = 0;
                parenthesesBalance++;
                index++;
                StringBuilder functionArgsStr = new StringBuilder();
                // 括弧内の値取得
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
            }else{
                // 例外処理
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

    /**
     * 与えられた演算リストから優先順位の高い位置を取得した後、<BR>
     * 演算メソッドを呼び出し演算リストと数値リストを編集する
     * @param numList 数値リスト
     * @param formulaList 演算リスト
     * @throws IllegalArgumentException 四則演算子以外の場合
     */
    public void editNumFormulaList(List<String> numList,List<String> formulaList){
        // 四則演算の優先度順、式の左側から演算していく
        int additionIndex = forumlaIndex("+",formulaList);
        int subtractionIndex = forumlaIndex("-",formulaList);
        int multiplicationIndex = forumlaIndex("*",formulaList);
        int divisionIndex = forumlaIndex("/",formulaList);
        int index;
        // 掛け算と割り算
        if(multiplicationIndex != -1 || divisionIndex != -1){
            if(multiplicationIndex != -1 && divisionIndex != -1){
                // 両方存在する場合は、式の左側に近い方を優先する
                index = min(multiplicationIndex,divisionIndex);
            }else if(multiplicationIndex != -1){
                index = multiplicationIndex;
            }else {
                index = divisionIndex;
            }
        }
        // 足し算と引き算
        else if(additionIndex != -1 || subtractionIndex != -1){
            if(additionIndex != -1 && subtractionIndex != -1){
                index = min(additionIndex,subtractionIndex);
            }else if(additionIndex != -1){
                index = additionIndex;
            }else {
                index = subtractionIndex;
            }
        }
        // 該当なし
        else {
            throw new IllegalArgumentException("不正な演算子です");
        }
        // 演算用の数値を格納
        String[] list = new String[]{numList.get(index),numList.get(index+1)};
        // 演算メソッド呼び出し
        String str = CalculatorExe.call(formulaList.get(index),list);
        // 数値リストと演算子リストの編集
        numList.set(index,str);
        numList.remove(index+1);
        formulaList.remove(index);
    }
    /**
     * 指定された演算子が最初に現れるインデックスを演算子リストから取得
     * @param forumulaStr 演算子
     * @param formulaList 演算子リスト
     * @return 引数で指定した演算子が最初に現れるインデックス
     */
    public int forumlaIndex(String forumulaStr,List<String> formulaList){
        // 演算子の位置取得
        return formulaList.indexOf(forumulaStr);
    }

    /**
     * 引数で渡された値から.00000のような0のみの小数値を削除し、<BR>
     * 5E+10のような値を5E10に変換する
     * @param numStr 数値
     * @return 修正された数値
     */
    public String editNum(String numStr){
        BigDecimal value = new BigDecimal(numStr);
        return value.stripTrailingZeros().toString().replace("+","");
    }
}
