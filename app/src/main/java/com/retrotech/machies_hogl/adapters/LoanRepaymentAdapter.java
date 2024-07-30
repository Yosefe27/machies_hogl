package com.retrotech.machies_hogl.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.retrotech.machies_hogl.BookWriterLoanRepaymentEdit;
import com.retrotech.machies_hogl.R;
import com.retrotech.machies_hogl.models.LoanRepaymentModel;

import java.util.ArrayList;

public class LoanRepaymentAdapter extends RecyclerView.Adapter<LoanRepaymentAdapter.ViewHolder> {
    ArrayList<LoanRepaymentModel> listLoanRequests;
    private Context context;
    View.OnClickListener clickListener;


    //constructor
    public LoanRepaymentAdapter(Context context, ArrayList<LoanRepaymentModel> listLoanRequests) {
        this.context = context;
        this.listLoanRequests = listLoanRequests;


    }
    public void setClickListener(View.OnClickListener callback) {
        clickListener = callback;
    }
    @NonNull
    @Override
    public LoanRepaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_loan_repayment, parent, false);
        LoanRepaymentAdapter.ViewHolder bind = new LoanRepaymentAdapter.ViewHolder(view);
        return bind;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final  LoanRepaymentModel loanRepaymentModel = listLoanRequests.get(position);
        holder.amount.setText(loanRepaymentModel.getFull_name()+"\n"+" Repayment of K"+loanRepaymentModel.getAmount());
        holder.date_created.setText("On: "+loanRepaymentModel.getDate_created()+" ID: "+loanRepaymentModel.getId());

        holder.edit_loan.setOnClickListener(v -> {

            Intent i = new Intent(context, BookWriterLoanRepaymentEdit.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("tran_amount",loanRepaymentModel.getAmount());
            i.putExtra("tran_month", loanRepaymentModel.getDate_created());
            i.putExtra("contributor_id",loanRepaymentModel.getContributor_id());
            i.putExtra("full_name",loanRepaymentModel.getFull_name());

            context.startActivity(i);


        });

    }

    @Override
    public int getItemCount() {
        return listLoanRequests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView amount, date_created, edit_loan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.amount);
            date_created = itemView.findViewById(R.id.date_created);
            edit_loan = itemView.findViewById(R.id.btn_loan_edit);
        }
    }
}
