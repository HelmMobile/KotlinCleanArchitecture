package cat.helm.ureentool.data.repository.datasource

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.data.repository.datasource.CacheDataSource
import cat.helm.basearchitecture.data.repository.query.Query
import java.util.*

/**
 * A simple and generic in-memory cache ready to use in your repositories.
 */
open class InMemoryCacheDataSource<Key, Value : Identifiable<Key>>(val timeProvider: TimeProvider, val ttlInMillis: Long, override val queries: MutableSet<Query>) : CacheDataSource<Key, Value> {

    var lastItemsUpdate: Long = 0
    val items: MutableMap<Key, Value> = LinkedHashMap()

    override fun getByKey(key: Key): Result<Value, kotlin.Exception> {
        return Result.of { items[key] }
    }

    override fun getAll(): Result<Collection<Value>, Exception> {
        return Result.of { ArrayList(items.values) }
    }

    override fun addOrUpdate(value: Value): Result<Value, Exception> {
        return Result.of {
            synchronized(this) {
                items.put(value.getKey(), value)
                lastItemsUpdate = timeProvider.currentTimeMillis()
                value
            }
        }
    }

    override fun addOrUpdateAll(values: Collection<Value>): Result<Collection<Value>, Exception> {
        return Result.of {
            synchronized(this) {
                values.forEach {
                    value ->
                    addOrUpdate(value)
                }

                values
            }
        }
    }

    override fun deleteByKey(key: Key): Result<Unit, Exception> {
        return Result.of {
            items.remove(key)
            Unit
        }
    }

    override fun deleteAll(): Result<Unit, Exception> {
        return Result.of {
            items.clear()
            lastItemsUpdate = 0
        }
    }

    override fun isValid(value: Value): Boolean {
        return timeProvider.currentTimeMillis() - lastItemsUpdate < ttlInMillis
    }
}