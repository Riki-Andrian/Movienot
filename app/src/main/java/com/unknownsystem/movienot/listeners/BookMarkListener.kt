package com.unknownsystem.movienot.listeners


import com.unknownsystem.movienot.models.Result

interface BookMarkListener {
    fun onBookMarkClicked(position: Int, result: Result?)
}