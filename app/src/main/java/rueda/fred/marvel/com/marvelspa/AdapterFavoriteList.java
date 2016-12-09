package rueda.fred.marvel.com.marvelspa;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.query.Delete;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Fred Rueda.
 * Developer Fred Rueda
 * Email fredjruedao@gmail.com
 */

public class AdapterFavoriteList extends RecyclerView.Adapter<AdapterFavoriteList.ListFavoriteViewHolder> {

    List<FavoriteComicsDb> comics;
    Context context;
    private View view;

    public AdapterFavoriteList(List<FavoriteComicsDb> comics, Context context) {
        this.comics = comics;
        this.context = context;
    }

    @Override
    public ListFavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_comics_fav, parent, false);
        ListFavoriteViewHolder viewHolder = new ListFavoriteViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListFavoriteViewHolder holder, final int position) {
        final FavoriteComicsDb comic = comics.get(position);
        Picasso.with(context).load(comic.getImage()).into(holder.portada);
        holder.title.setText(comic.getTitle());
        holder.description.setText(comic.getDescription());
        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Delete().from(FavoriteComicsDb.class).where("idcomic = ?", comic.getIdComic()).execute();
                comics.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    public class ListFavoriteViewHolder extends RecyclerView.ViewHolder {

        protected ImageView portada;
        protected TextView title;
        protected TextView description;
        protected FloatingActionButton btnSave;

        public ListFavoriteViewHolder(View itemView) {
            super(itemView);

            portada = (ImageView)itemView.findViewById(R.id.img_comic);
            title = (TextView)itemView.findViewById(R.id.title);
            description = (TextView)itemView.findViewById(R.id.description);
            btnSave = (FloatingActionButton)itemView.findViewById(R.id.fbsave);
        }
    }

}
