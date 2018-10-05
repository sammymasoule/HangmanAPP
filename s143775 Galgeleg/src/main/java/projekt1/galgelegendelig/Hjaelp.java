package projekt1.galgelegendelig;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Hjaelp extends AppCompatActivity {

    public Switch DR;
    public Boolean switch2;

    public Hjaelp(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        DR = (Switch) findViewById(R.id.switch1);
        ReadFile();
        DR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (DR.isChecked()) {
                    switch2 = true;
                    WriteFile();
                } else {
                    switch2 = false;
                    WriteFile();
                }
            }
        });
    }
    public void WriteFile(){

        SharedPreferences SP = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor SPEditor = SP.edit();
        SPEditor.putBoolean("DRStatus",switch2);
        SPEditor.apply();

        if(switch2) {
            Toast.makeText(getApplicationContext(), "Du spiller nu med ord fra DR!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Du spiller ikke med ord fra DR!", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void ReadFile(){
        SharedPreferences SPRead = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        Boolean Status = SPRead.getBoolean("DRStatus",Boolean.parseBoolean(""));

        if(Status){
            DR.setChecked(true);
        }
    }
}
