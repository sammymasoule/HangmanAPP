package projekt1.galgelegendelig;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class HighScore extends AppCompatActivity {
    Button Home;
    String lastname, name1, name2, name3;
    long lastscore, top1, top2, top3;
    TextView tv1;
    String[] tmp = new String[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        tv1 = (TextView) findViewById(R.id.textView5);
        Home = (Button) findViewById(R.id.Home);
        tmp[0] = "Highscore";

        //Hent highscores;
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        lastname = preferences.getString("name", "null");
        lastscore = preferences.getLong("score", 1000);
        SharedPreferences.Editor SPedit = preferences.edit();
        top1 = preferences.getLong("top1", 1000);
        top2 = preferences.getLong("top2", 1000);
        top3 = preferences.getLong("top3", 1000);
        name1 = preferences.getString("name1", "null");
        name2 = preferences.getString("name2", "null");
        name3 = preferences.getString("name3", "null");

        //Erstat, hvis highscore bliver slået
        if (lastscore <= top1) {
            String tempname = name1;
            String tempname1 = name2;
            long temp = top1;
            long temp1 = top2;
            name1 = lastname;
            top1 = lastscore;
            name2 = tempname;
            top2 = temp;
            name3 = tempname1;
            top3 = temp1;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong("top2", top2);
            editor.putLong("top1", top1);
            editor.putLong("top3", top3);
            editor.putString("name1", name1);
            editor.putString("name2",name2);
            editor.putString("name3",name3);
            editor.apply();
        }
        else if (lastscore < top2) {
            String tempname2 = name2;
            long tmp = top2;
            name2 = lastname;
            top2 = lastscore;
            name3 = tempname2;
            top3 = tmp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong("top3", top3);
            editor.putLong("top2", top2);
            editor.putString("name3", name3);
            editor.putString("name2",name2);
            editor.apply();
        }
        else if (lastscore < top3) {
            top3 = lastscore;
            name3 = lastname;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong("top3", top3);
            editor.putString("name3",name3);
            editor.apply();
        }

        SPedit.remove("score");
        SPedit.commit();

        ArrayAdapter AA = new ArrayAdapter(this, R.layout.highscorelist, R.id.list_name, tmp) {
            @Override
            public View getView(int position, View cachedView, ViewGroup parent) {
                View view = super.getView(position, cachedView, parent);


                TextView First = (TextView) view.findViewById(R.id.First);
                TextView Second = (TextView) view.findViewById(R.id.Second);
                TextView Third = (TextView) view.findViewById(R.id.Third);
                First.setText(name1 + " er på 1. pladsen med " + top1 + " point.");
                Second.setText(name2 + " er på 2. pladsen med "+ top2 + " point.");
                Third.setText(name3 + " er på 3. pladsen med "+ top3 + " point.");

                return view;
            }
        };
        ListView list = new ListView(this);
        list.setAdapter(AA);
        setContentView(list);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(HighScore.this, HovedMenu.class);
                HighScore.this.startActivity(home);
                finish();
            }
        });
    }

}