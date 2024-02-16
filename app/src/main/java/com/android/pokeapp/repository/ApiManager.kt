package com.android.pokeapp.repository


import android.content.Context
import com.android.pokeapp.R
import com.android.pokeapp.utils.toJsonElement
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.delete
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import org.json.JSONObject
import org.json.JSONTokener
import javax.inject.Inject


class ApiManager @Inject constructor(
    private val client: HttpClient,
    private val appContext: Context
) {

    suspend fun makeRequest(
        url: String,
        reqMethod: HttpMethod?,
        bodyMap: HashMap<String, Any>? = null,
        multiPartForm: MultiPartFormDataContent? = null,
        parameterFormData: ArrayList<Pair<String, Any>>? = null,
        successCallback: suspend (response: JSONObject) -> Unit,
        failureCallback: suspend (error: ApiResult.Error<*>) -> Unit,
    ) {
        try {

            val httpResponse: HttpResponse? =
                when (reqMethod) {
                    HttpMethod.Post -> {
                        client.post(url) {
                            if (multiPartForm != null) {
                                body = multiPartForm
                            }
                            if (bodyMap != null) {
                                contentType(ContentType.Application.Json)
                                body = bodyMap.toJsonElement()
                            }
                            if (parameterFormData != null) {
                                formData {
                                    for (param in parameterFormData) {
                                        parameter(param.first, param.second)
                                    }
                                }
                            }
                        }
                    }

                    HttpMethod.Put -> {
                        client.put(url) {
                            if (multiPartForm != null) {
                                body = multiPartForm
                            }

                            if (bodyMap != null) {
                                contentType(ContentType.Application.Json)
                                body = bodyMap.toJsonElement()
                            }
                            if (parameterFormData != null) {
                                formData {
                                    for (param in parameterFormData) {
                                        parameter(param.first, param.second)
                                    }
                                }
                            }

                        }
                    }

                    HttpMethod.Patch -> {
                        client.patch(url) {
                            if (multiPartForm != null) {
                                body = multiPartForm
                            }
                            if (bodyMap != null) {
                                contentType(ContentType.Application.Json)
                                body = bodyMap.toJsonElement()
                            }
                            if (parameterFormData != null) {
                                formData {
                                    for (param in parameterFormData) {
                                        parameter(param.first, param.second)
                                    }
                                }
                            }
                        }
                    }

                    HttpMethod.Get -> {
                        client.get(url) {
                            contentType(ContentType.Application.Json)
                            if (parameterFormData != null) {
                                formData {
                                    for (param in parameterFormData) {
                                        parameter(param.first, param.second)
                                    }
                                }
                            }
                        }
                    }

                    HttpMethod.Delete -> {
                        client.delete(url) {
                            contentType(ContentType.Application.Json)
                            if (parameterFormData != null) {
                                formData {
                                    for (param in parameterFormData) {
                                        parameter(param.first, param.second)
                                    }
                                }
                            }
                        }
                    }

                    else -> {
                        null
                    }
                }

            val response: String? = httpResponse?.receive()
            val jsonObject = JSONTokener(response).nextValue() as JSONObject
            successCallback(jsonObject)

        } catch (e: RedirectResponseException) {
            // 3xx - Response
            failureCallback(
                ApiResult.Error<Any>(
                    apiError = ApiError(
                        e.response.status.value,
                        e.response.content.toString(),
                    )
                )
            )
        } catch (e: ClientRequestException) {
            // 4xx - Response
            failureCallback(
                ApiResult.Error<Any>(
                    apiError = ApiError(
                        e.response.status.value,
                        e.response.content.toString(),
                    )
                )
            )

        } catch (e: ServerResponseException) {
            // 5xx - Response
            failureCallback(
                ApiResult.Error<Any>(
                    apiError = ApiError(
                        e.response.status.value,
                        appContext.getString(R.string.error_message_at_server),
                    )
                )
            )
        } catch (e: Exception) {
            failureCallback(
                ApiResult.Error<Any>(
                    apiError = ApiError(500, appContext.getString(R.string.error_message_at_server))
                )
            )
        }
    }
}
