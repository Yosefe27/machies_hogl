package com.retrotech.machies_hogl.adapters;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.retrotech.machies_hogl.R;
import com.retrotech.machies_hogl.models.FacilitatorRegisterModel;

import java.util.ArrayList;

public class FacilitatorRegisterAdapter extends RecyclerView.Adapter<FacilitatorRegisterAdapter.ViewHolder> {
ArrayList<FacilitatorRegisterModel> register;
Context context;

    public FacilitatorRegisterAdapter(ArrayList<FacilitatorRegisterModel> register, Context context){
    this.register = register;
    this.context = context;

}
    @NonNull
    @Override
    public FacilitatorRegisterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_group_members, parent, false);
        FacilitatorRegisterAdapter.ViewHolder viewHolder = new FacilitatorRegisterAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FacilitatorRegisterAdapter.ViewHolder holder, int position) {
       final FacilitatorRegisterModel finalRegister = register.get(position);
        holder.group_name_id.setText(finalRegister.getGroup_name()+" ID: "+finalRegister.getGroup_id());
        holder.member_name.setText(finalRegister.getRegister());
//        holder.member_id.setText("ID: "+model.getMember_id());
//        holder.member_contribution.setText("Member Contribution K"+model.getMember_contribution());

        holder.arrow.setOnClickListener(view -> {
            if (holder.hiddenView.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                holder.hiddenView.setVisibility(View.GONE);
                holder.arrow.setImageResource(R.drawable.ic_expand_more);
            }
            else {
                TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                holder.hiddenView.setVisibility(View.VISIBLE);
                holder.arrow.setImageResource(R.drawable.ic_expand_less);
            }
        });


    }

    @Override
    public int getItemCount() {
        return register.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView group_name_id,group_id,member_name,member_id,member_contribution;
        ImageButton arrow;
        LinearLayout hiddenView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            group_name_id = itemView.findViewById(R.id.group_name_id);
            member_name = itemView.findViewById(R.id.member_name);
//            member_id = itemView.findViewById(R.id.member_id);
//            member_contribution = itemView.findViewById(R.id.member_contribution);
            cardView = itemView.findViewById(R.id.base_cardview);
            arrow = itemView.findViewById(R.id.arrow_button);
            hiddenView = itemView.findViewById(R.id.hidden_view);

        }
    }
}
