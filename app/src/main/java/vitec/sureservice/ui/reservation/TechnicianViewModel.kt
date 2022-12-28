package vitec.sureservice.ui.reservation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vitec.sureservice.data.model.Technician
import vitec.sureservice.data.remote.ApiClient

class TechnicianViewModel(application: Application): AndroidViewModel(application) {
    private val technicianInterface = ApiClient.buildTechnician()

    private var _technician = MutableLiveData<Technician>()
    val technician get() = _technician

    private var technicianDto: TechnicianDto = TechnicianDto()

    fun createTechnicianDto(technician: Technician, valoration: Int) {
        technicianDto = TechnicianDto(technician.username, technician.email,technician.name,technician.last_name,technician.telephone_number,technician.dni,technician.professional_profile,technician.district,technician.speciality.id, valoration, technician.disponibility)
    }

    fun editTechnician(tech: Technician, valoration: Int) {
        viewModelScope.launch {
            try {
                createTechnicianDto(tech, valoration)
                val editTechnician = technicianInterface.editTechnician(tech.id, technicianDto)
                editTechnician.enqueue(object: Callback<Technician> {
                    override fun onResponse(
                        call: Call<Technician>,
                        response: Response<Technician>
                    ) {
                        technician.postValue(response.body()!!)
                    }

                    override fun onFailure(call: Call<Technician>, t: Throwable) {
                        Log.d("Fail", t.toString())
                    }
                })
            }
            catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }
    }

}