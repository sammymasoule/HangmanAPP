package projekt1.galgelegendelig;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameMode extends AppCompatActivity {

    Button SinglePlayer, MultiPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamemode);

        SinglePlayer = (Button) findViewById(R.id.SinglePlayer);
        SinglePlayer.setText("Spil alene");
        MultiPlayer = (Button) findViewById(R.id.Multiplayer);
        MultiPlayer.setText("Spil med venner!");

        SinglePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SinglePlayer = new Intent(GameMode.this, Name.class);
                String solo = "single";
                SinglePlayer.putExtra("solo", solo);
                GameMode.this.startActivity(SinglePlayer);
                finish();
            }
        });

        MultiPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Multiplayer = new Intent(GameMode.this, VaelgOrd.class);
                GameMode.this.startActivity(Multiplayer);
                finish();
            }
        });

    }
}
