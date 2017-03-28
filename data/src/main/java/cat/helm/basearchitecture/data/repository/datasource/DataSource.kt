package cat.helm.basearchitecture.data.repository.datasource

import cat.helm.basearchitecture.data.repository.implements
import cat.helm.basearchitecture.data.repository.query.Query

/**
 * Created by Borja on 6/3/17.
 */
interface CacheDataSource<in Key, Value> : ReadableDataSource<Key, Value>, WritableDataSource<Key, Value> {

    fun isValid(value: Value): Boolean
}

interface WritableDataSource<in Key, Value> {

    fun deleteByKey(key: Key)

    fun deleteAll()

    fun addOrUpdate(value: Value): Value

    fun addOrUpdateAll(values: Collection<Value>): Collection<Value>
}

interface ReadableDataSource<in Key, out Value> {

    val queries: Set<Query<*>>

    fun getByKey(key: Key): Value? {
        return null
    }

    fun getAll(): Collection<Value>? {
        return null
    }

    fun queryAll(query: Class<*>, parameters: HashMap<String, *>? = null): Collection<Value>? {
        queries.forEach {
            possibleQuery ->
            if (possibleQuery.implements(query)) {
                return possibleQuery.queryAll(parameters) as Collection<Value>?
            }
        }
        return null
    }

    fun query(query: Class<*>, parameters: HashMap<String, *>? = null): Value? {
        queries.forEach {
            possibleQuery ->
            if (possibleQuery.implements(query)) {
                return possibleQuery.query(parameters) as Value?
            }
        }
        return null
    }

}
