package learn.zero.say.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView input_tv, output_tv;
    MaterialButton btn_ac, btn_c, btn_percentage, btn_divide, btn_multiply, btn_subtract, btn_add,
                    btn_equal, btn_point,btn_bracket;
    MaterialButton  btn_0, btn_1, btn_2, btn_3, btn_4, btn_5,btn_6, btn_7, btn_8, btn_9;

    boolean checkBracket = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_tv = findViewById(R.id.input_tv);
        output_tv = findViewById(R.id.output_tv);
        btn_bracket = findViewById(R.id.btn_bracket);

        assignId(btn_c,R.id.btn_c);
        assignId(btn_ac,R.id.btn_ac);
        assignId(btn_percentage,R.id.btn_percentage);
        assignId(btn_divide,R.id.btn_divide);
        assignId(btn_multiply,R.id.btn_multiply);
        assignId(btn_subtract,R.id.btn_subtract);
        assignId(btn_add,R.id.btn_add);
        assignId(btn_equal,R.id.btn_equal);
        assignId(btn_point,R.id.btn_point);

        assignId(btn_0,R.id.btn_0);
        assignId(btn_1,R.id.btn_1);
        assignId(btn_2,R.id.btn_2);
        assignId(btn_3,R.id.btn_3);
        assignId(btn_4,R.id.btn_4);
        assignId(btn_5,R.id.btn_5);
        assignId(btn_6,R.id.btn_6);
        assignId(btn_7,R.id.btn_7);
        assignId(btn_8,R.id.btn_8);
        assignId(btn_9,R.id.btn_9);

    }


    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String data = input_tv.getText().toString();

        if (buttonText.equals("AC")){
            input_tv.setText("");
            output_tv.setText("");
            return;
        }
        if (buttonText.equals("=")){
            input_tv.setText(output_tv.getText());
            data = data.replace("×" , "*");
            data = data.replace("÷" , "/");
            return;

        }
        if (buttonText.equals("\u232b")){
            data = data.substring(0, data.length()-1);

        }
        else {
            data = data+buttonText;
        }

        btn_bracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String process;
                if (checkBracket)
                {
                    process = input_tv.getText().toString();
                    input_tv.setText(process + ")");
                    checkBracket = false;
                }else
                {
                    process = input_tv.getText().toString();
                    input_tv.setText(process + "(");
                    checkBracket = true;
                }
            }
        });

        input_tv.setText(data);

        String finalResult = getResult(data);
        if (!finalResult.equals("Error")){
            output_tv.setText(finalResult);
        }
    }


    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString
                    (scriptable,data,"Javascript", 1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }

    }



}





























