package projekt1.galgelegendelig;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SpilSolo extends AppCompatActivity {

    private EditText Input;
    private ImageView iv;
    private TextView textview;
    private TextView textview2;
    private TextView WrongLetters;
    private TextView Answer;
    private Galgelogik logik;
    private int count;
    private String ordet;
    private Chronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);
        Input = (EditText) findViewById(R.id.InputText);
        iv = (ImageView) findViewById(R.id.imageView);
        textview = (TextView) findViewById(R.id.textView);
        textview2 = (TextView) findViewById(R.id.textView2);
        WrongLetters = (TextView) findViewById(R.id.WrongLetters);
        Answer = (TextView) findViewById(R.id.Answer);
        logik = new Galgelogik();
        Intent i = getIntent();
        String check = i.getStringExtra("multiplayer");
        if(check != null){
            logik.MultiPlayerNulstil(check);
        }
        Answer.setText(logik.getSynligtOrd());
        count = logik.getAntalForkerteBogstaver();
        timer = (Chronometer) findViewById(R.id.meter);
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
        Toast toast= Toast.makeText(getApplicationContext(), "Tryk enter for at gætte på bogstavet", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();

        ReadFile();

        Input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                    if(keyCode == KeyEvent.KEYCODE_ENTER){
                        logik.gætBogstav(Input.getText().toString().toLowerCase());
                        logik.getSynligtOrd();
                        if (logik.erSidsteBogstavKorrekt()) {
                            Answer.setText(logik.getSynligtOrd());
                        } else {
                            WrongLetters.setText(WrongLetters.getText().toString() + " " + Input.getText().toString());
                            logik.getAntalForkerteBogstaver();
                            switch (logik.getAntalForkerteBogstaver()) {
                                case 1:
                                    count = 1;
                                    iv.setImageResource(R.drawable.forkert1);
                                    break;
                                case 2:
                                    count = 2;
                                    iv.setImageResource(R.drawable.forkert2);
                                    break;
                                case 3:
                                    count = 3;
                                    iv.setImageResource(R.drawable.forkert3);
                                    break;
                                case 4:
                                    count = 4;
                                    iv.setImageResource(R.drawable.forkert4);
                                    break;
                                case 5:
                                    count = 5;
                                    iv.setImageResource(R.drawable.forkert5);
                                    break;
                                case 6:
                                    count = 6;
                                    iv.setImageResource(R.drawable.forkert6);
                                    Toast lastchance= Toast.makeText(getApplicationContext(), "Sidste chance!", Toast.LENGTH_SHORT);
                                    lastchance.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                                    lastchance.show();
                                    break;
                            }
                        }
                        Input.setText("");
                        CheckStatus();
                    }
                    return true;
            }
        });


    }

    public void CheckStatus(){
        if (logik.erSpilletTabt()) {
            ordet = logik.getOrdet();
            Intent TabtSpil = new Intent(SpilSolo.this, projekt1.galgelegendelig.TabtSpil.class);
            TabtSpil.putExtra("Ordet", logik.getOrdet());
            SpilSolo.this.startActivity(TabtSpil);
            SpilSolo.this.finish();
        }

        if (logik.erSpilletVundet()) {
            timer.stop();
            long elapsedMillis = SystemClock.elapsedRealtime()-timer.getBase();
            Intent VandtSpil = new Intent(SpilSolo.this, projekt1.galgelegendelig.VundetSpil.class);
            VandtSpil.putExtra("Forsøg", logik.getAntalForkerteBogstaver());

            long score = elapsedMillis;
            if(score!=0) {
                score = score / 1000;
            }

            SharedPreferences preferences = getSharedPreferences("PREFS",0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong("score", score);
            editor.apply();
            SpilSolo.this.startActivity(VandtSpil);
            finish();
        }
    }

    public void HentDR() {

        class Asynctask1 extends AsyncTask {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    logik.hentOrdFraDr();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "execute";
            }

            @Override
            protected void onPostExecute(Object o) {
                logik.nulstil();
                Answer.setText(logik.getSynligtOrd());
            }
        }
        new Asynctask1().execute();
    }

    public void ReadFile() {
        SharedPreferences SPRead;
        SPRead = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        Boolean HentOrd = SPRead.getBoolean("DRStatus",Boolean.parseBoolean(""));
        if(HentOrd){
            HentDR();
        }
    }
}
