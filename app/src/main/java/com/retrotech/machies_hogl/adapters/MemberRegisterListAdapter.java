package com.retrotech.machies_hogl.adapters;
/*
 * Created by Yosefe27 on 21/09/2022.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.retrotech.machies_hogl.R;
import com.retrotech.machies_hogl.models.MemberRegisterListModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;



public class MemberRegisterListAdapter extends RecyclerView.Adapter<MemberRegisterListAdapter.SingleViewHolder> {

    public static List<MemberRegisterListModel> listMembers;
    private MemberRegisterListModel currentMembers;
    private Context mContext;

    View.OnClickListener clickListener;

    private int checkedPosition = -1;

    //constructor
    public MemberRegisterListAdapter(Context context, ArrayList<MemberRegisterListModel> listMembers) {
        this.mContext = context;
        this.listMembers = listMembers;


    }

    public void setClickListener(View.OnClickListener callback) {
        clickListener = callback;
    }

    public void setMembers(List<MemberRegisterListModel> listMembers) {
        this.listMembers = listMembers;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return listMembers.size();
    }

    public class SingleViewHolder extends RecyclerView.ViewHolder {

        public TextView member_name, register_status, date,member_id;
        CircleImageView member_img;
        ImageView member_status;


        public SingleViewHolder(View itemView) {
            super(itemView);

            member_img = itemView.findViewById(R.id.member_img);
            member_name = itemView.findViewById(R.id.member_name);
            register_status = itemView.findViewById(R.id.register_status);
            member_status = itemView.findViewById(R.id.member_status);
            member_id = itemView.findViewById(R.id.member_id);



        }
    }

    @Override
    public SingleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_register, parent, false);
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SingleViewHolder holder, int position) {
        currentMembers = listMembers.get(position);

        String full_name = currentMembers.getFirstname() + " " + currentMembers.getLastname();
        StringBuilder initials = new StringBuilder();
      
        holder.member_name.setSelected(true);
        holder.member_name.setText(currentMembers.getFirstname()+" "+currentMembers.getLastname());
        holder.register_status.setText("Meeting held on: "+currentMembers.getDate()+" Status: "+currentMembers.getRegister());
        holder.member_id.setText("ID: "+currentMembers.getId());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickListener.onClick(view);
            }
        });
    }


}
