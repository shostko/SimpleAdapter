package by.shostko.android.adapter.simple.filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface BaseFilterableHelper<F>
{
    @NonNull
    F convertConstraint(@Nullable CharSequence constraint);
}
