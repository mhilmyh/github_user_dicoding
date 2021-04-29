package com.hilmyhaidar.github_user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hilmyhaidar.github_user.R
import com.hilmyhaidar.github_user.databinding.ItemUserBinding
import com.hilmyhaidar.github_user.models.PersonModel

class PersonAdapter(
    private val context: Context,
    private val recyclerViewClickListener: RecyclerViewClickListener
): RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private var persons: ArrayList<PersonModel> = ArrayList()
    private var backupPersons: ArrayList<PersonModel> = ArrayList()

    class ViewHolder(
        view: View,
        recyclerViewClickListener: RecyclerViewClickListener
    ) : RecyclerView.ViewHolder(view), View.OnClickListener  {

        val binding: ItemUserBinding = ItemUserBinding.bind(view)
        private val listener: RecyclerViewClickListener = recyclerViewClickListener

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            listener.onClickItem(v, adapterPosition)
        }
    }

    fun setData(data: ArrayList<PersonModel>) {
        persons = data
        backupPersons = data
    }

    fun getData(position: Int): PersonModel {
        return persons[position]
    }

    fun filter(query: String) {
        persons = if(query.isEmpty()) backupPersons
        else persons.filter { it.name.contains(query, true) } as ArrayList<PersonModel>

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view, recyclerViewClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewName.text = persons[position].name
        holder.binding.textViewCompany.text = persons[position].company
        holder.binding.textViewLocation.text = persons[position].location
        holder.binding.imageViewPicture.setImageResource(persons[position].getResourceId(context))
    }

    override fun getItemCount(): Int = persons.size

    interface RecyclerViewClickListener {
        fun onClickItem(view: View, position: Int)
    }
}