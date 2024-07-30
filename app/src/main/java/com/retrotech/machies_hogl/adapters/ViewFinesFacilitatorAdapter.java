package com.retrotech.machies_hogl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retrotech.machies_hogl.R;
import com.retrotech.machies_hogl.models.ViewFinesFacilitatorModel;

import java.util.ArrayList;
import java.util.List;

public class ViewFinesFacilitatorAdapter extends RecyclerView.Adapter<ViewFinesFacilitatorAdapter.ViewHolder> {
    public static List<ViewFinesFacilitatorModel> listFines;
    private ViewFinesFacilitatorModel viewFinesFacilitatorModel;
    private Context context;

    View.OnClickListener clickListener;

    private int checkedPosition = -1;

    //constructor
    public ViewFinesFacilitatorAdapter(ArrayList<ViewFinesFacilitatorModel> listFines,Context context) {
        this.context = context;
        this.listFines = listFines;;


    }

    @NonNull
    @Override
    public ViewFinesFacilitatorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_facilitator_fines,parent,false);
        ViewFinesFacilitatorAdapter.ViewHolder viewHolder = new  ViewFinesFacilitatorAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewFinesFacilitatorAdapter.ViewHolder holder, int position) {
        viewFinesFacilitatorModel = listFines.get(position);
        holder.total_fine_charged.setText("Charged Fine K"+viewFinesFacilitatorModel.getTotal_fine_charged());
        holder.total_fine_paid.setText("Total Paid Fine K"+viewFinesFacilitatorModel .getTotal_fine_paid());
        holder.fines_balance.setText("Fine Balance K"+viewFinesFacilitatorModel.getFines_balance());
        holder.group_name.setText(viewFinesFacilitatorModel.getGroup_name());
        holder.group_id.setText("Group ID: "+viewFinesFacilitatorModel.getGroup_id());
    }

    @Override
    public int getItemCount() {
        return listFines.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView total_fine_charged, total_fine_paid, fines_balance,group_name,group_id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            total_fine_charged = itemView.findViewById(R.id.total_fine_charged);
            total_fine_paid = itemView.findViewById(R.id.total_fine_paid);
            fines_balance = itemView.findViewById(R.id.fines_balance);
            group_name = itemView.findViewById(R.id.group_name);
            group_id = itemView.findViewById(R.id.group_id);
        }
    }
}
