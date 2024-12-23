package com.lipanre.code.generator.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.lipanre.code.generator.DataBaseGeneratorDialog
import com.lipanre.code.generator.DialogConstant
import com.lipanre.code.generator.MessageSource

/**
 * 数据库代码生成器
 *
 * @author lipanre
 */
class DataBaseGenerator: AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        DataBaseGeneratorDialog(event.project).run {
            title = MessageSource.message("title.database-generator")
            setSize(DialogConstant.WIDTH, DialogConstant.HEIGHT)
            show()
        }
    }

}