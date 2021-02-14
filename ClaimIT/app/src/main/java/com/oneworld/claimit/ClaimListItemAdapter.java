package com.oneworld.claimit;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClaimListItemAdapter extends RecyclerView.Adapter<ClaimListItemAdapter.ClaimItemViewHolder> {

    private static final String TAG = "ClaimListItemAdapter";

    private ArrayList<ClaimItem> claimItemList;
    private CurrentBalance currentBalance;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ClaimItemViewHolder extends RecyclerView.ViewHolder {
        private final ImageButton imageButton;
        private final CurrentBalance currentBalance;

        public ClaimItemViewHolder(View v, CurrentBalance currentBalance) {
            super(v);
            this.currentBalance = currentBalance;
            imageButton = (ImageButton) v.findViewById(R.id.imageButton);
            // Define click listener for the ViewHolder's View.
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                    if (currentBalance.getBalance() == 0) {
                        Toast.makeText(v.getContext(), "Not enough balance to claim a prize", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(v.getContext(), "Claimed a prize", Toast.LENGTH_SHORT).show();
                    currentBalance.decreaseBalance(1);
                }
            });

        }

        public ImageButton getImageButton() {
            return imageButton;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param claimItemList String[] containing the data to populate views to be used by RecyclerView.
     */
    public ClaimListItemAdapter(CurrentBalance currentBalance, ArrayList<ClaimItem> claimItemList) {
        this.claimItemList = claimItemList;
        this.currentBalance = currentBalance;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ClaimListItemAdapter.ClaimItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.claim_list_item, viewGroup, false);

        return new ClaimListItemAdapter.ClaimItemViewHolder(v, currentBalance);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ClaimListItemAdapter.ClaimItemViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getImageButton().setImageBitmap(claimItemList.get(position).getIBitmap());
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return claimItemList.size();
    }

    public void deleteItem(int index) {
        claimItemList.remove(index);
    }
}


