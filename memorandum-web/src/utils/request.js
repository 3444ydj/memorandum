import axios from "axios";
import {Message} from "element-ui";
import Log from "@/utils/log";
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API,
  headers: {
    "Content-Type": "application/json;charset=utf-8 "
  }
});
// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent
    return config;
  },
  error => {
    // do something with request error
    Log.error(error);
    return Promise.reject(error);
  }
);

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    let res = response.data || response;
    if (typeof res === "string") {
      res = res.trim();
    }
    if (res !== "" && typeof res === "object") {
      if (typeof res === "string") {
        res = JSON.parse(res);
      }
      res.meta = {
        success: response.status === 200 || response.status === 201
      };
      if (res.code === 200) {
        if (res.msg !== "success") {
          //成功
          Message({
            message: res.msg,
            type: "success",
            duration: "5000"
          })
        }
        return res.data;
      } else if (res.code === 500) {
        //失败
        Message({
          message: res.msg || "服务器内部错误,请联系管理员！",
          type: "error",
          duration: "5000"
        })
        return res.data;
      }
    }
    // console.info("response:", response);
    return res;
  },
  error => {
    Log.error(error);
    Message({
      message: error.message,
      type: "error",
      duration: 5 * 1000
    });
    return Promise.reject(error);
  }
);

export default service;
