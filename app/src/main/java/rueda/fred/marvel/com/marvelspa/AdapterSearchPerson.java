package rueda.fred.marvel.com.marvelspa;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import rueda.fred.marvel.com.marvelspa.marvel.model.api.vo.Character;

/**
 * Created by Fred Rueda.
 * Developer Fred Rueda
 * Email fredjruedao@gmail.com
 */

public class AdapterSearchPerson extends RecyclerView.Adapter<AdapterSearchPerson.ListSearchViewHolder> {

    List<Character> heroe;
    Context context;
    private View view;

    public AdapterSearchPerson(List<Character> heroe, Context context) {
        this.heroe = heroe;
        this.context = context;
    }

    @Override
    public ListSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_search, parent, false);
        ListSearchViewHolder viewHolder = new ListSearchViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListSearchViewHolder holder, int position) {
        final Character cHeroe = heroe.get(position);
        holder.nameHeroe.setText(cHeroe.name);
        holder.descriptionHeroe.setText(cHeroe.description);
        //Log.e("Imagen",""+cHeroe.thumbnail.path+"."+cHeroe.thumbnail.extension);
        Picasso.with(context).load(cHeroe.thumbnail.path + "." + cHeroe.thumbnail.extension).into(holder.imgHeroe);
        holder.verMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(context, ComicsDetailActivity.class);
                myIntent.putExtra("idHeroe", cHeroe.id);
                context.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return heroe.size();
    }

    public class ListSearchViewHolder extends RecyclerView.ViewHolder {

        protected TextView nameHeroe;
        protected TextView descriptionHeroe;
        protected TextView verMas;
        protected CircleImageView imgHeroe;

        public ListSearchViewHolder(View itemView) {
            super(itemView);

            nameHeroe = (TextView) itemView.findViewById(R.id.title_heroe);
            descriptionHeroe = (TextView) itemView.findViewById(R.id.description_heroe);
            verMas = (TextView) itemView.findViewById(R.id.more);
            imgHeroe = (CircleImageView) itemView.findViewById(R.id.img_find);
        }
    }


}
