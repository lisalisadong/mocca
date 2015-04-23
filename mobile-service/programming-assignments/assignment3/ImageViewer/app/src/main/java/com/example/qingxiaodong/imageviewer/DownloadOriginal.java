package com.example.qingxiaodong.imageviewer;

import android.os.AsyncTask;
import android.net.Uri;
import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

public class DownloadOriginal extends AsyncTask<Uri, Void, Uri> {

    private final String TAG = DownloadOriginal.class.getSimpleName();
    private Context mContext;
    private ImageView imageView;

    public DownloadOriginal(Context mContext, ImageView imageView) {
        this.mContext = mContext;
        this.imageView = imageView;
    }

    protected void onPreExecute() {
        Toast.makeText(mContext, "Downloading image.", Toast.LENGTH_SHORT).show();
    }

    protected Uri doInBackground(Uri... uris) {
        return Utils.downloadImage(mContext, uris[0]);
    }

    protected void onPostExecute(Uri imageUri) {
        if (imageUri == null) {
            Toast.makeText(mContext, "Downloading image failed, please check url.", Toast.LENGTH_LONG).show();
        } else {
            imageView.setImageURI(imageUri);
        }
    }
}
