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
import com.retrotech.machies_hogl.models.FacilitatorViewGroupsModel;

import java.util.ArrayList;

public class FacilitatorViewGroupsAdapter extends RecyclerView.Adapter<FacilitatorViewGroupsAdapter.ViewHolder> {
    ArrayList<FacilitatorViewGroupsModel> memberProfileModels;
    Context context;

    public FacilitatorViewGroupsAdapter(Context context, ArrayList<FacilitatorViewGroupsModel> memberProfileModels){
        this.memberProfileModels = memberProfileModels;
        this.context = context;
    }
    @NonNull
    @Override
    public FacilitatorViewGroupsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_group_members,parent, false);
        FacilitatorViewGroupsAdapter.ViewHolder bind = new FacilitatorViewGroupsAdapter.ViewHolder(view);
        return bind;
    }

    @Override
    public void onBindViewHolder(@NonNull FacilitatorViewGroupsAdapter.ViewHolder holder, int position) {
        final FacilitatorViewGroupsModel model = memberProfileModels.get(position);
        holder.group_name_id.setText(model.getGroup_name()+" ID: "+model.getGroup_id());
        holder.member_name.setText(model.getMember_name());
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
        return memberProfileModels.size();
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
