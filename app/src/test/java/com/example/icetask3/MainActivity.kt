package com.example.icetask3

import android.content.Intent
import android.os.Build
import android.widget.Button
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.icetask2.R
import com.google.firebase.auth.FirebaseAuth
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var mockAuth: FirebaseAuth

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockAuth = Mockito.mock(FirebaseAuth::class.java)
    }

    @Test
    fun testLoginButton() {
        ActivityScenario.launch(MainActivity::class.java).onActivity { activity ->
            // Mocking FirebaseAuth instance
            activity.auth = mockAuth

            // Find the login button and perform a click
            val loginButton: Button = activity.findViewById(R.id.login_button)
            loginButton.performClick()

            // Verify that startActivity was called with the correct intent
            verify(activity).startActivity(Intent(activity, LoginActivity::class.java))
        }
    }

    @Test
    fun testRegisterButton() {
        ActivityScenario.launch(MainActivity::class.java).onActivity { activity ->
            // Mocking FirebaseAuth instance
            activity.auth = mockAuth

            // Find the register button and perform a click
            val registerButton: Button = activity.findViewById(R.id.register_button)
            registerButton.performClick()

            // Verify that startActivity was called with the correct intent
            verify(activity).startActivity(Intent(activity, RegisterActivity::class.java))
        }
    }
}
