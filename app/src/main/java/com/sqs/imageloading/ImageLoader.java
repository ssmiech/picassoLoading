package com.sqs.imageloading;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class ImageLoader {
    private final Picasso picasso;

    public ImageLoader(Picasso picasso) {
        this.picasso = picasso;
    }

    public void loadImagesForPage(final long startTime, final String url, final ImageView target, final int pageNumber, final TextView imageLoadTimeLabel) {
        Callback callback;
        if (pageNumber > 1) {
            callback = new Callback() {
                @Override
                public void onSuccess() {
                    Log.d("asdf", String.format(url, pageNumber));
                    imageLoadTimeLabel.setText(String.valueOf(pageNumber));
                    loadImagesForPage(startTime, url, target, pageNumber - 1, imageLoadTimeLabel);
                }

                @Override
                public void onError() {

                }
            };
        } else {
            callback = new Callback() {
                @Override
                public void onSuccess() {
                    Log.d("asdf", String.format(url, pageNumber));
                    long elapsedTime = System.nanoTime() - startTime;
                    imageLoadTimeLabel.setText(String.valueOf((double) elapsedTime / 1000000000.0));
                }

                @Override
                public void onError() {

                }
            };
        }

        picasso.load(String.format(url, pageNumber))
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .placeholder(R.color.grey)
                .into(target, callback);
    }
}
