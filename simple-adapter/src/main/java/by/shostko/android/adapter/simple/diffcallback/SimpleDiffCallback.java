package by.shostko.android.adapter.simple.diffcallback;

import android.support.annotation.NonNull;

import java.util.List;

public class SimpleDiffCallback<T> extends BaseDiffCallback<T, Object>
{
    private final static Object PARAM = new Object[0];

    @NonNull
    private final DiffCallbackHelper<T> callbackHelper;

    public SimpleDiffCallback(@NonNull List<T> oldList, @NonNull List<T> newList,
                              @NonNull DiffCallbackHelper<T> callbackHelper)
    {
        super(oldList, PARAM, newList, PARAM);
        this.callbackHelper = callbackHelper;
    }

    @Override
    protected boolean areItemsOfSameType(int oldItemPosition, @NonNull Object oldParam,
                                         int newItemPosition, @NonNull Object newParam)
    {
        return callbackHelper.areItemsOfSameType(oldItemPosition, newItemPosition);
    }

    @Override
    protected boolean areItemsTheSame(int oldPosition, @NonNull T oldItem, @NonNull Object oldParam,
                                      int newPosition, @NonNull T newItem, @NonNull Object newParam)
    {
        return callbackHelper.areItemsTheSame(oldPosition, oldItem, newPosition, newItem);
    }

    @Override
    protected boolean areContentsTheSame(int oldPosition, @NonNull T oldItem, @NonNull Object oldParam,
                                         int newPosition, @NonNull T newItem, @NonNull Object newParam)
    {
        return callbackHelper.areContentsTheSame(oldPosition, oldItem, newPosition, newItem);
    }
}
