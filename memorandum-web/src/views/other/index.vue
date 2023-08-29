<template>
  <div class="tab-container">
    <span align="center">{{ titleName }}</span>
  </div>
</template>

<script>
import {
} from "@/api/other";
export default {
  name: "Other",
  data() {
    return {
      titleName: "其他(后续根据需求增加)"
    };
  },
  watch: {},
  created() {
  },
  methods: {

  }
};
</script>

<style scoped>
.tab-container {
  margin: 30px;
}
</style>
