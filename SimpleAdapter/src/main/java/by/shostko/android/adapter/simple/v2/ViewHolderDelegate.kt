package by.shostko.android.adapter.simple.v2

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

@Suppress("unused")
interface ViewHolderDelegate {

    abstract class Parametrized<VH : RecyclerView.ViewHolder, T, P> {

        abstract fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH

        abstract fun onBindViewHolder(holder: VH, position: Int, item: T?, param: P?)

        @Suppress("UNUSED_PARAMETER")
        open fun onBindViewHolder(holder: VH, position: Int, item: T?, param: P?, payloads: MutableList<Any>?) {
            onBindViewHolder(holder, position, item, param)
        }
    }

    abstract class Items<VH : RecyclerView.ViewHolder, T> : Parametrized<VH, T, Unit>() {

        final override fun onBindViewHolder(holder: VH, position: Int, item: T?, param: Unit?) {

        }

        abstract fun onBindViewHolder(holder: VH, position: Int, item: T?)

        final override fun onBindViewHolder(holder: VH, position: Int, item: T?, param: Unit?, payloads: MutableList<Any>?) {
            onBindViewHolder(holder, position, item, payloads)
        }

        @Suppress("UNUSED_PARAMETER")
        open fun onBindViewHolder(holder: VH, position: Int, item: T?, payloads: MutableList<Any>?) {
            onBindViewHolder(holder, position, item)
        }
    }
}