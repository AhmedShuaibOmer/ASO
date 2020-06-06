// Copyright (c) 2019 . Wilberforce Uwadiegwu. All Rights Reserved.

package aso.mo.asoplayer.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import aso.mo.asoplayer.R

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*val navController =
            Navigation.findNavController(requireActivity(), R.id.SubNavHostFragment)
        navigationBar.setupWithNavController(navController)*/
    }
}
