# DataveinConstructorApi

All URIs are relative to *http://localhost:8080/api/v1/romtools*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**dataveinAnalyze**](DataveinConstructorApi.md#dataveinAnalyze) | **POST** /datavein/analyze | DataveinConstructor analysis |


<a id="dataveinAnalyze"></a>
# **dataveinAnalyze**
> DataveinAnalysisResult dataveinAnalyze(romFile, analysisType)

DataveinConstructor analysis

Advanced ROM analysis using DataveinConstructor AI

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.romtools.infrastructure.*
//import dev.aurakai.auraframefx.api.romtools.models.*

val apiInstance = DataveinConstructorApi()
val romFile : java.io.File = BINARY_DATA_HERE // java.io.File | ROM file for analysis
val analysisType : kotlin.String = analysisType_example // kotlin.String | 
try {
    val result : DataveinAnalysisResult = apiInstance.dataveinAnalyze(romFile, analysisType)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DataveinConstructorApi#dataveinAnalyze")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DataveinConstructorApi#dataveinAnalyze")
    e.printStackTrace()
}
```

### Parameters
| **romFile** | **java.io.File**| ROM file for analysis | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **analysisType** | **kotlin.String**|  | [optional] [default to structure] [enum: structure, security, performance, compatibility] |

### Return type

[**DataveinAnalysisResult**](DataveinAnalysisResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

