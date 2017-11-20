package by.shostko.android.adapter.simple.diffcallback;

import android.support.annotation.NonNull;

public interface DiffCallbackParametrizedHelper<T, P>
{
    boolean areItemsOfSameType(int oldItemPosition, @NonNull P oldParam,
                               int newItemPosition, @NonNull P newParam);

    boolean areItemsTheSame(int oldPosition, @NonNull T oldItem, @NonNull P oldParam,
                            int newPosition, @NonNull T newItem, @NonNull P newParam);

    boolean areContentsTheSame(int oldPosition, @NonNull T oldItem, @NonNull P oldParam,
                               int newPosition, @NonNull T newItem, @NonNull P newParam);
}
