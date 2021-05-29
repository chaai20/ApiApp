package com.example.appmobile.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.example.appmobile.Consts
import com.example.appmobile.databinding.FragmentCharacterBinding
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private lateinit var binding : FragmentCharacterBinding
    /**
     *  Collect character info.
     */
    private val args: CharacterFragmentArgs by navArgs()

    @Inject
    lateinit var glide : RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Shared material container transition from MainFragment to here
        sharedElementEnterTransition = MaterialContainerTransform().setDuration(300)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)

        // Set the transition name to avoid runtime IllegalStateException
        binding.root.transitionName = Consts.CHARACTER_CONTAINER_TRANSITION

        // Display character info
        glide.load(args.character?._image).into(binding.characterFragmentImageview)
        binding.characterFragmentNameText.text = args.character?.name
        binding.characterFragmentStatusText.text = args.character?.status
        binding.characterFragmentSpeciesText.text = args.character?.species
        binding.characterFragmentGenderText.text = args.character?.gender

        return binding.root
    }


}