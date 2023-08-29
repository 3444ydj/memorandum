<template>
  <div class="tab-container">
    <div class="filter-container">
      <el-button type="primary" icon="el-icon-circle-plus-outline" @click="handleAdd" plain>新增</el-button>
      <el-button v-if="saveStat==true" type="primary" icon="el-icon-circle-plus-outline" @click="handleSave" plain>
        保存
      </el-button>
      <el-button type="danger" icon="el-icon-remove-outline" @click="batchDelete" plain>批量删除
      </el-button>
      <el-button v-if="saveStat==false&&tableData.length>0"
                 size="auto"
                 icon="el-icon-edit-outline"
                 @click="handleEdit">编辑
      </el-button>
    </div>
    <el-table
      :data="tableData.filter(data => !search || data.title.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%;"
      @selection-change="selectionChange">
      <el-table-column
        type="selection"
        width="60"
      >
      </el-table-column>
      <el-table-column
        align="center"
        label="标题"
        prop="title">
        <template slot-scope="scope">
         <span>
         <el-input v-if="editStat==true" size="auto" clearable style="width: 350px; margin-left:50px;"
                   maxlength="100"
                   v-model="scope.row.title"/>
           <span v-else>{{ scope.row.title }}</span>
         </span>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="内容"
        prop="content">
        <template slot-scope="scope">
         <span>
         <el-input v-if="editStat==true" type="textarea" size="auto"
                   autosize
                   maxlength="500"
                   style="width: 450px; "
                   v-model="scope.row.content"></el-input>
           <span v-else>{{ scope.row.content }}</span>
         </span>
        </template>
      </el-table-column>
      <el-table-column
        align="center">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="small"
            placeholder="输入关键字搜索"/>
        </template>
      </el-table-column>
    </el-table>
  </div>

</template>

<script>

import {
  getTodoInfos,
  saveOrUpdateRecord,
  deleteByIds
} from "@/api/memorandum"

export default {
  name: "Memorandum",
  inject: ['reload'],
  data() {
    return {
      // tableData: [{
      //   title: "dsf",
      //   content: "dfsf"
      // }],
      tableData: [],
      search: '',
      idList: [],
      // 编辑状态, 用于隐藏显示编辑框
      editStat: false,
      // 保存状态, 用于控制保存按钮的显示和隐藏
      saveStat: false,
      // 选择的数据
      sel: []
    }
  },
  watch: {},
  created() {
    this.getData()
  },
  methods: {
    async getData() {
      this.tableData = await getTodoInfos();
    },
    selectionChange(val) {
      console.log("选择触发: ", val)
      this.sel = val;
    },
    handleAdd() {
      // console.log("增加点击")
      let row = {
        // title: '',
        // content: ''
      }
      this.tableData.push(row)
      // 添加输入框时判断是否点击了保存, 开始没有点击,会显示编辑和保存
      if (!this.saveStat) {
        this.editStat = !this.editStat;
        this.saveStat = !this.saveStat;
      }

    },
    handleSave() {
      console.log("删除的id 列表 ", this.idList)
      // console.log("列表中的数据: ", this.tableData)
      if (this.idList.length == 0) {
        saveOrUpdateRecord(this.tableData)
          .then(() => {
            this.idList.splice(0, this.idList.length)
            this.$message({
              type: "success",
              message: "保存修改成功!"
            });
          }).catch(err => {
          this.$log.info("err", err);
        });
      } else {
        deleteByIds(this.idList).then(() => {
          this.idList.splice(0, this.idList.length)
          this.$message({
            type: "success",
            message: "删除成功!"
          });
        }).catch(err => {
          this.$log.info("err", err);
        });
      }
      this.saveStat = !this.saveStat;
      this.reload()
    },
    batchDelete() {
      // 删除前的提示
      this.$confirm("确认删除所选记录吗?", "提示", {
        type: "warning",
      }).then(() => {
        let ids = this.sel.map((item) => item.id);
        if (ids.length > 0) {
          deleteByIds(ids).then(() => {
            this.$message({
              message: "删除成功",
              type: "success",
            });
          });
          this.reload()
        }
      }).catch(err => {
        this.$log.info("err", err);
      });
    },
    handleEdit() {
      // 判断是否点击了保存, 否则再次点击编辑没有效果
      if (!this.saveStat) {
        this.editStat = !this.editStat;
        this.saveStat = !this.saveStat;
      }
    },
    // handleDelete(index, row) {
    //   let value = this.tableData[index];
    //   if (value != null && Object.keys(value).length == 0) {
    //     this.idList.push(row.id)
    //     this.tableData.splice(index, 1)
    //   } else {
    //     this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
    //       confirmButtonText: '确定',
    //       cancelButtonText: '取消',
    //       type: 'warning',
    //       center: true
    //     }).then(() => {
    //       this.idList.push(row.id)
    //       this.tableData.splice(index, 1)
    //       this.$message({
    //         type: 'success',
    //         message: '删除成功!'
    //       });
    //     }).catch(() => {
    //       this.$message({
    //         type: 'info',
    //         message: '已取消删除'
    //       });
    //     });
    //
    //
    //   }
    // },

  }
};
</script>


<style scoped>
.tab-container {
  margin: 30px;
}
</style>
