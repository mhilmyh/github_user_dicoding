package com.hilmyhaidar.github_user.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hilmyhaidar.github_user.databinding.ActivityDetailUserBinding
import com.hilmyhaidar.github_user.models.PersonModel

class DetailUserActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val KEY = "DetailUserActivity:KEY"
    }

    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        val person = intent.getParcelableExtra<PersonModel>(KEY)
        if (person != null) bind(person)

        binding.buttonBack.setOnClickListener(this)
    }

    private fun bind(person: PersonModel) {
        binding.textViewName.text = person.name
        binding.textViewUsername.text = person.username
        binding.textViewCompany.text = person.company
        binding.textViewLocation.text = person.location
        binding.textViewRepositoryValue.text = person.repository.toString()
        binding.textViewFollowerValue.text = person.follower.toString()
        binding.textViewFollowingValue.text = person.following.toString()
        binding.imageViewUserAvatar.setImageResource(person.getResourceId(this))
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.buttonBack.id -> {
                finish()
            }
        }
    }
}