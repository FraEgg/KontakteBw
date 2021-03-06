package opencontacts.open.com.opencontacts.components.fieldcollections.spinnercollection;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.github.underscore.U;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import opencontacts.open.com.opencontacts.R;
import opencontacts.open.com.opencontacts.components.fieldcollections.InputFieldCollection;

import static opencontacts.open.com.opencontacts.utils.Common.mapIndexes;

public class SpinnerFieldCollection extends InputFieldCollection<SpinnerFieldHolder> {
    private List<String> options = new ArrayList<>(0);
    private boolean editDisabled;

    public SpinnerFieldCollection(Context context) {
        super(context);
    }

    public SpinnerFieldCollection(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SpinnerFieldCollection(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void processAttributesPassedThroughXML(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.InputFieldCollection);
        CharSequence[] options = typedArray.getTextArray(R.styleable.InputFieldCollection_android_entries);
        if (options != null) this.options = mapIndexes(options.length, index -> options[index].toString());
        editDisabled = typedArray.getInt(R.styleable.InputFieldCollection_android_inputType, InputType.TYPE_NULL) == InputType.TYPE_NULL;
        typedArray.recycle();
    }

    @Override
    public SpinnerFieldHolder addOneMoreView() {
        View inflatedView = layoutInflater.inflate(R.layout.component_fieldcollection_spinner_field, fieldsHolderLayout, false);
        fieldsHolderLayout.addView(inflatedView);
        SpinnerFieldHolder fieldViewHolder = createNewField(inflatedView);
        fieldViewHoldersList.add(fieldViewHolder);
        return fieldViewHolder;
    }

    @NonNull
    private SpinnerFieldHolder createNewField(View inflatedView) {
        SpinnerFieldHolder fieldViewHolder = new SpinnerFieldHolder(options, editDisabled, inflatedView, getContext());
        int indexOfNewField = fieldViewHoldersList.size();
        fieldViewHolder.setOnDelete(v -> removeField(indexOfNewField));
        return fieldViewHolder;
    }

    public List<String> getValues() {
        int childCount = fieldViewHoldersList.size();
        if (childCount == 0) return Collections.emptyList();
        return U.chain(mapIndexes(childCount, index -> fieldViewHoldersList.get(index).getValue()))
                .reject(TextUtils::isEmpty)
                .value();
    }

    public void set(List<String> options) {
        this.options = options;
    }

    public void addFields(List<String> values){
        U.forEach(values, this::addOneMoreView);
    }

    public void addOneMoreView(String value) {
        addOneMoreView().set(value);
    }
}
