package ca.corvivolant.wmginterview

import android.animation.LayoutTransition
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ca.corvivolant.wmginterview.databinding.FragmentFirstBinding
import ca.corvivolant.wmginterview.models.ToDo
import java.util.ArrayList

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    val mainViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        setupView()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.todoList.observe(viewLifecycleOwner) { todos ->
            (binding.recyclerTodo.adapter as CheckboxAdapter).updateData(todos?.toCollection(arrayListOf()) ?: arrayListOf())
        }
        mainViewModel.completeList.observe(viewLifecycleOwner) { completes ->
            (binding.recyclerCompleted.adapter as CheckboxAdapter).updateData(completes?.toCollection(arrayListOf()) ?: arrayListOf())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setupView() {
        // Setup LayoutTransition to accept automatic default transition animations
        binding.layout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        // Setup To-Do recycler
        binding.recyclerTodo.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CheckboxAdapter(arrayListOf(), CheckboxAdapter.ListItemType.TODO).apply {
                checkboxListener = object : CheckboxAdapter.CheckboxListener {
                    override fun onChecked(position: Int) {
                        Log.d("onChecked", position.toString())
                        mainViewModel.markComplete(position)
                    }
                }
            }
        }
        // Setup Completed recycler
        binding.recyclerCompleted.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CheckboxAdapter(arrayListOf(), CheckboxAdapter.ListItemType.COMPLETE)
        }

    }
}