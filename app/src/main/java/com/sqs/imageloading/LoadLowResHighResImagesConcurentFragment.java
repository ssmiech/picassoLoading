package com.sqs.imageloading;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoadLowResHighResImagesConcurentFragment extends Fragment {

    @Bind(R.id.first_image_label)
    TextView firstImageLabel;
    @Bind(R.id.first_image)
    ImageView firstImageView;
    @Bind(R.id.second_image_label)
    TextView secondImageLabel;
    @Bind(R.id.second_image)
    ImageView secondImageView;
    @Bind(R.id.start_button)
    Button startButton;
    private Picasso picasso;
    private long startTime;
    private ImageLoader imageLoader;
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startTime = System.nanoTime();
            imageLoader.loadImagesForPage(
                    System.nanoTime(),
                    "https://image.issuu.com/160802114056-0a45f8a1dc567dc4f53d332226f80c74/jpg/page_%d_thumb_large.jpg",
                    firstImageView,
                    100,
                    firstImageLabel);

            imageLoader.loadImagesForPage(
                    System.nanoTime(),
                    "https://image.issuu.com/160802114056-0a45f8a1dc567dc4f53d332226f80c74/jpg/page_%d.jpg",
                    secondImageView,
                    100,
                    secondImageLabel);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        picasso = Picasso.with(getActivity());
        picasso.setIndicatorsEnabled(true);
        imageLoader = new ImageLoader(picasso);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        startButton.setOnClickListener(clickListener);
    }

}
