package com.shivakoshta.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView input,solution;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton multiply,divide,subtract,add,equal,dot,openBracis,closeBracis,clr,ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        solution = findViewById(R.id.solution);
        input = findViewById(R.id.input);

        solution.setText("");
        input.setText("");

        button0 = findViewById(R.id.zero);
        button1 = findViewById(R.id.one);
        button2 = findViewById(R.id.two);
        button3 = findViewById(R.id.three);
        button4 = findViewById(R.id.four);
        button5 = findViewById(R.id.five);
        button6 = findViewById(R.id.six);
        button7 = findViewById(R.id.seven);
        button8 = findViewById(R.id.eight);
        button9 = findViewById(R.id.nine);
        multiply = findViewById(R.id.multiply);
        divide = findViewById(R.id.divide);
        subtract = findViewById(R.id.subtract);
        add = findViewById(R.id.add);
        equal = findViewById(R.id.equal);
        dot = findViewById(R.id.dot);
        openBracis = findViewById(R.id.openBracis);
        closeBracis = findViewById(R.id.closeBracis);
        clr = findViewById(R.id.clear);
        ac =findViewById(R.id.ac);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        multiply.setOnClickListener(this);
        subtract.setOnClickListener(this);
        add.setOnClickListener(this);
        equal.setOnClickListener(this);
        dot.setOnClickListener(this);
        openBracis.setOnClickListener(this);
        closeBracis.setOnClickListener(this);
        ac.setOnClickListener(this);
        clr.setOnClickListener(this);
        divide.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        MaterialButton materialButton = (MaterialButton) v;
        String Button_text = materialButton.getText().toString();
       if(Button_text.equals("AC"))
       {
           solution.setText("");
           input.setText("");
       }
       else if(Button_text.equals("Clr"))
       {
           String SolutionText = solution.getText().toString();
           if(SolutionText.length()!=0) {
               SolutionText = SolutionText.substring(0, SolutionText.length() - 1);
               solution.setText(SolutionText);
           }
       }
       else if(Button_text.equals("="))
       {
           solution.setText(input.getText().toString());
           input.setText("");
       }
       else
       {
           solution.append(Button_text);
       }

       String answer = result(solution.getText().toString());
       if(answer!="Err")
       {
           input.setText(answer);
       }

    }

    String result (String data){
        String answer="";
        data = data.replace("×","*");
        data = data.replace("÷","/");
        if(data.length()!=0) {
            try {
                Context context = Context.enter();
                context.setOptimizationLevel(-1);
                Scriptable scriptable = context.initStandardObjects();
                answer = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            } catch (Exception e) {
                answer = "Err";
            }
        }
        if(answer.endsWith(".0"))
        {
            answer = answer.replace(".0","");
        }
        if(answer.equals("143"))
        {
            answer = "Padae Krle Jaa Ke;)";
        }
        return answer;
    }

}