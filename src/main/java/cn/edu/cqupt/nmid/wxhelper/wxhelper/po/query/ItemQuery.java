package cn.edu.cqupt.nmid.wxhelper.wxhelper.po.query;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.enums.RankBy;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.page.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * @author MaYunHao
 * @version 1.0
 * @description  展品查询帮助类
 * @date 2020/2/5 16:11
 */

@ApiModel(description = "用于辅助查询")
public class ItemQuery {

    /**
     * 分页请求类
     */
    @ApiModelProperty(value = "分页查询类",dataType = "PageRequest",required = false)
    private PageRequest pageRequest ;
    /**
     * 展品名
     */
    @ApiModelProperty(value = "展品名",dataType = "string",required = false)
    private String itemname;
    /**
     * 展品类型名
     */
    @ApiModelProperty(value = "展品类型名",dataType = "string",required = false)
    private String typename;
    /**
     * 展品年代
     */
    @ApiModelProperty(value = "展品年代",dataType = "string",required = false)
    private String eraname;
    /**
     * 通过何种方式排名
     */
    @ApiModelProperty(value = "排名方式",dataType = "string",required = false)
    private RankBy rankBy;



    public ItemQuery() {
    }

    public ItemQuery(PageRequest pageRequest, String itemname, String typename, String eraname, RankBy rankBy) {
        this.pageRequest = pageRequest;
        this.itemname = itemname;
        setItemname(itemname);
        this.eraname = eraname;
        this.rankBy = rankBy;
    }

    public PageRequest getPageRequest() {
        if (pageRequest == null){
            return new PageRequest();
        }
        return pageRequest;
    }

    public void setPageRequest(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        if(itemname.indexOf(0) != '%'){
            itemname = "%"+itemname;
        }
        if(itemname.lastIndexOf(0) != '%'){
            itemname = itemname + "%";
        }
        this.itemname = itemname;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getEraname() {
        return eraname;
    }

    public void setEraname(String eraname) {
        this.eraname = eraname;
    }

    public RankBy getRankBy() {
        return rankBy;
    }

    public void setRankBy(RankBy rankBy) {
        this.rankBy = rankBy;
    }

    @Override
    public String toString() {
        return "ItemQuery{" +
                "pageRequest=" + pageRequest +
                ", itemname='" + itemname + '\'' +
                ", typename='" + typename + '\'' +
                ", eraname='" + eraname + '\'' +
                ", rankBy=" + rankBy +
                '}';
    }
}
