package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.*
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Pure view model tests can usually go in the test source set because their code doesn't usually require Android.
 *
 * When you have a local test where you need simulated Android framework classes (such as an Application Context), follow these steps to properly set up AndroidX Test:
 * Add the AndroidX Test core and ext dependencies
 * Add the Robolectric Testing library dependency
 * Annotate the class with the AndroidJunit4 test runner
 * NOTE: If your view model does not need an Application context, you can construct the view model without needing any additional libraries.
 *
 * What does @RunWith(AndroidJUnit4::class) do?
 * (We removed it since TasksViewModel no longer needs the ApplicationContext)
 * A test runner is a JUnit component that runs tests. Without a test runner, your tests would not run.
 * There's a default test runner provided by JUnit that you get automatically. @RunWith swaps out that default test runner.
 * The AndroidJUnit4 test runner allows for AndroidX Test to run your test differently depending on whether they are instrumented or local tests.
 */
class TasksViewModelTest {
    // Use a fake repository to be injected into the viewmodel
    private lateinit var tasksRepository: FakeTestRepository

    // When you use it with the @get:Rule annotation, it causes some code in the InstantTaskExecutorRule class to be run before and after the tests
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var tasksViewModel: TasksViewModel

    // When you have repeated setup code for multiple tests, you can use the @Before annotation to create a setup method and remove repeated code.
    @Before
    fun setupViewModel() {
        tasksRepository = FakeTestRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        tasksRepository.addTasks(task1, task2, task3)
        tasksViewModel = TasksViewModel(tasksRepository)
    }

    @Test
    fun addNewTask_setsNewTaskEvent() {
        // When adding a new task
        tasksViewModel.addNewTask()

        // Then the new task event is triggered
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
        assertThat(value.getContentIfNotHandled(), (not(nullValue())))
    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible() {
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(true))
    }
}