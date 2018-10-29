package by.shostko.android.adapter.simple.v2

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

@Suppress("unused")
abstract class SimpleAdapter<VH : RecyclerView.ViewHolder, T, P, F> : RecyclerView.Adapter<VH>() {

    abstract fun submit(items: List<T?>?)

    abstract fun submit(param: P?)

    abstract fun submit(items: List<T?>?, param: P?)

    abstract class Items<VH : RecyclerView.ViewHolder, T> : CoreAdapter<VH, T, Unit, Unit>() {

        init {
            holderDelegate = HolderDelegateWrapper()
            diffDelegate = DiffDelegateWrapper()
            filterDelegate = FilterDelegate.Items.empty()
        }

        private inner class HolderDelegateWrapper : ViewHolderDelegate.Items<VH, T>() {
            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
                return this@Items.onCreateViewHolder(parent, viewType)
            }

            override fun onBindViewHolder(holder: VH, position: Int, item: T?) {
                return this@Items.onBindViewHolder(holder, position, item)
            }

            override fun onBindViewHolder(holder: VH, position: Int, item: T?, payloads: MutableList<Any>?) {
                return this@Items.onBindViewHolder(holder, position, item, payloads)
            }
        }

        private inner class DiffDelegateWrapper : DiffDelegate.Items<T>() {
            override fun getItemViewType(position: Int, item: T?): Int {
                return this@Items.getItemViewType(position, item)
            }

            override fun areItemsTheSame(viewType: Int, oldPosition: Int, oldItem: T?, newPosition: Int, newItem: T?): Boolean {
                return this@Items.areItemsTheSame(viewType, oldPosition, oldItem, newPosition, newItem)
            }

            override fun areContentsTheSame(viewType: Int, oldPosition: Int, oldItem: T?, newPosition: Int, newItem: T?): Boolean {
                return this@Items.areContentsTheSame(viewType, oldPosition, oldItem, newPosition, newItem)
            }
        }

        abstract override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH

        abstract fun onBindViewHolder(holder: VH, position: Int, item: T?)

        @Suppress("UNUSED_PARAMETER")
        open fun onBindViewHolder(holder: VH, position: Int, item: T?, payloads: MutableList<Any>?) {
            onBindViewHolder(holder, position, item)
        }

        abstract fun getItemViewType(position: Int, item: T?): Int

        abstract fun areItemsTheSame(viewType: Int,
                                     oldPosition: Int, oldItem: T?,
                                     newPosition: Int, newItem: T?): Boolean

        abstract fun areContentsTheSame(viewType: Int,
                                        oldPosition: Int, oldItem: T?,
                                        newPosition: Int, newItem: T?): Boolean
    }

    abstract class FilterableItems<VH : RecyclerView.ViewHolder, T, F>(
            private val initialConstraint: F
    ) : CoreAdapter<VH, T, Unit, F>() {

        init {
            holderDelegate = HolderDelegateWrapper()
            diffDelegate = DiffDelegateWrapper()
            filterDelegate = FilterDelegateWrapper()
        }

        private inner class HolderDelegateWrapper : ViewHolderDelegate.Items<VH, T>() {
            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
                return this@FilterableItems.onCreateViewHolder(parent, viewType)
            }

            override fun onBindViewHolder(holder: VH, position: Int, item: T?) {
                return this@FilterableItems.onBindViewHolder(holder, position, item)
            }

            override fun onBindViewHolder(holder: VH, position: Int, item: T?, payloads: MutableList<Any>?) {
                return this@FilterableItems.onBindViewHolder(holder, position, item, payloads)
            }
        }

        private inner class DiffDelegateWrapper : DiffDelegate.Items<T>() {
            override fun getItemViewType(position: Int, item: T?): Int {
                return this@FilterableItems.getItemViewType(position, item)
            }

            override fun areItemsTheSame(viewType: Int, oldPosition: Int, oldItem: T?, newPosition: Int, newItem: T?): Boolean {
                return this@FilterableItems.areItemsTheSame(viewType, oldPosition, oldItem, newPosition, newItem)
            }

            override fun areContentsTheSame(viewType: Int, oldPosition: Int, oldItem: T?, newPosition: Int, newItem: T?): Boolean {
                return this@FilterableItems.areContentsTheSame(viewType, oldPosition, oldItem, newPosition, newItem)
            }
        }

        private inner class FilterDelegateWrapper : FilterDelegate.Parametrized<T, Unit, F>() {
            override fun initialConstraint(): F? {
                return this@FilterableItems.initialConstraint
            }

            override fun check(position: Int, viewType: Int, item: T?, param: Unit?, constraint: F?): Boolean {
                return this@FilterableItems.check(position, viewType, item, constraint)
            }

            override fun checkAll(param: Unit?, constraint: F?): Boolean {
                return this@FilterableItems.checkAll(constraint)
            }

            override fun convert(constraint: F?): CharSequence? {
                return this@FilterableItems.convert(constraint)
            }

            override fun convert(constraint: CharSequence?): F? {
                return this@FilterableItems.convert(constraint)
            }
        }

        abstract override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH

        abstract fun onBindViewHolder(holder: VH, position: Int, item: T?)

        @Suppress("UNUSED_PARAMETER")
        open fun onBindViewHolder(holder: VH, position: Int, item: T?, payloads: MutableList<Any>?) {
            onBindViewHolder(holder, position, item)
        }

        abstract fun getItemViewType(position: Int, item: T?): Int

        abstract fun areItemsTheSame(viewType: Int,
                                     oldPosition: Int, oldItem: T?,
                                     newPosition: Int, newItem: T?): Boolean

        abstract fun areContentsTheSame(viewType: Int,
                                        oldPosition: Int, oldItem: T?,
                                        newPosition: Int, newItem: T?): Boolean

        abstract fun check(position: Int, viewType: Int, item: T?, constraint: F?): Boolean

        abstract fun checkAll(constraint: F?): Boolean

        abstract fun convert(constraint: F?): CharSequence?

        abstract fun convert(constraint: CharSequence?): F?
    }

    abstract class Parametrized<VH : RecyclerView.ViewHolder, T, P> : CoreAdapter<VH, T, P, Unit>() {

        init {
            holderDelegate = HolderDelegateWrapper()
            diffDelegate = DiffDelegateWrapper()
            filterDelegate = FilterDelegate.Parametrized.empty()
        }

        private inner class HolderDelegateWrapper : ViewHolderDelegate.Parametrized<VH, T, P>() {
            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
                return this@Parametrized.onCreateViewHolder(parent, viewType)
            }

            override fun onBindViewHolder(holder: VH, position: Int, item: T?, param: P?) {
                return this@Parametrized.onBindViewHolder(holder, position, item, param)
            }

            override fun onBindViewHolder(holder: VH, position: Int, item: T?, param: P?, payloads: MutableList<Any>?) {
                return this@Parametrized.onBindViewHolder(holder, position, item, param, payloads)
            }
        }

        private inner class DiffDelegateWrapper : DiffDelegate.Parametrized<T, P>() {
            override fun getItemViewType(position: Int, item: T?, param: P?): Int {
                return this@Parametrized.getItemViewType(position, item, param)
            }

            override fun areItemsTheSame(viewType: Int,
                                         oldPosition: Int, oldItem: T?, oldParam: P?,
                                         newPosition: Int, newItem: T?, newParam: P?): Boolean {
                return this@Parametrized.areItemsTheSame(viewType, oldPosition, oldItem, oldParam, newPosition, newItem, newParam)
            }

            override fun areContentsTheSame(viewType: Int,
                                            oldPosition: Int, oldItem: T?, oldParam: P?,
                                            newPosition: Int, newItem: T?, newParam: P?): Boolean {
                return this@Parametrized.areContentsTheSame(viewType, oldPosition, oldItem, oldParam, newPosition, newItem, newParam)
            }
        }

        abstract override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH

        abstract fun onBindViewHolder(holder: VH, position: Int, item: T?, param: P?)

        @Suppress("UNUSED_PARAMETER")
        open fun onBindViewHolder(holder: VH, position: Int, item: T?, param: P?, payloads: MutableList<Any>?) {
            onBindViewHolder(holder, position, item, param)
        }

        abstract fun getItemViewType(position: Int, item: T?, param: P?): Int

        abstract fun areItemsTheSame(viewType: Int,
                                     oldPosition: Int, oldItem: T?, oldParam: P?,
                                     newPosition: Int, newItem: T?, newParam: P?): Boolean

        abstract fun areContentsTheSame(viewType: Int,
                                        oldPosition: Int, oldItem: T?, oldParam: P?,
                                        newPosition: Int, newItem: T?, newParam: P?): Boolean
    }

    abstract class FilterableParametrized<VH : RecyclerView.ViewHolder, T, P, F>(
            private val initialConstraint: F
    ) : CoreAdapter<VH, T, P, F>() {

        init {
            holderDelegate = HolderDelegateWrapper()
            diffDelegate = DiffDelegateWrapper()
            filterDelegate = FilterDelegateWrapper()
        }

        private inner class HolderDelegateWrapper : ViewHolderDelegate.Parametrized<VH, T, P>() {
            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
                return this@FilterableParametrized.onCreateViewHolder(parent, viewType)
            }

            override fun onBindViewHolder(holder: VH, position: Int, item: T?, param: P?) {
                return this@FilterableParametrized.onBindViewHolder(holder, position, item, param)
            }

            override fun onBindViewHolder(holder: VH, position: Int, item: T?, param: P?, payloads: MutableList<Any>?) {
                return this@FilterableParametrized.onBindViewHolder(holder, position, item, param, payloads)
            }
        }

        private inner class DiffDelegateWrapper : DiffDelegate.Parametrized<T, P>() {
            override fun getItemViewType(position: Int, item: T?, param: P?): Int {
                return this@FilterableParametrized.getItemViewType(position, item, param)
            }

            override fun areItemsTheSame(viewType: Int,
                                         oldPosition: Int, oldItem: T?, oldParam: P?,
                                         newPosition: Int, newItem: T?, newParam: P?): Boolean {
                return this@FilterableParametrized.areItemsTheSame(viewType, oldPosition, oldItem, oldParam, newPosition, newItem, newParam)
            }

            override fun areContentsTheSame(viewType: Int,
                                            oldPosition: Int, oldItem: T?, oldParam: P?,
                                            newPosition: Int, newItem: T?, newParam: P?): Boolean {
                return this@FilterableParametrized.areContentsTheSame(viewType, oldPosition, oldItem, oldParam, newPosition, newItem, newParam)
            }
        }

        private inner class FilterDelegateWrapper : FilterDelegate.Parametrized<T, P, F>() {
            override fun initialConstraint(): F? {
                return this@FilterableParametrized.initialConstraint
            }

            override fun check(position: Int, viewType: Int, item: T?, param: P?, constraint: F?): Boolean {
                return this@FilterableParametrized.check(position, viewType, item, param, constraint)
            }

            override fun checkAll(param: P?, constraint: F?): Boolean {
                return this@FilterableParametrized.checkAll(param, constraint)
            }

            override fun convert(constraint: F?): CharSequence? {
                return this@FilterableParametrized.convert(constraint)
            }

            override fun convert(constraint: CharSequence?): F? {
                return this@FilterableParametrized.convert(constraint)
            }
        }

        abstract override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH

        abstract fun onBindViewHolder(holder: VH, position: Int, item: T?, param: P?)

        @Suppress("UNUSED_PARAMETER")
        open fun onBindViewHolder(holder: VH, position: Int, item: T?, param: P?, payloads: MutableList<Any>?) {
            onBindViewHolder(holder, position, item, param)
        }

        abstract fun getItemViewType(position: Int, item: T?, param: P?): Int

        abstract fun areItemsTheSame(viewType: Int,
                                     oldPosition: Int, oldItem: T?, oldParam: P?,
                                     newPosition: Int, newItem: T?, newParam: P?): Boolean

        abstract fun areContentsTheSame(viewType: Int,
                                        oldPosition: Int, oldItem: T?, oldParam: P?,
                                        newPosition: Int, newItem: T?, newParam: P?): Boolean

        abstract fun check(position: Int, viewType: Int, item: T?, param: P?, constraint: F?): Boolean

        abstract fun checkAll(param: P?, constraint: F?): Boolean

        abstract fun convert(constraint: F?): CharSequence?

        abstract fun convert(constraint: CharSequence?): F?
    }

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