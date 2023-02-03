package com.example.android.architecture.blueprints.todoapp

import org.junit.Test

import org.junit.Assert.*

/**
 * These tests are run locally on your development machine's JVM and do not require an emulator or physical device.
 * Because of this, they run fast, but their fidelity is lower, meaning they act less like they would in the real world.
 *
 * These are highly focused tests that run on a single class, usually a single method in that class.
 * If a unit test fails, you can know exactly where in your code the issue is.
 * They have low fidelity since in the real world, your app involves much more than the execution of one method or class.
 * They are fast enough to run every time you change your code.
 * They will most often be locally run tests (in the test source set).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
