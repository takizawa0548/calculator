import java.util.Scanner;

/**
 * 計算機mainクラス
 * @author M.Takizawa
 * @version 1.0.0
 */
public class CalculatorEntry {
    /**
     * mainメソッド<BR>
     * 与えられた式を検証した後、計算機実行クラスを呼び出し結果を出力する
     * @param args 数式
     */
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final String str = sc.next();
        // 入力文字列の解析
        if(!ValidateNumFormula.validateInputStr(str)){
            System.out.println("使用できない文字列が入っています。");
            return;
        }
        // 括弧の数があっているか確認
        if(!ValidateNumFormula.checkParentheses(str)){
            System.out.println("括弧の数があっていません");
            return;
        }
        // 本計算
        try{
            final String ANSWER = new CalculateControler().exec(str);
            System.out.println(ANSWER);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

}
