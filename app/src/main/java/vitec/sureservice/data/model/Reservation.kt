package vitec.sureservice.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant.now
import java.time.LocalDateTime
import java.util.*

class Reservation (
    val id: Int,
    val date_of: LocalDateTime,
    val status: Int)

{
    @RequiresApi(Build.VERSION_CODES.O)
    constructor(): this(0, LocalDateTime.now(), 0)
}