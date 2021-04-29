package com.hilmyhaidar.github_user.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.hilmyhaidar.github_user.adapter.PersonAdapter
import com.hilmyhaidar.github_user.databinding.ActivityListUserBinding
import com.hilmyhaidar.github_user.models.PersonModel

class ListUserActivity : AppCompatActivity(), PersonAdapter.RecyclerViewClickListener, SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityListUserBinding
    private lateinit var adapter: PersonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUserBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPerson.layoutManager = layoutManager

        adapter = PersonAdapter(this, this)
        val data = PersonModel.loadJsonAssetToModel(this, "githubuser.json")
        adapter.setData(data)
        binding.recyclerViewPerson.adapter = adapter

        binding.searchView.setOnQueryTextListener(this)
    }

    override fun onClickItem(view: View, position: Int) {
        val intent = Intent(this@ListUserActivity, DetailUserActivity::class.java)
        intent.putExtra(DetailUserActivity.KEY, adapter.getData(position))
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String?): Boolean = false

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null) {
            adapter.filter(newText)
        }
        return false
    }
}