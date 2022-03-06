package com.myhome.android.findcountryapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.myhome.android.findcountryapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var ui: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        ui.buttonFindCountry.setOnClickListener {
            val countryName = ui.textViewSearchCountry.text.toString()

            lifecycleScope.launch {
                try {
                    val countries = restCountriesApi.getCountryByName(countryName)
                    val country = countries[0]

                    ui.textViewCountryName.text = country.name
                    ui.textViewCapital.text = country.capital
                    ui.textViewArea.text = separateNumber(country.area)
                    ui.textViewPopulation.text = separateNumber(country.population)
                    ui.textViewLanguages.text = languagesToString(country.languages)

                    loadSvg(ui.imageViewCountryFlag, country.flag)

                    ui.secondConstraint.visibility = View.VISIBLE
                    ui.searchConstraint.visibility = View.INVISIBLE
                } catch (e: Exception) {
                    ui.imageViewSearchImage.setImageResource(R.drawable.ic_baseline_error_24)
                    ui.textViewMessageInput.text = "Такой страны нет, введите страну повторно."
                    ui.secondConstraint.visibility = View.INVISIBLE
                    ui.searchConstraint.visibility = View.VISIBLE
                }
            }
        }
    }
}