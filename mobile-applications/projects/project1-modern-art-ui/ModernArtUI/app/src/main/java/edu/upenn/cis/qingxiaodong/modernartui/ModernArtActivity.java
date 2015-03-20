package edu.upenn.cis.qingxiaodong.modernartui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class ModernArtActivity extends ActionBarActivity {

    static private final String URL = "http://www.moma.org";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art);
        SeekBar colorChanger = (SeekBar) findViewById(R.id.seekBar);
        colorChanger.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progressValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ((ImageView) findViewById(R.id.color1)).setBackgroundColor(Color.argb(0xFF,
                        233+22*progress/100, 30+163*progress/100, 99-92*progress/100));
                ((ImageView) findViewById(R.id.color2)).setBackgroundColor(Color.argb(0xFF,
                        63+(244-63)*progress/100, 81+(255-81)*progress/100, 181-(181-129)*progress/100));
                ((ImageView) findViewById(R.id.color3)).setBackgroundColor(Color.argb(0xFF,
                        255, 235+(249-235)*progress/100, 59+(196-59)*progress/100));
                ((ImageView) findViewById(R.id.color5)).setBackgroundColor(Color.argb(0xFF,
                        183+(139-183)*progress/100, 28+(195-28)*progress/100, 28+(74-28)*progress/100));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modern_art, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_more_info) {
            final Dialog dialog = new Dialog(ModernArtActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog);
            dialog.show();
            Button nButton = (Button) dialog.findViewById(R.id.nButton);
            nButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            Button pButton = (Button) dialog.findViewById(R.id.pButton);
            pButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                    startActivity(newIntent);
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
