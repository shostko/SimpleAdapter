package by.shostko.android.adapter.simple;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.widget.Filter;

import java.util.List;

import by.shostko.android.adapter.simple.diffcallback.DiffCallbackParametrizedHelper;
import by.shostko.android.adapter.simple.diffcallback.SimpleParametrizedDiffCallback;
import by.shostko.android.adapter.simple.filter.DiffParametrizedHelper;
import by.shostko.android.adapter.simple.filter.FilterableParametrizedHelper;
import by.shostko.android.adapter.simple.filter.SimpleParametrizedFilter;

public abstract class SimpleParametrizedFilterableAdapter<VH extends RecyclerView.ViewHolder, T, P, F>
        extends BaseAdapter<VH, T, P, F>
        implements FilterableParametrizedHelper<T, P, F>, DiffParametrizedHelper<T, P>, DiffCallbackParametrizedHelper<T, P>
{
    private Filter filter = new SimpleParametrizedFilter<>(this, this, new SimpleAdapterHelper<>(this));

    @Override
    public Filter getFilter()
    {
        return filter;
    }

    @Override
    public boolean checkForAll(@NonNull F constraint, @NonNull P param)
    {
        return false;
    }

    @NonNull
    @Override
    public DiffUtil.Callback getDiffCallback(@NonNull List<T> oldList, @NonNull P oldParam,
                                             @NonNull List<T> newList, @NonNull P newParam)
    {
        return new SimpleParametrizedDiffCallback<>(oldList, oldParam, newList, newParam, this);
    }

    @Override
    public boolean areItemsOfSameType(int oldItemPosition, @NonNull P oldParam,
                                      int newItemPosition, @NonNull P newParam)
    {
        return getItemViewType(oldItemPosition) == getItemViewType(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldPosition, @NonNull T oldItem, @NonNull P oldParam,
                                      int newPosition, @NonNull T newItem, @NonNull P newParam)
    {
        return oldItem.equals(newItem) && oldParam.equals(newParam);
    }
}
