package by.shostko.android.adapter.simple.v2

@Suppress("unused")
interface FilterDelegate {

    abstract class Parametrized<T, P, F> {

        abstract fun initialConstraint(): F?

        abstract fun check(position: Int, viewType: Int, item: T?, param: P?, constraint: F?): Boolean

        abstract fun checkAll(param: P?, constraint: F?): Boolean

        abstract fun convert(constraint: F?): CharSequence?

        abstract fun convert(constraint: CharSequence?): F?

        companion object {
            fun <T, P> empty() = object : Parametrized<T, P, Unit>() {
                override fun initialConstraint(): Unit? = null

                override fun check(position: Int, viewType: Int, item: T?, param: P?, constraint: Unit?): Boolean = true

                override fun checkAll(param: P?, constraint: Unit?): Boolean = true

                override fun convert(constraint: Unit?): CharSequence? = null

                override fun convert(constraint: CharSequence?): Unit? = null
            }
        }
    }

    abstract class Items<T, F> : Parametrized<T, Unit, F>() {

        final override fun check(position: Int, viewType: Int,
                                 item: T?, param: Unit?, constraint: F?
        ): Boolean = check(position, viewType, item, constraint)

        final override fun checkAll(param: Unit?, constraint: F?): Boolean = checkAll(constraint)

        abstract fun check(position: Int, viewType: Int, item: T?, constraint: F?): Boolean

        abstract fun checkAll(constraint: F?): Boolean

        companion object {
            fun <T> empty() = object : Items<T, Unit>() {

                override fun initialConstraint(): Unit? = null

                override fun check(position: Int, viewType: Int, item: T?, constraint: Unit?): Boolean = true

                override fun checkAll(constraint: Unit?): Boolean = true

                override fun convert(constraint: Unit?): CharSequence? = null

                override fun convert(constraint: CharSequence?): Unit? = null
            }
        }
    }
}