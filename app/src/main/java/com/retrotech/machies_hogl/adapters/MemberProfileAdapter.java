package com.retrotech.machies_hogl.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retrotech.machies_hogl.R;
import com.retrotech.machies_hogl.models.MemberProfileModel;

import java.util.ArrayList;

public class MemberProfileAdapter extends RecyclerView.Adapter<MemberProfileAdapter.ViewHolder> {
    ArrayList<MemberProfileModel> memberProfileModels;
    Context context;

    public MemberProfileAdapter(ArrayList<MemberProfileModel> memberProfileModels, Context context){
        this.memberProfileModels = memberProfileModels;
        this.context = context;
    }
    @NonNull
    @Override
    public MemberProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_profile,parent, false);
        MemberProfileAdapter.ViewHolder bind = new MemberProfileAdapter.ViewHolder(view);
        return bind;
    }

    @Override
    public void onBindViewHolder(@NonNull MemberProfileAdapter.ViewHolder holder, int position) {
    final MemberProfileModel model = memberProfileModels.get(position);
    holder.member_name.setText(model.getMember_name());
    holder.member_id.setText("ID: "+model.getMember_id());
    holder.gender.setText("Gender\n"+model.getGender());
    holder.admission_date.setText("Admission Date\n"+model.getAdmission_date());
    holder.group_name.setText("Group Name\n"+model.getGroup_name());
    holder.total_savings.setText("Total Savings\n"+model.getTotal_savings());
    String loan, fine;
    loan = model.getLoan_due();
    fine = model.getFine_due();

    if(loan.isEmpty() && fine.isEmpty()){
        holder.member_dues.setText("Fines Due K 0"+model.getFine_due()+"\n"+"Loans due K 0"+model.getLoan_due());
    } else {
        holder.member_dues.setText("Fines Due K "+model.getFine_due()+"\n"+"Loans due K "+model.getLoan_due());

    }

    holder.member_dues.setText("Fines Due K "+model.getFine_due()+"\n"+"Loans due K "+model.getLoan_due());


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        String str_user_role = preferences.getString("user_role", "");
        if (str_user_role.equals("3")){

            holder.linearLayout.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return memberProfileModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView member_name,member_id,gender,admission_date,group_name,total_savings,total_social_fund,member_dues;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            member_name = itemView.findViewById(R.id.member_name);
            member_id = itemView.findViewById(R.id.member_id);
            gender = itemView.findViewById(R.id.gender);
            admission_date = itemView.findViewById(R.id.admission_date);
            group_name = itemView.findViewById(R.id.group_name);
            total_savings = itemView.findViewById(R.id.total_savings);
            total_social_fund = itemView.findViewById(R.id.total_social_fund);
            member_dues = itemView.findViewById(R.id.member_dues);
            linearLayout =  itemView.findViewById(R.id.member_content);


        }
    }
}
