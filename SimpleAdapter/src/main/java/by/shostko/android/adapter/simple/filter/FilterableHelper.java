package by.shostko.android.adapter.simple.filter;

import androidx.annotation.NonNull;

public interface FilterableHelper<T, F> extends BaseFilterableHelper<F>
{
    boolean checkForAll(@NonNull F constraint);

    boolean check(@NonNull F constraint, @NonNull T item);
}
