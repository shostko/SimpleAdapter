package by.shostko.android.adapter.simple;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

abstract class CoreAdapter<VH extends RecyclerView.ViewHolder, T, P>
        extends RecyclerView.Adapter<VH>
{
    @NonNull
    private final List<T> items = new ArrayList<>();

    @NonNull
    private P param = getInitialParam();

    @NonNull
    List<T> getItems()
    {
        return items;
    }

    @NonNull
    P getParam()
    {
        return param;
    }

    void setItemsWithParam(@NonNull List<T> items, @NonNull P param)
    {
        this.items.clear();
        if (!items.isEmpty())
        {
            this.items.addAll(items);
        }
        this.param = param;
    }

    @NonNull
    public T getItem(int position)
    {
        return items.get(position);
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    @Override
    public final void onBindViewHolder(VH holder, int position)
    {
        T item = getItem(position);
        onBindViewHolder(getItemViewType(item, param), holder, position, item, param);
    }

    protected abstract void onBindViewHolder(int viewType, VH holder, int position, @NonNull T item, @NonNull P param);

    @Override
    public final void onBindViewHolder(VH holder, int position, List<Object> payloads)
    {
        T item = getItem(position);
        onBindViewHolder(getItemViewType(item, param), holder, position, item, param, payloads);
    }

    protected void onBindViewHolder(int viewType, VH holder, int position, @NonNull T item, @NonNull P param, List<Object> payloads)
    {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public final int getItemViewType(int position) {
        return getItemViewType(getItem(position), param);
    }

    @SuppressWarnings("unused")
    public int getItemViewType(@NonNull T item, P param) {
        return 0;
    }

    @NonNull
    protected abstract P getInitialParam();
}
