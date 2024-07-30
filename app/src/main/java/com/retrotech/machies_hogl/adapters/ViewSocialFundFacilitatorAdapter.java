package com.retrotech.machies_hogl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retrotech.machies_hogl.R;
import com.retrotech.machies_hogl.models.ViewSocialFundFacilitatorModel;

import java.util.ArrayList;
import java.util.List;

public class ViewSocialFundFacilitatorAdapter extends RecyclerView.Adapter<ViewSocialFundFacilitatorAdapter.ViewHolder> {
    public static List<ViewSocialFundFacilitatorModel> listSocial;
    private ViewSocialFundFacilitatorModel viewSocialFundFacilitatorModel;
    private Context mContext;

    //constructor
    public ViewSocialFundFacilitatorAdapter(ArrayList<ViewSocialFundFacilitatorModel> listSocial,Context context) {
        this.mContext = context;
        this.listSocial = listSocial;


    }

    @NonNull
    @Override
    public ViewSocialFundFacilitatorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_facilitator_social_fund,parent,false);
        ViewSocialFundFacilitatorAdapter.ViewHolder viewHolder = new  ViewSocialFundFacilitatorAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewSocialFundFacilitatorAdapter.ViewHolder holder, int position) {
      viewSocialFundFacilitatorModel = listSocial.get(position);
        holder.total_social_in.setText("Collected Social Fund K"+ viewSocialFundFacilitatorModel.getTotal_social_in());
        holder.total_social_out.setText("Total Social Fund Used K"+viewSocialFundFacilitatorModel.getTotal_social_out());
        holder.social_fund_balance.setText("Fine Balance K"+viewSocialFundFacilitatorModel.getSocial_fund_balance());
        holder.group_name.setText( viewSocialFundFacilitatorModel.getGroup_name());
        holder.group_id.setText("Group ID: "+ viewSocialFundFacilitatorModel.getGroup_id());
    }

    @Override
    public int getItemCount() {
        return listSocial.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView total_social_in, total_social_out, social_fund_balance,group_name,group_id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            total_social_in = itemView.findViewById(R.id.total_social_in);
            total_social_out= itemView.findViewById(R.id.total_social_out);
            social_fund_balance = itemView.findViewById(R.id.social_fund_balance);
            group_name = itemView.findViewById(R.id.group_name);
            group_id = itemView.findViewById(R.id.group_id);
        }
    }
}
