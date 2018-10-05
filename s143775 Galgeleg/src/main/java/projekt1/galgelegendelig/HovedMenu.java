package projekt1.galgelegendelig;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HovedMenu extends AppCompatActivity {

    Button startGame, help, Rangliste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoved_menu);

        startGame = (Button) findViewById(R.id.startGame);
        help = (Button) findViewById(R.id.Help);
        Rangliste = (Button) findViewById(R.id.TextView);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Game = new Intent(HovedMenu.this, GameMode.class);
               HovedMenu.this.startActivity(Game);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Hjælp = new Intent(HovedMenu.this, Hjaelp.class);
                HovedMenu.this.startActivity(Hjælp);
            }
        });
        Rangliste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Scores = new Intent(HovedMenu.this, HighScore.class);
                HovedMenu.this.startActivity(Scores);
            }
        });
    }
}