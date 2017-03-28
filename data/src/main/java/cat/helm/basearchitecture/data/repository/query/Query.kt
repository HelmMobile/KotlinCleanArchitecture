package cat.helm.basearchitecture.data.repository.query

/**
 * Created by Borja on 21/3/17.
 */
interface Query<out Value>{

    fun  queryAll (parameters: HashMap<String,*>? = null): Collection<Value>
    fun  query (parameters: HashMap<String,*>? = null): Value

}