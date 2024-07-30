package com.retrotech.machies_hogl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retrotech.machies_hogl.R;
import com.retrotech.machies_hogl.models.ViewTotalFinesModel;

import java.util.ArrayList;

public class ViewTotalFinesAdapter extends RecyclerView.Adapter<ViewTotalFinesAdapter.ViewHolder> {
    ArrayList<ViewTotalFinesModel> viewTotalFinesModels;
    Context context;
    View.OnClickListener clickListener;

   public ViewTotalFinesAdapter(ArrayList<ViewTotalFinesModel> viewTotalFinesModels, Context context){
       this.viewTotalFinesModels = viewTotalFinesModels;
       this.context = context;
   }
    public void setClickListener(View.OnClickListener callback) {
        clickListener = callback;
    }

    @NonNull
    @Override
    public ViewTotalFinesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view_fines,parent, false);
       ViewTotalFinesAdapter.ViewHolder bind = new ViewTotalFinesAdapter.ViewHolder(view);
        return bind;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewTotalFinesAdapter.ViewHolder holder, int position) {
final ViewTotalFinesModel model = viewTotalFinesModels.get(position);
    holder.full_name.setText(model.getFull_name());
    holder.member_id.setText("ID: "+model.getMember_id());
    holder.charge_amount.setText("Charge K"+model.getCharge_amount());
    holder.post_amount.setText("Payment K"+model.getPost_amount());
    holder.fine_balance.setText("Balance K"+model.getFine_balance());
    }

    @Override
    public int getItemCount() {
        return viewTotalFinesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView full_name,member_id,charge_amount,post_amount,fine_balance;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            full_name = itemView.findViewById(R.id.full_name);
            member_id = itemView.findViewById(R.id.member_id);
            charge_amount = itemView.findViewById(R.id.charge_amount);
            post_amount = itemView.findViewById(R.id.post_amount);
            fine_balance = itemView.findViewById(R.id.fine_balance);
        }
    }
}
