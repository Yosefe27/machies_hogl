package com.retrotech.machies_hogl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retrotech.machies_hogl.R;
import com.retrotech.machies_hogl.models.ViewGroupSavingFacilitatorModel;

import java.util.ArrayList;

public class ViewGroupSavingFacilitatorAdapter extends RecyclerView.Adapter<ViewGroupSavingFacilitatorAdapter.ViewHolder> {
    ArrayList<ViewGroupSavingFacilitatorModel> savingModel;
    private ViewGroupSavingFacilitatorModel currentSaving;
    private Context mContext;
    View.OnClickListener clickListener;

    public ViewGroupSavingFacilitatorAdapter(ArrayList<ViewGroupSavingFacilitatorModel> savingModel, Context context) {
        this.mContext = context;
        this.savingModel = savingModel;

    }

    public void setClickListener(View.OnClickListener callback) {
        clickListener = callback;
    }
    @NonNull
    @Override
    public ViewGroupSavingFacilitatorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_facilitator_group_savings, parent, false);
        ViewGroupSavingFacilitatorAdapter.ViewHolder bind = new ViewGroupSavingFacilitatorAdapter.ViewHolder(view);
        return bind;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewGroupSavingFacilitatorAdapter.ViewHolder holder, int position) {
                currentSaving = savingModel.get(position);
                holder.group_name.setText(currentSaving.getGroup_name()+" GROUP");
                holder.group_id.setText("ID: "+currentSaving.getGroup_id());
                holder.total_contributions.setText("Total Contribution K "+currentSaving.getTotal_contribution());

    }

    @Override
    public int getItemCount() {
        return savingModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView group_name,group_id,total_contributions;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            group_name = itemView.findViewById(R.id.group_name);
            group_id = itemView.findViewById(R.id.group_id);
            total_contributions = itemView.findViewById(R.id.total_contribution);
        }
    }
}
