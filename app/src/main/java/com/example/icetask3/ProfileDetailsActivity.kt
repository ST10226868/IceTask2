package com.example.icetask3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.icetask2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileDetailsActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_details)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val currentUser = auth.currentUser
        val userId = currentUser?.uid

        val usernameEditText: EditText = findViewById(R.id.username_editText)
        val qualificationEditText: EditText = findViewById(R.id.qualification_editText)
        val studentNumberEditText: EditText = findViewById(R.id.student_number_editText)
        val editButton: Button = findViewById(R.id.edit_button)

        if (userId != null) {
            // Fetch user details from Firestore
            firestore.collection("users").document(userId)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val username = document.getString("username")
                        val qualification = document.getString("qualification")
                        val studentNumber = document.getString("studentNumber")

                        usernameEditText.setText(username)
                        qualificationEditText.setText(qualification)
                        studentNumberEditText.setText(studentNumber)
                    } else {
                        Toast.makeText(this, "User details not found", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error fetching user details: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        editButton.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }
    }
}
