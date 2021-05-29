/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.appmobile.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appmobile.databinding.ActivityMainBinding
import com.example.appmobile.model.CharacterQueryModel
import com.example.appmobile.ui.main.QueryBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(), QueryBottomSheet.BottomSheetListener {

    private lateinit var binding: ActivityMainBinding
    private val mainViewmodel by viewModels<MainActivityViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.mainActivityToolbar)



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(applicationContext,"Will be implemented",Toast.LENGTH_SHORT).show()
        return super.onOptionsItemSelected(item)
    }

    /**
     *  Interface - Listener mechanism for passing bottom sheet query data. Shared Viewmodels also can be used.
     */
    override fun queryButtonClicked(query: CharacterQueryModel?) {
        mainViewmodel.query.postValue(query)
    }




}
