package vitec.sureservice.ui.reservation

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vitec.sureservice.data.local.SureServiceDatabase
import vitec.sureservice.data.model.Reservation
import vitec.sureservice.data.model.ServiceRequest
import vitec.sureservice.data.remote.ApiClient
import vitec.sureservice.data.remote.ReservationInterface

class ReservationViewModel(application: Application) : AndroidViewModel(application) {
    private val serviceRequestInterface = ApiClient.buildServiceRequest()
    private val reservationInterface = ApiClient.buildReservation()
    val clientDao = SureServiceDatabase.getInstance(application).clientDao()

    private var _serviceRequests = MutableLiveData<List<ServiceRequest>>()
    val serviceRequests get() = _serviceRequests

    private var _serviceRequest = MutableLiveData<ServiceRequest>()
    val serviceRequest get() = _serviceRequest

    private var _reservation = MutableLiveData<Reservation>()
    val reservation get() = _reservation

    private var reservationDto: ReservationDto = ReservationDto()

    fun getServiceRequestsByClientId() {
        viewModelScope.launch {
            try {
                val getServiceRequests = serviceRequestInterface.getServiceRequestByClientId(clientDao.getAllClients()[0].id.toInt())
                getServiceRequests.enqueue(object: Callback<List<ServiceRequest>> {
                    override fun onResponse(
                        call: Call<List<ServiceRequest>>,
                        response: Response<List<ServiceRequest>>
                    ) { serviceRequests.postValue(response.body()!!) }

                    override fun onFailure(call: Call<List<ServiceRequest>>, t: Throwable) {
                        Log.d("Fail", t.toString())
                    }
                })
            }
            catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }
    }

    fun getServiceRequestById(serviceRequestId: Int) {
        viewModelScope.launch {
            try {
                val getServiceRequestById = serviceRequestInterface.getServiceRequestById(serviceRequestId)
                getServiceRequestById.enqueue(object: Callback<ServiceRequest> {
                    override fun onResponse(
                        call: Call<ServiceRequest>,
                        response: Response<ServiceRequest>
                    ) { serviceRequest.postValue(response.body()!!) }

                    override fun onFailure(call: Call<ServiceRequest>, t: Throwable) {
                        Log.d("Fail", t.toString())
                    }
                })
            }
            catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }
    }


    fun postReservation(serviceRequestId: Int) {
        viewModelScope.launch {
            try {
                val postReservation = reservationInterface.postReservation(serviceRequestId, reservationDto)
                postReservation.enqueue(object: Callback<Reservation> {

                    override fun onFailure(call: Call<Reservation>, t: Throwable) {
                        Log.d("Fail", t.toString())
                    }

                    override fun onResponse(
                        call: Call<Reservation>,
                        response: Response<Reservation>
                    ) {
                        reservation.postValue(response.body()!!)
                    }
                })
            }
            catch (e: Exception) {
                Log.e("Error", e.toString())
        }
    }

}}