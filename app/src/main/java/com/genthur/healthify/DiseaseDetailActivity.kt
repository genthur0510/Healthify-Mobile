package com.genthur.healthify

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DiseaseDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DISEASE = "extra_disease"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_detail)

        val dataDisease = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(EXTRA_DISEASE, Disease::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DISEASE)
        }

        val tvDetailName = findViewById<TextView>(R.id.disease_tittle)
        val tvDescriptionName = findViewById<TextView>(R.id.disease_description)
        val tvPhotoName = findViewById<ImageView>(R.id.img_disease)
        val tvIndicationName = findViewById<TextView>(R.id.disease_indication)
        val tvTreatmentName = findViewById<TextView>(R.id.disease_treatment)

        if (dataDisease != null) {
            Glide.with(this)
                .load(dataDisease.photo)
                .into(tvPhotoName)
            tvDetailName.text = dataDisease.name
            tvDescriptionName.text = dataDisease.description
            tvIndicationName.text = dataDisease.indication
            tvTreatmentName.text = dataDisease.treatment
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.about_page -> {
                val moveIntent = Intent(this@DiseaseDetailActivity, ProfileActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}