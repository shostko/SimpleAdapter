package by.shostko.android.adapter.simple;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import by.shostko.android.adapter.simple.filter.AdapterHelper;

abstract class BaseAdapter<VH extends RecyclerView.ViewHolder, T, P, F>
        extends CoreAdapter<VH, T, P>
        implements Filterable
{
    @NonNull
    private final List<T> itemsOriginal = new ArrayList<>();

    @Nullable
    private Pair<List<T>, P> working;

    @Nullable
    private Pair<List<T>, P> pending;

    @NonNull
    private F lastFilter = getInitialFilter();

    @NonNull
    public F getLastFilter()
    {
        return lastFilter;
    }

    @Override
    void setItemsWithParam(@NonNull List<T> items, @NonNull P param)
    {
        synchronized (this)
        {
            super.setItemsWithParam(items, param);
            if (working != null && working.first != null)
            {
                itemsOriginal.clear();
                if (!working.first.isEmpty())
                {
                    itemsOriginal.addAll(working.first);
                }
            }
            if (pending != null && pending.first != null && pending.second != null)
            {
                startWork(pending.first, pending.second);
            }
            else
            {
                working = null;
                pending = null;
            }
        }
    }

    @NonNull
    protected List<T> getLastItems()
    {
        if (pending != null && pending.first != null)
        {
            return pending.first;
        }
        else if (working != null && working.first != null)
        {
            return working.first;
        }
        else
        {
            return itemsOriginal;
        }
    }

    @NonNull
    protected P getLastParam()
    {
        if (pending != null && pending.second != null)
        {
            return pending.second;
        }
        else if (working != null && working.second != null)
        {
            return working.second;
        }
        else
        {
            return getParam();
        }
    }

    private <TE extends T> void updateInternal(@NonNull List<TE> items, @NonNull P param)
    {
        synchronized (this)
        {
            if (working != null)
            {
                List<T> pendingItems;
                if (pending != null && pending.first != null)
                {
                    pendingItems = pending.first;
                    pendingItems.clear();
                    if (!items.isEmpty())
                    {
                        pendingItems.addAll(items);
                    }
                }
                else
                {
                    //noinspection unchecked
                    pendingItems = (List<T>) new ArrayList<>(items);
                }
                pending = Pair.create(pendingItems, param);
            }
            else
            {
                startWork(items, param);
            }
        }
    }

    private <TE extends T> void startWork(@NonNull List<TE> items, @NonNull P param)
    {
        //noinspection unchecked
        working = Pair.create((List<T>) new ArrayList<>(items), param);
        pending = null;
        startFilter(lastFilter);
    }

    private void startFilter(@NonNull F filter)
    {
        getFilter().filter(convertConstraint(filter));
    }

    private <TE extends T> void updateInternal(@NonNull List<TE> items)
    {
        updateInternal(items, getLastParam());
    }

    private void updateInternal(@NonNull P param)
    {
        updateInternal(getLastItems(), param);
    }

    public <TE extends T> void changeItemsAndParam(@NonNull List<TE> items, @NonNull P param)
    {
        updateInternal(items, param);
    }

    public void changeParam(@NonNull P param)
    {
        updateInternal(param);
    }

    public <TE extends T> void changeItems(@NonNull List<TE> items)
    {
        updateInternal(items);
    }

    public <TE extends T> void changeItem(int position, @NonNull TE item)
    {
        if (position >= 0)
        {
            List<T> newItems = new ArrayList<>(getLastItems());
            if (position < newItems.size())
            {
                newItems.remove(position);
                newItems.add(position, item);
            }
            else
            {
                newItems.add(item);
            }
            updateInternal(newItems);
        }
    }

    public <TE extends T> void addItems(@NonNull List<TE> items)
    {
        boolean smthAdded = false;
        List<T> newItems = new ArrayList<>(getLastItems());
        for (T item : items)
        {
            if (!newItems.contains(item))
            {
                smthAdded = true;
                newItems.add(item);
            }
        }
        if (smthAdded)
        {
            updateInternal(newItems);
        }
    }

    public <TE extends T> void addItem(@NonNull TE item)
    {
        List<T> newItems = new ArrayList<>(getLastItems());
        if (!newItems.contains(item))
        {
            newItems.add(item);
            updateInternal(newItems);
        }
    }

    public <TE extends T> void removeItems(@NonNull List<TE> items)
    {
        boolean smthRemoved = false;
        List<T> newItems = new ArrayList<>(getLastItems());
        for (T item : items)
        {
            int index = newItems.indexOf(item);
            if (index >= 0)
            {
                smthRemoved = true;
                newItems.remove(index);
            }
        }
        if (smthRemoved)
        {
            updateInternal(newItems);
        }
    }

    public <TE extends T> void removeItem(@NonNull TE item)
    {
        List<T> newItems = new ArrayList<>(getLastItems());
        int index = newItems.indexOf(item);
        if (index >= 0)
        {
            newItems.remove(index);
            updateInternal(newItems);
        }
    }

    public void filter(@NonNull F filter)
    {
        synchronized (this)
        {
            lastFilter = filter;
            if (pending == null && !getLastItems().isEmpty())
            {
                startFilter(filter);
            }
        }
    }

    public void dispatchDiffResult(@NonNull DiffUtil.DiffResult diffResult)
    {
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    protected abstract F getInitialFilter();

    @NonNull
    protected abstract CharSequence convertConstraint(@NonNull F filter);

    static class SimpleAdapterHelper<T, P> implements AdapterHelper<T, P>
    {
        @NonNull
        private final BaseAdapter<?, T, P, ?> adapter;

        SimpleAdapterHelper(@NonNull BaseAdapter<?, T, P, ?> adapter)
        {
            this.adapter = adapter;
        }

        @NonNull
        @Override
        public List<T> getOriginalItems()
        {
            if (adapter.working != null && adapter.working.first != null)
            {
                return adapter.working.first;
            }
            else
            {
                return adapter.itemsOriginal;
            }
        }

        @NonNull
        @Override
        public List<T> getPreviousItems()
        {
            return adapter.getItems();
        }

        @NonNull
        @Override
        public P getOriginalParam()
        {
            if (adapter.working != null && adapter.working.second != null)
            {
                return adapter.working.second;
            }
            else
            {
                return adapter.getParam();
            }
        }

        @NonNull
        @Override
        public P getPreviousParam()
        {
            return adapter.getParam();
        }

        @Override
        public void dispatchFilterResults(@NonNull List<T> items, @NonNull P param)
        {
            adapter.setItemsWithParam(items, param);
        }
    }
}
