import java.util.Scanner;

public class CalculatorEntry {
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
        final String ANSWER = new CalculateControler().exec(str);
        System.out.println(ANSWER);
    }

}
