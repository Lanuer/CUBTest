package com.example.cubtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cubtest.data.api.RetrofitClient
import com.example.cubtest.data.model.Attraction
import kotlinx.coroutines.launch

class AttractionViewModel : ViewModel() {
    private val _selectedLanguage = MutableLiveData("zh-tw")
    val selectedLanguage: LiveData<String> = _selectedLanguage

    private val _attractions = MutableLiveData<List<Attraction>>()
    val attractions: LiveData<List<Attraction>> get() = _attractions

    fun setLanguage(language: String) {
        _selectedLanguage.value = language
    }

    fun fetchAttractionsByLanguage(language: String) {
        _selectedLanguage.value = language

        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getAttractions(language)
                if (response.isSuccessful) {
                    _attractions.value = response.body()?.attractions
                }
            } catch (e: Exception) {
            }
        }
    }
}