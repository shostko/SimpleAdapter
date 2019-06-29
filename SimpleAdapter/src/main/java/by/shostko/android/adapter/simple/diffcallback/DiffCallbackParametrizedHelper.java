package by.shostko.android.adapter.simple.diffcallback;

import androidx.annotation.NonNull;

public interface DiffCallbackParametrizedHelper<T, P>
{
    int getItemViewType(@NonNull T item, @NonNull P param);

    boolean areItemsTheSame(int viewType,
                            int oldPosition, @NonNull T oldItem, @NonNull P oldParam,
                            int newPosition, @NonNull T newItem, @NonNull P newParam);

    boolean areContentsTheSame(int viewType,
                               int oldPosition, @NonNull T oldItem, @NonNull P oldParam,
                               int newPosition, @NonNull T newItem, @NonNull P newParam);
}
