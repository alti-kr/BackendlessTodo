package com.gmail.sergii_tymofieiev.backendlesstodo.gui;

/**
 * @author Sergii Tymofieiev on 05.04.2018.
 */


import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;


public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerItemTouchHelperListener listener;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (((ITouchHelperViewHolder) viewHolder).isSwipeable()) {
            return super.getSwipeDirs(recyclerView, viewHolder);
        }
        return 0;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            final View foregroundView = ((ITouchHelperViewHolder) viewHolder).getViewForeground();

            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((ITouchHelperViewHolder) viewHolder).getViewForeground();
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = ((ITouchHelperViewHolder) viewHolder).getViewForeground();
        getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((ITouchHelperViewHolder) viewHolder).getViewForeground();

        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(((ITouchHelperViewHolder) viewHolder).getItemData(), viewHolder.getAdapterPosition());
    }


    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    public interface RecyclerItemTouchHelperListener<T extends Object> {
        void onSwiped(T itemData, int position);
    }

    public interface ITouchHelperViewHolder {
        boolean isSwipeable();

        View getViewForeground();

        <T extends Object> T getItemData();

    }


    public static abstract class TouchHelperViewHolder<T extends Object>
            extends RecyclerView.ViewHolder implements ITouchHelperViewHolder {
        public T data;
        View foregroundView;
        boolean isSwipeable;

        public TouchHelperViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public T getItemData() {
            return data;
        }

        @Override
        public View getViewForeground() {
            return foregroundView;
        }

        @Override
        public boolean isSwipeable() {
            return isSwipeable;
        }
    }
}