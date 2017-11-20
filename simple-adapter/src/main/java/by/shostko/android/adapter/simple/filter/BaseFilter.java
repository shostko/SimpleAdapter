package by.shostko.android.adapter.simple.filter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

abstract class BaseFilter<T, P, F> extends CoreFilter<T, P, F>
{
    @NonNull
    private final BaseFilterableHelper<F> filterableHelper;

    @NonNull
    private final BaseDiffHelper diffHelper;

    @NonNull
    private final AdapterHelper<T, P> adapterHelper;

    public BaseFilter(@NonNull BaseFilterableHelper<F> filterableHelper,
                      @NonNull BaseDiffHelper diffHelper,
                      @NonNull AdapterHelper<T, P> adapterHelper)
    {
        this.filterableHelper = filterableHelper;
        this.diffHelper = diffHelper;
        this.adapterHelper = adapterHelper;
    }

    @NonNull
    @Override
    protected F convertConstraint(@Nullable CharSequence constraint)
    {
        return filterableHelper.convertConstraint(constraint);
    }

    @NonNull
    @Override
    protected List<T> getOriginalItems()
    {
        return adapterHelper.getOriginalItems();
    }

    @NonNull
    @Override
    protected List<T> getPreviousItems()
    {
        return adapterHelper.getPreviousItems();
    }

    @NonNull
    @Override
    protected P getOriginalParam()
    {
        return adapterHelper.getOriginalParam();
    }

    @NonNull
    @Override
    protected P getPreviousParam()
    {
        return adapterHelper.getPreviousParam();
    }

    @Override
    protected void publishResults(CharSequence constraint, @NonNull FilterResult<T, P> result)
    {
        adapterHelper.dispatchFilterResults(result.filtered(), result.param());
        diffHelper.dispatchDiffResult(result.diffResult());
    }
}
