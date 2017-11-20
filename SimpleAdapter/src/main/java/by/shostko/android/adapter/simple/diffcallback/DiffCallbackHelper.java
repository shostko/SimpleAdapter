package by.shostko.android.adapter.simple.diffcallback;

import android.support.annotation.NonNull;

public interface DiffCallbackHelper<T>
{
    boolean areItemsOfSameType(int oldItemPosition,
                               int newItemPosition);

    boolean areItemsTheSame(int oldPosition, @NonNull T oldItem,
                            int newPosition, @NonNull T newItem);

    boolean areContentsTheSame(int oldPosition, @NonNull T oldItem,
                               int newPosition, @NonNull T newItem);
}
