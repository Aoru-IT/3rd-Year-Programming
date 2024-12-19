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

public class AllRecordsFragment extends Fragment {

    private RecyclerView recyclerView;
    private LoanAdapter loanAdapter;
    private DatabaseHelper databaseHelper;

    public AllRecordsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_records, container, false);

        recyclerView = view.findViewById(R.id.AllLoans);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseHelper = new DatabaseHelper(getActivity());

        Cursor cursor = databaseHelper.ViewAllRecordDetails();
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
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.user_records, parent, false);
            return new LoanViewHolder(view);
        }

        @Override
        public void onBindViewHolder(LoanViewHolder holder, int position) {
            if (cursor != null && cursor.moveToPosition(position)) {
                int empIDIndex = cursor.getColumnIndex("EmployeeID");
                int lastNameIndex = cursor.getColumnIndex("LastName");
                int firstNameIndex = cursor.getColumnIndex("FirstName");
                int middleInitialIndex = cursor.getColumnIndex("MiddleInitial");
                int dateHiredIndex = cursor.getColumnIndex("DateHired");

                if (empIDIndex >= 0 && lastNameIndex >= 0 && firstNameIndex >= 0 && middleInitialIndex >= 0 && dateHiredIndex >= 0) {
                    String empID = cursor.getString(empIDIndex);
                    String lastName = cursor.getString(lastNameIndex);
                    String firstName= cursor.getString(firstNameIndex);
                    String middleInitial = cursor.getString(middleInitialIndex);
                    String dateHired = cursor.getString(dateHiredIndex);

                    holder.tvEmployeeID.setText(empID);
                    holder.tvLastName.setText(lastName);
                    holder.tvFirstName.setText(firstName);
                    holder.tvMiddleInitial.setText(middleInitial);
                    holder.tvDateHired.setText(dateHired);

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

            TextView tvEmployeeID, tvLastName, tvFirstName, tvMiddleInitial, tvDateHired;

            public LoanViewHolder(View itemView) {
                super(itemView);
                tvEmployeeID = itemView.findViewById(R.id.tvEmployeeID);
                tvLastName = itemView.findViewById(R.id.tvLastName);
                tvFirstName = itemView.findViewById(R.id.tvFirstName);
                tvMiddleInitial = itemView.findViewById(R.id.tvMiddleInitial);
                tvDateHired = itemView.findViewById(R.id.tvDateHired);
            }
        }
    }
}