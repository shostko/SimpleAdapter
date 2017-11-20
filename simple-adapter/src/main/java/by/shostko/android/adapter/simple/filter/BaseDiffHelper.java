package by.shostko.android.adapter.simple.filter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

public interface BaseDiffHelper
{
    void dispatchDiffResult(@NonNull DiffUtil.DiffResult diffResult);
}
