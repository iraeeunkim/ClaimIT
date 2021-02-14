package com.oneworld.claimit;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CheckListItemAdapter extends
        RecyclerView.Adapter<CheckListItemAdapter.CheckItemViewHolder> {

    private static final String TAG = "CheckListItemAdapter";

    final private ArrayList<ToDoList> toDoList;
    final private CurrentBalance currentBalance;


    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class CheckItemViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox checkBox;
        private final CurrentBalance currentBalance;

        public CheckItemViewHolder(View v, CurrentBalance currentBalance) {
            super(v);
            this.currentBalance = currentBalance;
            checkBox = (CheckBox) v.findViewById(R.id.checkItem);

            // Define click listener for the ViewHolder's View.
            checkBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (checkBox.isChecked()) {
                        Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                        currentBalance.increaseBalance(1);
                        Toast.makeText(v.getContext(), checkBox.getText(), Toast.LENGTH_SHORT).show();
                        checkBox.setClickable(false);
                    }
                }
            });
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param toDoList String[] containing the data to populate views to be used by RecyclerView.
     */
    public CheckListItemAdapter(CurrentBalance currentBalance, ArrayList<ToDoList> toDoList) {
        this.toDoList = toDoList;
        this.currentBalance = currentBalance;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public CheckItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.check_list_item, viewGroup, false);

        return new CheckItemViewHolder(v, currentBalance);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CheckItemViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getCheckBox().setText(toDoList.get(position).getToDoItem());
        viewHolder.getCheckBox().setChecked(false);
        viewHolder.getCheckBox().setClickable(true);
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    public void deleteItem(int index) {
        toDoList.remove(index);
    }
}
