package com.lipanre.code.generator

import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBTextField
import com.intellij.ui.components.Panel
import com.intellij.ui.dsl.builder.RowLayout
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent

/**
 * 数据库生成器对话框
 *
 * @author lipanre
 */
class DataBaseGeneratorDialog(project: Project?) : DialogWrapper(project) {

    init {
        super.init()
    }

    override fun createCenterPanel(): JComponent {
        return panel {
            row { label("Data-Base-Generator") }
        }
    }
}