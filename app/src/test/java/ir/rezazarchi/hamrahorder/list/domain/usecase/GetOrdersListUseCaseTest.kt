package ir.rezazarchi.hamrahorder.list.domain.usecase

import assertk.assertThat
import assertk.assertions.first
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.size
import io.mockk.coEvery
import io.mockk.mockk
import ir.rezazarchi.hamrahorder.core.data.NetworkError
import ir.rezazarchi.hamrahorder.core.data.Result
import ir.rezazarchi.hamrahorder.list.data.remote.api.GetOrdersListService
import ir.rezazarchi.hamrahorder.list.data.remote.dto.OrdersListResponse
import ir.rezazarchi.hamrahorder.list.data.remote.mapper.toOrder
import ir.rezazarchi.hamrahorder.list.data.remote.repo.GetOrdersListRepoImplementation
import ir.rezazarchi.hamrahorder.list.domain.repo.GetOrdersListRepo
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GetOrdersListUseCaseTest {

    private lateinit var getOrdersListUseCase: GetOrdersListUseCase
    private lateinit var getOrdersListRepo: GetOrdersListRepo
    private lateinit var getOrdersListService: GetOrdersListService

    @Before
    fun setUp() {
        getOrdersListService = mockk(relaxed = true)
        getOrdersListRepo = GetOrdersListRepoImplementation(getOrdersListService)
        getOrdersListUseCase = GetOrdersListUseCase(getOrdersListRepo)
    }

    @Test
    fun `loading list fails with 500 server error`() = runBlocking {
        coEvery {
            getOrdersListService.getOrdersList()
        } returns Response.error(500, mockk(relaxed = true))
        val result = getOrdersListUseCase()
        println("Result is Error")
        assertThat(result).isInstanceOf(Result.Error::class)
        println("Result error is server error")
        assertThat(result).isEqualTo(Result.Error(NetworkError.SERVER_ERROR))
    }

    @Test
    fun `loading list fails with 400 server error`() = runBlocking {
        coEvery {
            getOrdersListService.getOrdersList()
        } returns Response.error(400, mockk(relaxed = true))
        val result = getOrdersListUseCase()
        println("Result is Error")
        assertThat(result).isInstanceOf(Result.Error::class)
        println("Result error is bad request")
        assertThat(result).isEqualTo(Result.Error(NetworkError.BAD_REQUEST))
    }

    @Test
    fun `get orders list succeeds`() = runBlocking {
        val ordersListResponse = listOf<OrdersListResponse>(
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true),
        )
        coEvery {
            getOrdersListService.getOrdersList()
        } returns Response.success(201, ordersListResponse)
        val result = getOrdersListUseCase()
        println("Result is Success")
        assertThat(result).isInstanceOf(Result.Success::class)
        println("Result is the orders list with size of 5")
        assertThat((result as Result.Success).data).size().isEqualTo(5)
        println("First item of result is same as first item of the api response")
        assertThat(result.data).first()
            .isEqualTo(ordersListResponse.map { it.toOrder() }.first())
    }
}