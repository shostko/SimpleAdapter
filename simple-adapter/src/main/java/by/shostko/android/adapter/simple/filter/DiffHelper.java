package by.shostko.android.adapter.simple.filter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import java.util.List;

public interface DiffHelper<T> extends BaseDiffHelper
{
    @NonNull
    DiffUtil.Callback getDiffCallback(@NonNull List<T> oldList, @NonNull List<T> newList);
}
