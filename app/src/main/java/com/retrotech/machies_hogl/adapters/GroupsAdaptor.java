package com.retrotech.machies_hogl.adapters;
/*
 * Created by Yosefe27 on 6/03/2019.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.retrotech.machies_hogl.R;
import com.retrotech.machies_hogl.models.Groups;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class GroupsAdaptor extends RecyclerView.Adapter<GroupsAdaptor.SingleViewHolder> {

    public static List<Groups> listGroups;
    private Groups currentGroups;
    private Context mContext;
    private List<Groups> listFullGroups;
    View.OnClickListener clickListener;
    // if checkedPosition = -1, there is no default selection
    // if checkedPosition = 0, 1st item is selected by default
    private int checkedPosition = -1;

    //constructor
    public GroupsAdaptor(Context context, ArrayList<Groups> listGroups) {
        this.mContext = context;
        this.listGroups = listGroups;
        listFullGroups = new ArrayList<>(listGroups);

    }

    public void setClickListener(View.OnClickListener callback) {
        clickListener = callback;
    }

    public void setGroups(List<Groups> listGroups) {
        this.listGroups = listGroups;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return listGroups.size();
    }

    public class SingleViewHolder extends RecyclerView.ViewHolder {

        public TextView group_name, member_role, group_id;
        CircleImageView group_img;
        ImageView member_status;


        public SingleViewHolder(View itemView) {
            super(itemView);
            group_id = itemView.findViewById(R.id.group_IDD);
            group_img = itemView.findViewById(R.id.group_img);
            group_name = itemView.findViewById(R.id.group_name);


        }//ends public viewholder

       /* void bind(final Groups transactions) {
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
                .inflate(R.layout.list_item_groups, parent, false);
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SingleViewHolder holder, int position) {
        currentGroups = listGroups.get(position);
        //holder.bind(listGroups.get(position));

        holder.group_name.setSelected(true);
        holder.group_id.setText(String.valueOf("ID: "+currentGroups.getId()));
        holder.group_name.setText(String.valueOf(currentGroups.getGroup_name()));//initials.toString()));



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickListener.onClick(view);
            }
        });
    }
}
