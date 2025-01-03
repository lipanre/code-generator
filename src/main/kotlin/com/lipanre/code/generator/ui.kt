package com.lipanre.code.generator

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.dsl.builder.*
import javax.swing.JComponent

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

    val templateFileNames = mutableSetOf<String>()


    /**
     * 主面板
     */
    val mainPaine by lazy {
        panel {

            row(MessageSource.message("title.output-path-file-chooser")) {
                textFieldWithBrowseButton(MessageSource.message("title.output-path-file-chooser"), fileChooserDescriptor = FileUtil.createFileChooserDescriptor(true)).bindText(config::outputPath).align(Align.FILL)
            }

            row(MessageSource.message("title.template-select")) {
                val comboBox = comboBox(templates).bindItem(config::template).align(Align.FILL).component
                comboBox.selectedItem?.let { fillTemplateFileNames(it as String) }
                // 点击事件监听
                comboBox.addActionListener { it -> (it.source as ComboBox<*>).selectedItem?.let { fillTemplateFileNames(it as String) } }
            }

            group(MessageSource.message("group.template-list")) {
                row {
                    templateFileNames.forEach { templateName ->
                        checkBox(templateName).bindSelected({ config.templateSwitch.getOrDefault(templateName, true) },{ config.templateSwitch[templateName] = it })
                    }
                }.rowComment(MessageSource.message("group.template-list-comment"))
            }
        }
    }

    /**
     * 模板目录名
     */
    private val dirName = "templates"



    /**
     * 模板列表
     */
    private val templates by lazy {
        FileUtil.listScratchesPluginFiles(dirName).toList()
    }

    init {
        super.init()
        this.title = title
        setSize(width, height)
    }

    override fun createCenterPanel(): JComponent {
        return mainPaine
    }

    override fun doOKAction() {
        super.doOKAction()
        println(config)
    }

    override fun show() {
        println("comboBox select template template name is: $templateFileNames")
        super.show()
    }

    private fun fillTemplateFileNames(fileName: String) {
        FileUtil.listScratchesPluginFiles(dirName, fileName).forEach(templateFileNames::add)
    }

}