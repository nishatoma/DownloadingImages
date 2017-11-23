package com.example.league95.downloadingimages;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    //lets do the async shit from before
    //Except this time we are downloadinga bitmap!
    public class DownloadTask extends AsyncTask<String, Void, Bitmap>
    {
        //Notice how the method changed to return bitmap!
        @Override
        protected Bitmap doInBackground(String... urls) {

            URL url;
            HttpURLConnection httpURLConnection;

            try {
                url = new URL(urls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                //connect
                httpURLConnection.connect();
                //reading from a website again
                InputStream inputStream = httpURLConnection.getInputStream();
                //Setup reader
                //InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                //Our bitmap from the stream
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;

            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=  findViewById(R.id.imageView);

    }

    public void downloadImage(View view)
    {
        // https://en.wikipedia.org/wiki/Bart_Simpson#/media/File:Bart_Simpson_200px.png

        DownloadTask downloadTask = new DownloadTask();

        Bitmap result;
        try{
            result = downloadTask.execute("https://vignette.wikia.nocookie.net/simpsons/images/a/ab/BartSimpson.jpg/revision/latest?cb=20141101133153").get();
            //then get the downloaded image to image view
            imageView.setImageBitmap(result);

        } catch (Exception e)
        {

        }
        //Log.i("Interacton:", "Button tapped");
    }


}
