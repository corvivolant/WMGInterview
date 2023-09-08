package ca.corvivolant.wmginterview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.corvivolant.wmginterview.models.ToDo

class MainViewModel : ViewModel() {
    val todoList = MutableLiveData<ArrayDeque<ToDo>>()
    val completeList = MutableLiveData<ArrayDeque<ToDo>>()

    init {
        todoList.value = ArrayDeque<ToDo>().apply {
            addFirst(ToDo("apple"))
            addFirst(ToDo("banana"))
            addFirst(ToDo("pear"))
        }
        completeList.value = ArrayDeque()
    }
    fun addToDo(string: String) {
        todoList.value?.addFirst(ToDo(string))
        // notify observer
        todoList.value = todoList.value
    }

    fun markComplete(position: Int) {
        todoList.value?.removeAt(position)?.let {
            it.status = true
            completeList.value?.addFirst(it)
        }

        // notify observer
        todoList.value = todoList.value
        completeList.value = completeList.value
    }
}