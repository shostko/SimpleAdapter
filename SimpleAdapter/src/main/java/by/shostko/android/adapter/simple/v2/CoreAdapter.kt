package by.shostko.android.adapter.simple.v2

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

@Suppress("MemberVisibilityCanBePrivate", "UNUSED_PARAMETER", "unused")
open class CoreAdapter<VH : RecyclerView.ViewHolder, T, P, F>() : SimpleAdapter<VH, T, P, F>() {

    internal lateinit var holderDelegate: ViewHolderDelegate.Parametrized<VH, T, P>
    internal lateinit var diffDelegate: DiffDelegate.Parametrized<T, P>
    internal lateinit var filterDelegate: FilterDelegate.Parametrized<T, P, F>

    private val data: Data<T, P> = Data()
    private var constraint: F? = filterDelegate.initialConstraint()
    private var originalData: Data<T, P>? = null
    private var pendingData: Data<T, P>? = null
    private var working: Boolean = false

    constructor(holderDelegate: ViewHolderDelegate.Parametrized<VH, T, P>,
                diffDelegate: DiffDelegate.Parametrized<T, P>,
                filterDelegate: FilterDelegate.Parametrized<T, P, F>) : this() {
        this.holderDelegate = holderDelegate
        this.diffDelegate = diffDelegate
        this.filterDelegate = filterDelegate
    }

    @Synchronized
    private fun submitData(data: Data<T, P>) {
        if (working) {
            pendingData = data
        } else {
            working = true
            val filter = SimpleFilter(this, data, this.data, filterDelegate, diffDelegate)
            filter.filter(filterDelegate.convert(constraint))
        }
    }

    @Synchronized
    internal fun publishResults(originalData: Data<T, P>, filteredData: Data<T, P>, diffResult: DiffUtil.DiffResult) {
        val pending = pendingData
        if (pending == null) {
            this.data.update(filteredData)
            this.originalData = originalData
            diffResult.dispatchUpdatesTo(this)
            working = false
        } else {
            pendingData = null
            val filter = SimpleFilter(this, pending, this.data, filterDelegate, diffDelegate)
            filter.filter(filterDelegate.convert(constraint))
        }
    }

    override fun submit(items: List<T?>?) = submitData(Data(items, data.param))

    override fun submit(param: P?) = submitData(Data(data.items, param))

    override fun submit(items: List<T?>?, param: P?) = submitData(Data(items, param))

    fun getItem(position: Int): T? = data[position]

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH = holderDelegate.onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: VH, position: Int) {
        holderDelegate.onBindViewHolder(holder, position, data[position], data.param)
    }

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>?) {
        holderDelegate.onBindViewHolder(holder, position, data[position], data.param, payloads)
    }
}