package com.unknownsystem.movienot.listeners

import com.unknownsystem.movienot.models.Result

interface BookMarkStatusesListener {
    fun onItemChanged(result: Result?)
    fun onAllCleared(size: Int)
}