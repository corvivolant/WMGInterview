package ca.corvivolant.wmginterview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioGroup.OnCheckedChangeListener
import androidx.recyclerview.widget.RecyclerView
import ca.corvivolant.wmginterview.databinding.ItemCompletedBinding
import ca.corvivolant.wmginterview.databinding.ItemTodoBinding
import ca.corvivolant.wmginterview.models.ToDo

/**
 * Note: By setting two types of list items here we forgo the need to generate a separate list adapter
 */
class CheckboxAdapter(var data: ArrayList<ToDo>, val listType: ListItemType = ListItemType.TODO): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    interface CheckboxListener {
        fun onChecked(position: Int)
    }
    var checkboxListener : CheckboxListener? = null
    class CheckboxViewHolder(val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ArrayList<ToDo>) {
            binding.apply {
            }
        }
    }
    class CompleteViewHolder(val binding: ItemCompletedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ArrayList<ToDo>) {
            binding.apply {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ListItemType.TODO.ordinal ->
                CheckboxViewHolder(
                    ItemTodoBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            else ->
                CompleteViewHolder(
                    ItemCompletedBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
        }

    }
    override fun getItemViewType(position: Int): Int {
        return if(data[position].status) ListItemType.COMPLETE.ordinal else ListItemType.TODO.ordinal
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listItem = data[position]

        when (listType) {
            ListItemType.TODO -> {
                if (!listItem.status) {
                    holder as CheckboxViewHolder
                    holder.binding.checkboxTodo.apply {
                        isChecked = listItem.status
                        text = listItem.data
                    }
                    holder.binding.checkboxTodo.setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked) checkboxListener?.onChecked(position)
                    }
                }
            }
            ListItemType.COMPLETE -> {
                if (listItem.status) {
                    holder as CompleteViewHolder
                    holder.binding.txtCompleted.apply {
                        text = listItem.data
                    }
                }
            }
        }

    }



    fun updateData(list: ArrayList<ToDo>) {
        data = list
        // NOTE: this is not the most efficient way to manage this list, for large sets to improve
        // performance you should update a single value at a time and notify the recycler adapter
        // which item was updated
        notifyDataSetChanged()
    }

    enum class ListItemType {
        TODO,
        COMPLETE
    }
}