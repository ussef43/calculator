package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,btn_dot,btn_pi,btn_equal,btn_plus,btn_min,btn_mul,btn_div,btn_inv,btn_sqrt,btn_square,btn_ln,btn_tan,btn_cos,btn_sin,btn_b1,btn_b2,btn_c,btn_ac;
    TextView testView_res,testView_sec;
    String pi = "3.14159";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.btn_1);
        b2 = findViewById(R.id.btn_2);
        b3 = findViewById(R.id.btn_3);
        b4 = findViewById(R.id.btn_4);
        b5 = findViewById(R.id.btn_5);
        b6 = findViewById(R.id.btn_6);
        b7 = findViewById(R.id.btn_7);
        b8 = findViewById(R.id.btn_8);
        b9 = findViewById(R.id.btn_9);
        b0 = findViewById(R.id.btn_0);
        btn_pi = findViewById(R.id.btn_pi);
        btn_dot = findViewById(R.id.btn_dot);
        btn_equal = findViewById(R.id.btn_equal);
        btn_plus = findViewById(R.id.btn_plus);
        btn_min = findViewById(R.id.btn_min);
        btn_mul = findViewById(R.id.btn_mul);
        btn_div = findViewById(R.id.btn_div);
        btn_inv = findViewById(R.id.btn_inv);
        btn_sqrt = findViewById(R.id.btn_sqrt);
        btn_square = findViewById(R.id.btn_square);
        btn_ln = findViewById(R.id.btn_ln);
        btn_tan = findViewById(R.id.btn_tan);
        btn_sin = findViewById(R.id.btn_sin);
        btn_cos = findViewById(R.id.btn_cos);
        btn_b1 = findViewById(R.id.btn_b1);
        btn_b2 = findViewById(R.id.btn_b2);
        btn_c = findViewById(R.id.btn_c);
        btn_ac = findViewById(R.id.btn_ac);

        testView_sec = findViewById(R.id.testView_sec);
        testView_res = findViewById(R.id.textView_res);

        b1.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"1"));
        b2.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"2"));
        b3.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"3"));
        b4.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"4"));
        b5.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"5"));
        b6.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"6"));
        b7.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"7"));
        b8.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"8"));
        b9.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"9"));
        b0.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"0"));
        btn_dot.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"."));
        btn_ac.setOnClickListener(v -> {
            testView_res.setText("");
            testView_sec.setText("");
        });
        btn_c.setOnClickListener(v -> {
            String val = testView_res.getText().toString();
            val = val.substring(0, val.length() - 1);
            testView_res.setText(val);
        });
        btn_plus.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"+"));
        btn_min.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"-"));
        btn_mul.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"×"));
        btn_div.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"÷"));
        btn_sqrt.setOnClickListener(v -> {
            String val = testView_res.getText().toString();
            double r = Math.sqrt(Double.parseDouble(val));
            testView_res.setText(String.valueOf(r));
        });
        btn_b1.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"("));
        btn_b2.setOnClickListener(v -> testView_res.setText(testView_res.getText()+")"));
        btn_pi.setOnClickListener(v -> {
            testView_sec.setText(btn_pi.getText());
            testView_res.setText(testView_res.getText()+pi);
        });
        btn_sin.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"sin"));
        btn_cos.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"cos"));
        btn_tan.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"tan"));
        btn_inv.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"^"+"(-1)"));
        btn_square.setOnClickListener(v -> {
            double d = Double.parseDouble(testView_res.getText().toString());
            double square = d*d;
            testView_res.setText(String.valueOf(square));
            testView_sec.setText(d+"²");
        });
        btn_ln.setOnClickListener(v -> testView_res.setText(testView_res.getText()+"ln"));
        btn_equal.setOnClickListener(v -> {
            String val = testView_res.getText().toString();
            String replacedstr = val.replace('÷','/').replace('×','*');
            double result = result(replacedstr);
            testView_res.setText(String.valueOf(result));
            testView_sec.setText(val);
        });

    }


    public static double result(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else if (func.equals("log")) x = Math.log10(x);
                    else if (func.equals("ln")) x = Math.log(x);
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());

                return x;
            }
        }.parse();
    }
}