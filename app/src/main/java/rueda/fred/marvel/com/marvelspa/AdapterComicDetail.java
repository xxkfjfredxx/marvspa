package rueda.fred.marvel.com.marvelspa;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.squareup.picasso.Picasso;

import java.util.List;

import rueda.fred.marvel.com.marvelspa.marvel.model.api.vo.Character;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.vo.Comic;

/**
 * Created by Fred Rueda.
 * Developer Fred Rueda
 * Email fredjruedao@gmail.com
 */

public class AdapterComicDetail extends RecyclerView.Adapter<AdapterComicDetail.ListComicViewHolder> {

    List<Comic> comics;
    Context context;
    private View view;

    public AdapterComicDetail(List<Comic> comics, Context context) {
        this.comics = comics;
        this.context = context;
    }

    @Override
    public ListComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_comics, parent, false);
        ListComicViewHolder viewHolder = new ListComicViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListComicViewHolder holder, int position) {
        final Comic comic = comics.get(position);
        Picasso.with(context).load(comic.thumbnail.path + "." + comic.thumbnail.extension).into(holder.portada);
        holder.title.setText(comic.title);
        holder.description.setText(comic.description);
        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<FavoriteComicsDb> lists = new Select().from(FavoriteComicsDb.class).where("idcomic = ?", comic.id).execute();

                if (lists.size() >= 1) {
                    Toast.makeText(context, "Ya esta en tus favoritos.", Toast.LENGTH_LONG).show();
                } else {
                    FavoriteComicsDb restaurants = new FavoriteComicsDb();
                    restaurants.idComic = comic.id;
                    restaurants.title = comic.title;
                    restaurants.description = comic.description;
                    restaurants.image = comic.thumbnail.path + "." + comic.thumbnail.extension;
                    restaurants.save();
                    Toast.makeText(context, "Se agrego a tus favoritos.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    public class ListComicViewHolder extends RecyclerView.ViewHolder {

        protected ImageView portada;
        protected TextView title;
        protected TextView description;
        protected FloatingActionButton btnSave;

        public ListComicViewHolder(View itemView) {
            super(itemView);

            portada = (ImageView) itemView.findViewById(R.id.img_comic);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            btnSave = (FloatingActionButton) itemView.findViewById(R.id.fbsave);

        }
    }

}
