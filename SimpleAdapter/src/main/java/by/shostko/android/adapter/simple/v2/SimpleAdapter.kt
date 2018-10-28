package by.shostko.android.adapter.simple.v2

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class SimpleAdapter<VH : RecyclerView.ViewHolder, T, P, F> : RecyclerView.Adapter<VH>() {

    class Builder<VH : RecyclerView.ViewHolder, T, P, F> {

        private val emptyHolderDelegate = object : ViewHolderDelegate.Parametrized<VH, T, P>() {
            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
                throw UnsupportedOperationException()
            }

            override fun onBindViewHolder(holder: VH, position: Int, item: T?, param: P?) {
                throw UnsupportedOperationException()
            }
        }
        private val emptyDiffDelegate = object : DiffDelegate.Parametrized<T, P>() {
            override fun getItemViewType(position: Int, item: T?, param: P?): Int {
                throw UnsupportedOperationException()
            }

            override fun areItemsTheSame(viewType: Int, oldPosition: Int, oldItem: T?, oldParam: P?,
                                         newPosition: Int, newItem: T?, newParam: P?): Boolean {
                throw UnsupportedOperationException()
            }

            override fun areContentsTheSame(viewType: Int, oldPosition: Int, oldItem: T?, oldParam: P?,
                                            newPosition: Int, newItem: T?, newParam: P?): Boolean {
                throw UnsupportedOperationException()
            }
        }
        private val emptyFilterDelegate = object : FilterDelegate.Parametrized<T, P, F>() {
            override fun initialConstraint(): F? = null

            override fun check(position: Int, viewType: Int, item: T?, param: P?, constraint: F?): Boolean = true

            override fun checkAll(param: P?, constraint: F?): Boolean = true

            override fun convert(constraint: F?): CharSequence? = null

            override fun convert(constraint: CharSequence?): F? = null

        }

        var viewHolderDelegate: ViewHolderDelegate.Parametrized<VH, T, P> = emptyHolderDelegate
        var diffDelegate: DiffDelegate.Parametrized<T, P> = emptyDiffDelegate
        var filterDelegate: FilterDelegate.Parametrized<T, P, F> = emptyFilterDelegate

        fun build(): SimpleAdapter<VH, T, P, F> {
            if (viewHolderDelegate == emptyHolderDelegate) {
                throw NullPointerException("ViewHolderDelegate should be provided!")
            }
            if (diffDelegate == emptyDiffDelegate) {
                throw NullPointerException("DiffDelegate should be provided!")
            }
            return CoreAdapter(viewHolderDelegate, diffDelegate, filterDelegate)
        }
    }
}