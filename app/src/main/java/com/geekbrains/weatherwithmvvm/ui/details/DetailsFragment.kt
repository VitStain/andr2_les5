package com.geekbrains.weatherwithmvvm.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geekbrains.weatherwithmvvm.R
import com.geekbrains.weatherwithmvvm.databinding.FragmentDetailsBinding
import com.geekbrains.weatherwithmvvm.model.AppState
import com.geekbrains.weatherwithmvvm.model.entities.Weather
import com.geekbrains.weatherwithmvvm.ui.main.MainViewModel

@Suppress("NAME_SHADOWING")
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather = arguments?.getParcelable<Weather>(BUNDLE_EXTRA)
        weather?.let {
            with(binding) {
                cityName.text = it.city.city
                cityCoordinates.text = String.format(
                    getString(R.string.city_coordinates),
                    it.city.lat.toString(),
                    it.city.lon.toString()
                )
                viewModel.liveDataToObserve.observe(this@DetailsFragment, { appState ->
                    when (appState) {
                        is AppState.Error -> {
                            //...
                            loadingLayout.visibility = View.GONE
                        }
                        AppState.Loading -> binding.loadingLayout.visibility = View.VISIBLE
                        is AppState.Success -> {
                            loadingLayout.visibility = View.GONE
                            temperatureValue.text = appState.weatherData[0].temperature?.toString()
                            feelsLikeValue.text = appState.weatherData[0].feelsLike?.toString()
                            weatherCondition.text = appState.weatherData[0].condition
                        }
                    }
                })
                viewModel.loadData(it.city.lat, it.city.lon)
            }
        }
    }

    companion object {
        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}