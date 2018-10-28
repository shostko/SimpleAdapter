package by.shostko.android.adapter.simple.v2

import android.support.v7.util.DiffUtil

class SimpleDiff<T, P> internal constructor(
        private val oldData: Data<T, P>,
        private val newData: Data<T, P>,
        private var diffDelegate: DiffDelegate.Parametrized<T, P>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldData[oldItemPosition]
        val newItem = newData[newItemPosition]
        val oldViewType = diffDelegate.getItemViewType(oldItemPosition, oldItem, oldData.param)
        val newViewType = diffDelegate.getItemViewType(newItemPosition, newItem, newData.param)
        return oldViewType == newViewType && diffDelegate.areItemsTheSame(oldViewType,
                oldItemPosition, oldItem, oldData.param,
                newItemPosition, newItem, newData.param)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldData[oldItemPosition]
        val newItem = newData[newItemPosition]
        val oldViewType = diffDelegate.getItemViewType(oldItemPosition, oldItem, oldData.param)
        val newViewType = diffDelegate.getItemViewType(newItemPosition, newItem, newData.param)
        return oldViewType == newViewType && diffDelegate.areContentsTheSame(oldViewType,
                oldItemPosition, oldItem, oldData.param,
                newItemPosition, newItem, newData.param)
    }
}