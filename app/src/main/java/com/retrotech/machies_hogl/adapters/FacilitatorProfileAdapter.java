package com.retrotech.machies_hogl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retrotech.machies_hogl.R;
import com.retrotech.machies_hogl.models.FacilitatorProfileModel;

import java.util.ArrayList;

public class FacilitatorProfileAdapter extends RecyclerView.Adapter<FacilitatorProfileAdapter.ViewHolder> {
    ArrayList<FacilitatorProfileModel> memberProfileModels;
    Context context;

    public FacilitatorProfileAdapter(Context context,ArrayList<FacilitatorProfileModel> memberProfileModels){
        this.memberProfileModels = memberProfileModels;
        this.context = context;
    }
    @NonNull
    @Override
    public FacilitatorProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.facilitator_profile,parent, false);
        FacilitatorProfileAdapter.ViewHolder bind = new FacilitatorProfileAdapter.ViewHolder(view);
        return bind;
    }

    @Override
    public void onBindViewHolder(@NonNull FacilitatorProfileAdapter.ViewHolder holder, int position) {
        final FacilitatorProfileModel model = memberProfileModels.get(position);
        holder.facilitator_name.setText(model.getFacilitator_name());
        holder.facilitator_id.setText("ID: "+model.getFacilitator_id());
        holder.gender.setText("Gender\n"+model.getGender());
        holder.admission_date.setText("Admission Date\n"+model.getAdmission_date());
        holder.group_name.setText("Group Name\n"+model.getGroup_name());
        holder.number_groups.setText("Total Groups Enrolled\n"+model.getNumber_groups());
        holder.number_members.setText("Total Members Enrolled\n"+model.getNumber_members());
        holder.total_groups_contribution.setText("Total Contribution\n"+model.getTotal_groups_contribution());

    }

    @Override
    public int getItemCount() {
        return memberProfileModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView facilitator_name,facilitator_id,gender,admission_date,group_name,number_groups, number_members, total_groups_contribution;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            facilitator_name = itemView.findViewById(R.id.facilitator_name);
            facilitator_id = itemView.findViewById(R.id.facilitator_id);
            gender = itemView.findViewById(R.id.gender);
            admission_date = itemView.findViewById(R.id.admission_date);
            group_name = itemView.findViewById(R.id.group_name);
            number_groups = itemView.findViewById(R.id.number_groups);
            number_members = itemView.findViewById(R.id.number_members);
            total_groups_contribution = itemView.findViewById(R.id. total_groups_contribution);

        }
    }
}
