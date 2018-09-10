package com.hap.trip.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hap.trip.R;

import javax.annotation.Nonnull;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luis on 6/18/18.
 */

public class EmptyScreenView extends LinearLayout {
    @BindView(R.id.info_icon)
    AppCompatImageView infoIcon;
    @BindView(R.id.info_header)
    AppCompatTextView infoHeader;
    @BindView(R.id.info_subheader)
    AppCompatTextView infoSubHeader;

    private IconVisibility iconVisibility;
    private String infoHeaderText;
    private String infoSubHeaderText;

    public EmptyScreenView(Context context) {
        this(context, null);
    }

    public EmptyScreenView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyScreenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null) {
            return;
        }
        final View view = inflater.inflate(R.layout.empty_screen_view, this);
        ButterKnife.bind(this, view);

        final TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.EmptyScreenViewAttrs, 0, 0);
        if (typedArray != null) {
            setupAttrs(typedArray);
            setupValues();
        }
    }

    private void setupAttrs(@Nonnull final TypedArray typedArray) {
        final int visibility = typedArray.getInteger(R.styleable.EmptyScreenViewAttrs_iconVisibility, IconVisibility.VISIBLE.visibility);
        iconVisibility = IconVisibility.fromVisibility(visibility);
        infoHeaderText = typedArray.getString(R.styleable.EmptyScreenViewAttrs_infoHeader);
        infoSubHeaderText = typedArray.getString(R.styleable.EmptyScreenViewAttrs_infoSubHeader);
    }

    private void setupValues() {
        setIconVisibility(iconVisibility);
        infoHeader.setText(infoHeaderText);
        infoSubHeader.setText(infoSubHeaderText);
    }

    public void setInfoHeader(final String header) {
        infoHeader.setText(header);
    }

    public void setInfoHeader(final int headerId) {
        infoHeader.setText(headerId);
    }

    public void setInfoSubHeader(final String subHeader) {
        infoSubHeader.setText(subHeader);
    }

    public void setInfoSubHeader(final int subHeaderId) {
        infoSubHeader.setText(subHeaderId);
    }

    private void setIconVisibility(final IconVisibility visibility) {
        switch (visibility) {
            case INVISIBLE:
                infoIcon.setVisibility(INVISIBLE);
                break;
            case VISIBLE:
                infoIcon.setVisibility(VISIBLE);
                break;
            case GONE:
                infoIcon.setVisibility(GONE);
                break;
        }
    }

    public enum IconVisibility {
        INVISIBLE(0),
        VISIBLE(1),
        GONE(2);

        private final int visibility;

        IconVisibility(int visibility) {
            this.visibility = visibility;
        }

        public static IconVisibility fromVisibility(final int visibility) {
            IconVisibility newVisibility = VISIBLE;

            for (final IconVisibility currentVisibility : values()) {
                if (currentVisibility.visibility == visibility) {
                    newVisibility = currentVisibility;
                    break;
                }
            }

            return newVisibility;
        }
    }
}
