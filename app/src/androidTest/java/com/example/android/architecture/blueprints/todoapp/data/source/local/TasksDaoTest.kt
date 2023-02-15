package com.example.android.architecture.blueprints.todoapp.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.android.architecture.blueprints.todoapp.data.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @ExperimentalCoroutinesApi—you'll be using runBlockingTest, which is part of kotlinx-coroutines-test, thus you need this annotation.
 * @SmallTest— Marks the test as a "small run-time" integration test (versus @MediumTest integration tests and @LargeTest end-to-end tests).
 * This helps you group and choose which size test to run. DAO tests are considered unit tests since you are only testing the DAO, thus you can call them small tests.
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class TasksDaoTest {
    private lateinit var database: ToDoDatabase

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        // Using an in-memory database so that the information stored here disappears when the
        // process is killed.
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            ToDoDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertTaskAndGetById() = runBlockingTest {
        // GIVEN - Insert a task.
        val task = Task("title", "description")
        database.taskDao().insertTask(task)

        // WHEN - Get the task by id from the database.
        val loaded = database.taskDao().getTaskById(task.id)

        // THEN - The loaded data contains the expected values.
        assertThat<Task>(loaded as Task, notNullValue())
        assertThat(loaded.id, `is`(task.id))
        assertThat(loaded.title, `is`(task.title))
        assertThat(loaded.description, `is`(task.description))
        assertThat(loaded.isCompleted, `is`(task.isCompleted))
    }

    @Test
    fun updateTaskAndGetById() = runBlockingTest{
        // 1. Insert a task into the DAO.
        val task = Task("title", "description")
        database.taskDao().insertTask(task)

        // 2. Update the task by creating a new task with the same ID but different attributes.
        val updatedTask = Task("title", "description", true, task.id)
        database.taskDao().updateTask(updatedTask)

        // 3. Check that when you get the task by its ID, it has the updated values.
        val loadedTask = database.taskDao().getTaskById(task.id)
        assertThat(loadedTask?.id, `is`(task.id))
        assertThat(loadedTask?.title, `is`("new title"))
        assertThat(loadedTask?.description, `is`("new description"))
        assertThat(loadedTask?.isCompleted, `is`(true))
    }
}