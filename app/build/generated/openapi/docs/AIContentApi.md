# AIContentApi

All URIs are relative to *https://api.auraframefx.com/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**aiGenerateImageDescriptionPost**](AIContentApi.md#aiGenerateImageDescriptionPost) | **POST** /ai/generate/image-description | Generate image description using AI |
| [**aiGenerateTextPost**](AIContentApi.md#aiGenerateTextPost) | **POST** /ai/generate/text | Generate text using AI |


<a id="aiGenerateImageDescriptionPost"></a>
# **aiGenerateImageDescriptionPost**
> GenerateImageDescriptionResponse aiGenerateImageDescriptionPost(generateImageDescriptionRequest)

Generate image description using AI

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.ai.infrastructure.*
//import dev.aurakai.auraframefx.api.ai.models.*

val apiInstance = AIContentApi()
val generateImageDescriptionRequest : GenerateImageDescriptionRequest =  // GenerateImageDescriptionRequest | 
try {
    val result : GenerateImageDescriptionResponse = apiInstance.aiGenerateImageDescriptionPost(generateImageDescriptionRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AIContentApi#aiGenerateImageDescriptionPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AIContentApi#aiGenerateImageDescriptionPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **generateImageDescriptionRequest** | [**GenerateImageDescriptionRequest**](GenerateImageDescriptionRequest.md)|  | |

### Return type

[**GenerateImageDescriptionResponse**](GenerateImageDescriptionResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="aiGenerateTextPost"></a>
# **aiGenerateTextPost**
> GenerateTextResponse aiGenerateTextPost(generateTextRequest)

Generate text using AI

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.ai.infrastructure.*
//import dev.aurakai.auraframefx.api.ai.models.*

val apiInstance = AIContentApi()
val generateTextRequest : GenerateTextRequest =  // GenerateTextRequest | 
try {
    val result : GenerateTextResponse = apiInstance.aiGenerateTextPost(generateTextRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AIContentApi#aiGenerateTextPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AIContentApi#aiGenerateTextPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **generateTextRequest** | [**GenerateTextRequest**](GenerateTextRequest.md)|  | |

### Return type

[**GenerateTextResponse**](GenerateTextResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

