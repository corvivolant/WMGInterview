package ca.corvivolant.wmginterview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ca.corvivolant.wmginterview.databinding.FragmentFirstBinding
import ca.corvivolant.wmginterview.models.ToDo

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
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        setupView()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setupView() {
        // Setup To-Do recycler
        binding.recyclerTodo.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CheckboxAdapter(arrayListOf(ToDo(false, "apple"), ToDo(false, "orange"), ToDo(false, "banana")))
        }
        // Setup Completed recycler

    }
}