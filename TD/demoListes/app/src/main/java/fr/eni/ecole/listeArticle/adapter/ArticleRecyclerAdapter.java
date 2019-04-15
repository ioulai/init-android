package fr.eni.ecole.listeArticle.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.eni.ecole.listeArticle.R;
import fr.eni.ecole.listeArticle.bo.Article;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.ViewHolder>{

    private List<Article> datas;
    private onClickItemListener event;

    /**
     *
     * @param articles List
     * @param e onClickItemListener
     */
    public ArticleRecyclerAdapter(List<Article> articles, onClickItemListener e){
        this.datas = articles;
        this.event = e;
    }

    //Créer ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_item, viewGroup,false);

        final ViewHolder viewHolder = new ViewHolder(v);

        //Pour log
        final int position = i;
        //Associe le viewholder onClickListener et l'instance de onClickItemListener locale
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(event != null){
                    //viewHolder.getAdapterPosition() donne la position dans les données,
                    //le paramètre i la position dans le nombre de viewHolder (0 puisque une seule instance)
                    event.onClickItem(ArticleRecyclerAdapter.this.datas.get(viewHolder.getAdapterPosition()));
                    Log.i("TAG_DEMO", "position : " + position);
                }
            }
        });

        return viewHolder;
    }

    //Remplir notre view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
            final Article p = this.datas.get(position);

            viewHolder.nom.setText(p.getNomArticle());

            //Associe le viewholder onClickListener et l'instance de onClickItemListener locale
        /*
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(event != null){
                       event.onClickItem(p);
                   }
               }
            });
        */

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nom;

        public ViewHolder(View itemView) {
            super(itemView);

            nom = itemView.findViewById(R.id.txtNomom);
        }
    }

    /**
     * Interface qui permet de gérer le click sur chaque ViewHolder
     */
    public interface onClickItemListener{
        void onClickItem(Article p);
    }
}
