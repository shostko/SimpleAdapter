package by.shostko.android.adapter.simple.filter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public final class FilterResult<T, P>
{
    @NonNull
    private final List<T> original;

    @NonNull
    private final List<T> filtered;

    @NonNull
    private final P param;

    @NonNull
    private final DiffUtil.DiffResult diffResult;

    public FilterResult(@NonNull List<T> original,
                        @NonNull List<T> filtered,
                        @NonNull P param,
                        @NonNull DiffUtil.DiffResult diffResult)
    {
        this.original = original;
        this.filtered = filtered;
        this.param = param;
        this.diffResult = diffResult;
    }

    @NonNull
    public List<T> original()
    {
        return original;
    }

    @NonNull
    public List<T> filtered()
    {
        return filtered;
    }

    @NonNull
    public P param()
    {
        return param;
    }

    @NonNull
    public DiffUtil.DiffResult diffResult()
    {
        return diffResult;
    }
}
