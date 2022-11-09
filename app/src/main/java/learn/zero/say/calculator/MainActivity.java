package learn.zero.say.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView solution_tv, result_tv;
    MaterialButton btn_ac, btn_c, btn_percentage, btn_divide, btn_multiply, btn_subtract, btn_add, btn_equal, btn_point;
    MaterialButton btn_00, btn_0, btn_1, btn_2, btn_3, btn_4, btn_5,btn_6, btn_7, btn_8, btn_9;

    String data;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        solution_tv = findViewById(R.id.solution_tv);
        result_tv = findViewById(R.id.result_tv);

        assignId(btn_c,R.id.btn_c);
        assignId(btn_ac,R.id.btn_ac);
        assignId(btn_percentage,R.id.btn_percentage);
        assignId(btn_divide,R.id.btn_divide);
        assignId(btn_multiply,R.id.btn_multiply);
        assignId(btn_subtract,R.id.btn_subtract);
        assignId(btn_add,R.id.btn_add);
        assignId(btn_equal,R.id.btn_equal);
        assignId(btn_point,R.id.btn_point);

        assignId(btn_00,R.id.btn_00);
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
        String data = solution_tv.getText().toString();

        if (buttonText.equals("AC")){
            solution_tv.setText("");
            result_tv.setText("");
            return;
        }
        if (buttonText.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;

        }
        if (buttonText.equals("C")){
            data = data.substring(0, data.length()-1);

        }
        else {
            data = data+buttonText;
        }

        solution_tv.setText(data);

        String finalResult = getResult(data);
        if (!finalResult.equals("Err")){
            result_tv.setText(finalResult);
        }
    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString (scriptable,data,"Javascript", 1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }

    }


}





























