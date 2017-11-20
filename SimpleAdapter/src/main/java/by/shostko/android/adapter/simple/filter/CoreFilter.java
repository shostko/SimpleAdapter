package by.shostko.android.adapter.simple.filter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

abstract class CoreFilter<T, P, F> extends Filter
{
    @NonNull
    protected abstract F convertConstraint(@Nullable CharSequence constraint);

    protected abstract boolean checkForAll(@NonNull F constraint, @NonNull P param);

    protected abstract boolean check(@NonNull F constraint, @NonNull T item, @NonNull P param);

    @NonNull
    protected abstract List<T> getOriginalItems();

    @NonNull
    protected abstract List<T> getPreviousItems();

    @NonNull
    protected abstract P getOriginalParam();

    @NonNull
    protected abstract P getPreviousParam();

    @NonNull
    protected abstract DiffUtil.Callback getDiffCallback(@NonNull List<T> oldList, @NonNull P oldParam,
                                                         @NonNull List<T> newList, @NonNull P newParam);

    @Override
    @NonNull
    protected final FilterResults performFiltering(CharSequence constraint)
    {
        final List<T> items = getOriginalItems();
        final P param = getOriginalParam();
        final List<T> filtered;

        F filter = convertConstraint(constraint);
        if (checkForAll(filter, param))
        {
            filtered = new ArrayList<>(items);
        }
        else
        {
            filtered = new LinkedList<>();
            for (T item : items)
            {
                if (check(filter, item, param))
                {
                    filtered.add(item);
                }
            }
        }

        final List<T> previousItems = getPreviousItems();
        final P previousParam = getPreviousParam();
        DiffUtil.Callback diffCallback = getDiffCallback(previousItems, previousParam, filtered, param);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        final FilterResults results = new FilterResults();
        results.values = new FilterResult<>(items, filtered, param, diffResult);
        results.count = filtered.size();
        return results;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected final void publishResults(CharSequence constraint, FilterResults results)
    {
        if (results != null && results.values != null)
        {
            publishResults(constraint, (FilterResult<T, P>) results.values);
        }
    }

    protected abstract void publishResults(CharSequence constraint, @NonNull FilterResult<T, P> result);
}
