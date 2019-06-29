package by.shostko.android.adapter.simple.filter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public interface DiffHelper<T> extends BaseDiffHelper
{
    @NonNull
    DiffUtil.Callback getDiffCallback(@NonNull List<T> oldList, @NonNull List<T> newList);
}
