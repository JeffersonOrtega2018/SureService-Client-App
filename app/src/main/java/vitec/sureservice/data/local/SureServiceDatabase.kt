package vitec.sureservice.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import vitec.sureservice.data.model.Client

@Database(entities = [Client::class], version = 1, exportSchema = false)
abstract class SureServiceDatabase: RoomDatabase() {

    abstract fun clientDao(): ClientDao

    companion object {
        fun getInstance(context: Context): SureServiceDatabase {
            val db = Room.databaseBuilder(
                context,
                SureServiceDatabase::class.java,
                "sure-service-db"
            ).allowMainThreadQueries().build()
            return db
        }
    }
}