# BootImageModificationApi

All URIs are relative to *http://localhost:8080/api/v1/romtools*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**modifyBootImage**](BootImageModificationApi.md#modifyBootImage) | **POST** /boot-image/modify | Modify boot image |


<a id="modifyBootImage"></a>
# **modifyBootImage**
> ModificationResult modifyBootImage(bootModificationRequest)

Modify boot image

Apply modifications to boot image (requires root)

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.romtools.infrastructure.*
//import dev.aurakai.auraframefx.api.romtools.models.*

val apiInstance = BootImageModificationApi()
val bootModificationRequest : BootModificationRequest =  // BootModificationRequest | 
try {
    val result : ModificationResult = apiInstance.modifyBootImage(bootModificationRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling BootImageModificationApi#modifyBootImage")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling BootImageModificationApi#modifyBootImage")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **bootModificationRequest** | [**BootModificationRequest**](BootModificationRequest.md)|  | |

### Return type

[**ModificationResult**](ModificationResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

