package kr.gonigoni.gondialer;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter.LengthFilter;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	private boolean isCallMode = true;
	private CalcClass calc = new CalcClass();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		final TextView txtCallCalc = (TextView) findViewById(R.id.txtv_number);
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
		Button callBtnSharp = (Button) findViewById(R.id.button_sharp);
		Button bkspBtn = (Button) findViewById(R.id.button_delete);
		final Button callBtn = (Button) findViewById(R.id.button_call_equal);
		Button clearBtn = (Button) findViewById(R.id.button_clear);
		Button plusBtn = (Button) findViewById(R.id.button_plus);
		Button minusBtn = (Button) findViewById(R.id.button_minus);
		Button divideBtn = (Button) findViewById(R.id.button_divide);
		final ToggleButton togCalcCall = (ToggleButton) findViewById(R.id.toggle_calc);
		
		// Event allocation
		callBtn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				txtCallCalc.setText(txtCallCalc.getText()+"1");
			}
		});
		
		callBtn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				txtCallCalc.setText(txtCallCalc.getText()+"2");
			}
		});
		
		callBtn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				txtCallCalc.setText(txtCallCalc.getText()+"3");
			}
		});
		
		callBtn4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				txtCallCalc.setText(txtCallCalc.getText()+"4");
			}
		});
		
		callBtn5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				txtCallCalc.setText(txtCallCalc.getText()+"5");
			}
		});
		
		callBtn6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				txtCallCalc.setText(txtCallCalc.getText()+"6");
			}
		});
		
		callBtn7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				txtCallCalc.setText(txtCallCalc.getText()+"7");
			}
		});
		
		callBtn8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				txtCallCalc.setText(txtCallCalc.getText()+"8");
			}
		});
		
		callBtn9.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				txtCallCalc.setText(txtCallCalc.getText()+"9");
			}
		});
		
		callBtn0.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isCallMode)
				{
					txtCallCalc.setText(txtCallCalc.getText()+"0");
				}
				else
				{
					// 계산 모드일 때, 처음부터 0이 입력되는 것 방지
					String str = txtCallCalc.getText().toString();
					if(str.equals("") ||
							str.charAt(str.length()-1) == '+' ||
							str.charAt(str.length()-1) == '-' ||
							str.charAt(str.length()-1) == '*' ||
							str.charAt(str.length()-1) == '/')
						return;
					else
						txtCallCalc.setText(txtCallCalc.getText()+"0");
				}
				
			}
		});
		
		callBtnStar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!isCallMode)			// when it's dialing mode,
				{
					// if expression is empty, return.
					if(txtCallCalc.getText().toString().equals(""))
						return;
				}
				
				txtCallCalc.setText(txtCallCalc.getText()+"*");
			}
		});
		
		callBtnSharp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isCallMode)
					txtCallCalc.setText(txtCallCalc.getText()+"#");
			}
		});
		
		bkspBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String original = txtCallCalc.getText().toString();
				
				if(original.length() == 0)
					return;
				
				original = original.substring(0, original.length()-1);
				
				txtCallCalc.setText(original);
			}
		});
		
		callBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (txtCallCalc.getText().toString().length() == 0)
					return;
				
				if(isCallMode)
				{	
					Intent in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+txtCallCalc.getText().toString()));
					startActivity(in);
				}
				else
				{
					int calcres;
					calcres = calc.getResult();
					
					Toast.makeText(getApplicationContext(), "Result : " + Integer.toString(calcres), Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		clearBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				txtCallCalc.setText("");
			}
		});
		
		plusBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!isCallMode)
				{
					// if expression is empty, return.
					if(txtCallCalc.getText().toString().equals(""))
						return;
					
					txtCallCalc.setText(txtCallCalc.getText()+"+");
				}
			}
		});
		
		minusBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!isCallMode)
				{
					// if expression is empty, return.
					if(txtCallCalc.getText().toString().equals(""))
						return;
					
					txtCallCalc.setText(txtCallCalc.getText()+"-");
				}
			}
		});
		
		divideBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!isCallMode)
				{
					// if expression is empty, return.
					if(txtCallCalc.getText().toString().equals(""))
						return;
					
					txtCallCalc.setText(txtCallCalc.getText()+"/");
				}
			}
		});
		
		togCalcCall.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (togCalcCall.isChecked())
				{
					isCallMode = false;
					callBtn.setText("=");
					txtCallCalc.setText("");
				}
				else
				{
					isCallMode = true;
					callBtn.setText("Call");
					txtCallCalc.setText("");
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
