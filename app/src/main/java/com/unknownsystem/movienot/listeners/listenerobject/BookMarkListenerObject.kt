package com.unknownsystem.movienot.listeners.listenerobject

import com.unknownsystem.movienot.listeners.BookMarkStatusesListener
import java.io.Serializable

class BookMarkListenerObject(listener: BookMarkStatusesListener) : Serializable {
    var listener: BookMarkStatusesListener = listener

}