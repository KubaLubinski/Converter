package pl.wlodkowic.converter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner conversion;
    private EditText number;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conversion = findViewById(R.id.spinner_conversion);
        number = findViewById(R.id.editText_input);
        result = findViewById(R.id.textView_result);

        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() != 0) {
                    calculate(charSequence.toString());
                } else {
                    result.setText("");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if(message()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("score", result.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        result.setText(saveInstanceState.getString("score", ""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.converter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent in= new Intent(this, SettingsActivity.class);
                startActivity(in);
                return true;
            case R.id.action_info:
                Intent i=new Intent(this, InfoActivity.class);
                startActivity(i);
                return true;
            case R.id.action_exit:
                finish();
                return  true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    public void calculate(String inData) {
        int method;
        int inBase;
        int outBase;
        String outcome;

        method = (int)conversion.getSelectedItemId();
        switch(method){
            case 0:
            default:
                inBase=10;
                outBase=2;
                break;
            case 1:
                inBase=10;
                outBase=16;
                break;
            case 2:
                inBase=2;
                outBase=10;
                break;
            case 3:
                inBase=2;
                outBase=16;
                break;
            case 4:
                inBase=16;
                outBase=10;
                break;
            case 5:
                inBase=16;
                outBase=2;
                break;
        }

        outcome = convert(inData, inBase, outBase);
        if(TextUtils.isEmpty(outcome)){
            statement();
        } else {
            result.setText(outcome.toUpperCase());
        }
    }
    public String convert(String number, int inBase, int outBase){
        try{
            return Integer.toString(Integer.parseInt(number, inBase), outBase);
        }
        catch (Exception e){
            return "";
        }
    }

    private void statement(){
        Vibrator v;
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(R.string.statementTitle);
        adb.setMessage(R.string.statementMessage);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
        AlertDialog ad = adb.create();
        ad.show();
    }

    private boolean  message(){
        Boolean switchPref = getSharedPreferences("Set", MODE_PRIVATE).getBoolean("theme", false);
        return switchPref;
    }



}