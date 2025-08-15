# SandboxApi

All URIs are relative to *https://api.auraframefx.com/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**sandboxComponentsComponentIdTestPost**](SandboxApi.md#sandboxComponentsComponentIdTestPost) | **POST** /sandbox/components/{componentId}/test | Execute component test |
| [**sandboxComponentsGet**](SandboxApi.md#sandboxComponentsGet) | **GET** /sandbox/components | List available UI components for testing |


<a id="sandboxComponentsComponentIdTestPost"></a>
# **sandboxComponentsComponentIdTestPost**
> ComponentTestResult sandboxComponentsComponentIdTestPost(componentId, componentTestRequest)

Execute component test

Run tests on a specific UI component in the sandbox environment

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.sandbox.infrastructure.*
//import dev.aurakai.auraframefx.api.sandbox.models.*

val apiInstance = SandboxApi()
val componentId : kotlin.String = componentId_example // kotlin.String | ID of the component to test
val componentTestRequest : ComponentTestRequest =  // ComponentTestRequest | 
try {
    val result : ComponentTestResult = apiInstance.sandboxComponentsComponentIdTestPost(componentId, componentTestRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SandboxApi#sandboxComponentsComponentIdTestPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SandboxApi#sandboxComponentsComponentIdTestPost")
    e.printStackTrace()
}
```

### Parameters
| **componentId** | **kotlin.String**| ID of the component to test | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **componentTestRequest** | [**ComponentTestRequest**](ComponentTestRequest.md)|  | |

### Return type

[**ComponentTestResult**](ComponentTestResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="sandboxComponentsGet"></a>
# **sandboxComponentsGet**
> kotlin.collections.List&lt;SandboxComponent&gt; sandboxComponentsGet()

List available UI components for testing

Retrieve list of UI components available in the sandbox environment

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.sandbox.infrastructure.*
//import dev.aurakai.auraframefx.api.sandbox.models.*

val apiInstance = SandboxApi()
try {
    val result : kotlin.collections.List<SandboxComponent> = apiInstance.sandboxComponentsGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SandboxApi#sandboxComponentsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SandboxApi#sandboxComponentsGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;SandboxComponent&gt;**](SandboxComponent.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

