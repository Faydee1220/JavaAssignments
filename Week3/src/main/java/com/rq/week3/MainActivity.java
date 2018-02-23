package com.rq.week3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.expressionTextView) TextView expressionTextView;
    @BindView(R.id.answerTextView) TextView answerTextView;

    @BindView(R.id.resetButton) ImageButton resetButton;
    @BindView(R.id.changeSignButton) ImageButton changeSignButton;
    @BindView(R.id.percentButton) ImageButton percentButton;
    @BindView(R.id.divideButton) ImageButton divideButton;

    private String[] operators = {"+","-","*","/"};
    List<Double> allNumbers = new ArrayList<>();
    List<String> allOperators = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void addNumber(int number) {
        String input = (String) expressionTextView.getText();
        if (input.equals("0")) {
            input = String.valueOf(number);
        }
        else {
            input += number;
        }
        expressionTextView.setText(input);
    }

    private void addOperator(String operator) {
        String input = (String) expressionTextView.getText();
        // 先排除尚未輸入任何數字時
        if (input.equals("0")) {
            return;
        }
        String last = input.substring(input.length() - 1);
        // 已經有輸入運算符號時，移除
        if (Arrays.asList(operators).contains(last)) {
            input = removeLastChar(input);
        }
        input += operator;
        expressionTextView.setText(input);
    }

    private void addPoint() {
        String input = (String) expressionTextView.getText();
        String last = input.substring(input.length() - 1);
        // 排除已經輸入 .
        if (last.equals(".") || input.contains(".")) {
            return;
        }
        input += ".";
        expressionTextView.setText(input);
    }

    private String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    @OnClick(R.id.resetButton) void resetButtonPressed() {
        expressionTextView.setText("0");
        answerTextView.setText("0");
    }

    @OnClick(R.id.plusButton) void plusButtonPressed() {
        addOperator("+");
    }

    @OnClick(R.id.minusButton) void minusButtonPressed() {
        addOperator("-");
    }

    @OnClick(R.id.timesButton) void timesButtonPressed() {
        addOperator("*");
    }

    @OnClick(R.id.divideButton) void divideButtonPressed() {
        addOperator("/");
    }

    @OnClick(R.id.equalsButton) void equalsButtonPressed() {
        // 先取得輸入內容
        String input = (String) expressionTextView.getText();

        if (input.equals("0")) {
            return;
        }

        // 提示最後輸入必須為數字
        String last = input.substring(input.length() - 1);
        try {
            int lastInt = Integer.parseInt(last);
        } catch (NumberFormatException e) {
            Toast.makeText(this,"請輸入數字",Toast.LENGTH_SHORT).show();
            return;
        }

        // 分隔數字和運算符號變成兩組 Array
        String[] inputNumbers = getInputNumbers(input);
        String[] inputOperators = getInputOperators(input);

        // 將 inputNumbers 轉 List
        getNumberList(inputNumbers);

        // 如果沒有輸入任何運算符號時，直接顯示結果
        if (inputOperators.length == 0) {
            showResult();
            return;
        }

        // 將 inputOperators 轉 List
        allOperators = getOperatorList(inputOperators);

        // 1.乘法
        calculate("*");

        // 2.除法
        calculate("/");

        // 3.加法
        calculate("+");

        // 4.減法
        calculate("-");

        showResult();
    }

    private void showResult() {
        double result = allNumbers.get(0);
        allNumbers.clear();
        double afterDecimal = result - Math.floor(result);
//        Log.d(TAG,String.valueOf(afterDecimal));
        if (afterDecimal == 0) {
            answerTextView.setText(String.valueOf((int)result));
        }
        else {
            answerTextView.setText(String.valueOf(result));
        }

        expressionTextView.setText("0");
    }

    @NonNull
    private String[] getInputOperators(String input) {
        // ^ 代表相反
        // + 代表可以用一個以上來分隔
//        String[] operators = input.split("[^+|\\-|/|*]+");
        //        Log.d(TAG, Arrays.toString(inputOperators));
//        [, +, -, *, /]
        return input.split("[\\d|.]+");
    }

    @NonNull
    private String[] getInputNumbers(String input) {
        // Example: 0.12+3-4*5/6
        //        String[] numbers = input.split("[^\\d|.]");

//        Log.d(TAG, Arrays.toString(inputNumbers));
//        [0.12, 3, 4, 5, 6]
        return input.split("[+|\\-|*|/]");
    }

    private void getNumberList(String[] inputNumbers) {
        List<String> numbers = new ArrayList<>(Arrays.asList(inputNumbers));
        for (String number : numbers) {
            allNumbers.add(Double.parseDouble(number));
        }
//        Log.d(TAG,String.valueOf(allNumbers));
    }

    @NonNull
    private List<String> getOperatorList(String[] inputOperators) {
        List<String> allOperators = new ArrayList<>(Arrays.asList(inputOperators));
        allOperators.remove(0);
//        Log.d(TAG,String.valueOf(allOperators));
        return allOperators;
    }

    private void calculate(String operator) {
        List<Integer> indexsNeedRemove = new ArrayList<>();
        do {
            for (int i = 0; i < allOperators.size(); i += 1) {
                if (allOperators.get(i).equals(operator)) {
                    double result = 0.0;
                    switch (operator) {
                        case "*":
                            result = allNumbers.get(i) * allNumbers.get(i + 1);
                            break;
                        case "/":
                            result = allNumbers.get(i) / allNumbers.get(i + 1);
                            break;
                        case "+":
                            result = allNumbers.get(i) + allNumbers.get(i + 1);
                            break;
                        case "-":
                            result = allNumbers.get(i) - allNumbers.get(i + 1);
                            break;
                    }
                    allNumbers.set(i, result);
                    allNumbers.remove(i + 1);
                    indexsNeedRemove.add(i);
                    break;
                }
            }

            for (Integer index : indexsNeedRemove) {
                allOperators.remove((int)index);
            }

            Log.d(TAG,String.valueOf(allNumbers));
            Log.d(TAG,String.valueOf(allOperators));
            indexsNeedRemove.clear();
        } while (allOperators.contains(operator));
    }

    @OnClick(R.id.pointButton) void pointButtonPressed() {
        addPoint();
    }

    //region Action - Press number
    @OnClick(R.id.number0Button) void number0ButtonPressed() {
        addNumber(0);
    }

    @OnClick(R.id.number1Button) void number1ButtonPressed() {
        addNumber(1);
    }

    @OnClick(R.id.number2Button) void number2ButtonPressed() {
        addNumber(2);
    }

    @OnClick(R.id.number3Button) void number3ButtonPressed() {
        addNumber(3);
    }

    @OnClick(R.id.number4Button) void number4ButtonPressed() {
        addNumber(4);
    }

    @OnClick(R.id.number5Button) void number5ButtonPressed() {
        addNumber(5);
    }

    @OnClick(R.id.number6Button) void number6ButtonPressed() {
        addNumber(6);
    }

    @OnClick(R.id.number7Button) void number7ButtonPressed() {
        addNumber(7);
    }

    @OnClick(R.id.number8Button) void number8ButtonPressed() {
        addNumber(8);
    }

    @OnClick(R.id.number9Button) void number9ButtonPressed() {
        addNumber(9);
    }
    //endregion

    @OnClick(R.id.changeSignButton) void changeSignButtonPressed() {
        showDevelopingToast();
    }

    @OnClick(R.id.percentButton) void percentButton() {
        showDevelopingToast();
    }

    private void showDevelopingToast() {
        Toast.makeText(this,"此功能開發中",Toast.LENGTH_SHORT).show();
    }
}
