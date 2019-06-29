package by.shostko.android.adapter.simple.filter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class SimpleParametrizedFilter<T, P, F> extends BaseFilter<T, P, F>
{
    @NonNull
    private final FilterableParametrizedHelper<T, P, F> filterableHelper;

    @NonNull
    private final DiffParametrizedHelper<T, P> diffHelper;

    public SimpleParametrizedFilter(@NonNull FilterableParametrizedHelper<T, P, F> filterableHelper,
                                    @NonNull DiffParametrizedHelper<T, P> diffHelper,
                                    @NonNull AdapterHelper<T, P> adapterHelper)
    {
        super(filterableHelper, diffHelper, adapterHelper);
        this.filterableHelper = filterableHelper;
        this.diffHelper = diffHelper;
    }

    @Override
    protected boolean checkForAll(@NonNull F constraint, @NonNull P param)
    {
        return filterableHelper.checkForAll(constraint, param);
    }

    @Override
    protected boolean check(@NonNull F constraint, @NonNull T item, @NonNull P param)
    {
        return filterableHelper.check(constraint, item, param);
    }

    @NonNull
    @Override
    protected DiffUtil.Callback getDiffCallback(@NonNull List<T> oldList, @NonNull P oldParam, @NonNull List<T> newList, @NonNull P newParam)
    {
        return diffHelper.getDiffCallback(oldList, oldParam, newList, newParam);
    }
}
