package by.shostko.android.adapter.simple.v2

@Suppress("MemberVisibilityCanBePrivate", "unused", "ConvertSecondaryConstructorToPrimary")
internal class Data<T, P>() {

    val items: MutableList<T?> = ArrayList()
    var param: P? = null

    var size: Int = 0
        get() = items.size

    constructor(items: List<T?>?, param: P?) : this() {
        update(items, param)
    }

    operator fun get(position: Int): T? = if (position < 0 || position >= items.size) {
        null
    } else {
        items[position]
    }

    fun update(items: List<T?>?) {
        this.items.clear()
        if (items != null) {
            this.items.addAll(items)
        }
    }

    fun update(param: P?) {
        this.param = param
    }

    fun update(items: List<T?>?, param: P?) {
        update(items)
        update(param)
    }

    fun update(data: Data<T, P>?) {
        update(data?.items, data?.param)
    }
}