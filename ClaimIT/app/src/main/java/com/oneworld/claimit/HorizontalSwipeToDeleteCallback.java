package com.oneworld.claimit;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class HorizontalSwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    private ClaimListItemAdapter mAdapter;
    public HorizontalSwipeToDeleteCallback(RecyclerView.Adapter adapter) {
        super(0,ItemTouchHelper.UP | ItemTouchHelper.DOWN);
        mAdapter = (ClaimListItemAdapter) adapter;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        mAdapter.deleteItem(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView,
                          RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        final int fromPos = viewHolder.getAdapterPosition();
        final int toPos = target.getAdapterPosition();
        // do nothing
        return true;// true if moved, false otherwise
    }
}
