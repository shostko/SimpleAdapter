package by.shostko.android.adapter.simple.diffcallback;

import android.support.annotation.NonNull;

public interface DiffCallbackHelper<T>
{
    int getItemViewType(@NonNull T item);

    boolean areItemsTheSame(int viewType,
                            int oldPosition, @NonNull T oldItem,
                            int newPosition, @NonNull T newItem);

    boolean areContentsTheSame(int viewType,
                               int oldPosition, @NonNull T oldItem,
                               int newPosition, @NonNull T newItem);
}
