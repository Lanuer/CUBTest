package com.example.cubtest.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cubtest.R
import com.example.cubtest.ui.adapter.AttractionAdapter
import com.example.cubtest.ui.viewmodel.AttractionViewModel
import android.widget.PopupMenu
import androidx.appcompat.view.ContextThemeWrapper

class ListFragment : Fragment() {
    private lateinit var viewModel: AttractionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    @SuppressLint("DiscouragedPrivateApi", "InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[AttractionViewModel::class.java]

        val actionBar = (activity as? AppCompatActivity)?.supportActionBar
        val customView = layoutInflater.inflate(R.layout.action_bar_layout, null)
        actionBar?.setCustomView(customView)
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setDisplayUseLogoEnabled(false)

        val textViewTitle: TextView = customView.findViewById(R.id.textview_title)
        viewModel.selectedLanguage.observe(viewLifecycleOwner) { language ->
            textViewTitle.text = when (language) {
                "zh-tw" -> getString(R.string.title_zh_tw)
                "zh-cn" -> getString(R.string.title_zh_cn)
                "en" -> getString(R.string.title_en)
                "ja" -> getString(R.string.title_ja)
                "ko" -> getString(R.string.title_ko)
                "es" -> getString(R.string.title_es)
                "id" -> getString(R.string.title_id)
                "th" -> getString(R.string.title_th)
                "vi" -> getString(R.string.title_vi)
                else -> getString(R.string.title_zh_tw)
            }
        }

        val imageViewLanguage: ImageView = customView.findViewById(R.id.imageview_language)
        imageViewLanguage.setOnClickListener {
            val popupMenu = PopupMenu(ContextThemeWrapper(requireContext(), R.style.Widget_PopupMenu_Custom), imageViewLanguage)

            popupMenu.menuInflater.inflate(R.menu.language_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item ->
                val selectedLanguage = item.title.toString()
                viewModel.setLanguage(selectedLanguage)
                viewModel.fetchAttractionsByLanguage(selectedLanguage)
                true
            }

            imageViewLanguage.post {
                popupMenu.show()
            }
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        if (viewModel.attractions.value == null) {
            viewModel.fetchAttractionsByLanguage("zh-tw")
        }

        viewModel.attractions.observe(viewLifecycleOwner) { attractions ->
            recyclerView.adapter = AttractionAdapter(attractions) { attraction ->
                val action = ListFragmentDirections.actionFragmentListToFragmentDetail(attraction)
                findNavController().navigate(action)
            }
        }
    }
}
