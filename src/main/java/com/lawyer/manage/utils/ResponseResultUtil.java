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


import com.lawyer.manage.exception.BusinessException;
import com.lawyer.manage.exception.ErrorCode;

/**
 * REST工具类
 *
 * @author zhongyi
 */
public class ResponseResultUtil {


    /**
     * 构建返回结果
     *
     * @param successMessage
     * @param data
     * @return
     */
    public static ResponseResult getSuccessResult(String successMessage, Object data) {
        return buildResult(ResponseResult.SUCCESS, successMessage, data);
    }

    /**
     * 构建返回结果
     *
     * @param successMessage
     * @return
     */
    public static ResponseResult getSuccessResult(String successMessage) {
        return buildResult(ResponseResult.SUCCESS, successMessage);
    }

    /**
     * 构建返回结果
     *
     * @param code
     * @param message
     * @return
     */
    public static ResponseResult getResult(String code, String message) {
        return buildResult(code, message);
    }

    /**
     * 构建返回结果
     *
     * @param ec
     * @return
     */
    public static ResponseResult getResult(ErrorCode ec) {
        return buildResult(ec.getCode(), ec.getDesc());
    }

    /**
     * 构建返回结果
     *
     * @param ex
     * @return
     */
    public static ResponseResult getResult(Exception ex) {
        ResponseResult rs;
        if (ex instanceof BusinessException) {
            rs = buildResult(((BusinessException) ex).getCode(), ((BusinessException) ex).getDesc());
        } else {
            rs = buildResult("-1", ex.getMessage());
        }
        return rs;
    }

    private static ResponseResult buildResult(String code, String message) {
        return buildResult(code, message, null);
    }

    private static ResponseResult buildResult(String code, String message, Object data) {
        ResponseResult rs = new ResponseResult();
        rs.setStatus(code);
        rs.setStatusInfo(message);
        if (data != null) {
            rs.setData(data);
        }
        return rs;
    }


}