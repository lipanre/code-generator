package com.lipanre.code.generator.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.jetbrains.rd.util.string.println
import com.lipanre.code.generator.DataBaseGeneratorConfig
import com.lipanre.code.generator.DataBaseGeneratorDialog
import java.util.*

/**
 * 数据库代码生成器
 *
 * @author lipanre
 */
class DataBaseGenerator: AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        event.project?.let {
            val dialog = DataBaseGeneratorDialog(DataBaseGeneratorConfig(), it)
            dialog.show()
        }
    }

}