package by.shostko.android.adapter.simple;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

public abstract class SimpleAdapter<VH extends RecyclerView.ViewHolder, T>
        extends SimpleFilterableAdapter<VH, T, Object>
{
    private static final Object EMPTY_CONSTRAINT_OBJ = new Object[0];
    private static final String EMPTY_CONSTRAINT_STR = "";

    @NonNull
    @Override
    public final Object convertConstraint(@Nullable CharSequence constraint)
    {
        return EMPTY_CONSTRAINT_OBJ;
    }

    @Override
    public final boolean checkForAll(@NonNull Object constraint)
    {
        return true;
    }

    @Override
    public final boolean check(@NonNull Object constraint, @NonNull T item)
    {
        return true;
    }

    @NonNull
    @Override
    protected final Object getInitialFilter()
    {
        return EMPTY_CONSTRAINT_OBJ;
    }

    @NonNull
    @Override
    protected final CharSequence convertConstraint(@NonNull Object filter)
    {
        return EMPTY_CONSTRAINT_STR;
    }
}
