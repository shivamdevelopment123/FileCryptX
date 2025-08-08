package coder.sentient.filecryptx

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coder.sentient.filecryptx.databinding.ActivityAppSettingsBinding
import coder.sentient.filecryptx.fragments.PasswordAuthentication
import coder.sentient.filecryptx.utility.AboutUs
import coder.sentient.filecryptx.utility.PreferencesHelper
import coder.sentient.filecryptx.utility.PrivacyPolicy

class AppSettings : AppCompatActivity() {
    private lateinit var binding: ActivityAppSettingsBinding
    private val themesMode = arrayOf("Light Mode", "Dark Mode", "System Default")
    private lateinit var sharedPreferences: PreferencesHelper


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAppSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = PreferencesHelper(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                binding.fragmentContainerView1.visibility = View.GONE
            }
        }

        val spinner = binding.settingsThemeSpinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, themesMode)
        spinner.adapter = adapter

        val savedTheme = sharedPreferences.getThemePreferences()
        val selectedIndex = when (savedTheme) {
            AppCompatDelegate.MODE_NIGHT_NO -> 0
            AppCompatDelegate.MODE_NIGHT_YES -> 1
            else -> 2
        }
        spinner.setSelection(selectedIndex)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedMode = when (position) {
                    0 -> AppCompatDelegate.MODE_NIGHT_NO
                    1 -> AppCompatDelegate.MODE_NIGHT_YES
                    else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                }

                sharedPreferences.saveThemePreferences(selectedMode)
                AppCompatDelegate.setDefaultNightMode(selectedMode)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val switchHiddenFiles = binding.settingsHiddenButtonSwitch

        switchHiddenFiles.isChecked = sharedPreferences.isFilesHidden()

        switchHiddenFiles.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.hiddenFiles(isChecked)
        }

        binding.vaultPasswordChangeButton.setOnClickListener {
            binding.fragmentContainerView1.visibility = View.VISIBLE
            val fragment = PasswordAuthentication.newInstance("change")
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView1, fragment)
                .addToBackStack(null)
                .commit()
        }

        binding.privacyPolicyButton.setOnClickListener {
            PrivacyPolicy(this).showPolicyDialog()
        }

        binding.aboutButton.setOnClickListener {
            AboutUs(this).showAboutUsDialog()
        }


    }

    override fun onResume() {
        binding.fragmentContainerView1.visibility = View.GONE
        super.onResume()
    }
}