package com.anwesh.uiprojects.linkedblackboxmultilineview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.blackboxmultilineview.BlackBoxMultiLineView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BlackBoxMultiLineView.create(this)
    }
}
