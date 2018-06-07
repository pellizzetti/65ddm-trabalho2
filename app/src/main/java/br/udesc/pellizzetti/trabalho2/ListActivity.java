package br.udesc.pellizzetti.trabalho2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = ListActivity.class.getSimpleName();
    private final static String API_KEY = "1f54bd990f1cdfb230adb312546d765d";

    private TextView mStatusView;
    private TextView mUserView;
    private RecyclerView mRecyclerView;
    private SharedPreferencesConfig sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        sessionManager = new SharedPreferencesConfig(this);

        mStatusView = findViewById(R.id.status_login);
        mUserView = findViewById(R.id.user_logged);

        Boolean isLogged = sessionManager.isLogged();
        String status = isLogged ? "Logado: Sim" : "Logado: Não";

        if (isLogged) {
            String user = sessionManager.getUser();
            mUserView.setText(user);
        }

        mStatusView.setText(status);

        mRecyclerView = findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getApiData();
    }

    public void getApiData() {

        MovieApiService movieApiService = RetrofitConfig.getRetrofitInstance().create(MovieApiService.class);

        Call<MovieResponse> call = movieApiService.getPopularMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                mRecyclerView.setAdapter(new MovieAdapter(movies, R.layout.list_item1, getApplicationContext()));
                Log.d(TAG, "Number of movies received: " + movies.size());

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }
}
