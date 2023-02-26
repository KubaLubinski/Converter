package pl.wlodkowic.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
    }

    public void onClickConvertButton(View view) {
        int method;
        int inBase;
        int outBase;

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
       result.setText(convert(number.getText().toString(), inBase, outBase).toUpperCase());
    }
    public String convert(String number, int inBase, int outBase){
        try{
            return Integer.toString(Integer.parseInt(number, inBase), outBase);
        }
        catch (Exception e){
            return "";
        }
    }
}