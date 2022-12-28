package vitec.sureservice.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "client")
data class Client (

    @PrimaryKey
    var id: Long = 0,

    @ColumnInfo
    var username: String = "",

) {
    @Ignore
    var dni: String = ""

    @Ignore
    var email: String = ""

    @Ignore
    var last_name: String = ""

    @Ignore
    var name: String = ""

    @Ignore
    var password: String = ""

    @Ignore
    var telephone_number: String = ""

    @Ignore
    var image_url: String = ""

    @Ignore
    var image_Id: String = ""

    @Ignore
    var rol_id: Long = 1

    @Ignore
    var roles: Array<String> = emptyArray()
}