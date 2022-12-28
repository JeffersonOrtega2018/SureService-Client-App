package vitec.sureservice.ui.reservation

class TechnicianDto (
    val username: String,
    val email: String,
    val name: String,
    val last_name: String,
    val telephone_number: String,
    val dni: String,
    val professional_profile: String,
    val district: String,
    val speciality: Int,
    val valoration: Int,
    val disponibility: Int
    ) {
    constructor(): this("", "", "", "","","","","",0,0,0)
}