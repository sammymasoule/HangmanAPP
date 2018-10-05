package projekt1.galgelegendelig;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TabtSpil extends AppCompatActivity {

    private Button Afslut;
    private TextView Ord;
    String ordet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabt_spil);

        Ord = (TextView) findViewById(R.id.Ord);
        Afslut = (Button) findViewById(R.id.Btn1);
        ordet = getIntent().getStringExtra("Ordet");
        final MediaPlayer losesound = MediaPlayer.create(this, R.raw.losesound);

        Ord.setText("Ordet var: " + ordet);
        losesound.start();

        Afslut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent HovedMenu = new Intent(TabtSpil.this, projekt1.galgelegendelig.HovedMenu.class);
                TabtSpil.this.startActivity(HovedMenu);
                finish();
            }
        });


    }
}
