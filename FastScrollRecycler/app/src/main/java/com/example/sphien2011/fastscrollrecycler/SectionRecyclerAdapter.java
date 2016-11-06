package com.example.sphien2011.fastscrollrecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by sphien2011 on 06/11/2016.
 */
public class SectionRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = SectionRecyclerAdapter.class.getSimpleName();
    private RecyclerView.Adapter mBaseAdapter;
    private int SECTION_TYPE = 0;
    private boolean mValid = true;

    private SparseArray<Section> mSections = new SparseArray<>();
    private SparseArray<Integer> mKeyPositionMap = new SparseArray<>();

    public SectionRecyclerAdapter(Context context, final RecyclerView.Adapter mBaseAdapter) {
        this.mBaseAdapter = mBaseAdapter;
        if (mBaseAdapter instanceof SectionRecyclerDelegate) {
            setSections((((SectionRecyclerDelegate) mBaseAdapter).getSections()));
        } else {
            Log.e(TAG, "The base adapter has not implement the SectionRecyclerDelegate");
        }

        mBaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                mValid = mBaseAdapter.getItemCount() > 0;
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                mValid = mBaseAdapter.getItemCount() > 0;
                notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                mValid = mBaseAdapter.getItemCount() > 0;
                notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                mValid = mBaseAdapter.getItemCount() > 0;
                notifyItemRangeRemoved(positionStart, itemCount);
            }
        });
    }

    public boolean isSectionHeaderPosition(int position) {
        return mSections.get(position) != null;
    }

    public int sectionPositionToPosition(int sectionPosition) {
        if (isSectionHeaderPosition(sectionPosition)) {
            return RecyclerView.NO_POSITION;
        }
        int offset = 0;
        for (int i = 0; i<mSections.size(); i++) {
            if (mSections.valueAt(i).sectionedPosition > sectionPosition) {
                break;
            }

            --offset;
        }
        return sectionPosition + offset;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SECTION_TYPE) {
            Context context = parent.getContext();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_section, parent, false);
            return new SectionViewHolder(view);
        } else {
            return mBaseAdapter.onCreateViewHolder(parent, viewType - 1);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isSectionHeaderPosition(position)) {
            ((SectionViewHolder) holder).txtSection.setText(mSections.get(position).section);
        } else {
            mBaseAdapter.onBindViewHolder(holder, sectionPositionToPosition(position));
        }
    }

    @Override
    public int getItemCount() {
        return (mValid ? mBaseAdapter.getItemCount() + mSections.size() : 0);
    }

    @Override
    public long getItemId(int position) {
        return isSectionHeaderPosition(position) ? Integer.MAX_VALUE - mSections.indexOfKey(position)
                : mBaseAdapter.getItemId(sectionPositionToPosition(position));
    }

    public static class SectionViewHolder extends RecyclerView.ViewHolder {
        private TextView txtSection;

        public SectionViewHolder(View itemView) {
            super(itemView);
            txtSection = (TextView) itemView.findViewById(R.id.txt_section);
        }
    }

    public static class Section {
        int firstPosition;
        int sectionedPosition;
        CharSequence section;

        public Section(int firstPosition, CharSequence section) {
            this.firstPosition = firstPosition;
            this.section = section;
        }

        public CharSequence getSection() {
            return section;
        }
    }

    public Integer getSectionPosition(int asciiPosition) {
        return mKeyPositionMap.get(asciiPosition);
    }

    public void setSections(List<Section> sections) {
        mSections.clear();
        Collections.sort(sections, new Comparator<Section>() {
            @Override
            public int compare(Section section, Section t1) {
                return (section.firstPosition == t1.firstPosition) ? 0 : ((section.firstPosition < t1.firstPosition) ? -1 : 1);
            }
        });

        int offset = 0;
        for (Section section : sections) {
            section.sectionedPosition = section.firstPosition + offset;
            mSections.append(section.sectionedPosition, section);
            ++offset;
            String title = section.section.toString();
            if (title.charAt(0) >= 'A' && title.charAt(0) <= 'Z') {
                mKeyPositionMap.put(title.charAt(0) - 'A' + 1, section.sectionedPosition);
            } else {
                mKeyPositionMap.put(0, 0);
            }
        }

        notifyDataSetChanged();
    }

    public interface SectionRecyclerDelegate {
        List<Section> mSections = new ArrayList<>();
        List<Section> getSections();
    }
}
