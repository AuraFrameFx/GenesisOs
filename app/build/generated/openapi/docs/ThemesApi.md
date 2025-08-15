# ThemesApi

All URIs are relative to *https://api.auraframefx.com/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**themeApplyPut**](ThemesApi.md#themeApplyPut) | **PUT** /theme/apply | Apply a theme |
| [**themesGet**](ThemesApi.md#themesGet) | **GET** /themes | Get available themes |


<a id="themeApplyPut"></a>
# **themeApplyPut**
> themeApplyPut(themeApplyRequest)

Apply a theme

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.customization.infrastructure.*
//import dev.aurakai.auraframefx.api.customization.models.*

val apiInstance = ThemesApi()
val themeApplyRequest : ThemeApplyRequest =  // ThemeApplyRequest | 
try {
    apiInstance.themeApplyPut(themeApplyRequest)
} catch (e: ClientException) {
    println("4xx response calling ThemesApi#themeApplyPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ThemesApi#themeApplyPut")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **themeApplyRequest** | [**ThemeApplyRequest**](ThemeApplyRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="themesGet"></a>
# **themesGet**
> kotlin.collections.List&lt;Theme&gt; themesGet()

Get available themes

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.customization.infrastructure.*
//import dev.aurakai.auraframefx.api.customization.models.*

val apiInstance = ThemesApi()
try {
    val result : kotlin.collections.List<Theme> = apiInstance.themesGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ThemesApi#themesGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ThemesApi#themesGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;Theme&gt;**](Theme.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

