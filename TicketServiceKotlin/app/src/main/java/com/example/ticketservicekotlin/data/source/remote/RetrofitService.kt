package com.example.ticketservicekotlin.data.source.remote

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {

    @POST("/user")
    fun registration(@Body body: NetworkUserRegistration): Deferred<Response<NetworkStatus>>

    @POST("/login")
    fun login(@Body body: NetworkLoginRequest): Deferred<Response<NetworkLoginResponse>>

    @GET("/events/{page}/{page_size}")
    fun getCurrentEvents(@Path("page") page: Int,
                         @Path("page_size") pageSize: Int): Deferred<List<NetworkEvent>>

    @POST("/events/bydate/{page}/{page_size}")
    fun getCurrentEventsByDates(@Path("page") page: Int,
                                @Path("page_size") pageSize: Int,
                                @Body body: NetworkDateRange): Deferred<Response<NetworkEventContainer>>

    @GET("/event/{eventId}")
    fun getEvent(@Path("eventId") id: String): Deferred<Response<NetworkEvent>>

    @GET("/events/rest/{eventId}")
    fun getEventInfo(@Path("eventId") id: String): Deferred<Response<NetworkEventInfo>>

    @GET("/event/{eventId}/{isShort}")
    fun getHallStructure(@Path("eventId") id: String,
                         @Path("isShort") short: Boolean): Deferred<Response<NetworkHallStructure>>

    @POST("/event/book")
    fun bookSeats(@Body body: NetworkEventBooking): Job

    @HTTP(method = "DELETE", path = "/event/book/", hasBody = true)
    fun cancelBooking(@Body body: NetworkEventBooking): Job

    @POST("/user/to_sell")
    fun sellTickets(@Body body: NetworkEventBooking): Deferred<Response<String>>
}