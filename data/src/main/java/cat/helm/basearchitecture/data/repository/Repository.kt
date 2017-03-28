package cat.helm.basearchitecture.data.repository

import cat.helm.basearchitecture.data.repository.datasource.CacheDataSource
import cat.helm.basearchitecture.data.repository.datasource.ReadableDataSource
import cat.helm.basearchitecture.data.repository.datasource.WritableDataSource
import cat.helm.basearchitecture.data.repository.policy.ReadPolicy
import java.util.*

/**
 * Created by Borja on 27/2/17.
 */
open class Repository<Key, Value> {


    val writableDataSources = ArrayList<WritableDataSource<Key, Value>>()
    val readableDataSources = ArrayList<ReadableDataSource<Key, Value>>()
    val cacheDataSources = ArrayList<CacheDataSource<Key, Value>>()


    fun getByKey(key: Key, policy: ReadPolicy = ReadPolicy.READ_ALL): Value? {
        var value: Value? = null

        if (policy.useCache()) {
            value = getValueFromCacheDataSources(key)

        }
        if (value == null && policy.useReadable()) {
            value = getValueFromReadableDataSources(key)
        }

        if (value != null) {
            populateCaches(value)
        }



        return value
    }


    fun getAll(policy: ReadPolicy = ReadPolicy.READ_ALL): Collection<Value>? {
        var values: Collection<Value>? = null

        if (policy.useCache()) {
            values = getValuesFromCacheDataSources()

        }

        if (values == null && policy.useReadable()) {
            values = getValuesFromReadableDataSources()
        }
        if (values != null) {
            populateCaches(values)
        }

        return values
    }

    fun addOrUpdate(value: Value) {

        var updatedValue: Value? = null

        writableDataSources.forEach {
            writableDataSource ->

            updatedValue = writableDataSource.addOrUpdate(value)
        }
        updatedValue?.let {
            populateCaches(updatedValue!!)
        }
    }

    fun addOrUpdateAll(values: Collection<Value>) {
        var updatedValues: Collection<Value>? = null
        writableDataSources.forEach {
            writableDataSource ->

            updatedValues = writableDataSource.addOrUpdateAll(values)
        }
        updatedValues?.let {
            populateCaches(updatedValues!!)
        }
    }

    fun deleteByKey(key: Key){
        writableDataSources.forEach {
            writableDataSource ->
                writableDataSource.deleteByKey(key)
        }
        cacheDataSources.forEach {
            cacheDataSource ->
                cacheDataSource.deleteByKey(key)
        }
    }

    fun deleteAll(){
        writableDataSources.forEach(WritableDataSource<Key, Value>::deleteAll)
        cacheDataSources.forEach(CacheDataSource<Key,Value>::deleteAll)
    }


    fun <Key, Value> Repository<Key, Value>.query(query: Class<*>,parameters: HashMap<String,*>? = null,policy: ReadPolicy = ReadPolicy.READ_ALL): Value? {
        var value: Value? = null
        if (policy.useCache()) {
            cacheDataSources.forEach {
                cacheDataSource ->
                value = cacheDataSource.query(query,parameters)
            }
        }

        if(value == null && policy.useReadable()){
            readableDataSources.forEach {
                readableDataSource ->
                value = readableDataSource.query(query,parameters)
            }
        }

        value?.let {
            populateCaches(value!!)
        }

        return value
    }

    fun <Key, Value> Repository<Key, Value>.queryAll(query: Class<*>,parameters: HashMap<String,*>? = null,policy: ReadPolicy = ReadPolicy.READ_ALL): Collection<Value>? {
        var values: Collection<Value>? = null
        if (policy.useCache()) {
            cacheDataSources.forEach {
                cacheDataSource ->
                values = cacheDataSource.queryAll(query,parameters)
            }
        }

        if(values == null && policy.useReadable()){
            readableDataSources.forEach {
                readableDataSource ->
                values = readableDataSource.queryAll(query,parameters)
            }
        }

        values?.let {
            populateCaches(values!!)
        }

        return values
    }


    private fun populateCaches(value: Value) {
        cacheDataSources.forEach {
            cacheDataSource ->

            cacheDataSource.addOrUpdate(value)
        }
    }

    private fun populateCaches(values: Collection<Value>) {
        cacheDataSources.forEach {
            cacheDataSource ->

            cacheDataSource.addOrUpdateAll(values)
        }
    }

    private fun getValuesFromCacheDataSources(



    ): Collection<Value>? {
        var values: Collection<Value>? = null

        cacheDataSources.forEach {

            cacheDataSource ->

            values = cacheDataSource.getAll()
            values?.let {
                if (areValidValues(values, cacheDataSource)) {
                    return@forEach
                } else {
                    cacheDataSource.deleteAll()
                    values = null
                }
            }
        }
        return values
    }

    private fun getValueFromReadableDataSources(key: Key): Value? {
        var value: Value? = null
        readableDataSources.forEach {
            readableDataSource ->
            value = readableDataSource.getByKey(key)
            value?.let {
                return@forEach
            }
        }
        return value
    }

    private fun getValueFromCacheDataSources(key: Key): Value? {
        var value: Value? = null
        cacheDataSources.forEach {
            cacheDataSource ->

            value = cacheDataSource.getByKey(key)
            value?.let {
                if (cacheDataSource.isValid(value!!)) {
                    return@forEach
                } else {
                    cacheDataSource.deleteByKey(key)
                    value = null
                }
            }
        }
        return value
    }

    private fun getValuesFromReadableDataSources(): Collection<Value>? {
        var values: Collection<Value>? = null
        readableDataSources.forEach {
            readableDataSource ->
            values = readableDataSource.getAll()
            values?.let {
                return@forEach
            }
        }
        return values
    }

    private fun areValidValues(values: Collection<Value>?, cacheDataSource: CacheDataSource<Key, Value>): Boolean {
        var areValidValues = false
        values?.forEach {
            value ->
            areValidValues = areValidValues or cacheDataSource.isValid(value)
        }
        return areValidValues
    }


}