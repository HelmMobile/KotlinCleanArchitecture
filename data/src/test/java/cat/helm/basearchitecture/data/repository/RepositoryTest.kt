package cat.helm.basearchitecture.data.repository

import cat.helm.basearchitecture.data.repository.datasource.CacheDataSource
import cat.helm.basearchitecture.data.repository.datasource.ReadableDataSource
import cat.helm.basearchitecture.data.repository.datasource.WritableDataSource
import cat.helm.basearchitecture.data.repository.policy.ReadPolicy
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Created by Borja on 23/3/17.
 */
class RepositoryTest {


    private lateinit var repository: Repository<String,String>
    private val cacheDataSource: CacheDataSource<String,String> = mock()
    private val readableDataSource: ReadableDataSource<String,String> = mock()
    private val writableDataSource: WritableDataSource<String,String> = mock()

    @Before
    fun setUp(){
        repository = Repository()
        repository.cacheDataSources.add(cacheDataSource)
        repository.readableDataSources.add(readableDataSource)
        repository.writableDataSources.add(writableDataSource)
    }

    @Test fun shouldGetDataFromCacheDataSourceIfthereAreAndPolicyIsNotSet(){
        whenever(cacheDataSource.getByKey(any())).thenReturn("")
        whenever(cacheDataSource.isValid(any())).thenReturn(true)

        repository.getByKey("")

        verify(cacheDataSource).getByKey(any())
        verifyZeroInteractions(readableDataSource)
        verify(cacheDataSource).addOrUpdate(any())
    }

    @Test fun shouldGetDataFromReadableDAtaSourceIfThereArentInCacheAndPolicyIsNotSet(){
        whenever(cacheDataSource.getByKey(any())).thenReturn(null)
        whenever(readableDataSource.getByKey(any())).thenReturn("")

        repository.getByKey("")
        verify(readableDataSource).getByKey(any())
        verify(cacheDataSource).addOrUpdate(any())
    }

    @Test fun shouldNotPopulateCachesIfThereisNoData(){
        whenever(cacheDataSource.getByKey(any())).thenReturn(null)
        whenever(readableDataSource.getByKey(any())).thenReturn(null)

        repository.getByKey("")

        verify(cacheDataSource, never()).addOrUpdate(any())
    }

    @Test fun shouldNeverSearchInCacheIfPolicyIsReadableOnly(){

        repository.getByKey("",ReadPolicy.READABLE_ONLY)

        verifyZeroInteractions(cacheDataSource)
        verify(readableDataSource, times(1)).getByKey(any())
    }

    @Test fun shouldNeverSearchInReadableIfPolicyIsCacheOnly(){
        repository.getByKey("",ReadPolicy.CACHE_ONLY)
        verifyZeroInteractions(readableDataSource)
        verify(cacheDataSource).getByKey(any())
    }

    @Test fun getAllShouldGetDataFromCacheDataStoreIfThereIsAndPolicyIsNotSet(){
        val returnList = ArrayList<String>()
        returnList.add("")
        whenever(cacheDataSource.getAll()).thenReturn(returnList)
        whenever(cacheDataSource.isValid(any())).thenReturn(true)

        repository.getAll()

        verify(cacheDataSource).getAll()
        verifyZeroInteractions(readableDataSource)
        verify(cacheDataSource).addOrUpdateAll(any())

    }

    @Test fun getAllShouldGetDataFromReadableDataStoreIfThereIsAndPolicyIsNotSet(){
        val returnList = ArrayList<String>()
        returnList.add("")
        whenever(cacheDataSource.getAll()).thenReturn(null)
        whenever(readableDataSource.getAll()).thenReturn(returnList)

        repository.getAll()

        verify(readableDataSource).getAll()
        verify(cacheDataSource).addOrUpdateAll(any())

    }

    @Test fun getAllShouldNeverPopulateCachesIfThereIsNoData(){
        whenever(cacheDataSource.getAll()).thenReturn(null)
        whenever(readableDataSource.getAll()).thenReturn(null)

        repository.getAll()
        verify(cacheDataSource, never()).addOrUpdateAll(ArrayList<String>())
    }

    @Test fun getAllShouldNeverSearchDataInCacheDataSourceIfPolicyIsReadableOnly(){
        whenever(readableDataSource.getAll()).thenReturn(null)

        repository.getAll(ReadPolicy.READABLE_ONLY)

        verifyZeroInteractions(cacheDataSource)
        verify(readableDataSource).getAll()
    }

    @Test fun getAllShouldNeverSearchDataInReadableDataSourceIfPolicyIsCacheOnly(){
        whenever(cacheDataSource.getAll()).thenReturn(null)

        repository.getAll(ReadPolicy.CACHE_ONLY)

        verifyZeroInteractions(readableDataSource)
        verify(cacheDataSource).getAll()

    }

    @Test fun addOrUpdateShouldWriteOnWritableDataStore(){
        whenever(writableDataSource.addOrUpdate(any())).thenReturn("")

        repository.addOrUpdate("")

        verify(writableDataSource).addOrUpdate(any())
        verify(cacheDataSource).addOrUpdate(any())
    }

    @Test fun addOrUpdateShouldNotPopulateCachesIfTereArentUpdatedValues(){
        whenever(writableDataSource.addOrUpdate(any())).thenReturn(null)

        repository.addOrUpdate("")

        verify(writableDataSource).addOrUpdate(any())
        verifyZeroInteractions(cacheDataSource)
    }

    @Test fun addOrUpdateAllShouldWriteOnWritableDataStore(){
        whenever(writableDataSource.addOrUpdateAll(any())).thenReturn(Collections.emptyList())

        repository.addOrUpdateAll(Collections.emptyList())

        verify(writableDataSource).addOrUpdateAll(any())
        verify(cacheDataSource).addOrUpdateAll(any())
    }

    @Test fun addOrUpdateAllShouldNotPopulateCachesIfThereArentUpdatedValues(){
        whenever(writableDataSource.addOrUpdateAll(any())).thenReturn(null)

        repository.addOrUpdateAll(Collections.emptyList())

        verify(writableDataSource).addOrUpdateAll((any()))
        verifyZeroInteractions(cacheDataSource)
    }

    @Test fun deleteByKeyShouldDeleteFromWritableDataSources(){

        repository.deleteByKey("")

        verify(writableDataSource).deleteByKey(any())
    }

    @Test fun deleteByKeyShouldDeleteFromReadableDataSources(){
        repository.deleteByKey("")

        verify(cacheDataSource).deleteByKey(any())
    }
}