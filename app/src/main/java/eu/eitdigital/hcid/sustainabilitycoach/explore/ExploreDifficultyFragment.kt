package eu.eitdigital.hcid.sustainabilitycoach.explore

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import eu.eitdigital.hcid.sustainabilitycoach.R
import eu.eitdigital.hcid.sustainabilitycoach.model.Difficulty
import kotlinx.android.synthetic.main.fragment_explore_difficulty.*
import kotlinx.android.synthetic.main.fragment_explore_difficulty.next_button


private const val ARG_DIFFICULTY = "difficulty"

class ExploreDifficultyFragment : Fragment() {
    private var difficulty: String = Difficulty.MEDIUM.difficulty
    private var listener: ExploreFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val difArgument = it.getString(ARG_DIFFICULTY)

            if (difArgument != null && difArgument != "") {
                difficulty = difArgument
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore_difficulty, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        next_button.setOnClickListener {
            if (difficulty != Difficulty.EASY.difficulty) {
                Snackbar.make(difficulty_button_group, "This selection is currently not supported!", Snackbar.LENGTH_LONG)
                    .show()

                return@setOnClickListener
            }

            listener?.onFragmentInteraction(STEP)
        }

        difficulty_button_group.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.button_easy -> difficulty = Difficulty.EASY.difficulty
                    R.id.button_medium -> difficulty = Difficulty.MEDIUM.difficulty
                    R.id.button_difficult -> difficulty = Difficulty.DIFFICULT.difficulty
                }

                listener?.setDifficultySelection(difficulty)
            }
        }

        when (difficulty) {
            Difficulty.EASY.difficulty -> difficulty_button_group.check(R.id.button_easy)
            Difficulty.MEDIUM.difficulty -> difficulty_button_group.check(R.id.button_medium)
            Difficulty.DIFFICULT.difficulty -> difficulty_button_group.check(R.id.button_difficult)
        }

        skip_button.setOnClickListener { listener?.showUnsupportedActionMessage() }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ExploreFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement ExploreFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        val STEP = "Difficulty"

        @JvmStatic
        fun newInstance(difficulty: String?) =
            ExploreDifficultyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_DIFFICULTY, difficulty)
                }
            }
    }
}
