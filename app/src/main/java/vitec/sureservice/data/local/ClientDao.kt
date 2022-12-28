package vitec.sureservice.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import vitec.sureservice.data.model.Client

@Dao
interface ClientDao {

    @Insert
    fun insertClient(vararg client: Client)

    @Delete
    fun deleteClient(vararg client: Client)

    @Query("select * from client")
    fun getAllClients(): List<Client>
}