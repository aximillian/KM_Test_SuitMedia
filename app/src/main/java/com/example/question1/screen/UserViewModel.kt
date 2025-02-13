package com.example.question1.screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.question1.data.model.User
import com.example.question1.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private var currentPage = 1
    private val allUsers = mutableListOf<User>()

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getUsers(currentPage)
                if (response.data.isNotEmpty()) {
                    allUsers.addAll(response.data)
                    _users.value = allUsers
                    currentPage++
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching users: ${e.message}")
            }
        }
    }

    fun refreshUsers() {
        currentPage = 1
        allUsers.clear()
        _users.value = emptyList()
        fetchUsers()
    }
}
