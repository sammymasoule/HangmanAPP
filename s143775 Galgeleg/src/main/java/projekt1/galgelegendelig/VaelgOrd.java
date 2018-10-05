package projekt1.galgelegendelig;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class VaelgOrd extends AppCompatActivity {

    Galgelogik logik;
    ListView ord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);
        logik = new Galgelogik();

        ord = (ListView) findViewById(R.id.ListView1);
        HentDR();
        ord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String word = ord.getItemAtPosition(position).toString();
                Intent intent = new Intent(VaelgOrd.this, SpilSolo.class);
                intent.putExtra("multiplayer", word);
                VaelgOrd.this.startActivity(intent);
                finish();
            }
        });


    }

    public void HentDR() {

        class Asynctask1 extends AsyncTask {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    logik.populateList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "execute";
            }

            @Override
            protected void onPostExecute(Object o) {
                ArrayAdapter<String> arrayAdapter =
                        new ArrayAdapter<String>(VaelgOrd.this, android.R.layout.simple_list_item_1, logik.getListAfMuligeOrd());
                ord.setAdapter(arrayAdapter);
            }
        }
        new Asynctask1().execute();
    }
}
