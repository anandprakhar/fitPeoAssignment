package com.assesment.fitpeoassignment.viewmodels

import com.assesment.fitpeoassignment.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainViewModelTest {
    private val testDispatcher= StandardTestDispatcher()

    @get:Rule
//    val instanceTaskExecutorRule= InstanceTaskExecutorRule()

    @Mock
    lateinit var mainRepository: MainRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun test_GetPhotos(){

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}