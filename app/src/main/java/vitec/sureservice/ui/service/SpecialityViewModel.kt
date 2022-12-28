package vitec.sureservice.ui.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vitec.sureservice.data.model.Speciality
import vitec.sureservice.data.model.Technician
import vitec.sureservice.data.remote.ApiClient

class SpecialityViewModel: ViewModel() {

    private val specialityInterface = ApiClient.buildSpeciality()

    private var _specialities = MutableLiveData<List<Speciality>>()
    val specialities get() = _specialities

    fun getAllSpecialities() {
        viewModelScope.launch {
            try {
                val getAllSpecialities = specialityInterface?.getAllSpecialities()
                getAllSpecialities?.enqueue(object: Callback<List<Speciality>> {
                    override fun onResponse(
                        call: Call<List<Speciality>>,
                        response: Response<List<Speciality>>
                    ) { specialities.postValue(response.body()!!) }

                    override fun onFailure(call: Call<List<Speciality>>, t: Throwable) {
                        Log.d("Fail", t.toString())
                    }
                })
            }
            catch (e: Exception) {
                Log.e("Error retrieve special.", e.toString())
            }
        }
    }

}