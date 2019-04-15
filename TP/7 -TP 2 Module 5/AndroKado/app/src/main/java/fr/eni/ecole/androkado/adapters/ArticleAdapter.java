package fr.eni.ecole.androkado.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import fr.eni.ecole.androkado.R;
import fr.eni.ecole.androkado.bo.Article;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder>{

    private List<Article> data;
    private CustomItemClickListener listener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_article,parent,false);

        final MyViewHolder viewHolder = new MyViewHolder(mView);

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    Log.i("ITEMS_ARTICLE", "Position : " + viewHolder.getAdapterPosition());
                    listener.onItemClick(v, ArticleAdapter.this.data.get( viewHolder.getAdapterPosition()));
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Article article = data.get(i);
        myViewHolder.nom.setText(article.getNom());
        myViewHolder.envie.setRating(article.getEnvie());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     *
     * @param data List<Article>
     * @param listener CustomItemClickListener
     */
    public ArticleAdapter(List<Article> data, CustomItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

     static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView nom;
        public RatingBar envie;
        public MyViewHolder(View v) {
            super(v);
            nom = v.findViewById(R.id.txtNomArticle);
            envie = v.findViewById(R.id.rtbEnvieArticle);
        }
    }


    /**
    * Interface Listener
     */
    public interface CustomItemClickListener{
        public void onItemClick(View v, Article article);
    }
}
