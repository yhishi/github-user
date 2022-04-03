package com.yhishi.github_user.domain.model.api

import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.*
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


object ZonedDateTimeAdapter : JsonAdapter<ZonedDateTime>() {
    private const val PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    @RequiresApi(Build.VERSION_CODES.O)
    @FromJson
    override fun fromJson(reader: JsonReader): ZonedDateTime {

        return ZonedDateTime.parse(
            reader.nextString(),
            DateTimeFormatter.ofPattern(PATTERN).withZone(ZoneId.of("UTC"))
        )
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: ZonedDateTime?) {
        writer.value(value.toString())
    }
}
