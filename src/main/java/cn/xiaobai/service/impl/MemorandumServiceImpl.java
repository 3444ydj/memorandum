package cn.xiaobai.service.impl;

import cn.xiaobai.domain.Memorandum;
import cn.xiaobai.mapper.MemorandumMapper;
import cn.xiaobai.service.MemorandumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author yangdaji
 * @Date 2023/1/25
 * @PackageName cn.xiaobai.service.impl
 * @ClassName TestServiceImpl
 */
@Service
public class MemorandumServiceImpl extends ServiceImpl<MemorandumMapper, Memorandum> implements MemorandumService {
}
