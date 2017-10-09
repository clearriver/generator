package ${packageStr};
import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.river.common.orm.mybatis.MyBatisPageRepo;
import com.river.common.orm.mybatis.MyBatisRepo;
import com.river.common.orm.mybatis.PageBase;
import com.river.common.query.Searchable;
${importStr}
/**
 * ${entityDesc}Dao
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * ${author} 	1.0  		${time} 	Created
 * </pre>
 * @since 1.
 */
@MyBatisRepo
public interface ${className} extends MyBatisPageRepo<${entityClassName}, Serializable> {
    /**
     * 批量新增
     * 
     * @param list
     * @return
     */
    int addBatch(@Param("list")List<${entityClassName}> list);
    
    /**
     * 批量更新
     * 
     * @param list
     * @return
     */
    int updateBatch(@Param("list")List<${entityClassName}> list);
    
    /**
     * 批量删除
     * 
     * @param list
     * @return
     */
    int deleteBatch(@Param("list")List<Long> list);
    
    /**
     * 查询列表
     * 
     * @param entity
     * @return
     */
    List<${entityClassName}> queryList(${entityClassName} entity);
}