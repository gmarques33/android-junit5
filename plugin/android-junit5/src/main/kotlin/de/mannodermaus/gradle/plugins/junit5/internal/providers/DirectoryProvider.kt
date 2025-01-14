package de.mannodermaus.gradle.plugins.junit5.internal.providers

import java.io.File

/**
 * General interface for providers of class & source directories
 * towards the construction of JUnit 5 tasks and its companions.
 *
 * Registered through the plugin, integrations with different languages
 * and frameworks can provide their own collection of directories.
 * The most prominent example consists of the opt-in Kotlin support,
 * which provides the "/kotlin" directories to each JUnit 5 task,
 * allowing Kotlin classes to be used for test detection & execution.
 */
internal interface DirectoryProvider {
    /**
     * The locations of compiled class files
     */
    fun mainClassDirectories(): Set<File>

    /**
     * The locations of compiled test class files
     */
    fun testClassDirectories(): Set<File>

    /**
     * The combined locations of all compiled class files
     */
    fun classDirectories() = mainClassDirectories() + testClassDirectories()

    /**
     * The locations of source files
     */
    fun mainSourceDirectories(): Set<File>

    /**
     * The locations of test source files
     */
    fun testSourceDirectories(): Set<File>

    /**
     * The combined locations of all source files
     */
    fun sourceDirectories() = mainSourceDirectories() + testSourceDirectories()
}

/* Extensions */

internal fun Iterable<DirectoryProvider>.classDirectories() = flatMap { it.classDirectories() }.distinct()
internal fun Iterable<DirectoryProvider>.mainClassDirectories() = flatMap { it.mainClassDirectories() }.distinct()
internal fun Iterable<DirectoryProvider>.testClassDirectories() = flatMap { it.testClassDirectories() }.distinct()
internal fun Iterable<DirectoryProvider>.sourceDirectories() = flatMap { it.sourceDirectories() }.distinct()
internal fun Iterable<DirectoryProvider>.mainSourceDirectories() = flatMap { it.mainSourceDirectories() }.distinct()
internal fun Iterable<DirectoryProvider>.testSourceDirectories() = flatMap { it.testSourceDirectories() }.distinct()
