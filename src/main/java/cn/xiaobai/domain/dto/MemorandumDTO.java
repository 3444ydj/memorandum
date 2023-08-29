package cn.xiaobai.domain.dto;

import cn.xiaobai.domain.Memorandum;
import lombok.Data;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author yangdaji
 * @Date 2023/1/25
 * @PackageName cn.xiaobai.domain
 * @ClassName Test
 */
@Data
public class MemorandumDTO {

    private Integer id;
    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;


    public static List<Memorandum> convert(List<MemorandumDTO> memorandumDTO) {

        if (!CollectionUtils.isEmpty(memorandumDTO)) {
            return memorandumDTO.stream()
                    .filter(data ->
                            Objects.nonNull(data.getTitle())
                                    || Objects.nonNull(data.getContent()))
                    .map(dto -> {
                        Memorandum memorandum = new Memorandum();
                        BeanUtils.copyProperties(dto, memorandum);
                        return memorandum;
                    }).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }
}
