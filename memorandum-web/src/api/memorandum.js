/*
 * @Author: wuhao
 * @Date: 2019-12-17 13:54:11
 * @LastEditors  : wuhao1
 * @LastEditTime : 2020-02-07 16:43:34
 * @Description: description
 */
import request from "@/utils/request";

/**
 * @msg 获取待办列表
 */
export function getTodoInfos() {
  return request({
    url: `/api/queryList`,
    method: "get"
  });
}

/**
 * @msg 保存修改记录
 */
export function saveOrUpdateRecord(data) {
  return request({
    url: `api/saveOrUpdateRecord`,
    method: "post",
    data
  });
}

/**
 * @msg 根据id列表删除
 */
export function deleteByIds(data) {
  return request({
    url: `api/deleteByIds`,
    method: "post",
    data
  });
}


