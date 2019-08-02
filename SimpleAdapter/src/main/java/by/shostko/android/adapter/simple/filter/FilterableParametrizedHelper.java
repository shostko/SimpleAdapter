package by.shostko.android.adapter.simple.filter;

import androidx.annotation.NonNull;

public interface FilterableParametrizedHelper<T, P, F> extends BaseFilterableHelper<F>
{
    boolean checkForAll(@NonNull F constraint, @NonNull P param);

    boolean check(@NonNull F constraint, @NonNull T item, @NonNull P param);
}
