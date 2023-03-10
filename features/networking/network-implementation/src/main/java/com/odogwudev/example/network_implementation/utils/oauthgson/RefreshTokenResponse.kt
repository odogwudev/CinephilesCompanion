/*
 * Copyright (c) 2020 Halcyon Mobile.
 * https://www.halcyonmobile.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.halcyonmobile.oauthgson

import com.google.gson.annotations.SerializedName
import com.odogwudev.example.network_implementation.utils.oauth.SessionDataResponse

/**
 * Default model implementing [SessionDataResponse].
 */
data class RefreshTokenResponse(
    @SerializedName("user_id") override val userId: String,
    @SerializedName("access_token") override val token: String,
    @SerializedName("refresh_token") override val refreshToken: String,
    @SerializedName("token_type") override val tokenType: String
) : SessionDataResponse