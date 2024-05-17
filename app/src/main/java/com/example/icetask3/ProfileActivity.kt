package com.example.icetask3
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.icetask2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val saveButton: Button = findViewById(R.id.save_button)

        saveButton.setOnClickListener {
            saveProfileDetails()
        }
    }

    private fun saveProfileDetails() {
        val currentUser = auth.currentUser
        val userId = currentUser?.uid
        val username = findViewById<EditText>(R.id.username_editText).text.toString()
        val qualification = findViewById<EditText>(R.id.qualification_editText).text.toString()
        val studentNumber = findViewById<EditText>(R.id.student_number_editText).text.toString()

        if (userId != null) {
            val user = hashMapOf(
                "userId" to userId,
                "username" to username,
                "qualification" to qualification,
                "studentNumber" to studentNumber
                // Add more fields as needed
            )

            firestore.collection("users").document(userId)
                .set(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "Profile details saved successfully", Toast.LENGTH_SHORT).show()
                    // You can add any further actions here
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error saving profile details: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}



