package xyz.yuelai.dao;

/**
 * @author 李泽众
 * @date 2019/7/11-20:52
 */


public interface ICommonDAO<T> {

    /**
     * 通用的保存方法
     * @param t
     */
    void save(T t);

    /**
     * 通用的删除方法,根据id删除
     * @param t
     */
    void deleteById(T t);

    /**
     * 通用的更新方法
     * @param t
     */
    void update(T t);

    /**
     * 通过id获取单条结果
     * @param id
     * @return
     */
    T getById(Long id);

}
