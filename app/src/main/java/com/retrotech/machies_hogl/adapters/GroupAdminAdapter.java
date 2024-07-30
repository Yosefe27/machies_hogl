package com.retrotech.machies_hogl.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.retrotech.machies_hogl.AddMemberToGroupActivity;
import com.retrotech.machies_hogl.FacilitatorGroupsActivity;
import com.retrotech.machies_hogl.FacilitatorNewGroupActivity;
import com.retrotech.machies_hogl.R;
import com.retrotech.machies_hogl.FacilitatorViewGroupsActivity;
import com.retrotech.machies_hogl.models.GroupAdminModel;

import java.util.ArrayList;

public class GroupAdminAdapter extends RecyclerView.Adapter<GroupAdminAdapter.viewHolder> {
    ArrayList<GroupAdminModel> models;
    Context context;
    public GroupAdminAdapter( ArrayList<GroupAdminModel> models, Context context){
        this.models = models;
        this.context = context;
    }
    @NonNull
    @Override
    public GroupAdminAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_layout_cardview,parent,false);
        GroupAdminAdapter.viewHolder viewHolder = new GroupAdminAdapter.viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdminAdapter.viewHolder holder, int position) {
        final GroupAdminModel groupAdminModel = models.get(position);
        //   holder.imageView.setImageDrawable(mainActivityModel.getImageView());
        holder.name.setText(groupAdminModel.getNameType());
        holder.imageView.setBackgroundResource(groupAdminModel.getImage());
        holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, groupAdminModel.getColor()));

        String id = groupAdminModel.getCardID();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(id){
                    case "create group":
                        Intent groupAdmin = new Intent(context, FacilitatorNewGroupActivity.class);
                        context.startActivity(groupAdmin);
                        break;
                    case "view groups":
                        Intent viewGroup = new Intent(context, FacilitatorGroupsActivity.class);
                        context.startActivity(viewGroup);
                        break;
                    case "add member":
                        Intent addMember = new Intent(context, AddMemberToGroupActivity.class);
                        context.startActivity(addMember);
                        break;
                    case "view members":
                        Intent viewMembers = new Intent(context, FacilitatorViewGroupsActivity.class);
                        context.startActivity(viewMembers);
                }
            }
        });
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
            name = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
