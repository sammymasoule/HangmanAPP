package projekt1.galgelegendelig;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Name extends AppCompatActivity {
    Button OK;
    TextView tv_name;
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        OK = (Button) findViewById(R.id.OK);
        tv_name = (TextView) findViewById(R.id.tv_name);
        name = (EditText) findViewById(R.id.name);
        Intent i = getIntent();
        final String temp = i.getStringExtra("solo");

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = name.getText().toString();
                SharedPreferences preferences = getSharedPreferences("PREFS",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name", tmp);
                editor.apply();
                Intent i = new Intent(Name.this, SpilSolo.class);
                i.putExtra("solo", temp);
                Name.this.startActivity(i);
                finish();
            }
        });
    }
}
