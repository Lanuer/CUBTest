package com.example.cubtest.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cubtest.R
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModelProvider
import com.example.cubtest.data.model.Attraction
import com.example.cubtest.ui.viewmodel.AttractionViewModel

class DetailFragment : Fragment() {
    private lateinit var viewModel: AttractionViewModel
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[AttractionViewModel::class.java]

        val imageViewDetail: ImageView = view.findViewById(R.id.imageview_detail)
        val textViewDetailName: TextView = view.findViewById(R.id.textview_detail_name)
        val textViewDetailIntro: TextView = view.findViewById(R.id.textview_detail_intro)
        val textViewDetailAddress: TextView = view.findViewById(R.id.textview_detail_address)
        val textViewDetailModified: TextView = view.findViewById(R.id.textview_detail_modified)
        val textViewDetailOfficialSite: TextView = view.findViewById(R.id.textview_detail_official_site)

        val actionBar = (activity as? AppCompatActivity)?.supportActionBar
        actionBar?.setDisplayShowCustomEnabled(false)
        actionBar?.setDisplayShowHomeEnabled(true)

        val attraction = args.attraction

        (activity as? AppCompatActivity)?.supportActionBar?.title = attraction.name
        textViewDetailName.text = attraction.name
        textViewDetailIntro.text = attraction.introduction
        textViewDetailOfficialSite.text = attraction.officialSite ?: ""

        viewModel.selectedLanguage.observe(viewLifecycleOwner) { language ->
            val addressLabel = when (language) {
                "zh-tw" -> getString(R.string.address_label_zh_tw)
                "zh-cn" -> getString(R.string.address_label_zh_cn)
                "en" -> getString(R.string.address_label_en)
                "ja" -> getString(R.string.address_label_ja)
                "ko" -> getString(R.string.address_label_ko)
                "es" -> getString(R.string.address_label_es)
                "id" -> getString(R.string.address_label_id)
                "th" -> getString(R.string.address_label_th)
                "vi" -> getString(R.string.address_label_vi)
                else -> getString(R.string.address_label_zh_tw)
            }

            val modifiedLabel = when (language) {
                "zh-tw" -> getString(R.string.modified_label_zh_tw)
                "zh-cn" -> getString(R.string.modified_label_zh_cn)
                "en" -> getString(R.string.modified_label_en)
                "ja" -> getString(R.string.modified_label_ja)
                "ko" -> getString(R.string.modified_label_ko)
                "es" -> getString(R.string.modified_label_es)
                "id" -> getString(R.string.modified_label_id)
                "th" -> getString(R.string.modified_label_th)
                "vi" -> getString(R.string.modified_label_vi)
                else -> getString(R.string.modified_label_zh_tw)
            }

            textViewDetailAddress.text = "$addressLabel\n${attraction.address}"
            textViewDetailModified.text = "$modifiedLabel\n${attraction.modified}"
        }

        val spannable = SpannableString(textViewDetailOfficialSite.text)
        spannable.setSpan(UnderlineSpan(), 0, textViewDetailOfficialSite.text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(ForegroundColorSpan("#2396F3".toColorInt()), 0, textViewDetailOfficialSite.text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textViewDetailOfficialSite.text = spannable

        if (attraction.images.isNotEmpty()) {
            Glide.with(this)
                .load(attraction.images[0].src)
                .error(R.drawable.broken_image)
                .into(imageViewDetail)
        } else {
            imageViewDetail.setImageResource(R.drawable.broken_image)
            imageViewDetail.scaleType = ImageView.ScaleType.FIT_CENTER
        }

        textViewDetailOfficialSite.setOnClickListener {
            attraction.officialSite?.let { url: String ->
                val action = DetailFragmentDirections.actionFragmentDetailToFragmentWeb(url)
                findNavController().navigate(action)
            }
        }
    }
}