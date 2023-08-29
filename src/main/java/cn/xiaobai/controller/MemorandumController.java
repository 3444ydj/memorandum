package cn.xiaobai.controller;

import cn.xiaobai.domain.Memorandum;
import cn.xiaobai.domain.dto.MemorandumDTO;
import cn.xiaobai.service.MemorandumService;
import cn.xioabai.common.domain.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author yangdaji
 * @Date 2023/1/25
 * @PackageName cn.xiaobai.controller
 * @ClassName TestController
 */
@RestController
@RequestMapping("/api")
public class MemorandumController {

    @Autowired
    private MemorandumService memorandumService;

    /**
     * 查询列表
     *
     * @return
     */
    @RequestMapping("queryList")
    public ResultDTO<List<Memorandum>> queryList() {
        List<Memorandum> memorandumList = memorandumService.list();
        if (CollectionUtils.isEmpty(memorandumList)) {
            ResultDTO.success();
        }
        return ResultDTO.success(memorandumList);
    }


    /**
     * 添加修改记录
     *
     * @param memorandumDTOS 备忘列表
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("saveOrUpdateRecord")
    public ResultDTO<Boolean> saveOrUpdateRecord(@RequestBody List<MemorandumDTO> memorandumDTOS) {
        List<Memorandum> memorandumList = MemorandumDTO.convert(memorandumDTOS);
        return ResultDTO.success(memorandumService.saveOrUpdateBatch(memorandumList));
    }

    /**
     * 根据id删除
     *
     * @param ids 主键
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("deleteByIds")
    public ResultDTO<Boolean> deleteByIds(@RequestBody List<Integer> ids) {
        return ResultDTO.success(memorandumService.removeBatchByIds(ids));
    }

}
