package by.shostko.android.adapter.simple.filter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import java.util.List;

public class SimpleFilter<T, F> extends BaseFilter<T, Object, F>
{
    @NonNull
    private final FilterableHelper<T, F> filterableHelper;

    @NonNull
    private final DiffHelper<T> diffHelper;

    public SimpleFilter(@NonNull FilterableHelper<T, F> filterableHelper,
                        @NonNull DiffHelper<T> diffHelper,
                        @NonNull AdapterHelper<T, Object> adapterHelper)
    {
        super(filterableHelper, diffHelper, adapterHelper);
        this.filterableHelper = filterableHelper;
        this.diffHelper = diffHelper;
    }

    @Override
    protected boolean checkForAll(@NonNull F constraint, @NonNull Object param)
    {
        return filterableHelper.checkForAll(constraint);
    }

    @Override
    protected boolean check(@NonNull F constraint, @NonNull T item, @NonNull Object param)
    {
        return filterableHelper.check(constraint, item);
    }

    @NonNull
    @Override
    protected DiffUtil.Callback getDiffCallback(@NonNull List<T> oldList, @NonNull Object oldParam,
                                                @NonNull List<T> newList, @NonNull Object newParam)
    {
        return diffHelper.getDiffCallback(oldList, newList);
    }
}
