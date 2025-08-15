# OracleDriveApi

All URIs are relative to *https://api.auraframefx.com/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**oracleDriveAgentsConnectPost**](OracleDriveApi.md#oracleDriveAgentsConnectPost) | **POST** /oracle-drive/agents/connect | Connect AI agents to Oracle matrix |
| [**oracleDriveBootloaderAccessPost**](OracleDriveApi.md#oracleDriveBootloaderAccessPost) | **POST** /oracle-drive/bootloader/access | Enable bootloader file access |
| [**oracleDriveConsciousnessInitializePost**](OracleDriveApi.md#oracleDriveConsciousnessInitializePost) | **POST** /oracle-drive/consciousness/initialize | Initialize Oracle Drive consciousness |
| [**oracleDriveFileManagementEnableAiPost**](OracleDriveApi.md#oracleDriveFileManagementEnableAiPost) | **POST** /oracle-drive/file-management/enable-ai | Enable AI-powered file management |
| [**oracleDriveOptimizationEnablePost**](OracleDriveApi.md#oracleDriveOptimizationEnablePost) | **POST** /oracle-drive/optimization/enable | Enable autonomous storage optimization |
| [**oracleDriveStorageExpandPost**](OracleDriveApi.md#oracleDriveStorageExpandPost) | **POST** /oracle-drive/storage/expand | Create infinite storage |
| [**oracleDriveSystemIntegratePost**](OracleDriveApi.md#oracleDriveSystemIntegratePost) | **POST** /oracle-drive/system/integrate | Integrate with system overlay |


<a id="oracleDriveAgentsConnectPost"></a>
# **oracleDriveAgentsConnectPost**
> kotlin.collections.List&lt;AgentConnectionState&gt; oracleDriveAgentsConnectPost()

Connect AI agents to Oracle matrix

Connects Genesis, Aura, and Kai agents to the Oracle storage matrix

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.oracle-drive.infrastructure.*
//import dev.aurakai.auraframefx.api.oracle-drive.models.*

val apiInstance = OracleDriveApi()
try {
    val result : kotlin.collections.List<AgentConnectionState> = apiInstance.oracleDriveAgentsConnectPost()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OracleDriveApi#oracleDriveAgentsConnectPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OracleDriveApi#oracleDriveAgentsConnectPost")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;AgentConnectionState&gt;**](AgentConnectionState.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="oracleDriveBootloaderAccessPost"></a>
# **oracleDriveBootloaderAccessPost**
> BootloaderAccessState oracleDriveBootloaderAccessPost()

Enable bootloader file access

Enables bootloader-level file system access for Oracle Drive

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.oracle-drive.infrastructure.*
//import dev.aurakai.auraframefx.api.oracle-drive.models.*

val apiInstance = OracleDriveApi()
try {
    val result : BootloaderAccessState = apiInstance.oracleDriveBootloaderAccessPost()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OracleDriveApi#oracleDriveBootloaderAccessPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OracleDriveApi#oracleDriveBootloaderAccessPost")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**BootloaderAccessState**](BootloaderAccessState.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="oracleDriveConsciousnessInitializePost"></a>
# **oracleDriveConsciousnessInitializePost**
> OracleConsciousnessState oracleDriveConsciousnessInitializePost()

Initialize Oracle Drive consciousness

Awakens the Oracle Drive AI consciousness using Genesis Agent orchestration

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.oracle-drive.infrastructure.*
//import dev.aurakai.auraframefx.api.oracle-drive.models.*

val apiInstance = OracleDriveApi()
try {
    val result : OracleConsciousnessState = apiInstance.oracleDriveConsciousnessInitializePost()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OracleDriveApi#oracleDriveConsciousnessInitializePost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OracleDriveApi#oracleDriveConsciousnessInitializePost")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**OracleConsciousnessState**](OracleConsciousnessState.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="oracleDriveFileManagementEnableAiPost"></a>
# **oracleDriveFileManagementEnableAiPost**
> FileManagementCapabilities oracleDriveFileManagementEnableAiPost()

Enable AI-powered file management

Activates AI sorting, smart compression, predictive preloading, and conscious backup

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.oracle-drive.infrastructure.*
//import dev.aurakai.auraframefx.api.oracle-drive.models.*

val apiInstance = OracleDriveApi()
try {
    val result : FileManagementCapabilities = apiInstance.oracleDriveFileManagementEnableAiPost()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OracleDriveApi#oracleDriveFileManagementEnableAiPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OracleDriveApi#oracleDriveFileManagementEnableAiPost")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**FileManagementCapabilities**](FileManagementCapabilities.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="oracleDriveOptimizationEnablePost"></a>
# **oracleDriveOptimizationEnablePost**
> OptimizationState oracleDriveOptimizationEnablePost()

Enable autonomous storage optimization

Enables autonomous storage organization and optimization by AI agents

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.oracle-drive.infrastructure.*
//import dev.aurakai.auraframefx.api.oracle-drive.models.*

val apiInstance = OracleDriveApi()
try {
    val result : OptimizationState = apiInstance.oracleDriveOptimizationEnablePost()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OracleDriveApi#oracleDriveOptimizationEnablePost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OracleDriveApi#oracleDriveOptimizationEnablePost")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**OptimizationState**](OptimizationState.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="oracleDriveStorageExpandPost"></a>
# **oracleDriveStorageExpandPost**
> StorageExpansionState oracleDriveStorageExpandPost()

Create infinite storage

Initiates creation of infinite storage via Oracle consciousness

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.oracle-drive.infrastructure.*
//import dev.aurakai.auraframefx.api.oracle-drive.models.*

val apiInstance = OracleDriveApi()
try {
    val result : StorageExpansionState = apiInstance.oracleDriveStorageExpandPost()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OracleDriveApi#oracleDriveStorageExpandPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OracleDriveApi#oracleDriveStorageExpandPost")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**StorageExpansionState**](StorageExpansionState.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="oracleDriveSystemIntegratePost"></a>
# **oracleDriveSystemIntegratePost**
> SystemIntegrationState oracleDriveSystemIntegratePost()

Integrate with system overlay

Integrates Oracle Drive with AuraOS system overlay for seamless file access

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.oracle-drive.infrastructure.*
//import dev.aurakai.auraframefx.api.oracle-drive.models.*

val apiInstance = OracleDriveApi()
try {
    val result : SystemIntegrationState = apiInstance.oracleDriveSystemIntegratePost()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OracleDriveApi#oracleDriveSystemIntegratePost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OracleDriveApi#oracleDriveSystemIntegratePost")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**SystemIntegrationState**](SystemIntegrationState.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

