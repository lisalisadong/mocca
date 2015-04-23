package com.example.qingxiaodong.imageviewer;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;


public class MainActivity extends Activity {

    private EditText mUrlEditText;
    private ImageView mImageView;
    private Uri mDefaultUrl =
            Uri.parse("http://images.huffingtonpost.com/2015-03-12-1426188041-1888511-EarthDay-thumb.jpg");
    static int code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUrlEditText = (EditText) findViewById(R.id.mUrlEditText);
        mImageView = (ImageView) findViewById(R.id.mImageView);
    }

    public void downloadOriginal(View view) {
        code = 1;
        new DownloadOriginal(this, mImageView).execute(getUrl());
    }

    public void downloadAndFilter(View view) {
        code = 2;
        new GreyFilter(this, mImageView).execute(getUrl());
    }

    protected Uri getUrl() {
        Uri url = null;

        url = Uri.parse(mUrlEditText.getText().toString());

        String uri = url.toString();
        if (uri == null || uri.equals(""))
            url = mDefaultUrl;

        if (URLUtil.isValidUrl(url.toString())) {
            return url;
        }
        else {
            Toast.makeText(this,
                    "Invalid URL",
                    Toast.LENGTH_SHORT).show();
            return null;
        }
    }

}
