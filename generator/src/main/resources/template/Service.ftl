package ${packageStr};
import java.util.List;
import ${getCommandType};
import ${listCommandType};
import ${batchCommandType};
import ${commandType};
import ${queryCommandType};
import ${voType};
import com.river.common.query.Page;
import com.river.common.spi.SPIException;
/**
 * ${entityDesc}服务service
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * ${author}    1.0  ${time} Created
 * </pre>
 * @since 1.
 */
public interface ${className} {

    /**
     * 单个新增
     * 
     * @param command
     * @return
     */
    Long create${entityName}(${entityName}Command command) throws SPIException;

    /**
     * 批量新增
     * 
     * @param command
     * @return
     */
    void createBatch${entityName}(${entityName}BatchCommand command) throws SPIException;

    /**
     * 单个删除
     * 
     * @param command
     * @return
     */
    void delete${entityName}(Get${entityName}Command command) throws SPIException;

    /**
     * 批量删除
     * 
     * @param command
     * @return
     */
    void deleteBatch${entityName}(List${entityName}Command command) throws SPIException;

    /**
     * 单个更新
     * 
     * @param command
     * @return
     */
    void update${entityName}(${entityName}Command command) throws SPIException;

    /**
     * 批量更新
     * 
     * @param command
     * @return
     */
    void updateBatch${entityName}(${entityName}BatchCommand command) throws SPIException;

    /**
     * 单个查询
     * 
     * @param command
     * @return
     */
    ${voClassName} get${entityName}(Get${entityName}Command command) throws SPIException;

    /**
     * 分页查询
     * 
     * @param command
     * @return
     */
    Page<${voClassName}> find${entityName}Page(${entityName}QueryCommand command) throws SPIException;

    /**
     * 查询所有
     * 
     * @param command
     * @return
     */
    List<${voClassName}> query${entityName}List(${entityName}QueryCommand command) throws SPIException;
}
