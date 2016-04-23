package kr.gonigoni.gondialer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean isCallMode = true;
    private static CalcClass calc = new CalcClass();

    private TextView txtCallCalc;
    private ToggleButton togCalcCall;
    private Button callBtn;
    private Button callBtnSharp;

    private boolean checkDuplicatedOperator (String s) {
        char[] operator = {'+', '-', '*', '/'};

        for (char ch : operator) {
            if (ch == s.charAt(s.length()-1))
                return true;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCallCalc = (TextView) findViewById(R.id.txtv_number);

        // Buttons
        Button callBtn1 = (Button) findViewById(R.id.button1);
        Button callBtn2 = (Button) findViewById(R.id.button2);
        Button callBtn3 = (Button) findViewById(R.id.button3);
        Button callBtn4 = (Button) findViewById(R.id.button4);
        Button callBtn5 = (Button) findViewById(R.id.button5);
        Button callBtn6 = (Button) findViewById(R.id.button6);
        Button callBtn7 = (Button) findViewById(R.id.button7);
        Button callBtn8 = (Button) findViewById(R.id.button8);
        Button callBtn9 = (Button) findViewById(R.id.button9);
        Button callBtn0 = (Button) findViewById(R.id.button0);
        Button callBtnStar = (Button) findViewById(R.id.button_star);
        callBtnSharp = (Button) findViewById(R.id.button_sharp);
        Button bkspBtn = (Button) findViewById(R.id.button_delete);
        callBtn = (Button) findViewById(R.id.button_call_equal);
        Button clearBtn = (Button) findViewById(R.id.button_clear);
        Button plusBtn = (Button) findViewById(R.id.button_plus);
        Button minusBtn = (Button) findViewById(R.id.button_minus);
        Button divideBtn = (Button) findViewById(R.id.button_divide);
        togCalcCall = (ToggleButton) findViewById(R.id.toggle_calc);
        Button leftParentBtn = (Button) findViewById(R.id.button_leftparent);
        Button rightParentBtn = (Button) findViewById(R.id.button_rightparent);

        // Event allocation
        callBtn1.setOnClickListener(this);
        callBtn2.setOnClickListener(this);
        callBtn3.setOnClickListener(this);
        callBtn4.setOnClickListener(this);
        callBtn5.setOnClickListener(this);
        callBtn6.setOnClickListener(this);
        callBtn7.setOnClickListener(this);
        callBtn8.setOnClickListener(this);
        callBtn9.setOnClickListener(this);
        callBtn0.setOnClickListener(this);

        callBtnStar.setOnClickListener(this);
        callBtnSharp.setOnClickListener(this);
        bkspBtn.setOnClickListener(this);
        callBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        divideBtn.setOnClickListener(this);
        togCalcCall.setOnClickListener(this);
        leftParentBtn.setOnClickListener(this);
        rightParentBtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkInsertNumber(String str) {
        /* If string length is zero */
        if (str.length() == 0)
            return true;

        /* If last character is right parenthesis */
        return str.charAt(str.length() - 1) != ')';

    }

    @Override
    public void onClick(View v) {
        String str = txtCallCalc.getText().toString();
        boolean insertNumber = checkInsertNumber(str);

        switch (v.getId()) {
            case R.id.button0:
                if (isCallMode || insertNumber)
                    txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.num0)));
                break;
            case R.id.button1:
                if (isCallMode || insertNumber)
                    txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.num1)));
                break;
            case R.id.button2:
                if (isCallMode || insertNumber)
                    txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.num2)));
                break;
            case R.id.button3:
                if (isCallMode || insertNumber)
                    txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.num3)));
                break;
            case R.id.button4:
                if (isCallMode || insertNumber)
                    txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.num4)));
                break;
            case R.id.button5:
                if (isCallMode || insertNumber)
                    txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.num5)));
                break;
            case R.id.button6:
                if (isCallMode || insertNumber)
                    txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.num6)));
                break;
            case R.id.button7:
                if (isCallMode || insertNumber)
                    txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.num7)));
                break;
            case R.id.button8:
                if (isCallMode || insertNumber)
                    txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.num8)));
                break;
            case R.id.button9:
                if (isCallMode || insertNumber)
                    txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.num9)));
                break;
            case R.id.button_call_equal:
                if (txtCallCalc.getText().toString().length() == 0)
                    return;

                if(isCallMode) {
                    Intent in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + txtCallCalc.getText().toString()));
                    startActivity(in);
                } else {
                    double calcResult;
                    calcResult = calc.getResult(txtCallCalc.getText().toString());

                    txtCallCalc.setText(String.format(Locale.getDefault(), "%f", calcResult));
                }
                break;
            case R.id.button_clear:
                txtCallCalc.setText("");
                break;
            case R.id.button_delete:
                String original = txtCallCalc.getText().toString();

                if(original.length() == 0)
                    return;

                //Log.i("GonDialer", "(Before) Original: " + original + " Length: " + original.length());
                original = original.substring(0, original.length()-1);
                //Log.i("GonDialer", "(After) Original: " + original + " Length: " + original.length());

                txtCallCalc.setText(original);
                break;
            case R.id.button_divide:
                if(!isCallMode) {
                    // if expression is empty, return.
                    if(txtCallCalc.getText().toString().equals(""))
                        return;

                    if (!checkDuplicatedOperator(txtCallCalc.getText().toString()))
                        txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.divide)));
                }
                break;
            case R.id.button_minus:
                if(!isCallMode) {
                    // if expression is empty, return.
                    if(txtCallCalc.getText().toString().equals(""))
                        return;

                    if (!checkDuplicatedOperator(txtCallCalc.getText().toString()))
                        txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.minus)));
                }
                break;
            case R.id.button_plus:
                if(!isCallMode) {
                    // if expression is empty, return.
                    if(txtCallCalc.getText().toString().equals(""))
                        return;

                    if (!checkDuplicatedOperator(txtCallCalc.getText().toString()))
                        txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.plus)));
                }
                break;
            case R.id.button_sharp:
                if (isCallMode)
                    txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.sharp)));
                else {
                    // 계산 모드로 바뀌면 소수점을 입력하도록 변경.
                    // 계산 모드일 때, 처음부터 소수점이 입력되는 것 방지
                    if(str.equals("") ||
                            str.charAt(str.length()-1) == '+' ||
                            str.charAt(str.length()-1) == '-' ||
                            str.charAt(str.length()-1) == '*' ||
                            str.charAt(str.length()-1) == '/')
                        return;
                    else
                        txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.dot)));
                }
                break;
            case R.id.button_star:
                // when it's dialing mode,
                if(!isCallMode) {
                    // if expression is empty, return.
                    if(txtCallCalc.getText().toString().equals(""))
                        return;

                    if (!checkDuplicatedOperator(txtCallCalc.getText().toString()))
                        txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.multi)));
                } else
                    txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.multi)));
                break;
            case R.id.toggle_calc:
                if (togCalcCall.isChecked()) {
                    isCallMode = false;
                    callBtn.setText("=");
                    callBtnSharp.setText(".");
                    txtCallCalc.setText("");
                } else {
                    isCallMode = true;
                    callBtn.setText(R.string.calltxt);
                    callBtnSharp.setText("#");
                    txtCallCalc.setText("");
                }
                break;
            case R.id.button_leftparent:
                if (!isCallMode) {
                    /* If last character is not operator,
                        can't insert left parenthesis.
                        (Also If string length is zero)*/
                    if (str.length() == 0 ||
                        str.charAt(str.length()-1) == '+' ||
                        str.charAt(str.length()-1) == '-' ||
                        str.charAt(str.length()-1) == '*' ||
                        str.charAt(str.length()-1) == '/' ||
                        str.charAt(str.length()-1) == '(')
                        txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.leftp)));
                }
                break;
            case R.id.button_rightparent:
                if (!isCallMode) {
                    /* If string length is not zero,
                        you can insert right parenthesis. */
                    if (str.length() != 0)
                        txtCallCalc.setText(txtCallCalc.getText().toString().concat(getString(R.string.rightp)));
                }
                break;
        }
    }
}
