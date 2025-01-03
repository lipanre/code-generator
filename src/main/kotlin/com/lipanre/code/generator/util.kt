package com.lipanre.code.generator

import com.intellij.ide.extensionResources.ExtensionsRootType
import com.intellij.ide.scratch.ScratchFileService
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import java.io.File
import java.util.Objects

/**
 * {@code description}
 * 工具
 *
 * <br>
 * {@code date} 2025/1/2 14:49
 */


object FileUtil {

    /**
     * 创建文件选择器
     */
    fun createFileChooserDescriptor( chooseFolders: Boolean, chooseFiles: Boolean = !chooseFolders ): FileChooserDescriptor {
        return FileChooserDescriptor(chooseFiles, chooseFolders, false, false, false, false)
    }

    /**
     * 获取scratches and consoles下面的所有文件对象
     */
    fun listScratchesPluginFiles(vararg paths: String, pluginId: String = DataConstant.pluginId, type: ExtensionsRootType = ExtensionsRootType.getInstance()): Array<out String> {

        val rootPath = ScratchFileService.getInstance().getRootPath(type)
        val id: PluginId? = PluginId.findId(pluginId)
        if (!exists(rootPath, *paths) && id != null) {
            type.extractBundledResources(id, "")
        }
        return getFile(rootPath, pluginId, *paths).list().orEmpty()
    }

    /**
     * 判断指定路径的文件是否存在
     */
    private fun exists(path: String, vararg subPaths: String): Boolean {
        return getFile(path, *subPaths).exists()
    }

    /**
     * 获取文件对象
     */
    private fun getFile(path: String, vararg paths: String): File {
        return File(path + File.separator + paths.joinToString(separator = File.separator))
    }
}