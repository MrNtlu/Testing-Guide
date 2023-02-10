package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Assert.*
import org.junit.Test

/** TESTING NOTES
 * implementation—The dependency is available in all source sets, including the test source sets.
 * testImplementation—The dependency is only available in the test source set.
 * androidTestImplementation—The dependency is only available in the androidTest source set.
 *
 * Unit tests are focused and fast.
 * Integration tests verify interaction between parts of your program.
 * End-to-end tests verify features, have the highest fidelity, are often instrumented, and may take longer to run.
 *
 * Use dependency injection to replace a real class with a testing class, for example, a repository or a networking layer.
 *
 * Use instrumented testing (androidTest) to launch UI components.
 *
 * Suggested test naming `subjectUnderTest_actionOrInput_resultState`
 */
class StatisticsUtilsTest {
    // https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-basics#7
    // https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-test-doubles
    // https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-survey#2
    // https://github.com/googlecodelabs/android-testing/blob/end_codelab_3/app/build.gradle
    // https://github.com/googlecodelabs/android-testing/blob/end_codelab_3/build.gradle

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

    @Test
    fun getActiveAndCompletedStats_both_returnsFortySixty() {
        // Given 3 completed tasks and 2 active tasks
        val tasks = listOf(
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false)
        )
        // When the list of tasks is computed
        val result = getActiveAndCompletedStats(tasks)

        // Then the result is 40-60
        assertEquals(result.activeTasksPercent, 40f)
        assertEquals(result.completedTasksPercent, 60f)
    }

    @Test
    fun getActiveAndCompletedStats_emptyList_returnsZero() {

        val result = getActiveAndCompletedStats(emptyList())

        assertEquals(result.completedTasksPercent, 0f)
        assertEquals(result.activeTasksPercent, 0f)
    }

    @Test
    fun getActiveAndCompletedStats_null_returnsZero() {

        val result = getActiveAndCompletedStats(null)

        assertEquals(result.completedTasksPercent, 0f)
        assertEquals(result.activeTasksPercent, 0f)
    }
}