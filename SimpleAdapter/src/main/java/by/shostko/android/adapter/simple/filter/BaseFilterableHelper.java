package by.shostko.android.adapter.simple.filter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface BaseFilterableHelper<F>
{
    @NonNull
    F convertConstraint(@Nullable CharSequence constraint);
}
