package com.lipanre.code.generator

import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.observable.util.bind
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.mac.MacPathChooserDialog
import java.awt.Component
import javax.swing.JComponent

/**
 * 数据库生成器对话框
 *
 * @author lipanre
 */
class DataBaseGeneratorDialog(
    private val project: Project,
    title: String = MessageSource.message("title.database-generator"),
    width: Int = DialogConstant.WIDTH,
    height: Int = DialogConstant.HEIGHT
) : DialogWrapper(project) {

    init {
        super.init()
        setTitle(title)
        setSize(width, height)
        show()
    }

    override fun createCenterPanel(): JComponent {
        val p = panel {
            row(MessageSource.message("label.please-select-output-path")) {
                cell(TextFieldWithBrowseButton {
                    val fileChooseDescriptor = FileChooserDescriptor(false, true, false, false, false, false)
                    val virtualFile = FileChooser.chooseFile(fileChooseDescriptor, project, null)
                    println(virtualFile)
                }).align(Align.FILL)
            }
        }
        return p
    }
}