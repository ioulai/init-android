package fr.eni.ecole.listeArticle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.eni.ecole.listeArticle.R;
import fr.eni.ecole.listeArticle.bo.Article;

public class ArticleAdapter extends ArrayAdapter<Article> {

    private List<Article> datas;
    private int res;

    public ArticleAdapter(Context context, int resource, List<Article> articles) {
        super(context, resource, articles);
        this.datas = articles;
        this.res = resource;
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        View view;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            view = inflater.inflate(res, parent, false);
        }
        else{
            view = convertView;
        }

        //Récuperer une instance
        Article p = getItem(position);

        TextView article = view.findViewById(R.id.txtNomom);

        article.setText(p.getNomArticle());

        return view;
    }
}
