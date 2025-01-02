package com.lipanre.code.generator

import com.intellij.ide.extensionResources.ExtensionsRootType
import com.intellij.ide.scratch.RootType
import com.intellij.ide.scratch.ScratchFileService
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.util.containers.stream
import java.io.File
import kotlin.io.path.Path

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
    fun createFileChooserDescriptor(
        chooseFolders: Boolean,
        chooseFiles: Boolean = !chooseFolders
    ): FileChooserDescriptor {
        return FileChooserDescriptor(chooseFiles, chooseFolders, false, false, false, false)
    }

    /**
     * 获取scratches and consoles下面的所有文件对象
     */
    fun listScratchesPluginFiles(
        dirName: String,
        type: ExtensionsRootType = ExtensionsRootType.getInstance(),
        pluginId: String = "com.lipanre.code.generator"
    ): Array<out String>? {

        val rootPath = ScratchFileService.getInstance().getRootPath(type)
        val id: PluginId? = PluginId.findId(pluginId)
        if (!exists(rootPath, dirName) && id != null) {
            type.extractBundledResources(id, "")
        }
        return getFile(rootPath, pluginId, dirName).list()
    }

    /**
     * 判断指定路径的文件是否存在
     */
    private fun exists(path: String, fileName: String): Boolean {
        return getFile(path, fileName).exists()
    }

    /**
     * 获取文件对象
     */
    private fun getFile(path: String, vararg paths: String): File {
        return File(path + File.separator + paths.joinToString(separator = File.separator))
    }
}