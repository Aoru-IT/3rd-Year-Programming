package com.example.acs_loanapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;

public class ApprovedFragment extends Fragment {

    private RecyclerView recyclerView;
    private LoanAdapter loanAdapter;
    private DatabaseHelper databaseHelper;

    public ApprovedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_approved, container, false);

        recyclerView = view.findViewById(R.id.ApprovedLoans);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseHelper = new DatabaseHelper(getActivity());

        Cursor cursor = databaseHelper.ViewAllApprovedLoans();

        loanAdapter = new LoanAdapter(cursor);
        recyclerView.setAdapter(loanAdapter);

        return view;
    }

    private class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.LoanViewHolder> {

        private Cursor cursor;

        public LoanAdapter(Cursor cursor) {
            this.cursor = cursor;
        }

        @Override
        public LoanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.loan_records, parent, false);
            return new LoanViewHolder(view);
        }

        @Override
        public void onBindViewHolder(LoanViewHolder holder, int position) {
            if (cursor != null && cursor.moveToPosition(position)) {
                // Get the column indices
                int empIDIndex = cursor.getColumnIndex("EmployeeID");
                int loanTypeIndex = cursor.getColumnIndex("LoanType");
                int loanAmountIndex = cursor.getColumnIndex("LoanAmount");
                int loanStatusIndex = cursor.getColumnIndex("LoanStatus");

                if (empIDIndex >= 0 && loanAmountIndex >= 0 && loanStatusIndex >= 0) {
                    String empID = cursor.getString(empIDIndex);
                    String loanType = cursor.getString(loanTypeIndex);
                    double loanAmount = cursor.getDouble(loanAmountIndex);
                    String loanStatus = cursor.getString(loanStatusIndex);

                    holder.tvEmployeeID.setText(empID);
                    holder.tvLoanType.setText(loanType);
                    holder.tvLoanAmount.setText(String.format("%.2f", loanAmount));
                    holder.tvLoanStatus.setText(loanStatus);

                    holder.btnView.setOnClickListener(v -> {
                        Bundle args = new Bundle();
                        args.putString("employeeID", empID);
                        args.putString("loanType", loanType);
                        args.putString("loanStatus", loanStatus);
                        LoanApplicationFragment loanApplicationFragment = new LoanApplicationFragment();
                        loanApplicationFragment.setArguments(args);

                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, loanApplicationFragment);
                        transaction.addToBackStack(null); // Add to back stack to allow navigation back
                        transaction.commit();
                    });
                } else {
                    Toast.makeText(getActivity(), "Column names not found in the database", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public int getItemCount() {
            return cursor != null ? cursor.getCount() : 0;
        }

        class LoanViewHolder extends RecyclerView.ViewHolder {

            TextView tvEmployeeID, tvLoanType, tvLoanAmount, tvLoanStatus;
            Button btnView;

            public LoanViewHolder(View itemView) {
                super(itemView);
                tvEmployeeID = itemView.findViewById(R.id.tvEmployeeID);
                tvLoanType = itemView.findViewById(R.id.tvLoanType);
                tvLoanAmount = itemView.findViewById(R.id.tvLoanAmount);
                tvLoanStatus = itemView.findViewById(R.id.tvLoanStatus);
                btnView = itemView.findViewById(R.id.btnView);
            }
        }
    }
}