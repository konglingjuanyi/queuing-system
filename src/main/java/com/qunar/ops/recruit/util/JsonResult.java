package com.qunar.ops.recruit.util;

import qunar.api.pojo.CodeMessage;


public class JsonResult {
    /**
     * 返回错误状态的json串
     *
     * @param status  错误代码，非零数值
     * @param message 错误消息
     * @return CodeMessage
     */
    public static CodeMessage getErrorMessage(final int status, final String message, final Object data) {
        return new CodeMessage() {
            @Override
            public int getStatus() {
                return status;
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public Object getData() {
                return data;
            }
        };
    }

    /**
     * 返回成功状态的json串
     *
     * @param data 成功时返回的数据
     * @return CodeMessage
     */

    public static CodeMessage getSuccessMessage(final int status, final String message, final Object data) {
        return new CodeMessage() {
            @Override
            public int getStatus() {
                return status;
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public Object getData() {
                return data;
            }
        };
    }
}
