# AuraAgentApi

All URIs are relative to *https://api.genesis.aurakai.dev/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**auraEmpathyPost**](AuraAgentApi.md#auraEmpathyPost) | **POST** /aura/empathy | Process empathetic AI analysis |


<a id="auraEmpathyPost"></a>
# **auraEmpathyPost**
> EmpathyResponse auraEmpathyPost(empathyRequest)

Process empathetic AI analysis

Analyzes input through Aura&#39;s empathetic processing framework

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.genesis.infrastructure.*
//import dev.aurakai.auraframefx.api.genesis.models.*

val apiInstance = AuraAgentApi()
val empathyRequest : EmpathyRequest =  // EmpathyRequest | 
try {
    val result : EmpathyResponse = apiInstance.auraEmpathyPost(empathyRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuraAgentApi#auraEmpathyPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuraAgentApi#auraEmpathyPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **empathyRequest** | [**EmpathyRequest**](EmpathyRequest.md)|  | |

### Return type

[**EmpathyResponse**](EmpathyResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

