package xyz.yuelai.pojo.bo;

import lombok.Data;
import xyz.yuelai.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李泽众
 * @date 2019/7/29-14:19
 */


@Data
public class PageBO<T> {

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 总数据
     */
    private Integer totalCount;

    /**
     * 当前页显示的数据数量
     */
    private Integer pageCount;

    /**
     * 当前页面的数据
     */
    private List<T> currentPageData;

    public PageBO() {
    }

    public PageBO(Integer currentPage, Integer totalCount, List<T> currentPageData) {
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.currentPageData = currentPageData;
        Integer remainder = totalCount % Constant.PAGE_COUNT;
        this.totalPage = remainder == 0 ? (totalCount / Constant.PAGE_COUNT) : (totalCount / Constant.PAGE_COUNT) + 1;
        this.pageCount = currentPageData.size();
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        PageBO<String> pageBO = new PageBO<>(4, 100, list);
        System.out.println(pageBO);
    }
}
