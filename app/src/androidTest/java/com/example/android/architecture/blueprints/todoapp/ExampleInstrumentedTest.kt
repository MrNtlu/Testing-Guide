package com.example.android.architecture.blueprints.todoapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * These tests run on real or emulated Android devices, so they reflect what will happen in the real world, but are also much slower.
 *
 * These test the interaction of several classes to make sure they behave as expected when used together.
 * One way to structure integration tests is to have them test a single feature, such as the ability to save a task.
 * They test a larger scope of code than unit tests, but are still optimized to run fast, versus having full fidelity.
 * They can be run either locally or as instrumentation tests, depending on the situation.
 * Example: Testing all the functionality of a single fragment and view model pair.
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.android.architecture.blueprints.reactive", appContext.packageName)
    }
}
