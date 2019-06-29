package by.shostko.android.adapter.simple.diffcallback;

import androidx.annotation.NonNull;

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
    protected int getItemViewType(@NonNull T item, @NonNull P oldParam) {
        return callbackHelper.getItemViewType(item, oldParam);
    }

    @Override
    protected boolean areItemsTheSame(int viewType,
                                      int oldPosition, @NonNull T oldItem, @NonNull P oldParam,
                                      int newPosition, @NonNull T newItem, @NonNull P newParam)
    {
        return callbackHelper.areItemsTheSame(viewType, oldPosition, oldItem, oldParam, newPosition, newItem, newParam);
    }

    @Override
    protected boolean areContentsTheSame(int viewType,
                                         int oldPosition, @NonNull T oldItem, @NonNull P oldParam,
                                         int newPosition, @NonNull T newItem, @NonNull P newParam)
    {
        return callbackHelper.areContentsTheSame(viewType, oldPosition, oldItem, oldParam, newPosition, newItem, newParam);
    }
}
