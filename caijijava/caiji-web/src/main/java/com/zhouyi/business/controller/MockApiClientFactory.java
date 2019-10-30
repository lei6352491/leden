package com.zhouyi.business.controller;

import java.util.Map;

/*
* Copyright 2017 Alibaba Group
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import com.alibaba.cloudapi.sdk.core.BaseApiClient;
import com.alibaba.cloudapi.sdk.core.BaseApiClientBuilder;
import com.alibaba.cloudapi.sdk.core.annotation.NotThreadSafe;
import com.alibaba.cloudapi.sdk.core.annotation.ThreadSafe;
import com.alibaba.cloudapi.sdk.core.enums.Method;
import com.alibaba.cloudapi.sdk.core.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.core.enums.Scheme;
import com.alibaba.cloudapi.sdk.core.model.ApiRequest;
import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.alibaba.cloudapi.sdk.core.model.BuilderParams;

@ThreadSafe
public final class MockApiClientFactory extends BaseApiClient {
    public final static String GROUP_HOST = "api.zhouyilive.com";

    private MockApiClientFactory(BuilderParams builderParams) {
        super(builderParams);
    }

    @NotThreadSafe
    public static class Builder extends BaseApiClientBuilder<MockApiClientFactory.Builder, MockApiClientFactory>{

        @Override
        protected MockApiClientFactory build(BuilderParams params) {
            return new MockApiClientFactory(params);
        }
    }

    public static Builder newBuilder(){
        return new MockApiClientFactory.Builder();
    }

    public static MockApiClientFactory getInstance(){
        return getApiClassInstance(MockApiClientFactory.class);
    }

    public ApiResponse NameExplain(Map<String,String> params) {
        String _apiPath = "/name/explain";

        ApiRequest _apiRequest = new ApiRequest(Scheme.HTTP, Method.POST_FORM, GROUP_HOST, _apiPath);
        _apiRequest.addMappedParams(params, ParamPosition.FORM);

        
        return syncInvoke(_apiRequest);
    }

    public ApiResponse NameGenerator(Map<String,String> params) {
        String _apiPath = "/name/generate/";

        ApiRequest _apiRequest = new ApiRequest(Scheme.HTTP, Method.POST_FORM, GROUP_HOST, _apiPath);
        _apiRequest.addMappedParams(params, ParamPosition.FORM);

        return syncInvoke(_apiRequest);
    }
    
    public ApiResponse NameGeneratorLimit(Map<String,String> params) {
        String _apiPath = "/name/generate/limit/";

        ApiRequest _apiRequest = new ApiRequest(Scheme.HTTP, Method.POST_FORM, GROUP_HOST, _apiPath);
        _apiRequest.addMappedParams(params, ParamPosition.FORM);

        return syncInvoke(_apiRequest);
    }

}

