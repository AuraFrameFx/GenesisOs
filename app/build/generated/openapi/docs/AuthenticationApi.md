# AuthenticationApi

All URIs are relative to *https://api.aurafx.dev/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**authLoginPost**](AuthenticationApi.md#authLoginPost) | **POST** /auth/login | Login user |
| [**authRegisterPost**](AuthenticationApi.md#authRegisterPost) | **POST** /auth/register | Register a new user |


<a id="authLoginPost"></a>
# **authLoginPost**
> AuthLoginPost200Response authLoginPost(username, password, grantType)

Login user

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.aura.infrastructure.*
//import dev.aurakai.auraframefx.api.aura.models.*

val apiInstance = AuthenticationApi()
val username : kotlin.String = username_example // kotlin.String | 
val password : kotlin.String = password_example // kotlin.String | 
val grantType : kotlin.String = grantType_example // kotlin.String | 
try {
    val result : AuthLoginPost200Response = apiInstance.authLoginPost(username, password, grantType)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthenticationApi#authLoginPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthenticationApi#authLoginPost")
    e.printStackTrace()
}
```

### Parameters
| **username** | **kotlin.String**|  | |
| **password** | **kotlin.String**|  | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **grantType** | **kotlin.String**|  | [optional] [default to &quot;password&quot;] |

### Return type

[**AuthLoginPost200Response**](AuthLoginPost200Response.md)

### Authorization


Configure OAuth2:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

<a id="authRegisterPost"></a>
# **authRegisterPost**
> User authRegisterPost(authRegisterPostRequest)

Register a new user

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.aura.infrastructure.*
//import dev.aurakai.auraframefx.api.aura.models.*

val apiInstance = AuthenticationApi()
val authRegisterPostRequest : AuthRegisterPostRequest =  // AuthRegisterPostRequest | 
try {
    val result : User = apiInstance.authRegisterPost(authRegisterPostRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthenticationApi#authRegisterPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthenticationApi#authRegisterPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **authRegisterPostRequest** | [**AuthRegisterPostRequest**](AuthRegisterPostRequest.md)|  | |

### Return type

[**User**](User.md)

### Authorization


Configure OAuth2:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

