package com.lipanre.code.generator

import com.intellij.database.util.common.isInstanceOf
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBPanel
import com.intellij.ui.dsl.builder.*
import com.intellij.ui.layout.selected
import com.intellij.util.ui.CheckBox
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
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

    /**
     * 模板控制开关面板
     */
    private lateinit var checkBoxPanel: JBPanel<JBPanel<*>>

    /**
     * 下拉框选择器监听器
     */
    private val comboBoxActionListener: (ActionEvent) -> Unit = {
        it -> (it.source as ComboBox<*>).selectedItem?.let { config.fillTemplateFileNames(it as String) }
        updateCheckBoxPanel()
    }

    /**
     * 多选框checkbox监听器
     */
    private val checkBoxActionListener: (ActionEvent) -> Unit = {
        val checkBox = it.source as JBCheckBox

        // 记录开启与关闭的模板
        config.templateSwitch[checkBox.text] = checkBox.isSelected
    }


    init {
        super.init()
        this.title = title
        setSize(width, height)
    }

    override fun createCenterPanel(): JComponent {
        return panel {
            row(MessageSource.message("title.output-path-file-chooser")) {
                textFieldWithBrowseButton(MessageSource.message("title.output-path-file-chooser"), fileChooserDescriptor = FileUtil.createFileChooserDescriptor(true)).bindText(config::outputPath).align(Align.FILL)
            }
            row(MessageSource.message("title.template-select")) {
                val comboBox = comboBox(config.templates).bindItem(config::template).align(Align.FILL).component
                comboBox.selectedItem?.let { config.fillTemplateFileNames(it as String) }
                // 点击事件监听
                comboBox.addActionListener(comboBoxActionListener)
            }

            group(MessageSource.message("group.template-list")) {
                row {
                    checkBoxPanel = cell(JBPanel<JBPanel<*>>()).component
                    updateCheckBoxPanel()
                }.rowComment(MessageSource.message("group.template-list-comment"))
            }
        }
    }

    /**
     * 更新checkBox面板
     */
    private fun updateCheckBoxPanel() {
        checkBoxPanel.removeAll()
        config.templateFileNames.forEach {
            checkBoxPanel.add(createCheckBox(it))
        }
        checkBoxPanel.revalidate()
        checkBoxPanel.repaint()
    }

    /**
     * 创建checkBox组件
     */
    private fun createCheckBox(text: String): JBCheckBox {
        val checkBox = JBCheckBox(text)
        // 默认全部是开启状态
        checkBox.isSelected = true
        checkBox.addActionListener(checkBoxActionListener)
        return checkBox
    }

}