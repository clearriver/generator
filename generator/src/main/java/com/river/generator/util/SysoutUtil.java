/**
 * Project Name:generator
 * File Name:SysoutUtil.java
 * Package Name:com.river.generator.util
 * Date:2017年10月06日下午2:00:00
*/

package com.river.generator.util;

import java.text.MessageFormat;

/**
 * ClassName:SysoutUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年10月06日下午2:00:00 <br/>
 * 
 * @author wuqh
 * @version
 * @since JDK 1.6
 * @see
 */
public class SysoutUtil {

    public static void printInfo(String str, Object... objects) {
        System.out.println(MessageFormat.format(str, objects));
    }
}
