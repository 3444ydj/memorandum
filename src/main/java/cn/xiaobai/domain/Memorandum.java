package cn.xiaobai.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author yangdaji
 * @Date 2023/1/25
 * @PackageName cn.xiaobai.domain
 * @ClassName Test
 */
@Data
@TableName("t_memorandum")
public class Memorandum implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;
}
