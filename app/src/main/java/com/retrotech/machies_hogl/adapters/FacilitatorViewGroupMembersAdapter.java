package com.retrotech.machies_hogl.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retrotech.machies_hogl.R;
import com.retrotech.machies_hogl.models.FacilitatorViewMembersModel;
import com.retrotech.machies_hogl.models.Groups;

import java.util.ArrayList;
import java.util.List;


public class FacilitatorViewGroupMembersAdapter extends RecyclerView.Adapter<FacilitatorViewGroupMembersAdapter.viewHolder> {
    Context context;
    ArrayList<FacilitatorViewMembersModel> listGroups;
    View.OnClickListener clickListener;
    private Groups currentGroups;

    public FacilitatorViewGroupMembersAdapter(Context context, ArrayList<FacilitatorViewMembersModel> listGroups) {
        this.context = context;
        this.listGroups = listGroups;
    }

    @NonNull
    @Override
    public FacilitatorViewGroupMembersAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_groups, parent, false);
        FacilitatorViewGroupMembersAdapter.viewHolder binder = new FacilitatorViewGroupMembersAdapter.viewHolder(view);
        return binder;
    }

    @Override
    public void onBindViewHolder(@NonNull FacilitatorViewGroupMembersAdapter.viewHolder holder, int position) {
        final FacilitatorViewMembersModel currentGroups = listGroups.get(position);
        holder.group_name.setSelected(true);
        String name = String.valueOf(currentGroups.getGroup_name());
        String id = String.valueOf(currentGroups.getId());
        holder.group_name.setText(name);
        holder.group_id.setText("ID:  "+id);





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickListener.onClick(view);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listGroups.size();
    }

    public void setClickListener(View.OnClickListener callback) {
        clickListener = callback;
    }

    public void setGroups(List<FacilitatorViewMembersModel> listGroups) {
        this.listGroups = (ArrayList<FacilitatorViewMembersModel>) listGroups;
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView group_name,group_id;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            group_name = itemView.findViewById(R.id.group_name);
            group_id = itemView.findViewById(R.id.group_IDD);



        }
    }
}