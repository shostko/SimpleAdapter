package by.shostko.android.adapter.simple.v2

interface DiffDelegate {

    abstract class Parametrized<T, P> {

        abstract fun getItemViewType(position: Int, item: T?, param: P?): Int

        abstract fun areItemsTheSame(viewType: Int,
                                     oldPosition: Int, oldItem: T?, oldParam: P?,
                                     newPosition: Int, newItem: T?, newParam: P?): Boolean

        abstract fun areContentsTheSame(viewType: Int,
                                        oldPosition: Int, oldItem: T?, oldParam: P?,
                                        newPosition: Int, newItem: T?, newParam: P?): Boolean
    }

    abstract class Items<T> : Parametrized<T, Unit>() {

        final override fun getItemViewType(position: Int, item: T?, param: Unit?): Int = getItemViewType(position, item)

        final override fun areItemsTheSame(viewType: Int,
                                           oldPosition: Int, oldItem: T?, oldParam: Unit?,
                                           newPosition: Int, newItem: T?, newParam: Unit?
        ): Boolean = areItemsTheSame(viewType, oldPosition, oldItem, newPosition, newItem)

        final override fun areContentsTheSame(viewType: Int,
                                              oldPosition: Int, oldItem: T?, oldParam: Unit?,
                                              newPosition: Int, newItem: T?, newParam: Unit?
        ): Boolean = areContentsTheSame(viewType, oldPosition, oldItem, newPosition, newItem)

        abstract fun getItemViewType(position: Int, item: T?): Int

        abstract fun areItemsTheSame(viewType: Int,
                                     oldPosition: Int, oldItem: T?,
                                     newPosition: Int, newItem: T?): Boolean

        abstract fun areContentsTheSame(viewType: Int,
                                        oldPosition: Int, oldItem: T?,
                                        newPosition: Int, newItem: T?): Boolean
    }
}
