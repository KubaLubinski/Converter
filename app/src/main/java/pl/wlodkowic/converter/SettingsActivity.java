package pl.wlodkowic.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    private Switch decision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        decision = findViewById(R.id.switchTheme);
        decision.setChecked(readPreferences());
        if (decision != null) {
            decision.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    SharedPreferences prefs = getSharedPreferences("Set", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("theme", decision.isChecked());
                    editor.apply();
                }
            });
        }
    }
    Boolean readPreferences(){
        SharedPreferences prefs=getSharedPreferences("Set",MODE_PRIVATE);
        return prefs.getBoolean("theme",false);
    }
}