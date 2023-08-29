/*
 * @Author: wuhao
 * @Date: 2019-11-10 14:03:40
 * @LastEditors: wuhao
 * @LastEditTime: 2019-11-10 15:55:58
 * @Description: description
 */
import Vue from "vue";

import Cookies from "js-cookie";

import "normalize.css/normalize.css"; // a modern alternative to CSS resets

import Element from "element-ui";
import "./styles/element-variables.scss";

import "@/styles/index.scss"; // global css

import App from "./App";
import store from "./store";
import router from "./router";

import Log from "@/utils/log";
Vue.prototype.$log = Log;
import "./utils/common";
import "./icons"; // icon
import "./permission"; // permission control
import "./utils/error-log"; // error log

import * as filters from "./filters"; // global filters
Vue.use(Element, {
  size: Cookies.get("size") || "medium" // set element-ui default size
});

// register global utility filters
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key]);
});

Vue.config.productionTip = false;

new Vue({
  el: "#app",
  router,
  store,
  render: h => h(App)
});
