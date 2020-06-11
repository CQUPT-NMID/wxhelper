package cn.edu.cqupt.nmid.wxhelper.wxhelper.converter;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.RankBy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/2/5 19:17
 */
public class RankByEnumConverter implements Converter<String, RankBy> {

    @Override
    public RankBy convert(String source) {
        for (RankBy rankBy:RankBy.values()
             ) {
            if(source !=null && source.equals(rankBy.getSign())){
                return rankBy;
            }
        }
        return RankBy.DEFAULT;
    }

}
