package vitec.sureservice.ui.reservation

import java.text.SimpleDateFormat
import java.util.*

class ReservationDto {
    var date_of: String
    constructor() {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        this.date_of = sdf.format(Date())
    }
}