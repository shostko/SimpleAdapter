package by.shostko.android.adapter.simple.filter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public interface BaseDiffHelper
{
    void dispatchDiffResult(@NonNull DiffUtil.DiffResult diffResult);
}
