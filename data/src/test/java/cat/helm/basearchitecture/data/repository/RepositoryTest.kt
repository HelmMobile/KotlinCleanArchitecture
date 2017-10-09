package cat.helm.basearchitecture.data.repository

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.data.repository.datasource.CacheDataSource
import cat.helm.basearchitecture.data.repository.datasource.ReadableDataSource
import cat.helm.basearchitecture.data.repository.datasource.WritableDataSource
import cat.helm.basearchitecture.data.repository.policy.ReadPolicy
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.test.assertTrue

/**
 * Created by Borja on 23/3/17.
 */
class RepositoryTest {


    private lateinit var repository: Repository<String, String>
    private val cacheDataSource: CacheDataSource<String, String> = mock()
    private val secondCacheDataSource: CacheDataSource<String, String> = mock()
    private val readableDataSource: ReadableDataSource<String, String> = mock()
    private val secondReadableDataSource: ReadableDataSource<String,String> = mock()
    private val writableDataSource: WritableDataSource<String, String> = mock()

    @Before
    fun setUp() {
        repository = Repository()
        repository.cacheDataSources.add(cacheDataSource)
        repository.readableDataSources.add(readableDataSource)
        repository.writableDataSources.add(writableDataSource)
    }

    @Test fun shouldGetDataFromCacheDataSourceIfThereAreAndPolicyIsNotSet() {
        whenever(cacheDataSource.getByKey(any())).thenReturn(Result.Success(""))
        whenever(cacheDataSource.isValid(any())).thenReturn(true)

        val result = repository.getByKey("")

        assertTrue { result is Result.Success }
        verify(cacheDataSource).getByKey(any())
        verifyZeroInteractions(readableDataSource)
        verify(cacheDataSource).addOrUpdate(any())
    }

    @Test fun shouldGetDataFromReadableDAtaSourceIfThereArentInCacheAndPolicyIsNotSet() {
        whenever(cacheDataSource.getByKey(any())).thenReturn(Result.Failure())
        whenever(readableDataSource.getByKey(any())).thenReturn(Result.Success(""))

        val result = repository.getByKey("")

        assertTrue { result is Result.Success }
        verify(readableDataSource).getByKey(any())
        verify(cacheDataSource).addOrUpdate(any())
    }

    @Test fun shouldNotPopulateCachesIfThereisNoData() {
        whenever(cacheDataSource.getByKey(any())).thenReturn(Result.Failure())
        whenever(readableDataSource.getByKey(any())).thenReturn(Result.Failure())

        val result = repository.getByKey("")

        assertTrue { result is Result.Failure }
        verify(cacheDataSource, never()).addOrUpdate(any())
    }

    @Test fun shouldNeverSearchInCacheIfPolicyIsReadableOnly() {
        whenever(cacheDataSource.getByKey(any())).thenReturn(Result.Success(""))
        whenever(readableDataSource.getByKey(any())).thenReturn(Result.Failure())

        val result = repository.getByKey("", ReadPolicy.READABLE_ONLY)
        assertTrue { result is Result.Failure }

        verifyZeroInteractions(cacheDataSource)
        verify(readableDataSource, times(1)).getByKey(any())
    }

    @Test fun shouldNeverSearchInReadableIfPolicyIsCacheOnly() {
        whenever(cacheDataSource.getByKey(any())).thenReturn(Result.Success(""))
        whenever(readableDataSource.getByKey(any())).thenReturn(Result.Failure())

        val result = repository.getByKey("", ReadPolicy.CACHE_ONLY)
        assertTrue { result is Result.Failure }

        verifyZeroInteractions(readableDataSource)
        verify(cacheDataSource).getByKey(any())
    }

    @Test fun shouldBreakSearchIfCacheReturnsSuccessResult(){

        repository.cacheDataSources.add(secondCacheDataSource)

        whenever(cacheDataSource.getByKey(any())).thenReturn(Result.Success(""))
        whenever(cacheDataSource.isValid(any())).thenReturn(true)
        whenever(secondCacheDataSource.getByKey(any())).thenReturn(Result.Failure())

        val result = repository.getByKey("", ReadPolicy.CACHE_ONLY)
        assertTrue { result is Result.Success }

        verify(secondCacheDataSource, times(0)).getByKey(any())


    }

    @Test fun shouldBreakSearchIfReadableReturnsSuccessResult(){

        repository.readableDataSources.add(readableDataSource)

        whenever(readableDataSource.getByKey(any())).thenReturn(Result.Success(""))
        whenever(secondReadableDataSource.getByKey(any())).thenReturn(Result.Failure())

        val result = repository.getByKey("", ReadPolicy.READABLE_ONLY)
        assertTrue { result is Result.Success }

        verify(secondReadableDataSource, times(0)).getByKey(any())


    }

    @Test fun getAllShouldGetDataFromCacheDataStoreIfThereIsAndPolicyIsNotSet() {
        val returnList = ArrayList<String>()
        returnList.add("")
        whenever(cacheDataSource.getAll()).thenReturn(Result.Success(returnList))
        whenever(cacheDataSource.isValid(any())).thenReturn(true)

        val result = repository.getAll()
        assertTrue { result is Result.Success }

        verify(cacheDataSource).getAll()
        verifyZeroInteractions(readableDataSource)
        verify(cacheDataSource).addOrUpdateAll(any())

    }

    @Test fun getAllShouldGetDataFromReadableDataStoreIfThereIsAndPolicyIsNotSet() {
        val returnList = ArrayList<String>()
        returnList.add("")
        whenever(cacheDataSource.getAll()).thenReturn(Result.Failure())
        whenever(readableDataSource.getAll()).thenReturn(Result.Success(returnList))

        val result = repository.getAll()
        assertTrue { result is Result.Success }

        verify(readableDataSource).getAll()
        verify(cacheDataSource).addOrUpdateAll(any())

    }

    @Test fun getAllShouldNeverPopulateCachesIfThereIsNoData() {
        whenever(cacheDataSource.getAll()).thenReturn(Result.Failure())
        whenever(readableDataSource.getAll()).thenReturn(Result.Failure())

        val result = repository.getAll()
        assertTrue { result is Result.Failure }

        verify(cacheDataSource, never()).addOrUpdateAll(ArrayList<String>())
    }

    @Test fun getAllShouldNeverSearchDataInCacheDataSourceIfPolicyIsReadableOnly() {
        val returnList = ArrayList<String>()
        returnList.add("")
        whenever(cacheDataSource.getAll()).thenReturn(Result.Success(returnList))
        whenever(readableDataSource.getAll()).thenReturn(Result.Failure())

        val result = repository.getAll(ReadPolicy.READABLE_ONLY)
        assertTrue { result is Result.Failure }

        verifyZeroInteractions(cacheDataSource)
        verify(readableDataSource).getAll()
    }

    @Test fun getAllShouldNeverSearchDataInReadableDataSourceIfPolicyIsCacheOnly() {

        whenever(cacheDataSource.getAll()).thenReturn(Result.Failure())
        whenever(readableDataSource.getAll()).thenReturn(Result.Success(Collections.emptyList()))

        val result = repository.getAll(ReadPolicy.CACHE_ONLY)
        assertTrue { result is Result.Failure }

        verifyZeroInteractions(readableDataSource)
        verify(cacheDataSource).getAll()

    }

    @Test fun shouldBreakSearchIfThereIsDataOnCache(){
        repository.cacheDataSources.add(secondCacheDataSource)
        val returnList = ArrayList<String>()
        returnList.add("")

        whenever(cacheDataSource.getAll()).thenReturn(Result.Success(returnList))
        whenever(cacheDataSource.isValid(any())).thenReturn(true)
        whenever(secondCacheDataSource.getAll()).thenReturn(Result.Failure())

        repository.getAll()

        verify(secondCacheDataSource, never()).getAll()

    }

    @Test fun shouldBreakSearchIfThereIsDataOnReadable(){
        repository.readableDataSources.add(secondReadableDataSource)


        whenever(readableDataSource.getAll()).thenReturn(Result.Success(Collections.emptyList()))
        whenever(secondReadableDataSource.getAll()).thenReturn(Result.Failure())

        repository.getAll(ReadPolicy.READABLE_ONLY)

        verify(secondReadableDataSource, never()).getAll()

    }

    @Test fun addOrUpdateShouldWriteOnWritableDataStore() {
        whenever(writableDataSource.addOrUpdate(any())).thenReturn(Result.Success(""))

        val result = repository.addOrUpdate("")

        assertTrue { result is Result.Success }

        verify(writableDataSource).addOrUpdate(any())
        verify(cacheDataSource).addOrUpdate(any())
    }

    @Test fun addOrUpdateShouldNotPopulateCachesIfThereArentUpdatedValues() {
        whenever(writableDataSource.addOrUpdate(any())).thenReturn(Result.Failure())

        val result = repository.addOrUpdate("")
        assertTrue { result is Result.Failure }

        verify(writableDataSource).addOrUpdate(any())
        verifyZeroInteractions(cacheDataSource)
    }

    @Test fun addOrUpdateAllShouldWriteOnWritableDataStore() {
        whenever(writableDataSource.addOrUpdateAll(any())).thenReturn(Result.Success(Collections.emptyList()))

        repository.addOrUpdateAll(Collections.emptyList())

        verify(writableDataSource).addOrUpdateAll(any())
        verify(cacheDataSource).addOrUpdateAll(any())
    }

    @Test fun addOrUpdateAllShouldNotPopulateCachesIfThereArentUpdatedValues() {
        whenever(writableDataSource.addOrUpdateAll(any())).thenReturn(Result.Failure())

        val result = repository.addOrUpdateAll(Collections.emptyList())
        assertTrue { result is Result.Failure }

        verify(writableDataSource).addOrUpdateAll((any()))
        verifyZeroInteractions(cacheDataSource)
    }

    @Test fun deleteByKeyShouldDeleteFromWritableDataSources() {

        repository.deleteByKey("")

        verify(writableDataSource).deleteByKey(any())
    }

    @Test fun deleteByKeyShouldDeleteFromReadableDataSources() {
        repository.deleteByKey("")

        verify(cacheDataSource).deleteByKey(any())
    }

   /* @Test fun shouldBreakExecutionIfQueryCacheIsSuccess(){
        repository.cacheDataSources.add(secondCacheDataSource)

        whenever(cacheDataSource.query(any(),any())).thenReturn(Result.Success(""))
        whenever(secondCacheDataSource.query(any(),any())).thenReturn(Result.Failure())


        val result = repository.query(QuerySomething::class.java, HashMap<String,String>())

        assertTrue { result is Result.Success }
        verify(secondCacheDataSource, never()).query(any(),any())
    }

    @Test fun shouldBreakExecutionIfQueryReadableIsSuccess(){
        repository.readableDataSources.add(secondReadableDataSource)

        whenever(readableDataSource.query(any(),any())).thenReturn(Result.Success(""))
        whenever(secondReadableDataSource.query(any(),any())).thenReturn(Result.Failure())


        val result = repository.query(QuerySomething::class.java, HashMap<String,String>(), ReadPolicy.READABLE_ONLY)

        assertTrue { result is Result.Success }
        verify(secondReadableDataSource, never()).query(any(),any())
    }

    @Test fun shouldBreakExecutionIfQueryAllCacheIsSuccess(){
        repository.cacheDataSources.add(secondCacheDataSource)

        whenever(cacheDataSource.queryAll(any(),any())).thenReturn(Result.Success(Collections.emptyList()))
        whenever(secondCacheDataSource.queryAll(any(),any())).thenReturn(Result.Failure())


        val result = repository.queryAll(QuerySomething::class.java, HashMap<String,String>())

        assertTrue { result is Result.Success }
        verify(secondCacheDataSource, never()).query(any(),any())
    }
    @Test fun shouldBreakExecutionIfQueryAllReadableIsSuccess(){
        repository.readableDataSources.add(secondReadableDataSource)

        whenever(readableDataSource.queryAll(any(),any())).thenReturn(Result.Success(Collections.emptyList()))
        whenever(secondReadableDataSource.queryAll(any(),any())).thenReturn(Result.Failure())


        val result = repository.queryAll(QuerySomething::class.java, HashMap<String,String>(), ReadPolicy.READABLE_ONLY)

        assertTrue { result is Result.Success }
        verify(secondReadableDataSource, never()).query(any(),any())
    }
    */
}