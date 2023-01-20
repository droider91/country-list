package com.ila.data.repository.country

import android.content.Context
import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ila.data.entities.CountriesItemData
import com.ila.data.mapper.CountryDataMapper
import com.ila.data.util.DispatchersProvider
import com.ila.domain.entities.CountriesDomain
import com.ila.domain.util.Result
import kotlinx.coroutines.withContext
import java.io.IOException
import java.nio.charset.Charset

/**
 * Created by devendra on 17/01/2023
 */
class CountryRemoteDataSource(
    private val dispatchers: DispatchersProvider,
    private val context: Context
) : CountryDataSource.Local {

    override suspend fun getCountriesFromAssets(): Result<CountriesDomain> =
        withContext(dispatchers.getIO()) {
            return@withContext try {
                val str = context.getJsonFromAssets("demo_data.json")
                val result: ArrayList<CountriesItemData> =
                    Gson().fromJson(str, object : TypeToken<List<CountriesItemData>>() {}.type)
                Result.Success(CountryDataMapper.toDomain(result))
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
}

fun Context.getJsonFromAssets(fileName: String): String? {
    val jsonString: String
    try {
        val assetManager: AssetManager = assets
        val inputStream = assetManager.open(fileName)

        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        jsonString = String(buffer, Charset.defaultCharset())
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    }

    return jsonString
}