package com.lipanre.code.generator

import com.intellij.ide.extensionResources.ExtensionsRootType
import com.intellij.ide.scratch.ScratchFileService
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ui.componentsList.components.ScrollablePanel
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.dsl.builder.*
import com.intellij.ui.util.minimumHeight
import javax.swing.JComponent
import javax.swing.ScrollPaneLayout
import javax.swing.Scrollable

/**
 * 数据库生成器对话框
 *
 * @author lipanre
 */
class DataBaseGeneratorDialog(
    private val config: DataBaseGeneratorConfig,
    project: Project,
    title: String = MessageSource.message("title.database-generator"),
    width: Int = DialogConstant.WIDTH,
    height: Int = DialogConstant.HEIGHT,
) : DialogWrapper(project) {

    /**
     * 模板目录名
     */
    private val dirName = "templates"

    /**
     * 模板列表
     */
    private val templates by lazy {
        FileUtil.listScratchesPluginFiles(dirName)?.toList().orEmpty()
    }

    init {
        super.init()
        this.title = title
        setSize(width, height)
        this.show()
    }

    override fun createCenterPanel(): JComponent {
        return panel {
            row(MessageSource.message("label.please-select-output-path")) {
                textFieldWithBrowseButton(
                    MessageSource.message("title.output-path-file-chooser"),
                    fileChooserDescriptor = FileUtil.createFileChooserDescriptor(true)
                ).bindText(config::outputPath).align(Align.FILL)
            }
            row (MessageSource.message("title.template-select")){
                comboBox(templates).bindItem(config::template).align(Align.FILL)
            }

        }
    }
}