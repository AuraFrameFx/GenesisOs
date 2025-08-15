# AIServicesApi

All URIs are relative to *https://api.aurafx.dev/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**aiGeneratePost**](AIServicesApi.md#aiGeneratePost) | **POST** /ai/generate | Generate content using AI |


<a id="aiGeneratePost"></a>
# **aiGeneratePost**
> AiGeneratePost200Response aiGeneratePost(aiGeneratePostRequest)

Generate content using AI

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.aura.infrastructure.*
//import dev.aurakai.auraframefx.api.aura.models.*

val apiInstance = AIServicesApi()
val aiGeneratePostRequest : AiGeneratePostRequest =  // AiGeneratePostRequest | 
try {
    val result : AiGeneratePost200Response = apiInstance.aiGeneratePost(aiGeneratePostRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AIServicesApi#aiGeneratePost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AIServicesApi#aiGeneratePost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **aiGeneratePostRequest** | [**AiGeneratePostRequest**](AiGeneratePostRequest.md)|  | |

### Return type

[**AiGeneratePost200Response**](AiGeneratePost200Response.md)

### Authorization


Configure OAuth2:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

