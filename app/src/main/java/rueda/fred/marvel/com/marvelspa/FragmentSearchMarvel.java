package rueda.fred.marvel.com.marvelspa;

import android.app.Activity;
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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.MarvelApi;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.manager.CharacterManager;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.request.CharacterRequest;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.request.EventRequest;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.request.RequestSignature;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.response.ServiceResponse;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.vo.Character;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.vo.Event;

public class FragmentSearchMarvel extends Fragment {

    RecyclerView recyclerView;
    AdapterSearchPerson adapter;
    Button btnBack, btnNext;
    int offset;
    int numberListOffset;
    ProgressDialog loading;

    public FragmentSearchMarvel() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_marvel_search, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_search_marv);
        btnBack = (Button) view.findViewById(R.id.back);
        btnNext = (Button) view.findViewById(R.id.next);
        final SearchView search = (SearchView)view.findViewById(R.id.search);
        search.setQueryHint("Buscar Personaje");
        List<Character> heroes = new ArrayList<>();
        adapter = new AdapterSearchPerson(heroes, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        MarvelApi.create("c0ebf39cc41517eb8b57ed231ddece5a5050fdbb", "38c589122d7b43401ca9a55a226da408", getActivity(), 5 * 1024 * 1024);
        offset = 0;
        numberListOffset = 0;
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(getActivity(), "Cargando...", "Porfavor espere...", false, false);
                numberListOffset += 40;
                loadHeroes(1);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (offset >= 1) {
                    loading = ProgressDialog.show(getActivity(), "Cargando...", "Porfavor espere...", false, false);
                    numberListOffset -= 40;
                    loadHeroes(2);
                }
            }
        });

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        search.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        search.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        ImageView closeButton = (ImageView)search.findViewById(R.id.search_close_btn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setQuery("",false);
                offset = 0;
                numberListOffset = 0;
                loading = ProgressDialog.show(getActivity(), "Cargando...", "Porfavor espere...", false, false);
                loadHeroes(0);
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loading = ProgressDialog.show(getActivity(), "Cargando...", "Porfavor espere...", false, false);
                loadForName(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        loadHeroes(0);
        loading = ProgressDialog.show(getActivity(), "Cargando...", "Porfavor espere...", false, false);

        return view;
    }

    public void loadHeroes(final int number) {
        CharacterRequest request = new CharacterRequest(RequestSignature.create());
        request.setLimit(40);
        request.setOffset(numberListOffset);
        Log.e("Number", "" + number);
        CharacterManager client = new CharacterManager();
        client.listCharacters(request, new Callback<ServiceResponse<Character>>() {
            @Override
            public void success(ServiceResponse<Character> characterServiceResponse, Response response) {
                Log.e("llega", "" + characterServiceResponse.status);
                //Log.e("llega algo",""+characterServiceResponse.data.results);
                adapter = new AdapterSearchPerson(characterServiceResponse.data.results, getActivity());
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                if (number == 1) {
                    offset += 1;
                } else if (number == 2) {
                    offset -= 1;
                }
                Log.e("LIST OFFSET", "" + numberListOffset);
                btnNext.setVisibility(View.VISIBLE);
                btnBack.setVisibility(View.VISIBLE);
                loading.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Error", "" + error.toString());
                loadHeroes(number);
            }
        });
    }

    public void loadForName(final String name){
        CharacterRequest request = new CharacterRequest(RequestSignature.create());
        request.setLimit(40);
        request.setOffset(0);
        request.setName(name);
        CharacterManager client = new CharacterManager();
        client.listCharacters(request, new Callback<ServiceResponse<Character>>() {
            @Override
            public void success(ServiceResponse<Character> characterServiceResponse, Response response) {
                Log.e("llega", "" + characterServiceResponse.status);
                adapter = new AdapterSearchPerson(characterServiceResponse.data.results, getActivity());
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                btnNext.setVisibility(View.GONE);
                btnBack.setVisibility(View.GONE);
                loading.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Error", "" + error.toString());
                loadForName(name);
            }
        });
    }

    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}
