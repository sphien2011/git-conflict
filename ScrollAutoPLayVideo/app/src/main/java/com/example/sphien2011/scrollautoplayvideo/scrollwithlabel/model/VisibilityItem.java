package com.example.sphien2011.scrollautoplayvideo.scrollwithlabel.model;

import android.graphics.Rect;
import android.view.View;

import com.example.sphien2011.scrollautoplayvideo.R;
import com.example.sphien2011.scrollautoplayvideo.scrollwithlabel.adapter.ViewHolder;
import com.volokh.danylo.visibility_utils.items.ListItem;

/**
 * Created by sphien2011 on 08/10/2016.
 */
public class VisibilityItem implements ListItem {
    private final String mTitle;
    private final Rect mCurrentViewRect = new Rect();
//    private ItemCallback mItemCallback;

//    public VisibilityItem(String mTitle, ItemCallback mItemCallback) {
//        this.mTitle = mTitle;
//        this.mItemCallback = mItemCallback;
//    }


    public VisibilityItem(String mTitle) {
        this.mTitle = mTitle;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        int color = holder.itemView.getResources().getColor(R.color.colorAccent);
        holder.positionView.setText("Position: " + mTitle);
        holder.itemView.setBackgroundColor(color);
    }

    private boolean viewIsPartiallyHiddenBottom(int height) {
        return mCurrentViewRect.bottom > 0 && mCurrentViewRect.bottom < height;
    }

    private boolean viewIsPartiallyHiddenTop() {
        return mCurrentViewRect.top > 0;
    }

    @Override
    public int getVisibilityPercents(View view) {
        int percents = 100;
        view.getLocalVisibleRect(mCurrentViewRect);

        int height = view.getHeight();

        if (viewIsPartiallyHiddenTop()) {
            percents = (height - mCurrentViewRect.top) * 100 / height;
        } else if (viewIsPartiallyHiddenBottom(height)) {
            percents = mCurrentViewRect.bottom * 100 / height;
        }
        return percents;
    }

    @Override
    public void setActive(View newActiveView, int newActiveViewPosition) {
        final View whiteView = newActiveView.findViewById(R.id.white_view);

        whiteView.animate()
                .alpha(1f)
                .setDuration(500)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        whiteView.animate()
                                .alpha(0f)
                                .setDuration(500)
                                .start();
                    }
                }).start();
//        mItemCallback.makeToast("New Active View at Position " + newActiveViewPosition);
//        mItemCallback.onActiveViewChangedActive(newActiveView, newActiveViewPosition);

    }


    @Override
    public void deactivate(View currentView, int position) {
//        mItemCallback.makeToast("Deactivate View");
    }


//    public interface ItemCallback {
//        void makeToast(String text);
////        void onActiveViewChangedActive(View newActiveView, int newActiveViewPosition);
//    }
}
