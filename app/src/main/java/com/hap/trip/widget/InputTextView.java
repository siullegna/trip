package com.hap.trip.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hap.trip.R;
import com.hap.trip.util.StringHelper;

import javax.annotation.Nonnull;

/**
 * Created by luis on 6/25/18.
 */

public class InputTextView extends LinearLayout {
    private final AppCompatTextView inputHint;
    private final AppCompatTextView inputMessage;
    private final View inputDivider;

    private String hint;
    private String mainHint;
    private int colorError;
    private int colorNormal;
    private InputTextType inputTextType;

    private String text = null;
    private long date = -1;
    private int adultCount = 1; // default value to 1
    private int childrenCount = -1;
    private int infantCount = -1;

    public InputTextView(Context context) {
        this(context, null);
    }

    public InputTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null) {
            inputHint = null;
            inputMessage = null;
            inputDivider = null;
            return;
        }
        inflater.inflate(R.layout.input_text_view, this);

        inputHint = findViewById(R.id.input_hint);
        inputMessage = findViewById(R.id.input_message);
        inputDivider = findViewById(R.id.input_divider);

        final TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.InputTextViewAttrs, 0, 0);
        if (typedArray != null) {
            setupAttrs(typedArray);
            setupValues();
        }
    }

    private void setupAttrs(@Nonnull final TypedArray typedArray) {
        hint = typedArray.getString(R.styleable.InputTextViewAttrs_hint);
        mainHint = typedArray.getString(R.styleable.InputTextViewAttrs_mainHint);
        colorError = typedArray.getColor(R.styleable.InputTextViewAttrs_colorInputError, ContextCompat.getColor(getContext(), R.color.colorDividerError));
        colorNormal = typedArray.getColor(R.styleable.InputTextViewAttrs_colorInputNormal, ContextCompat.getColor(getContext(), R.color.colorDivider));
        final int inputType = typedArray.getInteger(R.styleable.InputTextViewAttrs_inputType, InputTextType.TEXT.type);
        inputTextType = InputTextType.fromType(inputType);
    }

    private void setupValues() {
        inputHint.setText(hint);
        inputMessage.setText(mainHint);
        inputMessage.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryText));
        inputDivider.setBackgroundColor(colorNormal);
    }

    public void resetValues() {
        setupValues();
    }

    public boolean isValidInput() {
        boolean isValidInput = false;

        switch (inputTextType) {
            case TEXT:
                isValidInput = !TextUtils.isEmpty(text);
                break;
            case DATE:
                isValidInput = date != -1;
                break;
            case NUMBER:
                isValidInput = childrenCount != -1 && infantCount != -1;
                break;
        }

        return isValidInput;
    }

    public void setText(final String text) {
        this.text = text;
        inputMessage.setText(text);
    }

    public String getText() {
        return text;
    }

    public void setDate(final long date) {
        this.date = date;
        inputMessage.setText(StringHelper.getFormattedDate(date));
    }

    public long getDate() {
        if (inputTextType != InputTextType.DATE) {
            throw new RuntimeException("cannot get the date from inputTextType that is not a DATE");
        }

        return date;
    }

    public void setPassengers(final int adultCount, final int childrenCount, final int infantCount) {
        this.adultCount = adultCount;
        this.childrenCount = childrenCount;
        this.infantCount = infantCount;
        inputMessage.setText(StringHelper.getFormattedPassenger(getResources(), adultCount, childrenCount, infantCount));
    }

    public int getAdultCount() {
        return adultCount;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public int getInfantCount() {
        return infantCount;
    }

    public void setError(final String error) {
        inputMessage.setText(error);
        inputMessage.setTextColor(colorError);
        inputDivider.setBackgroundColor(colorError);
    }

    private enum InputTextType {
        TEXT(0),
        DATE(1),
        NUMBER(2);

        private final int type;

        InputTextType(int type) {
            this.type = type;
        }

        public static InputTextType fromType(final int type) {
            if (DATE.type == type) {
                return DATE;
            } else if (NUMBER.type == type) {
                return NUMBER;
            } else if (TEXT.type == type) {
                return TEXT;
            } else {
                return TEXT;
            }
        }
    }
}