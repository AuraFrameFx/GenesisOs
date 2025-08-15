# AIAgentsApi

All URIs are relative to *https://api.auraframefx.com/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**agentAgentTypeProcessRequestPost**](AIAgentsApi.md#agentAgentTypeProcessRequestPost) | **POST** /agent/{agentType}/process-request | Send a request to an AI agent |
| [**agentsStatusGet**](AIAgentsApi.md#agentsStatusGet) | **GET** /agents/status | Get status of all AI agents |


<a id="agentAgentTypeProcessRequestPost"></a>
# **agentAgentTypeProcessRequestPost**
> AgentMessage agentAgentTypeProcessRequestPost(agentType, agentProcessRequest)

Send a request to an AI agent

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.ai.infrastructure.*
//import dev.aurakai.auraframefx.api.ai.models.*

val apiInstance = AIAgentsApi()
val agentType : AgentType =  // AgentType | Type of AI agent to interact with
val agentProcessRequest : AgentProcessRequest =  // AgentProcessRequest | 
try {
    val result : AgentMessage = apiInstance.agentAgentTypeProcessRequestPost(agentType, agentProcessRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AIAgentsApi#agentAgentTypeProcessRequestPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AIAgentsApi#agentAgentTypeProcessRequestPost")
    e.printStackTrace()
}
```

### Parameters
| **agentType** | [**AgentType**](.md)| Type of AI agent to interact with | [enum: Aura, Kai, Genesis, Cascade, NeuralWhisper, AuraShield, GenKitMaster] |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **agentProcessRequest** | [**AgentProcessRequest**](AgentProcessRequest.md)|  | |

### Return type

[**AgentMessage**](AgentMessage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="agentsStatusGet"></a>
# **agentsStatusGet**
> kotlin.collections.List&lt;AgentStatus&gt; agentsStatusGet()

Get status of all AI agents

### Example
```kotlin
// Import classes:
//import dev.aurakai.auraframefx.api.ai.infrastructure.*
//import dev.aurakai.auraframefx.api.ai.models.*

val apiInstance = AIAgentsApi()
try {
    val result : kotlin.collections.List<AgentStatus> = apiInstance.agentsStatusGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AIAgentsApi#agentsStatusGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AIAgentsApi#agentsStatusGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;AgentStatus&gt;**](AgentStatus.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

