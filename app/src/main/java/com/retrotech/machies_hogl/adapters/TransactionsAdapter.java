package com.retrotech.machies_hogl.adapters;
/*
 * Created by Yosefe27 on 21/09/2022.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.retrotech.machies_hogl.R;
import com.retrotech.machies_hogl.models.MyContributions;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.SingleViewHolder> {

    public static List<MyContributions> listMyContributions;
    private MyContributions currentMyContributions;
    private Context mContext;
    private List<MyContributions> listFullMyContributions;
    View.OnClickListener clickListener;
    // if checkedPosition = -1, there is no default selection
    // if checkedPosition = 0, 1st item is selected by default
    private int checkedPosition = -1;
    int list_size;
    //constructor
    public TransactionsAdapter(Context context, ArrayList<MyContributions> listMyContributions, int list_size) {
        this.mContext = context;
        this.listMyContributions = listMyContributions;
        listFullMyContributions = new ArrayList<>(listMyContributions);
        list_size = list_size;
    }

    public void setClickListener(View.OnClickListener callback) {
        clickListener = callback;
    }
    public void setMyContributions(List<MyContributions> listMyContributions) {
        this.listMyContributions = listMyContributions;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return listMyContributions.size();
    }

    public class SingleViewHolder extends RecyclerView.ViewHolder {

        public TextView initials, fullname, transaction_type_date,amount;
        CircleImageView trans_img;


        public SingleViewHolder(View itemView) {
            super(itemView);

            initials = itemView.findViewById(R.id.initials);
            trans_img = itemView.findViewById(R.id.trans_img);
            fullname = itemView.findViewById(R.id.fullname);
            transaction_type_date = itemView.findViewById(R.id.transaction_type_date);
            amount = itemView.findViewById(R.id.amount);

        }//ends public viewholder

       /* void bind(final MyContributions transactions) {
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
                .inflate(R.layout.list_item_transactions, parent, false);
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SingleViewHolder holder, int position) {
        currentMyContributions = listMyContributions.get(position);
        //holder.bind(listMyContributions.get(position));

        StringBuilder initials = new StringBuilder();
        for (String s : currentMyContributions.getTransaction_type().split(" ")) {
            initials.append(s.charAt(0));
        }
        holder.initials.setText(String.valueOf(initials.toString()));
        holder.fullname.setSelected(true);
        holder.amount.setSelected(true);

        holder.fullname.setText(currentMyContributions.getTransaction_type());
        /*switch (currentMyContributions.getTransaction_type()) {
            case "1":
                holder.fullname.setText("MTN");
                break;
            case "2":
                holder.fullname.setText("Airtel");
                break;
            case "3":
                holder.fullname.setText("Zamtel");
                break;
            case "4":
                holder.fullname.setText("Visa");
                break;
            default:
                holder.fullname.setText("Cash Payment");
                break;
        }*/

        holder.transaction_type_date.setText(currentMyContributions.getTransaction_month() + " - " + currentMyContributions.getTransaction_ref_number());
        holder.amount.setText(String.valueOf("K " + currentMyContributions.getTransaction_amount()));
        //String pay_mode =currentMyContributions.getTransaction_type();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickListener.onClick(view);
            }
        });

    }



}
