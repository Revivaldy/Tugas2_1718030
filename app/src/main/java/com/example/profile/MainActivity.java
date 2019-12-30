package com.example.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    TextView text_1, text_2, text_3, text_4, text_5, text_6, text_7;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalHellper.OnAttach(newBase, "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_1=(TextView)findViewById(R.id.text_4);
        text_2=(TextView)findViewById(R.id.text1);
        text_3=(TextView)findViewById(R.id.text22);
        text_4=(TextView)findViewById(R.id.text3);
        text_5=(TextView)findViewById(R.id.pubg);
        text_6=(TextView)findViewById(R.id.cod);
        text_7=(TextView)findViewById(R.id.rush);

        Paper.init(this);

        String language = Paper.book().read("language");
        if (language == null)
            Paper.book().write("Language", "en");

        updateView((String)Paper.book().read("language"));

        Typeface customfont3=Typeface.createFromAsset(getAssets(),"font/Slowly One.ttf");
        text_1.setTypeface(customfont3);
    }

    private void updateView(String lang) {
        Context context = LocalHellper.setLocale(this, lang);
        Resources resources = context.getResources();
        text_2.setText(resources.getString(R.string.foll));
        text_3.setText(resources.getString(R.string.follwing));
        text_4.setText(resources.getString(R.string.post));
        text_5.setText(resources.getString(R.string.pubgs));
        text_6.setText(resources.getString(R.string.cods));
        text_7.setText(resources.getString(R.string.chess));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.indo){
            Paper.book().write("language", "in");
            updateView((String)Paper.book().read("language"));

        } else if (id == R.id.eng){
            Paper.book().write("language", "en");
            updateView((String)Paper.book().read("language"));
        }

        return true;
    }
}
