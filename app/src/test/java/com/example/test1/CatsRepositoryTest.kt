package com.example.test1

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.listcomponent.network.Async
import com.example.test1.network.api.MainApiInterface
import com.example.test1.network.response.CatBreedModel
import com.example.test1.persistance.CatBreedsDao
import com.example.test1.repo.CatsRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CatsRepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)

    private lateinit var mainApiInterface: MainApiInterface
    private lateinit var catBreedsDao: CatBreedsDao
    private lateinit var catsRepository: CatsRepositoryImpl

    @Before
    fun setUp() {
        mainApiInterface = mockk()
        catBreedsDao = mockk()
        catsRepository = CatsRepositoryImpl(mainApiInterface, catBreedsDao, testDispatcher, testScope)
    }

    @Test
    fun `getCatBreeds emits success when API call is successful`() = runBlocking {
        val mockResponse = listOf(CatBreedModel("id", "name", "description"))

        coEvery { mainApiInterface.getCatBreeds() } returns Response.success(mockResponse)

        val result = mutableListOf<Async<List<CatBreedModel>>>()
        catsRepository.getCatBreeds().collect {
            result.add(it)
        }

        assertEquals(result.size, 1)
        assertEquals(result[0], Async.Success(mockResponse))
    }

    @Test
    fun `getCatBreeds emits error when exception is caught`() = runBlocking {
        val exception = Exception("API call failed")
        coEvery { mainApiInterface.getCatBreeds() } throws exception

        val result = mutableListOf<Async<List<CatBreedModel>>>()
        catsRepository.getCatBreeds().collect {
            result.add(it)
        }

        assertEquals(result.size, 1)
        assertEquals(result[0], Async.Error(100, exception.message))
    }

    @Test
    fun `getBreedsFromDb emits success with data from database`() = runBlocking {
        val mockData = listOf(CatBreedModel("id", "name", "description"))

        coEvery { catBreedsDao.getBreeds() } returns mockData

        val result = mutableListOf<Async<List<CatBreedModel>>>()
        catsRepository.getBreedsFromDb().collect {
            result.add(it)
        }

        assertEquals(result.size, 1)
        assertEquals(result[0], Async.Success(mockData))
    }
}
