/**
 * Copyright (c) 2014 Baidu, Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lawyer.manage.utils;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Rest Service结果集对象，封装rest返回值
 *
 */
public class ResponseResult implements Serializable {

    private static final long serialVersionUID = -4826258907545698916L;

    /**
     * 操作成功
     */
    public static final String SUCCESS = "0000";

    /**
     * 返回状态
     */
    private String status;

    /**
     * 返回状态信息，如提示信息、错误描述信息等
     */
    private String statusInfo;


    /**
     * 数据信息(对象的话，用json)，如果需要返回数据，将数据封装到集合对象中返回
     */
    private String data;

    /**
     * 其他未定属性信息
     */
    private Map<String, Serializable> properties = new HashMap<String, Serializable>();

    /**
     * 添加属性信息
     *
     * @param key
     * @param value
     */
    public void addProperty(String key, Serializable value) {
        this.properties.put(key, value);
    }

    /**
     * get statusInfo
     *
     * @return the statusInfo
     */
    public String getStatusInfo() {
        return statusInfo;
    }

    /**
     * set statusInfo with statusInfo
     *
     * @param statusInfo the statusInfo to set
     */
    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    /**
     * get status
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * set status with status
     *
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * get data
     *
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * set data with data
     *
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * 如果是对象，调用本方法自动将对象转换成JSON
     *
     * @param dataObj 返回的对象
     */
    public void setData(Object dataObj) {
        if(dataObj instanceof  String){
            this.data = (String)dataObj;
        }else{
            this.data = JSON.toJSONString(dataObj);
        }
    }

    /**
     * get properties
     *
     * @return the properties
     */
    public Map<String, Serializable> getProperties() {
        return properties;
    }

    /**
     * set properties with properties
     *
     * @param properties the properties to set
     */
    public void setProperties(Map<String, Serializable> properties) {
        this.properties = properties;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ResponseResult [status=" + status + ", statusInfo=" + statusInfo + ", data=" + data + ", properties="
                + properties + "]";
    }
}
