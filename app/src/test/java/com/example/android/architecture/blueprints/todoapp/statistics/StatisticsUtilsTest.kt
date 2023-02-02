package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest {
    /**
     * implementation—The dependency is available in all source sets, including the test source sets.
     * testImplementation—The dependency is only available in the test source set.
     * androidTestImplementation—The dependency is only available in the androidTest source set.
     *
     * Suggested test naming `subjectUnderTest_actionOrInput_resultState`
     */



    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = false)
        )

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(result.completedTasksPercent, 0f)
        assertEquals(result.activeTasksPercent, 100f)
    }

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZeroHamCrest() {
        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = false)
        )

        val result = getActiveAndCompletedStats(tasks)

        assertThat(result.activeTasksPercent, `is`(100f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }
}