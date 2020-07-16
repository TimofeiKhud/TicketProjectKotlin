package com.example.ticketservicekotlin.presentation.eventlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.ticketservicekotlin.R
import com.example.ticketservicekotlin.databinding.FragmentEventListBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * This fragment shows a list of the upcoming events.
 */
@AndroidEntryPoint
class EventListFragment : Fragment() {

    private val viewModel by viewModels<EventListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEventListBinding.inflate(layoutInflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        // Giving the binding access to the EventListViewModel
        binding.viewModel = viewModel
        // Sets the adapter of the eventList RecyclerView with clickHandler lambda that
        // tells the viewModel when our event is clicked
        binding.eventList.adapter = EventListAdapter(EventListAdapter.OnClickListener{
            viewModel.displayEventDetails(it)
        })

        // Observe the navigateToSelectedEvent LiveData and Navigate when it isn't null
        // After navigating, call displayEventDetailsComplete() so that the ViewModel is ready
        // for another navigation event.
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (it !=null) {
                this.findNavController()
                    .navigate(EventListFragmentDirections.actionEventListFragmentToEventDetailFragment(it))
                // Tell the ViewModel navigate call was made to prevent multiple navigation
                viewModel.displayEventDetailsComplete()
            }
        })
        setHasOptionsMenu(true)

        return binding.root
    }
}