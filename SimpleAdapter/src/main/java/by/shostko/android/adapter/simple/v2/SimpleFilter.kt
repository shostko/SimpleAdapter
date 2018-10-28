package by.shostko.android.adapter.simple.v2

import android.support.v7.util.DiffUtil
import android.widget.Filter

class SimpleFilter<T, P, F> internal constructor(
        private val adapter: CoreAdapter<*, T, P, F>,
        private val original: Data<T, P>,
        private val previous: Data<T, P>,
        private val filterDelegate: FilterDelegate.Parametrized<T, P, F>,
        private var diffDelegate: DiffDelegate.Parametrized<T, P>
) : Filter() {

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val filter = filterDelegate.convert(constraint)
        val filtered = if (filterDelegate.checkAll(original.param, filter)) {
            original.items
        } else {
            original.items.filterIndexed { position, item ->
                val viewType = diffDelegate.getItemViewType(position, item, original.param)
                filterDelegate.check(position, viewType, item, original.param, filter)
            }
        }

        val diffCallback = SimpleDiff(previous, Data(filtered, original.param), diffDelegate)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        val results = Filter.FilterResults()
        results.values = Pair(filtered, diffResult)
        results.count = filtered.size
        return results
    }

    @Suppress("UNCHECKED_CAST")
    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        val (filtered, diffResult) = results?.values as Pair<Data<T, P>, DiffUtil.DiffResult>
        adapter.publishResults(original, filtered, diffResult)
    }
}