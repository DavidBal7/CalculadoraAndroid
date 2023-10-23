package com.David.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    /*Se definen todos los componentes que vamos a usar en la aplicacion*/
    private Button button1;
    private Button button0;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonpoint;
    private Button buttonplus;
    private Button buttonequal;
    private Button buttonminus;
    private Button buttonmulti;
    private Button buttondivided;
    private Button buttonborrar;
    private Button buttonborrartodo;

    private EditText display;
    private double currentValue = 0;

    private String operation = "";

    /*Se llaman a los componentes mediante su id*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        button1 = findViewById(R.id.uno);
        button0 = findViewById(R.id.cero);
        button2 = findViewById(R.id.dos);
        button3 = findViewById(R.id.tres);
        button4 = findViewById(R.id.cuatro);
        button5 = findViewById(R.id.cinco);
        button6 = findViewById(R.id.seis);
        button7 = findViewById(R.id.siete);
        button8 = findViewById(R.id.ocho);
        button9 = findViewById(R.id.nueve);
        buttonpoint = findViewById(R.id.punto);
        buttonplus = findViewById(R.id.suma);
        buttonequal = findViewById(R.id.igual);
        buttonminus = findViewById(R.id.resta);
        buttonmulti = findViewById(R.id.multi);
        buttondivided = findViewById(R.id.division);
        buttonborrar = findViewById(R.id.borrar);
        buttonborrartodo = findViewById(R.id.borrarall);

        /*se les da un evento a los componentes, en este caso de tipo click*/

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button clickedButton = (Button) view;
                String number = clickedButton.getText().toString();
                addtoDisplay(number);
            }
        };
        button1.setOnClickListener(numberClickListener);
        button0.setOnClickListener(numberClickListener);
        button2.setOnClickListener(numberClickListener);
        button3.setOnClickListener(numberClickListener);
        button4.setOnClickListener(numberClickListener);
        button5.setOnClickListener(numberClickListener);
        button6.setOnClickListener(numberClickListener);
        button7.setOnClickListener(numberClickListener);
        button8.setOnClickListener(numberClickListener);
        button9.setOnClickListener(numberClickListener);
        buttonpoint.setOnClickListener(numberClickListener);
        buttonplus.setOnClickListener(view ->setOperation("+"));
        buttonequal.setOnClickListener(view ->calculate());
        buttonminus.setOnClickListener(view ->setOperation("-"));
        buttonmulti.setOnClickListener(view ->setOperation("*"));
        buttondivided.setOnClickListener(view ->setOperation("/"));
        buttonborrar.setOnClickListener(view ->clearAllDisplay());
        buttonborrartodo.setOnClickListener(view ->clearDisplay());
    }

    /*En esta funcion se establece el numero 0 y se bloquea la posiblidad de poner mas de un punto*/
    private void addtoDisplay (String number){
        String currentVal = this.display.getText().toString();
        if (currentVal.equals("0")) {
            this.display.setText(number);
            return;
        }
        if (currentVal.contains(".") && number.equals("."))
            return;
        this.display.setText(currentVal + number);
    }
    /*Se convierte el valor de tipo string a double*/
    private void setOperation (String op){
        this.currentValue = Double.parseDouble(this.display.getText().toString());
        this.operation = op;
        this.display.setText("0");
    }

    /*Se realizan las operaciones de la calculadora*/
    private void calculate(){
        double secondValue = Double.parseDouble(this.display.getText().toString());
        double result = 0;
        switch (this.operation){
            case "+":
                result = this.currentValue + secondValue;
                break;
            case "-":
                result = secondValue - this.currentValue;
                break;
            case "*":
                result = this.currentValue * secondValue;
                break;
            case "/":
                result = this.currentValue / secondValue;
                break;
        }
        this.display.setText(String.valueOf(result));
        this.currentValue = result;

    }

    /*Se borra de derecha a izquierda el valor actual*/

    private void clearDisplay (){
        String currentVal = this.display.getText().toString();
        if(currentVal.length() > 1){
            currentVal  = currentVal.substring(0,currentVal.length() - 1);
        }else{
            currentVal = "0";
        }
        this.display.setText(currentVal);
    }

    /*Se borra el valor actual y la operacion haciendo alusion de haber borrado todo */

    private void clearAllDisplay (){
        this.currentValue = 0;
        this.operation = "";
        this.display.setText("0");
    }
}
