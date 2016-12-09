package rueda.fred.marvel.com.marvelspa;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.MarvelApi;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.manager.ComicManager;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.request.RequestSignature;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.request.StoryRequest;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.response.ServiceResponse;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.vo.Story;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

}
