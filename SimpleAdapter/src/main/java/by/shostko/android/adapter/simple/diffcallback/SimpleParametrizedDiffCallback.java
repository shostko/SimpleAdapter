package by.shostko.android.adapter.simple.diffcallback;

import android.support.annotation.NonNull;

import java.util.List;

public class SimpleParametrizedDiffCallback<T, P> extends BaseDiffCallback<T, P>
{
    @NonNull
    private final DiffCallbackParametrizedHelper<T, P> callbackHelper;

    public SimpleParametrizedDiffCallback(@NonNull List<T> oldList, @NonNull P oldParam,
                                          @NonNull List<T> newList, @NonNull P newParam,
                                          @NonNull DiffCallbackParametrizedHelper<T, P> callbackHelper)
    {
        super(oldList, oldParam, newList, newParam);
        this.callbackHelper = callbackHelper;
    }

    @Override
    protected boolean areItemsOfSameType(int oldItemPosition, @NonNull P oldParam,
                                         int newItemPosition, @NonNull P newParam)
    {
        return callbackHelper.areItemsOfSameType(oldItemPosition, oldParam, newItemPosition, newParam);
    }

    @Override
    protected boolean areItemsTheSame(int oldPosition, @NonNull T oldItem, @NonNull P oldParam,
                                      int newPosition, @NonNull T newItem, @NonNull P newParam)
    {
        return callbackHelper.areItemsTheSame(oldPosition, oldItem, oldParam, newPosition, newItem, newParam);
    }

    @Override
    protected boolean areContentsTheSame(int oldPosition, @NonNull T oldItem, @NonNull P oldParam,
                                         int newPosition, @NonNull T newItem, @NonNull P newParam)
    {
        return callbackHelper.areContentsTheSame(oldPosition, oldItem, oldParam, newPosition, newItem, newParam);
    }
}
