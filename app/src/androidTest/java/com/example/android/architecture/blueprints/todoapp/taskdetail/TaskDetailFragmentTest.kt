package com.example.android.architecture.blueprints.todoapp.taskdetail

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.ServiceLocator
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeAndroidTestRepository
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Why put this test in the androidTest source set?
 * Fragments (at least the ones you'll be testing) are visual and make up the user interface.
 * Because of this, when testing them, it helps to render them on a screen, as they would when the app is running.
 * Thus when testing fragments, you usually write instrumented tests, which live in the androidTest source set.
 * As a general rule of thumb, if you are testing something visual, run it as an instrumented test.
 *
 * @MediumTest
 * Marks the test as a "medium run-time" integration test (versus @SmallTest unit tests and @LargeTest end-to-end tests).
 * This helps you group and choose which size of test to run.
 */
@MediumTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class TaskDetailFragmentTest {
    private lateinit var repository: TasksRepository

    @Before
    fun initRepository() {
        repository = FakeAndroidTestRepository()
        ServiceLocator.tasksRepository = repository
    }

    @After
    fun cleanupDb() = runBlockingTest {
        ServiceLocator.resetRepository()
    }


    @Test
    fun activeTaskDetails_DisplayedInUi()  = runBlockingTest{
        // GIVEN - Add active (incomplete) task to the DB
        val activeTask = Task("Active Task", "AndroidX Rocks", false)
        repository.saveTask(activeTask)

        // WHEN - Details fragment launched to display task
        val bundle = TaskDetailFragmentArgs(activeTask.id).toBundle()
        launchFragmentInContainer<TaskDetailFragment>(bundle, R.style.AppTheme)
    }
}