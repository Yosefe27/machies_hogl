package com.retrotech.machies_hogl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.retrotech.machies_hogl.R;
import com.retrotech.machies_hogl.models.BookWriterAdminModel;

import java.util.ArrayList;

public class BookWriterAdminAdapter extends RecyclerView.Adapter<BookWriterAdminAdapter.viewHolder> {
    ArrayList<BookWriterAdminModel> models;
    Context context;
    View.OnClickListener clickListener;
    public BookWriterAdminAdapter(ArrayList<BookWriterAdminModel> models, Context context){
        this.models = models;
        this.context = context;
    }

    public void setClickListener(View.OnClickListener callback) {

        clickListener = callback;
    }

    @NonNull
    @Override
    public BookWriterAdminAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO rename layout for the bookwriteradmin
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_layout_cardview,parent,false);
        BookWriterAdminAdapter.viewHolder viewHolder = new BookWriterAdminAdapter.viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookWriterAdminAdapter.viewHolder holder, int position) {
        final BookWriterAdminModel bookWriterAdminModel = models.get(position);

        holder.name.setText(bookWriterAdminModel.getNameType());
        holder.imageView.setBackgroundResource(bookWriterAdminModel.getImage());
        holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, bookWriterAdminModel.getColor()));

        holder.itemView.setOnClickListener(view -> clickListener.onClick(view));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imageView;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
//TODO name and imageView need to be descriptive e.g cardLabel
            name = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
