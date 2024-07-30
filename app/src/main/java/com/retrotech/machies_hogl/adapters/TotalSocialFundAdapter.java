package com.retrotech.machies_hogl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retrotech.machies_hogl.R;
import com.retrotech.machies_hogl.models.TotalSocialFundModel;

import java.util.ArrayList;

public class TotalSocialFundAdapter extends RecyclerView.Adapter<TotalSocialFundAdapter.ViewHolder> {
   ArrayList<TotalSocialFundModel> totalSocialFundModel;
   Context context;
   
    public TotalSocialFundAdapter(Context context, ArrayList<TotalSocialFundModel> totalSocialFundModel) {
        this.context = context;
        this.totalSocialFundModel = totalSocialFundModel;
    }
    
    @NonNull
    @Override
    public TotalSocialFundAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.social_fund_total,parent,false);
        TotalSocialFundAdapter.ViewHolder binder = new TotalSocialFundAdapter.ViewHolder(view);
        return binder;
    }

    @Override
    public void onBindViewHolder(@NonNull TotalSocialFundAdapter.ViewHolder holder, int position) {
    final TotalSocialFundModel model = totalSocialFundModel.get(position);
        holder.total_amount_social.setText("K "+model.getAmount());
        holder.current_balance.setText("Current Balance: "+"K"+model.getCurrent_balance());
        holder.group_id.setText("Group ID: "+model.getGroup_id());
        holder.group_name.setText("Group Name: "+model.getGroup_name());
        holder.total_disbursed.setText("K "+model.getTotal_disbursed());
    }

    @Override
    public int getItemCount() {
        return totalSocialFundModel.size();
    }

    public void setClickListener(View.OnClickListener onClickListener) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView total_amount_social,group_name,total_disbursed,current_balance,group_id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            group_name = itemView.findViewById(R.id.group_name);
            group_id = itemView.findViewById(R.id.group_id);
            total_amount_social = itemView.findViewById(R.id.total_amount_social);
            total_disbursed = itemView.findViewById(R.id.total_disbursed);
            current_balance = itemView.findViewById(R.id.current_balance);
        }
    }
}
