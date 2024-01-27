package com.example.mesajlasmauygulamasibitirme.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mesajlasmauygulamasibitirme.adapter.ChatAdapter
import com.example.mesajlasmauygulamasibitirme.databinding.FragmentChatBinding
import com.example.mesajlasmauygulamasibitirme.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatFragment : Fragment() {

  lateinit var binding:FragmentChatBinding
  private var database:FirebaseDatabase?=null
  lateinit var userList:ArrayList<UserModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentChatBinding.inflate(layoutInflater)
        userList= ArrayList()

        database=FirebaseDatabase.getInstance()

        database!!.reference.child("users")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    userList.clear()

                    for(snapshot1 in snapshot.children){
                        var user=snapshot1.getValue(UserModel::class.java)
                        if(user!!.uid!=FirebaseAuth.getInstance().uid){
                            userList.add(user)
                        }
                    }

                    binding.userListRecycleView.adapter = ChatAdapter(requireContext(),userList)

                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

        return binding.root
    }


}