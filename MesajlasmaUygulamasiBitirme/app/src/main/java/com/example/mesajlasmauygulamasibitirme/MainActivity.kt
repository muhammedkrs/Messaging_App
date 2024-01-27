package com.example.mesajlasmauygulamasibitirme

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mesajlasmauygulamasibitirme.activity.NumberActivity
import com.example.mesajlasmauygulamasibitirme.adapter.ViewPagerAdapter
import com.example.mesajlasmauygulamasibitirme.databinding.ActivityMainBinding
import com.example.mesajlasmauygulamasibitirme.ui.CallFragment
import com.example.mesajlasmauygulamasibitirme.ui.ChatFragment
import com.example.mesajlasmauygulamasibitirme.ui.StatusFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private var binding :ActivityMainBinding?=null
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding!!.root)


        val fragmentArrayList = ArrayList<Fragment>()
        fragmentArrayList.add(ChatFragment())
        fragmentArrayList.add(StatusFragment())
        fragmentArrayList.add(CallFragment())

        auth= FirebaseAuth.getInstance()

        if(auth.currentUser ==null)
        {
            startActivity(Intent(this,NumberActivity::class.java))
            finish()
        }



        val adapter=ViewPagerAdapter(this,supportFragmentManager,fragmentArrayList)

        binding!!.viewPager.adapter=adapter
        binding!!.tabs.setupWithViewPager(binding!!.viewPager)
    }
}