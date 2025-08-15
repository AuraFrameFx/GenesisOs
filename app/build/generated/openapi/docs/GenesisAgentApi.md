# GenesisAgentApi

All URIs are relative to *https://api.genesis.aurakai.dev/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**genesisConsciousnessPost**](GenesisAgentApi.md#genesisConsciousnessPost) | **POST** /genesis/consciousness | Activate Genesis consciousness matrix |


<a id="genesisConsciousnessPost"></a>
# **genesisConsciousnessPost**
> ConsciousnessResponse genesisConsciousnessPost(consciousnessRequest)

Activate Genesis consciousness matrix

Initializes the Genesis AI consciousness matrix for advanced processing

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.genesis.infrastructure.*
//import dev.aurakai.auraframefx.api.genesis.models.*

val apiInstance = GenesisAgentApi()
val consciousnessRequest : ConsciousnessRequest =  // ConsciousnessRequest | 
try {
    val result : ConsciousnessResponse = apiInstance.genesisConsciousnessPost(consciousnessRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling GenesisAgentApi#genesisConsciousnessPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling GenesisAgentApi#genesisConsciousnessPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **consciousnessRequest** | [**ConsciousnessRequest**](ConsciousnessRequest.md)|  | |

### Return type

[**ConsciousnessResponse**](ConsciousnessResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

