package by.shostko.android.adapter.simple.diffcallback;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import java.util.List;

abstract class BaseDiffCallback<T, P> extends DiffUtil.Callback
{
    private static final String ERROR_NULL = "Cant handle lists with null items";

    @NonNull
    private final List<T> oldList;

    @NonNull
    private final P oldParam;

    @NonNull
    private final List<T> newList;

    @NonNull
    private final P newParam;

    protected BaseDiffCallback(@NonNull List<T> oldList,
                               @NonNull P oldParam,
                               @NonNull List<T> newList,
                               @NonNull P newParam)
    {
        this.oldList = oldList;
        this.oldParam = oldParam;
        this.newList = newList;
        this.newParam = newParam;
    }

    @Override
    public final int getOldListSize()
    {
        return oldList.size();
    }

    @Override
    public final int getNewListSize()
    {
        return newList.size();
    }

    @Override
    public final boolean areItemsTheSame(int oldItemPosition, int newItemPosition)
    {
        T oldT = oldList.get(oldItemPosition);
        T newT = newList.get(newItemPosition);
        if (oldT == null || newT == null)
        {
            throw new IllegalArgumentException(ERROR_NULL);
        }
        int oldViewType = getItemViewType(oldT, oldParam);
        int newViewType = getItemViewType(newT, newParam);
        boolean result = newViewType == oldViewType;
        if (result)
        {
            result = areItemsTheSame(newViewType, oldItemPosition, oldT, oldParam, newItemPosition, newT, newParam);
        }
        return result;
    }

    @Override
    public final boolean areContentsTheSame(int oldItemPosition, int newItemPosition)
    {
        T oldT = oldList.get(oldItemPosition);
        T newT = newList.get(newItemPosition);
        if (oldT == null || newT == null)
        {
            throw new IllegalArgumentException(ERROR_NULL);
        }
        int oldViewType = getItemViewType(oldT, oldParam);
        int newViewType = getItemViewType(newT, newParam);
        boolean result = newViewType == oldViewType;
        if (result)
        {
            result = areContentsTheSame(newViewType, oldItemPosition, oldT, oldParam, newItemPosition, newT, newParam);
        }
        return result;
    }

    protected abstract int getItemViewType(@NonNull T item, @NonNull P oldParam);

    protected abstract boolean areItemsTheSame(int viewType,
                                               int oldPosition, @NonNull T oldItem, @NonNull P oldParam,
                                               int newPosition, @NonNull T newItem, @NonNull P newParam);

    protected abstract boolean areContentsTheSame(int viewType,
                                                  int oldPosition, @NonNull T oldItem, @NonNull P oldParam,
                                                  int newPosition, @NonNull T newItem, @NonNull P newParam);
}
