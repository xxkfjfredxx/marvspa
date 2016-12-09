package rueda.fred.marvel.com.marvelspa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.manager.CharacterManager;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.manager.ComicManager;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.request.CharacterRequest;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.request.ComicRequest;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.request.RequestSignature;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.response.ServiceResponse;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.vo.Character;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.vo.Comic;

public class ComicsDetailActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterComicDetail adapter;
    Context context;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Comics");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recycler_comic);
        context = this;

        List<Comic> heroes = new ArrayList<>();
        adapter = new AdapterComicDetail(heroes, context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("idHeroe", 0);

        loadComics(intValue);
        loading = ProgressDialog.show(this, "Cargando...", "Porfavor espere...", false, false);

    }

    public void loadComics(final int idComic){
        ComicRequest request = new ComicRequest(RequestSignature.create());
        request.setLimit(40);
        CharacterManager client = new CharacterManager();
        client.getComicsForCharacterId(idComic, request, new Callback<ServiceResponse<Comic>>() {
            @Override
            public void success(ServiceResponse<Comic> comicServiceResponse, Response response) {
                if(comicServiceResponse.data.results.size()>0) {
                    adapter = new AdapterComicDetail(comicServiceResponse.data.results, context);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ComicsDetailActivity.this));
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(context,"No se encontraron comics..",Toast.LENGTH_LONG).show();
                    finish();
                }
                loading.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Error",""+error.toString());
                loadComics(idComic);
            }
        });
    }
}
