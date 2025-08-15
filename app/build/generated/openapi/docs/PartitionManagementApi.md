# PartitionManagementApi

All URIs are relative to *http://localhost:8080/api/v1/romtools*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**listPartitions**](PartitionManagementApi.md#listPartitions) | **GET** /partitions | List system partitions |
| [**mountPartition**](PartitionManagementApi.md#mountPartition) | **POST** /partitions/{partitionName}/mount | Mount partition |
| [**unmountPartition**](PartitionManagementApi.md#unmountPartition) | **POST** /partitions/{partitionName}/unmount | Unmount partition |


<a id="listPartitions"></a>
# **listPartitions**
> kotlin.collections.List&lt;PartitionInfo&gt; listPartitions()

List system partitions

Get information about all system partitions

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.romtools.infrastructure.*
//import dev.aurakai.auraframefx.api.romtools.models.*

val apiInstance = PartitionManagementApi()
try {
    val result : kotlin.collections.List<PartitionInfo> = apiInstance.listPartitions()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PartitionManagementApi#listPartitions")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PartitionManagementApi#listPartitions")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;PartitionInfo&gt;**](PartitionInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="mountPartition"></a>
# **mountPartition**
> mountPartition(partitionName, mountRequest)

Mount partition

Mount a specific partition (requires root)

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.romtools.infrastructure.*
//import dev.aurakai.auraframefx.api.romtools.models.*

val apiInstance = PartitionManagementApi()
val partitionName : kotlin.String = partitionName_example // kotlin.String | Name of the partition to mount
val mountRequest : MountRequest =  // MountRequest | 
try {
    apiInstance.mountPartition(partitionName, mountRequest)
} catch (e: ClientException) {
    println("4xx response calling PartitionManagementApi#mountPartition")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PartitionManagementApi#mountPartition")
    e.printStackTrace()
}
```

### Parameters
| **partitionName** | **kotlin.String**| Name of the partition to mount | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **mountRequest** | [**MountRequest**](MountRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a id="unmountPartition"></a>
# **unmountPartition**
> unmountPartition(partitionName)

Unmount partition

Unmount a specific partition (requires root)

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.romtools.infrastructure.*
//import dev.aurakai.auraframefx.api.romtools.models.*

val apiInstance = PartitionManagementApi()
val partitionName : kotlin.String = partitionName_example // kotlin.String | Name of the partition to unmount
try {
    apiInstance.unmountPartition(partitionName)
} catch (e: ClientException) {
    println("4xx response calling PartitionManagementApi#unmountPartition")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PartitionManagementApi#unmountPartition")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **partitionName** | **kotlin.String**| Name of the partition to unmount | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

