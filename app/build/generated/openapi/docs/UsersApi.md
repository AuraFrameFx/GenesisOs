# UsersApi

All URIs are relative to *https://api.aurafx.dev/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**usersMeGet**](UsersApi.md#usersMeGet) | **GET** /users/me | Get current user profile |
| [**usersMePut**](UsersApi.md#usersMePut) | **PUT** /users/me | Update current user profile |


<a id="usersMeGet"></a>
# **usersMeGet**
> User usersMeGet()

Get current user profile

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.aura.infrastructure.*
//import dev.aurakai.auraframefx.api.aura.models.*

val apiInstance = UsersApi()
try {
    val result : User = apiInstance.usersMeGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UsersApi#usersMeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UsersApi#usersMeGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**User**](User.md)

### Authorization


Configure OAuth2:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="usersMePut"></a>
# **usersMePut**
> User usersMePut(usersMePutRequest)

Update current user profile

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.aura.infrastructure.*
//import dev.aurakai.auraframefx.api.aura.models.*

val apiInstance = UsersApi()
val usersMePutRequest : UsersMePutRequest =  // UsersMePutRequest | 
try {
    val result : User = apiInstance.usersMePut(usersMePutRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UsersApi#usersMePut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UsersApi#usersMePut")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **usersMePutRequest** | [**UsersMePutRequest**](UsersMePutRequest.md)|  | |

### Return type

[**User**](User.md)

### Authorization


Configure OAuth2:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

