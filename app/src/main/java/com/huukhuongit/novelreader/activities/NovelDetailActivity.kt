package com.huukhuongit.novelreader.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.huukhuongit.novelreader.R
import com.huukhuongit.novelreader.adapters.AdapterPortaitNovel
import com.huukhuongit.novelreader.adapters.OnItemClickListener
import com.huukhuongit.novelreader.databinding.ActivityNovelDetailBinding
import com.huukhuongit.novelreader.models.CategoryModel
import com.huukhuongit.novelreader.models.NovelModel
import com.huukhuongit.novelreader.network.APIService
import com.huukhuongit.novelreader.utils.Constants
import com.huukhuongit.novelreader.utils.Helpers
import com.huukhuongit.novelreader.utils.NovelReaderSQLiteHelpers
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NovelDetailActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var binding: ActivityNovelDetailBinding

    private val listener = this

    private val retrofit = Constants.retrofit.create(APIService::class.java)

    private lateinit var listRecommended: ArrayList<NovelModel>
    private lateinit var adapterRecommended: AdapterPortaitNovel
    private lateinit var novelSelected: NovelModel
    private lateinit var categoryOfNovel: CategoryModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNovelDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addControls()
        addEvents()
    }

    private fun addControls() {
        val novelId = intent.getIntExtra("novelId", 1)

        // get recommended novels
        retrofit.getNovelsRecommended(novelId)
            .enqueue(object : Callback<ArrayList<NovelModel>> {
                override
                fun onResponse(
                    call: Call<ArrayList<NovelModel>>,
                    response: Response<ArrayList<NovelModel>>
                ) {
                    val body = response.body()
                    if (body != null) {
                        adapterRecommended =
                            AdapterPortaitNovel(R.layout.item_novel_portait, body, listener)
                        binding.rcvRecommended.adapter = adapterRecommended
                    }
                }

                override
                fun onFailure(call: Call<ArrayList<NovelModel>>, t: Throwable) {
                    Log.e("activity", Log.getStackTraceString(t.cause))
                }
            })

        retrofit.getByNovelId(novelId)
            .enqueue(object : Callback<NovelModel> {
                override
                fun onResponse(
                    call: Call<NovelModel>,
                    response: Response<NovelModel>
                ) {
                    val body = response.body()
                    if (body != null) {
                        novelSelected = body
                        Picasso.get().load(body.thumbnail).into(binding.imgNovelBlur)
                        Picasso.get().load(body.thumbnail).into(binding.imgNovelThumb)
                        binding.txtNovelName.text = body.name
                        binding.txtNovelAuthor.text = body.author
                        binding.txtNumViews.text = body.reads?.let { Helpers.formatNumber(it) }
                        binding.txtNumChapters.text = "${body.chapters}"
                        binding.txtNovelDescription.text = body.description
                    }
                }

                override
                fun onFailure(call: Call<NovelModel>, t: Throwable) {
                    Log.e("activity", Log.getStackTraceString(t.cause))
                }
            })

        listRecommended = ArrayList()

        adapterRecommended = AdapterPortaitNovel(R.layout.item_novel_portait, listRecommended, this)
        binding.rcvRecommended.adapter = adapterRecommended
    }

    private fun addEvents() {
        binding.btnReadBook.setOnClickListener {
            goToReadBookActivity()
        }

        binding.btnDownload.setOnClickListener {
            processDownloadBook()
        }

        binding.btnComment.setOnClickListener {
            Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun processDownloadBook() {
        Log.e("ITEM", "==========================================")


        val sqlHelpers = NovelReaderSQLiteHelpers(this)
//        sqlHelpers.add(novelSelected)

//        val list = sqlHelpers.getListNovels()
//        for (item in list) {
//            Log.e("ITEM", item.name!!)
//        }

        val chaps = sqlHelpers.getListChaptersByNovelId(novelSelected.id!!)
        for (item in chaps) {
            Log.e("CHAP", item.title!!)
        }

        Log.e("ITEM", "==========================================")

    }

    private fun goToReadBookActivity() {
        if (novelSelected.listChapters == null) {
            Toast.makeText(this, "No chapters", Toast.LENGTH_SHORT).show()
            return
        }
        if (novelSelected.listChapters?.size!! > 0) {
            val intent = Intent(this, ReadChapterActivity::class.java)
            intent.putExtra("listChapters", novelSelected.listChapters)
            startActivity(intent)
        } else {
            Toast.makeText(this, "No chapters", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(position: Int, item: Any) {
        val itemNovel: NovelModel = item as NovelModel
        val intent: Intent = Intent(this, NovelDetailActivity::class.java)
        intent.putExtra("novelId", itemNovel.id)
        this.startActivity(intent)
    }

}