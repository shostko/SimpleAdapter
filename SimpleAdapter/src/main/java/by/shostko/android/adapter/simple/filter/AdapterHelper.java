package by.shostko.android.adapter.simple.filter;

import androidx.annotation.NonNull;

import java.util.List;

public interface AdapterHelper<T, P>
{
    @NonNull
    List<T> getOriginalItems();

    @NonNull
    List<T> getPreviousItems();

    @NonNull
    P getOriginalParam();

    @NonNull
    P getPreviousParam();

    void dispatchFilterResults(@NonNull List<T> items, @NonNull P param);
}
