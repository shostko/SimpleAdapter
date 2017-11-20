package by.shostko.android.adapter.simple;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

public abstract class SimpleParametrizedAdapter<VH extends RecyclerView.ViewHolder, T, P>
        extends SimpleParametrizedFilterableAdapter<VH, T, P, Object>
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
    public final boolean checkForAll(@NonNull Object constraint, @NonNull P param)
    {
        return true;
    }

    @Override
    public final boolean check(@NonNull Object constraint, @NonNull T item, @NonNull P param)
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
