package com.rq.week3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.expressionTextView) TextView expressionTextView;
    @BindView(R.id.answerTextView) TextView answerTextView;

    @BindView(R.id.resetButton) ImageButton resetButton;
    @BindView(R.id.changeSignButton) ImageButton changeSignButton;
    @BindView(R.id.percentButton) ImageButton percentButton;
    @BindView(R.id.divideButton) ImageButton divideButton;

    private String[] operators = {"+","-","*","/"};

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

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    @OnClick(R.id.resetButton) void resetButtonPressed() {
        expressionTextView.setText("0");
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
}
