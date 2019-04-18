package fr.eni.ecole.androkado.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.eni.ecole.androkado.R;
import fr.eni.ecole.androkado.bo.Contact;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private List<Contact> data;
    private CustomItemClickListener listener;

    public ContactAdapter(List<Contact> data, CustomItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contact,parent,false);

        final ContactAdapter.MyViewHolder viewHolder = new ContactAdapter.MyViewHolder(mView);

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onItemClick(v, ContactAdapter.this.data.get( viewHolder.getAdapterPosition()));
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Contact contact = data.get(i);
        myViewHolder.nom.setText(contact.getNom());
        myViewHolder.telephone.setText(contact.getTelephone());
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    protected static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView nom;
        public TextView telephone;
        public MyViewHolder(View v) {
            super(v);
            nom = v.findViewById(R.id.txtNomContact);
            telephone = v.findViewById(R.id.txtTelephone);
        }
    }

    /**
     * Interface Listener
     */
    public interface CustomItemClickListener{
        public void onItemClick(View v, Contact contact);
    }

}
