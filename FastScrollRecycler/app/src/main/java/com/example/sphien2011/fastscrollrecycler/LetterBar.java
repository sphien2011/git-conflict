package com.example.sphien2011.fastscrollrecycler;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sphien2011 on 30/10/2016.
 */
public class LetterBar extends View {
    public static final int SECTION_SIZE = 27;
    private static final List<String> mListSections = new ArrayList<>(SECTION_SIZE);
    private OnLetterSelectListener mListener;

    private int mTextColor = Color.RED;
    private float mTextSize = 25.0f;
    private float mItemHeight;

    private int mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom;
    private int mContentWith, mContentHeight;

    private float mCenter;
    private static int mSectionSize;
    private TextPaint mTextPaint;

    static {
        mListSections.add("#");
        char character = 'A';
        for (int i = 0; i<SECTION_SIZE - 1; i++) {
            mListSections.add(String.valueOf((char) (character + i)));
        }
        mSectionSize = mListSections.size();
    }

    public LetterBar(Context context) {
        super(context);
        init(null, 0);
    }

    public LetterBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public LetterBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        float y = event.getY();
        switch (action) {
            case MotionEvent.ACTION_UP:
                onSelect(y, true);
                break;
            default:
                onSelect(y, false);
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i<SECTION_SIZE; i++) {
            canvas.drawText(mListSections.get(i), mCenter, mPaddingTop + mItemHeight * (i+1), mTextPaint);
        }
    }

    private void init(AttributeSet attrs, int defStyle){
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.LetterBar, defStyle, 0);

        mTextColor = a.getColor(R.styleable.LetterBar_textColor, mTextColor);
        mTextSize = a.getDimension(R.styleable.LetterBar_textSize, mTextSize);

        a.recycle();

        initTextPaint();

        post(new Runnable() {
            @Override
            public void run() {
                mPaddingLeft = getPaddingLeft();
                mPaddingTop = getPaddingTop();
                mPaddingRight = getPaddingRight();
                mPaddingBottom = getPaddingBottom();

                mContentWith = getWidth() - mPaddingLeft - mPaddingRight;
                mContentHeight = getHeight() - mPaddingTop - mPaddingBottom;
                mCenter = mContentWith / 2.0f;
                mItemHeight = mContentHeight/mSectionSize;
            }
        });
    }

    private void initTextPaint() {
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
    }

    public void setOnLetterSelectListener(OnLetterSelectListener onLetterSelectListener) {
        mListener = onLetterSelectListener;
    }

    private void onSelect(float y, boolean stop) {
        int index = (int) ((y - mPaddingTop) / mContentHeight * mSectionSize);
        if (index < 0) {
            index = 0;
        } else if (index >= mSectionSize) {
            index = mSectionSize - 1;
        }
        if (mListener != null) {
            mListener.onLetterSelect(index, mListSections.get(index), stop);
        }
    }

    public interface OnLetterSelectListener {
        void onLetterSelect(int position, String letter, boolean confirmed);
    }
}
