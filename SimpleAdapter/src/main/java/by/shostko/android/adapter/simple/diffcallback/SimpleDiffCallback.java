package by.shostko.android.adapter.simple.diffcallback;

import androidx.annotation.NonNull;

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
    protected int getItemViewType(@NonNull T item, @NonNull Object oldParam) {
        return callbackHelper.getItemViewType(item);
    }

    @Override
    protected boolean areItemsTheSame(int viewType,
                                      int oldPosition, @NonNull T oldItem, @NonNull Object oldParam,
                                      int newPosition, @NonNull T newItem, @NonNull Object newParam)
    {
        return callbackHelper.areItemsTheSame(viewType, oldPosition, oldItem, newPosition, newItem);
    }

    @Override
    protected boolean areContentsTheSame(int viewType,
                                         int oldPosition, @NonNull T oldItem, @NonNull Object oldParam,
                                         int newPosition, @NonNull T newItem, @NonNull Object newParam)
    {
        return callbackHelper.areContentsTheSame(viewType, oldPosition, oldItem, newPosition, newItem);
    }
}
