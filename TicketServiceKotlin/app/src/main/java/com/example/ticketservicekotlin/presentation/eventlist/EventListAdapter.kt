package com.example.ticketservicekotlin.presentation.eventlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketservicekotlin.R
import com.example.ticketservicekotlin.databinding.EventListItemBinding
import com.example.ticketservicekotlin.domain.model.Event

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class EventListAdapter(val onClickListener: OnClickListener):
    ListAdapter<Event, EventListAdapter.EventViewHolder>(DiffCallback) {

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Event]
     * has been updated.
     */
    companion object DiffCallback: DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder.from(parent)
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(event)
        }
    }

    /**
     * The [EventViewHolder] constructor takes the binding variable from the associated
     * EventListItem, which gives it access to the full [Event] information.
     */
    class EventViewHolder(private var binding: EventListItemBinding):
    RecyclerView.ViewHolder(binding.root){

        companion object {
            fun from(parent: ViewGroup): EventViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = EventListItemBinding.inflate(layoutInflater, parent, false)
                return EventViewHolder(binding)
            }
        }

        fun bind(event: Event){
            binding.event = event
            // This forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [Event]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [Event]
     */
    class OnClickListener(val clickListener: (event: Event) -> Unit){
        fun onClick(event: Event) = clickListener(event)
    }
}