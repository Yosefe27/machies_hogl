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
import com.retrotech.machies_hogl.models.Members;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;



public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.SingleViewHolder> {

    public static List<Members> listMembers;
    private Members currentMembers;
    private Context mContext;
    private List<Members> listFullMembers;
    View.OnClickListener clickListener;
    private int checkedPosition = -1;

    //constructor
    public MembersAdapter(Context context, ArrayList<Members> listMembers) {
        this.mContext = context;
        this.listMembers = listMembers;
        listFullMembers = new ArrayList<>(listMembers);

    }

    public void setClickListener(View.OnClickListener callback) {
        clickListener = callback;
    }

    public void setMembers(List<Members> listMembers) {
        this.listMembers = listMembers;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return listMembers.size();
    }

    public class SingleViewHolder extends RecyclerView.ViewHolder {

        public TextView member_name, member_role, member_id;
        CircleImageView member_img;
        ImageView member_status;


        public SingleViewHolder(View itemView) {
            super(itemView);

            member_id = itemView.findViewById(R.id.member_ID);
            member_img = itemView.findViewById(R.id.member_img);
            member_name = itemView.findViewById(R.id.member_name);
            member_role = itemView.findViewById(R.id.member_role);
            member_status = itemView.findViewById(R.id.member_status);


        }//ends public viewholder

       /* void bind(final Members transactions) {
            initials.setText(transactions.getInitials());
            fullname.setText(transactions.getFullname());
            transaction_type_date.setText(transactions.getTransaction_type());
            amount.setText(transactions.getTransaction_amount());
//
//
        }*/
    }

    @Override
    public SingleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //inflating the layouts of each row
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_group_members, parent, false);
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SingleViewHolder holder, int position) {
        currentMembers = listMembers.get(position);
        //holder.bind(listMembers.get(position));
        String full_name = currentMembers.getFirstname() + " " + currentMembers.getLastname();
        StringBuilder initials = new StringBuilder();
        holder.member_id.setText("ID: "+currentMembers.getId());
        holder.member_name.setSelected(true);
        holder.member_name.setText(String.valueOf(full_name));//initials.toString()));

        holder.member_role.setSelected(true);
        if(currentMembers.getUser_role().equals("Facilitator")){
            holder.member_role.setText("Member");
        }
        else if(currentMembers.getUser_role().equals("Book Writer")) {
            holder.member_role.setText("Member");
        }
        else {
            holder.member_role.setText("Member");
        }
        String img_name = currentMembers.getNrc().replace("/", "");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickListener.onClick(view);
            }
        });
    }


}
