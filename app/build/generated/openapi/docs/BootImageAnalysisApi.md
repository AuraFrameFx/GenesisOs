# BootImageAnalysisApi

All URIs are relative to *http://localhost:8080/api/v1/romtools*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**analyzeBootImage**](BootImageAnalysisApi.md#analyzeBootImage) | **POST** /boot-image/analyze | Analyze boot image |


<a id="analyzeBootImage"></a>
# **analyzeBootImage**
> BootAnalysisResult analyzeBootImage(bootImage, analysisLevel)

Analyze boot image

Performs comprehensive analysis of Android boot.img files

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.romtools.infrastructure.*
//import dev.aurakai.auraframefx.api.romtools.models.*

val apiInstance = BootImageAnalysisApi()
val bootImage : java.io.File = BINARY_DATA_HERE // java.io.File | Boot image file to analyze
val analysisLevel : kotlin.String = analysisLevel_example // kotlin.String | Level of analysis to perform
try {
    val result : BootAnalysisResult = apiInstance.analyzeBootImage(bootImage, analysisLevel)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling BootImageAnalysisApi#analyzeBootImage")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling BootImageAnalysisApi#analyzeBootImage")
    e.printStackTrace()
}
```

### Parameters
| **bootImage** | **java.io.File**| Boot image file to analyze | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **analysisLevel** | **kotlin.String**| Level of analysis to perform | [optional] [default to detailed] [enum: basic, detailed, security] |

### Return type

[**BootAnalysisResult**](BootAnalysisResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

