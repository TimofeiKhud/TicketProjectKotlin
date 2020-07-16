package com.example.ticketservicekotlin.presentation.eventdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.ticketservicekotlin.R
import com.example.ticketservicekotlin.databinding.FragmentEventDetailBinding
import com.example.ticketservicekotlin.presentation.eventlist.EventListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventDetailFragment : Fragment() {

    private val viewModel by viewModels<EventDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val binding = FragmentEventDetailBinding.inflate(layoutInflater).apply {
//            this.lifecycleOwner = lifecycleOwner
//            this.viewModel = viewModel
//        }
        val binding = FragmentEventDetailBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.event = viewModel.selectedEvent.value
        return binding.root
    }
}