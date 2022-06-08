package com.example.iplay.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.iplay.R

class CreateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var eventEditText: EditText
    private lateinit var whenEditText: EditText
    private lateinit var locationEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var createButton: Button
    private lateinit var eventString: String
    private lateinit var whenString: String
    private lateinit var locationString: String
    private lateinit var descriptionString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_create, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createButton = view.findViewById(R.id.createButton)
        eventEditText = view.findViewById(R.id.eventEditText)
        whenEditText = view.findViewById(R.id.whenEditText)
        locationEditText = view.findViewById(R.id.locationEditText)
        descriptionEditText = view.findViewById(R.id.descriptionEditText)
        eventString = ""
        whenString = ""
        locationString = ""
        descriptionString = ""

        createButton.setOnClickListener {
            if (eventEditText.text.isNotEmpty() && whenEditText.text.isNotEmpty() && locationEditText.text.isNotEmpty() && descriptionEditText.text.isNotEmpty()) {
                eventString = eventEditText.text.toString()
                whenString = whenEditText.text.toString()
                locationString = locationEditText.text.toString()
                descriptionString = descriptionEditText.text.toString()
            } else {
                Toast.makeText(activity, "Fill in all fields", Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}