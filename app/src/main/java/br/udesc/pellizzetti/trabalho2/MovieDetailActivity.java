package br.udesc.pellizzetti.trabalho2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetailActivity extends AppCompatActivity {

    TextView movieTitle, date, movieDescription, rating;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        movieTitle = findViewById(R.id.title);
        date = findViewById(R.id.date);
        movieDescription = findViewById(R.id.description);
//        rating = findViewById(R.id.rating);
        img = findViewById(R.id.movie_image);

        getDetailsData();
    }

    public void getDetailsData() {

        Bundle extras = getIntent().getExtras();
        Bitmap mImage = null;
        String mTitle = null;
        String mDate = null;
        String mDesc = null;
        String mRating = null;

        if (extras != null) {
            byte[] byteArray = getIntent().getByteArrayExtra("movieImage");
            mImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            mTitle = extras.getString("movieTitle");
            mDate = extras.getString("data");
            mDesc = extras.getString("movieDescription");
            mRating = extras.getString("rating");
        }

        img.setImageBitmap(mImage);
        movieTitle.setText(mTitle);
        date.setText(mDate);
        movieDescription.setText(mDesc);
//        rating.setText(mRating);
        //

    }
}
