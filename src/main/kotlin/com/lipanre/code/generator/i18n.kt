package com.lipanre.code.generator

import com.intellij.DynamicBundle
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey

/**
 * 多语言
 *
 * @author lipanre
 */

@NonNls
private const val BUNDLE = "i18n.zh-CN"

/**
 * 多语言获取工具类
 */
object MessageSource: DynamicBundle(BUNDLE) {

    /**
     * 从多语言资源包中获取对应key的值
     */
    @JvmStatic
    fun message(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any): String = getMessage(key, *params)
}