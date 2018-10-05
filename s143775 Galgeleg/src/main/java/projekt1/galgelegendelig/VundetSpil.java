package projekt1.galgelegendelig;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jinatonic.confetti.ConfettiView;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class VundetSpil extends AppCompatActivity {
    private Button Afslut;
    private TextView AntalForsøg;
    private ImageView highscore;
    private long lastscore, top1, top2, top3;
    KonfettiView konfettiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vundet_spil);
        final MediaPlayer winsound = MediaPlayer.create(this, R.raw.winsound);

      //Konfetti source https://youtu.be/9ElwO8MwUF4
        konfettiView = (KonfettiView) findViewById(R.id.confetti);

        konfettiView.build()
                .addColors(Color.YELLOW, Color.RED, Color.GREEN, Color.MAGENTA, Color.WHITE)
                .setDirection(359.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.CIRCLE, Shape.RECT)
                .addSizes(new Size(12, 5f))
                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, 50f)
                .stream(300, 5000L);



        AntalForsøg = (TextView) findViewById(R.id.AntalGæt);
        Afslut = (Button) findViewById(R.id.Btn1);
        Intent VundetSpil = getIntent();
        int forsøg = VundetSpil.getIntExtra("Forsøg",0);
        highscore = (ImageView) findViewById(R.id.highscore);
        highscore.setVisibility(View.GONE);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        lastscore = preferences.getLong("score", 0);
        top1 = preferences.getLong("top1", 1000);
        top2 = preferences.getLong("top2", 1000);
        top3 = preferences.getLong("top3", 1000);

        if (lastscore < top1 || (lastscore < top2 ||(lastscore < top3))){
           highscore.setVisibility(View.VISIBLE);
        }

        AntalForsøg.setText("Du havde " + forsøg +  " forkerte,\n med en tid på " + lastscore + " sekunder" + "!");
        winsound.start();

        Afslut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Hovedmenu = new Intent(VundetSpil.this, HovedMenu.class);
                VundetSpil.this.startActivity(Hovedmenu);
                finish();
            }
        });
    }

}
