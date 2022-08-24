
package com.example.newcalcapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity{
    private EditText display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);

        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        display = findViewById(R.id.userInput);

        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }

            }
        });
    }

    private void updateText(String strToAdd) {

        String oldString = display.getText().toString();
        int cursorPosition = display.getSelectionStart();
        String leftString = oldString.substring(0, cursorPosition);
        String rightString = oldString.substring(cursorPosition);

        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPosition + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftString, strToAdd, rightString));
            display.setSelection(cursorPosition + 1);
        }
    }


    public void zeroButton(View view) {
        updateText("0");
        Log.i("test", "zero pressed");
    }

    public void oneButton(View view) {
        updateText("1");
    }

    public void twoButton(View view) {
        updateText("2");
    }

    public void threeButton(View view) {
        updateText("3");
    }

    public void fourButton(View view) {
        updateText("4");
    }

    public void fiveButton(View view) {
        updateText("5");
    }

    public void sixButton(View view) {
        updateText("6");
    }

    public void sevenButton(View view) {
        updateText("7");
    }

    public void eightButton(View view) {
        updateText("8");
    }

    public void nineButton(View view) {
        updateText("9");
    }

    public void addButton(View view) {
        updateText("+");
    }

    public void subtractButton(View view) {
        updateText("-");
    }

    public void multiplyButton(View view) {
        updateText("×");
    }

    public void divideButton(View view) {
        updateText("÷");
    }

    public void clearButton(View view) {
        display.setText("");
    }

    public void parenthesisButton(View view) {
        int cursorPosition = display.getSelectionStart();
        int openParenthesis = 0;
        int closedParenthesis = 0;
        int textLen = display.getText().length();

            for (int i = 0; i < cursorPosition; i++) {
                if (display.getText().toString().substring(i, i + 1).equals("(")) {
                    openParenthesis += 1;
                }
                if (display.getText().toString().substring(i, i + 1).equals(")")) {
                    closedParenthesis += 1;
                }
            }



        if (openParenthesis == closedParenthesis || display.getText().toString().substring(textLen - 1, textLen).equals("("))
        {
            updateText("(");
            display.setSelection(cursorPosition + 1);

        }
        else if (closedParenthesis < openParenthesis && !display.getText().toString().substring(textLen - 1, textLen).equals("("))
        {
            updateText(")");

        }
        display.setSelection(cursorPosition + 1);
    }

        public void equalsButton (View view){
            String userExp = display.getText().toString();

            userExp = userExp.replaceAll("÷", "/");
            userExp = userExp.replaceAll("×","*");
            // just formatting strings so MXparser can read it.

            Expression exp = new Expression(userExp);

            String result = String.valueOf(exp.calculate());

            display.setText(result);
            display.setSelection(result.length());
    }

    public void pointButton(View view) {
        updateText(".");
    }
    public void plusminusButton(View view) {
        //x *= -1;
       // updateText("");
        String reverseNumber = display.getText().toString();
        int IntReverseNumber = Integer.parseInt(reverseNumber);
        IntReverseNumber *= -1;
        String backToString = Integer.toString(IntReverseNumber);
        display.setText(backToString);
        display.setSelection(backToString.length());

    }
    public void exponentButton(View view) {
        updateText("^");
    }
    public void backspaceButton(View view) {
        int cursorPosition = display.getSelectionStart();
        int textLen = display.getText().length();
        if(cursorPosition != 0 && textLen != 0){
        SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
        selection.replace(cursorPosition - 1, cursorPosition, "");
        display.setText(selection);
        display.setSelection(cursorPosition - 1);}
        else { display.setText("");
        }



    }
}
