package ca.corvivolant.wmginterview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.corvivolant.wmginterview.databinding.ItemTodoBinding
import ca.corvivolant.wmginterview.models.ToDo

class CheckboxAdapter(val data: ArrayList<ToDo>): RecyclerView.Adapter<CheckboxAdapter.CheckboxViewHolder>() {

    class CheckboxViewHolder(val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ArrayList<ToDo>) {
            binding.apply {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckboxViewHolder {
        return CheckboxViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CheckboxViewHolder, position: Int) {
        val listItem = data[position]

        listItem as ToDo
        holder.binding.checkboxTodo.apply {
            isChecked = listItem.status
            text = listItem.data
        }
    }
}