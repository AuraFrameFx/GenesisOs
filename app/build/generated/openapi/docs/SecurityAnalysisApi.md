# SecurityAnalysisApi

All URIs are relative to *http://localhost:8080/api/v1/romtools*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**securityScanRom**](SecurityAnalysisApi.md#securityScanRom) | **POST** /security/scan | Security scan ROM |


<a id="securityScanRom"></a>
# **securityScanRom**
> SecurityScanResult securityScanRom(securityScanRequest)

Security scan ROM

Perform security analysis on ROM components

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.romtools.infrastructure.*
//import dev.aurakai.auraframefx.api.romtools.models.*

val apiInstance = SecurityAnalysisApi()
val securityScanRequest : SecurityScanRequest =  // SecurityScanRequest | 
try {
    val result : SecurityScanResult = apiInstance.securityScanRom(securityScanRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SecurityAnalysisApi#securityScanRom")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SecurityAnalysisApi#securityScanRom")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **securityScanRequest** | [**SecurityScanRequest**](SecurityScanRequest.md)|  | |

### Return type

[**SecurityScanResult**](SecurityScanResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

