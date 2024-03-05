package com.genthur.healthify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.genthur.healthify.DiseaseDetailActivity.Companion.EXTRA_DISEASE

class MainActivity : AppCompatActivity() {
    private lateinit var rvDiseases: RecyclerView
    private var list = ArrayList<Disease>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rvDiseases = findViewById(R.id.rv_disease)
        rvDiseases.setHasFixedSize(true)

        list.addAll(getListDiseases())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListDiseases():ArrayList<Disease>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataIndication = resources.getStringArray(R.array.data_indication)
        val dataTreatment = resources.getStringArray(R.array.data_treatment)
        val listDisease = ArrayList<Disease>()
        for (i in dataName.indices){
            val disease = Disease(dataName[i], dataDescription[i], dataPhoto[i], dataIndication[i], dataTreatment[i])
            listDisease.add(disease)
        }
        return listDisease
    }

    private fun showRecyclerList(){
        rvDiseases.layoutManager = LinearLayoutManager(this)
        val listDiseaseAdapter = ListDiseaseAdapter(list)
        rvDiseases.adapter = listDiseaseAdapter

        listDiseaseAdapter.setOnItemClickCallback(object : ListDiseaseAdapter.OnItemClickCallBack{
            override fun onItemClicked(data: Disease) {
                showSelectedDisease(data)
            }
        })
    }

    private fun showSelectedDisease(disease: Disease){
        val intentDetail = Intent(this@MainActivity, DiseaseDetailActivity::class.java)
        intentDetail.putExtra(EXTRA_DISEASE, disease)
        startActivity(intentDetail)
    }
}