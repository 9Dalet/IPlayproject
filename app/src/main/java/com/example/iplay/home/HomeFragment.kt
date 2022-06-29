package com.example.iplay.home

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.iplay.R
import com.example.iplay.login.LoginActivity
import com.example.iplay.navbar.NavBarActivity
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private lateinit var imageProfile: ImageView
    private var auth = FirebaseAuth.getInstance()
    private lateinit var logout : Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logout = view.findViewById(R.id.logOutButton)
        logout.setOnClickListener {

            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            auth.signOut()
        }

        imageProfile = view.findViewById(R.id.imageView)
        imageProfile.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3)
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && data != null) {
            val selectedImage: Uri? = data.data;
            imageProfile.setImageURI(selectedImage)
        }
    }

}