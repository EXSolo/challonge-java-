/*
 * Copyright 2017 Stefan Geyer <stefangeyer at outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package at.stefangeyer.challonge.serializer.gson.adapter

import at.stefangeyer.challonge.model.Attachment
import at.stefangeyer.challonge.serializer.gson.util.getOrNull
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.time.OffsetDateTime

/**
 * Type adapter for the [Attachment] class.
 * The received json object is being unpacked.
 *
 * @author Stefan Geyer
 * @version 2018-07-06
 */
class AttachmentAdapter : JsonDeserializer<Attachment> {

    @Throws(JsonParseException::class)
    override fun deserialize(jsonElement: JsonElement, type: Type, context: JsonDeserializationContext): Attachment {
        var e = jsonElement.asJsonObject

        if (e.has("match_attachment")) {
            e = e.get("match_attachment").asJsonObject
        }

        val id = e.get("id").asLong
        val matchId = e.get("match_id").asLong
        val userId = e.get("user_id").asLong
        val description = e.getOrNull("description")?.asString
        val url = e.getOrNull("url")?.asString
        val originalFileName = e.getOrNull("original_file_name")?.asString
        val createdAt = context.deserialize<OffsetDateTime>(e.get("created_at"), OffsetDateTime::class.java)
        val updatedAt = context.deserialize<OffsetDateTime>(e.get("updated_at"), OffsetDateTime::class.java)
        val assetFileName = e.getOrNull("asset_file_name")?.asString
        val assetContentType = e.getOrNull("asset_content_type")?.asString
        val assetFileSize = e.getOrNull("asset_file_size")?.asLong
        var assetUrl = e.getOrNull("asset_url")?.asString
        if (assetUrl != null) assetUrl = "https:$assetUrl"

        return Attachment(
                id = id, matchId = matchId, userId = userId, description = description, url = url, originalFileName = originalFileName,
                createdAt = createdAt, updatedAt = updatedAt, assetFileName = assetFileName, assetContentType = assetContentType,
                assetFileSize = assetFileSize, assetUrl = assetUrl)
    }
}