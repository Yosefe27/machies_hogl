package com.retrotech.machies_hogl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retrotech.machies_hogl.R;
import com.retrotech.machies_hogl.models.NewLoanRequestModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewLoanRequestAdapter extends RecyclerView.Adapter<NewLoanRequestAdapter.ViewHolder> {

    public static ArrayList<NewLoanRequestModel> listMembers;
    private NewLoanRequestModel currentMembers;
    private Context mContext;
    View.OnClickListener clickListener;
    //constructor
    public NewLoanRequestAdapter(Context context, ArrayList<NewLoanRequestModel> listMembers) {
        this.mContext = context;
        this.listMembers = listMembers;

    }

    public void setClickListener(View.OnClickListener callback) {
        clickListener = callback;
    }

    public void setMembers(ArrayList<NewLoanRequestModel> listMembers) {
        this.listMembers = listMembers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewLoanRequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_loan_members_dialog, parent, false);
        NewLoanRequestAdapter.ViewHolder holder =    new NewLoanRequestAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewLoanRequestAdapter.ViewHolder holder, int position) {
        currentMembers = listMembers.get(position);

        holder.member_name.setText(currentMembers.getMember_name());//initials.toString()));
        holder.member_id.setText(currentMembers.getMember_id());



        holder.itemView.setOnClickListener(view -> clickListener.onClick(view));
    }

    @Override
    public int getItemCount() {
        return listMembers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView member_name, member_role, member_id;
        CircleImageView member_img;
        ImageView member_status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            member_id = itemView.findViewById(R.id.member_ID);
            member_img = itemView.findViewById(R.id.member_img);
            member_name = itemView.findViewById(R.id.member_name);
            member_role = itemView.findViewById(R.id.member_role);
            member_status = itemView.findViewById(R.id.member_status);
        }
    }
}
