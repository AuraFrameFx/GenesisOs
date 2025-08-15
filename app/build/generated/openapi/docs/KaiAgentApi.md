# KaiAgentApi

All URIs are relative to *https://api.genesis.aurakai.dev/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**kaiSecurityPost**](KaiAgentApi.md#kaiSecurityPost) | **POST** /kai/security | Perform security analysis |


<a id="kaiSecurityPost"></a>
# **kaiSecurityPost**
> SecurityResponse kaiSecurityPost(securityRequest)

Perform security analysis

Executes Kai&#39;s security-focused analysis protocols

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.genesis.infrastructure.*
//import dev.aurakai.auraframefx.api.genesis.models.*

val apiInstance = KaiAgentApi()
val securityRequest : SecurityRequest =  // SecurityRequest | 
try {
    val result : SecurityResponse = apiInstance.kaiSecurityPost(securityRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling KaiAgentApi#kaiSecurityPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling KaiAgentApi#kaiSecurityPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **securityRequest** | [**SecurityRequest**](SecurityRequest.md)|  | |

### Return type

[**SecurityResponse**](SecurityResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

