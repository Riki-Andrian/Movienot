package com.unknownsystem.movienot.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unknownsystem.movienot.db.DatabaseService
import com.unknownsystem.movienot.models.SearchResult
import com.unknownsystem.movienot.network.ApiService
import com.unknownsystem.movienot.utils.Helper
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val apiService: ApiService,
    private val helper: Helper,
    private val databaseService: DatabaseService
) : ViewModel() {

    val searchResultMutableLiveData=MutableLiveData<SearchResult>()
    val msg=MutableLiveData<String>()
    val loading=MutableLiveData<Boolean>()
    init {
        loading.value=false
    }
    fun getSearchResults(p0: String) {
        if(helper.getConnection()) {
            viewModelScope.launch {
                loading.postValue(true)
                try {
                    val res=apiService.getSearchResults(p0,true)
                    searchResultMutableLiveData.postValue(res)
                } catch(exception:Exception){
                    searchResultMutableLiveData.postValue(SearchResult())
                    msg.postValue("Something went wrong")
                }
                finally {
                    loading.postValue(false)
                }
            }
        }
        else{
            msg.postValue("No internet connection")
        }

    }



}