package vitec.sureservice.ui.service

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vitec.sureservice.data.model.Technician
import vitec.sureservice.data.remote.ApiClient

class ServiceViewModel: ViewModel() {

    private val technicianInterface = ApiClient.buildTechnician()

    private var _technicians = MutableLiveData<List<Technician>>()
    val technicians get() = _technicians

    private var _technician = MutableLiveData<Technician>()
    val technician get() = _technician

    fun getAllTechnicians() {
        viewModelScope.launch {
            try {
                val getAllTechnicians = technicianInterface?.getAllTechnicians()
                getAllTechnicians?.enqueue(object: Callback<List<Technician>> {
                    override fun onResponse(
                        call: Call<List<Technician>>,
                        response: Response<List<Technician>>
                    ) { technicians.postValue(response.body()!!) }

                    override fun onFailure(call: Call<List<Technician>>, t: Throwable) {
                        Log.d("Fail", t.toString())
                    }
                })
            }
            catch (e: Exception) {
                Log.e("Error retrieve technic.", e.toString())
            }
        }
    }

    fun getTechniciansBySpeciality(specialityId: Int, district: String, rating: String) {
        viewModelScope.launch {
            try {
                val getAllTechniciansBySpeciality = technicianInterface?.getTechniciansBySpeciality(specialityId)
                getAllTechniciansBySpeciality?.enqueue(object: Callback<List<Technician>> {
                    @RequiresApi(Build.VERSION_CODES.N)
                    override fun onResponse(
                        call: Call<List<Technician>>,
                        response: Response<List<Technician>>
                    )
                    {
                        var auxResponse: MutableList<Technician> = mutableListOf()
                        //var aux: MutableList<Technician> = mutableListOf()
                        auxResponse.addAll(response.body()!!)

                        if (district != ""){
                            /*
                            auxResponse.forEach {
                                    technician ->
                                if (technician.district.contains(district)){
                                    aux.add(technician)
                                }
                            }*/

                            auxResponse.removeIf { tech -> !tech.district.contains(district) }
                            //auxResponse = aux
                            //aux = mutableListOf()

                        }


                        if (rating != "") {
                            /*
                            auxResponse.forEach {
                                technician ->
                                if (technician.valoration == rating.toInt()){
                                    aux.add(technician)
                                }
                            }*/
                            auxResponse.removeIf { tech -> tech.valoration != rating.toInt() }
                            //auxResponse = aux
                            //aux = mutableListOf()

                        }
                        technicians.postValue(auxResponse)
                    }
                    override fun onFailure(call: Call<List<Technician>>, t: Throwable) {
                        Log.d("Fail", t.toString())
                    }
                })
            }
            catch (e: Exception) {
                Log.e("Error retrieve technic.", e.toString())
            }
        }
    }

    fun getATechnicianById(id: Int) {
        viewModelScope.launch {
            try {
                val getATechnicianById = technicianInterface?.getTechnicianById(id)
                getATechnicianById?.enqueue(object: Callback<Technician> {
                    override fun onResponse(
                        call: Call<Technician>,
                        response: Response<Technician>
                    ) { technician.postValue(response.body()!!) }

                    override fun onFailure(call: Call<Technician>, t: Throwable) {
                        Log.d("Fail", t.toString())
                    }
                })

            }
            catch (e: Exception) {
                Log.e("Error get a technic.", e.toString())
            }
        }
    }

}