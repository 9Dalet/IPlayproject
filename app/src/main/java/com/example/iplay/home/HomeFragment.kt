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
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.iplay.R
import com.example.iplay.login.LoginActivity
import com.example.iplay.navbar.NavBarActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private lateinit var imageProfile: ImageView
    private var auth = FirebaseAuth.getInstance()
    private lateinit var logout : Button
    private lateinit var nicknameUser: TextView
    private lateinit var nicknameString: String
    private lateinit var instagram: ImageButton
    private lateinit var facebook: ImageButton
    private lateinit var youtube: ImageButton

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

        val user = auth.currentUser
        nicknameUser = view.findViewById(R.id.userTextView)
        nicknameString = ""
        instagram = view.findViewById(R.id.instagramButton)
        facebook = view.findViewById(R.id.facebookButton)
        youtube = view.findViewById(R.id.youtubeButton)

        if (user != null) {
            loadUserdata(user)
        }

        logout = view.findViewById(R.id.logOutButton)

        //se schiacciamo il button 'logout' ci riporta alla LoginActivity e l'utente non è più salvato
        logout.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            auth.signOut()
        }

        imageProfile = view.findViewById(R.id.imageView)

        //per cambiare immagine profilo dalla galleria
        imageProfile.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3)
            }

        instagram.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/its_ict_spotted/"))
            startActivity(intent)
        }

        facebook.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/MilesDavis"))
            startActivity(intent)
        }

        youtube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=A28hMd9uaOg&t=6s"))
            startActivity(intent)
        }
    }

    private fun loadUserdata(user: FirebaseUser) {
        nicknameString = nicknameUser.text.toString()
        FirebaseFirestore.getInstance().collection("userData").document(user.uid).get().addOnCompleteListener {
            if (it.isSuccessful) {
                val name: String = it.result.data?.get("name").toString()
                val surname: String = it.result.data?.get("surname").toString()
                nicknameString = name + surname

                //manca il pezzo per far apparire lo stesso nome nella home
            }

        }
    }

    //si attiva quando si deve scegliere la foto profilo dalla galleria, e la salva al posto di quella predefinita
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && data != null) {
            val selectedImage: Uri? = data.data;
            imageProfile.setImageURI(selectedImage)
        }
    }

}