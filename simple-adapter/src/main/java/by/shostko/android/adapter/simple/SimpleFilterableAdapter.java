package by.shostko.android.adapter.simple;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.widget.Filter;

import java.util.List;

import by.shostko.android.adapter.simple.diffcallback.DiffCallbackHelper;
import by.shostko.android.adapter.simple.diffcallback.SimpleDiffCallback;
import by.shostko.android.adapter.simple.filter.DiffHelper;
import by.shostko.android.adapter.simple.filter.FilterableHelper;
import by.shostko.android.adapter.simple.filter.SimpleFilter;

public abstract class SimpleFilterableAdapter<VH extends RecyclerView.ViewHolder, T, F>
        extends BaseAdapter<VH, T, Object, F>
        implements FilterableHelper<T, F>, DiffHelper<T>, DiffCallbackHelper<T>
{
    private final static Object PARAM = new Object[0];

    private Filter filter = new SimpleFilter<>(this, this, new SimpleAdapterHelper<>(this));

    @Override
    public Filter getFilter()
    {
        return filter;
    }

    @Override
    public boolean checkForAll(@NonNull F constraint)
    {
        return false;
    }

    @NonNull
    @Override
    public DiffUtil.Callback getDiffCallback(@NonNull List<T> oldList, @NonNull List<T> newList)
    {
        return new SimpleDiffCallback<>(oldList, newList, this);
    }

    @Override
    public boolean areItemsOfSameType(int oldItemPosition, int newItemPosition)
    {
        return getItemViewType(oldItemPosition) == getItemViewType(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldPosition, @NonNull T oldItem, int newPosition, @NonNull T newItem)
    {
        return oldItem.equals(newItem);
    }

    @NonNull
    @Override
    protected final Object getInitialParam()
    {
        return PARAM;
    }

    @Override
    protected final void onBindViewHolder(VH holder, int position, @NonNull T item, @NonNull Object param)
    {
        onBindViewHolder(holder, position, item);
    }

    protected abstract void onBindViewHolder(VH holder, int position, @NonNull T item);

    @Override
    protected final void onBindViewHolder(VH holder, int position, @NonNull T item, @NonNull Object param, List<Object> payloads)
    {
        onBindViewHolder(holder, position, item, payloads);
    }

    protected void onBindViewHolder(VH holder, int position, @NonNull T item, List<Object> payloads)
    {
        super.onBindViewHolder(holder, position, item, PARAM, payloads);
    }
}
