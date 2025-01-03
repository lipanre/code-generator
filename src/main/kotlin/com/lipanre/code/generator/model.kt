package com.lipanre.code.generator

/**
 * {@code description}
 * 数据相关
 *
 * <br>
 * {@code date} 2025/1/2 14:38
 */

/**
 * 数据库配置类
 * @param outputPath 生成的文件输出路径
 * @param template   选择使用的模板文件夹名称
 * @param templateSwitch 模板开关，选择启用哪些模板来生成文件，如果map中不存在默认为需要生成
 */
data class DataBaseGeneratorConfig(var outputPath: String = "", var template: String? = "", var templateSwitch: MutableMap<String, Boolean> = mutableMapOf())