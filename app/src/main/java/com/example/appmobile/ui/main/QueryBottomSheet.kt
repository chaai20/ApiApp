package com.example.appmobile.ui.main

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.appmobile.R
import com.example.appmobile.databinding.BottomSheetLayoutBinding
import com.example.appmobile.model.CharacterQueryModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Query bottom sheet
 */

class QueryBottomSheet() : BottomSheetDialogFragment(), Parcelable {

    private var mListener: BottomSheetListener? = null
    private var statusQuery : String? = null
    private var genderQuery : String? = null

    constructor(parcel: Parcel) : this() {
        statusQuery = parcel.readString()
        genderQuery = parcel.readString()
    }

    /**
     *  Search button in bottom sheet
     */
    interface BottomSheetListener{
        fun queryButtonClicked(query : CharacterQueryModel?)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = BottomSheetLayoutBinding.inflate(inflater,container,false)

        // Init spinners for gender and status

        context?.let {
            ArrayAdapter.createFromResource(it, R.array.status_spinner, android.R.layout.simple_spinner_item).also { adapter ->

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.queryBottomSheetStatusSpinner.adapter = adapter
            }
            ArrayAdapter.createFromResource(it, R.array.gender_spinner, android.R.layout.simple_spinner_item).also { adapter ->

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.queryBottomSheetGenderSpinner.adapter = adapter
            }
        }

        // Click listener for search button
        binding.queryBottomSheetQueryButton.setOnClickListener {
            statusQuery = findStatus(binding.queryBottomSheetStatusSpinner.selectedItemId)
            genderQuery = findGender(binding.queryBottomSheetGenderSpinner.selectedItemId)

            mListener?.queryButtonClicked(
                CharacterQueryModel(
                    binding.queryBottomSheetEditText.text.toString(),
                    statusQuery,
                    genderQuery
                )
            )
            // after clicking search button, close bottom sheet
            dismiss()
        }

        return binding.root
    }

    private fun findGender(selectedItemId: Long): String? {
        return when(selectedItemId){
            0L -> {
                null
            }
            1L ->{
                "female"
            }
            2L ->{
                "male"
            }
            3L ->{
                "genderless"
            }
            4L ->{
                "unknown"
            }
            else -> null
        }
    }

    private fun findStatus(selectedItemId: Long): String? {
        return when(selectedItemId){
            0L -> {
                null
            }
            1L ->{
                "alive"
            }
            2L ->{
                "dead"
            }
            3L ->{
                "unknown"
            }
            else -> null
        }
    }

    // Attach main activity to search button listener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = context as BottomSheetListener
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(statusQuery)
        parcel.writeString(genderQuery)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QueryBottomSheet> {
        override fun createFromParcel(parcel: Parcel): QueryBottomSheet {
            return QueryBottomSheet(parcel)
        }

        override fun newArray(size: Int): Array<QueryBottomSheet?> {
            return arrayOfNulls(size)
        }
    }

}