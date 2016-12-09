package rueda.fred.marvel.com.marvelspa;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.MarvelApi;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.manager.CharacterManager;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.request.CharacterRequest;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.request.RequestSignature;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.response.ServiceResponse;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.vo.Character;

public class FragmentFavoriteMarvel extends Fragment {

    RecyclerView recyclerView;
    AdapterFavoriteList adapter;
    Button btnBack, btnNext;
    int offset;
    int numberListOffset;
    ProgressDialog loading;

    public FragmentFavoriteMarvel() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_comic);
        List<FavoriteComicsDb> heroes = getListComics();
        if(heroes.size()<=0){
            Toast.makeText(getContext(),"No tienes comics favoritos.",Toast.LENGTH_LONG).show();
        }
        adapter = new AdapterFavoriteList(heroes, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    public static List<FavoriteComicsDb> getListComics (){
        List<FavoriteComicsDb> comics = new Select().from(FavoriteComicsDb.class).execute();
        return comics;
    }


}
