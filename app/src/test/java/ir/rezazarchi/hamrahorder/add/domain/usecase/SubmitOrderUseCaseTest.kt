package ir.rezazarchi.hamrahorder.add.domain.usecase

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import io.mockk.coEvery
import io.mockk.mockk
import ir.rezazarchi.hamrahorder.add.data.remote.api.SubmitOrderService
import ir.rezazarchi.hamrahorder.add.data.remote.dto.SubmitOrderResponse
import ir.rezazarchi.hamrahorder.add.data.remote.mapper.toOrder
import ir.rezazarchi.hamrahorder.add.data.remote.mapper.toSubmitOrderRequest
import ir.rezazarchi.hamrahorder.add.data.remote.repo.SubmitOrderRepositoryImplementation
import ir.rezazarchi.hamrahorder.add.domain.model.Gender
import ir.rezazarchi.hamrahorder.add.domain.model.OrderSubmissionData
import ir.rezazarchi.hamrahorder.add.domain.model.SelectedLocation
import ir.rezazarchi.hamrahorder.add.domain.repo.SubmitOrderRepository
import ir.rezazarchi.hamrahorder.core.data.NetworkError
import ir.rezazarchi.hamrahorder.core.data.Result
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class SubmitOrderUseCaseTest {

    private lateinit var submitOrderRepository: SubmitOrderRepository
    private lateinit var submitOrderUseCase: SubmitOrderUseCase
    private lateinit var submitOrderApiService: SubmitOrderService

    @Before
    fun setUp() {
        submitOrderApiService = mockk(relaxed = true)
        submitOrderRepository = SubmitOrderRepositoryImplementation(submitOrderApiService)
        submitOrderUseCase = SubmitOrderUseCase(submitOrderRepository)
    }

    @Test
    fun `submit order fails with 500 server error`() = runBlocking {
        val order = OrderSubmissionData(
            name = "Reza",
            family = "Zarchi",
            mobile = "09123456789",
            phone = "02123456789",
            address = "Tehran",
            gender = Gender.Male,
            selectedLocation = SelectedLocation(35.6892, 51.3890)
        )
        coEvery {
            submitOrderApiService.submitOrder(
                order.toSubmitOrderRequest()
            )
        } returns Response.error(500, mockk(relaxed = true))
        val result = submitOrderUseCase(order)
        println("Result is Error")
        assertThat(result).isInstanceOf(Result.Error::class)
        println("Result error is server error")
        assertThat(result).isEqualTo(Result.Error(NetworkError.SERVER_ERROR))
    }

    @Test
    fun `submit order fails with 400 server error`() = runBlocking {
        val order = OrderSubmissionData(
            name = "Reza",
            family = "Zarchi",
            mobile = "09123456789",
            phone = "02123456789",
            address = "Tehran",
            gender = Gender.Male,
            selectedLocation = SelectedLocation(35.6892, 51.3890)
        )
        coEvery {
            submitOrderApiService.submitOrder(
                order.toSubmitOrderRequest()
            )
        } returns Response.error(400, mockk(relaxed = true))
        val result = submitOrderUseCase(order)
        println("Result is Error")
        assertThat(result).isInstanceOf(Result.Error::class)
        println("Result error is bad request")
        assertThat(result).isEqualTo(Result.Error(NetworkError.BAD_REQUEST))
    }

    @Test
    fun `submit order succeeds`() = runBlocking {
        val order = OrderSubmissionData(
            name = "Reza",
            family = "Zarchi",
            mobile = "09123456789",
            phone = "02123456789",
            address = "Tehran",
            gender = Gender.Male,
            selectedLocation = SelectedLocation(35.6892, 51.3890)
        )
        val submitOrderResponse = mockk<SubmitOrderResponse>(relaxed = true)
        coEvery {
            submitOrderApiService.submitOrder(
                order.toSubmitOrderRequest()
            )
        } returns Response.success(201, submitOrderResponse)
        val result = submitOrderUseCase(order)
        println("Result is Success")
        assertThat(result).isInstanceOf(Result.Success::class)
        println("Result is the order response")
        assertThat(result).isEqualTo(Result.Success(submitOrderResponse.toOrder()))
    }
}