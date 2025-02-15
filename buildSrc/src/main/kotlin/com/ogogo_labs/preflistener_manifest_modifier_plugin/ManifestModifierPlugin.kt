package com.ogogo_labs.preflistener_manifest_modifier_plugin

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.support.uppercaseFirstChar
import java.io.File

const val manifestReleaseVersion = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
        "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
        "    <application/>" +
        "</manifest>"


class ManifestModifierPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val androidComponents = project.extensions.getByType(LibraryAndroidComponentsExtension::class.java)

        androidComponents.beforeVariants { variantBuilder ->

            System.out.println("TASKKK ${variantBuilder.buildType}")

            if (variantBuilder.buildType == "release") {

                System.out.println("TASKKK Start in ${variantBuilder.buildType}")
                System.out.println("TASKKK $\"modify${variantBuilder.name.uppercaseFirstChar()}Manifest\"")
                val modifyManifestTask = project.tasks.register(
                    "modify${variantBuilder.name.uppercaseFirstChar()}Manifest",
                    ModifyManifestTask1::class.java
                ) {
                    manifestFile.set(project.file("src/main/AndroidManifest.xml"))
                }
                modifyManifestTask.configure {
                    group = "manifest"
                    description = "Modify the manifest file for release builds"
                }

                System.out.println("TASKKK" + "pre${variantBuilder.name.uppercaseFirstChar()}Build")
                project.afterEvaluate {
                    project.tasks.named("pre${variantBuilder.name.uppercaseFirstChar()}Build").configure {
                        dependsOn(modifyManifestTask)
                    }
                }
            }
        }
    }
}

abstract class ModifyManifestTask1 : org.gradle.api.DefaultTask() {

    @get:org.gradle.api.tasks.InputFile
    abstract val manifestFile: org.gradle.api.provider.Property<File>

    @org.gradle.api.tasks.TaskAction
    fun modifyManifest() {
        val inputFile = manifestFile.get() //
        val modifiedManifest = inputFile.readText()
        val text = modifiedManifest
        System.out.println("TASKKK:")
        System.out.println(text)
        val output = manifestReleaseVersion
        System.out.println("TASKKK: out")
        System.out.println(output)

        inputFile.writeText(output)
    }
}
