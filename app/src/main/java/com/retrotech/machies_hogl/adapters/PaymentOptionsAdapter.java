package com.retrotech.machies_hogl.adapters;
/*
 * Created by Yosefe27 on 21/09/2022.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.retrotech.machies_hogl.R;
import com.retrotech.machies_hogl.models.PaymentOptions;

import java.util.ArrayList;
import java.util.List;


public class PaymentOptionsAdapter extends RecyclerView.Adapter<PaymentOptionsAdapter.ViewHolder> implements Filterable {

    public static List<PaymentOptions> listPaymentOptions;
    private PaymentOptions currentPaymentOptions;
    private Context mContext;
    private CustomersAdapterListener listener;
    private List<PaymentOptions> itemListFull;

    //constructor
    public PaymentOptionsAdapter(Context context, ArrayList<PaymentOptions> listPaymentOptions){
        this.mContext = context;
        this.listPaymentOptions = listPaymentOptions;
        itemListFull = new ArrayList<>(listPaymentOptions);
    }

    public void setPaymentOptions(List<PaymentOptions> listPaymentOptions){
        this.listPaymentOptions = listPaymentOptions;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listPaymentOptions.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //inflating the layouts of each row
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_payment_options, parent, false);
        return new ViewHolder(view);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView item_name, item_description;
        public ImageView item_image;


        public ViewHolder(View view) {
            super(view);
            item_name = itemView.findViewById(R.id.item_name);
            item_image = itemView.findViewById(R.id.item_image);
        }
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //final PaymentOptions customer = customerListFiltered.get(position);
        currentPaymentOptions = listPaymentOptions.get(position);
//        holder.item_name.setSelected(true);
        holder.item_name.setText(currentPaymentOptions.getPayment_option_name());
        holder.item_image.setImageResource(R.drawable.ic_user);
           }

    public interface CustomersAdapterListener {
        void onCustomerSelected(PaymentOptions customer);
    }


    @Override
    public Filter getFilter() {
        return productFilter;
    }

    private Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PaymentOptions> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(itemListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (PaymentOptions item : itemListFull) {
                    if (item.getPayment_option_name().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listPaymentOptions.clear();
            listPaymentOptions.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


}
