package com.lipanre.code.generator

import com.intellij.openapi.graph.impl.view.JBPartialLayouter
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.JBCardLayout
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.Panel
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.bindItem
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.util.maximumWidth
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import javax.swing.BoxLayout
import javax.swing.JComponent
import javax.swing.plaf.PanelUI

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
        val panel: JBPanel<JBPanel<*>> = JBPanel<JBPanel<*>>()

        val childPanel: JBPanel<JBPanel<*>> = JBPanel<JBPanel<*>>()
        childPanel.add(JBLabel("test label"))
        childPanel.background = JBColor.GREEN

        val childPanel2: JBPanel<JBPanel<*>> = JBPanel<JBPanel<*>>()
        childPanel2.add(JBLabel("test label2"))
        childPanel2.background = JBColor.BLUE

        val childPanel3: JBPanel<JBPanel<*>> = JBPanel<JBPanel<*>>()
        childPanel3.add(JBLabel("test label3"))
        childPanel3.background = JBColor.YELLOW
//        childPanel3.size = Dimension(100, 100)

        val childPanel4: JBPanel<JBPanel<*>> = JBPanel<JBPanel<*>>()
        childPanel4.add(JBLabel("test label4"))
        childPanel4.background = JBColor.CYAN
//        childPanel4.size = Dimension(100, 100)

        val childPanel5: JBPanel<JBPanel<*>> = JBPanel<JBPanel<*>>()
        childPanel5.add(JBLabel("test label5"))
        childPanel5.background = JBColor.ORANGE
//        childPanel4.size = Dimension(100, 100)

//        panel.layout = BorderLayout(panel, BoxLayout.PAGE_AXIS)
        panel.layout = BorderLayout(0, 0)

        panel.add(childPanel, BorderLayout.EAST)
        panel.add(childPanel2, BorderLayout.SOUTH)
        panel.add(childPanel3, BorderLayout.NORTH)
        panel.add(childPanel4, BorderLayout.WEST)
        panel.add(childPanel5, BorderLayout.CENTER)
        panel.background = JBColor.RED
        return panel
    }
}