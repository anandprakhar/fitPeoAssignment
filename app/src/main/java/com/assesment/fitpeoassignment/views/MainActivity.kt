package com.assesment.fitpeoassignment.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assesment.fitpeoassignment.MainApplication
import com.assesment.fitpeoassignment.R
import com.assesment.fitpeoassignment.adapters.PhotoListAdapter
import com.assesment.fitpeoassignment.model.PhotoDetail
import com.assesment.fitpeoassignment.utils.NetworkResult
import com.assesment.fitpeoassignment.utils.NetworkState
import com.assesment.fitpeoassignment.viewmodels.MainViewModel
import com.assesment.fitpeoassignment.viewmodels.ViewModelFactory


class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel

    //    private lateinit var binding: MainActivityBinding
    private lateinit var adapter: PhotoListAdapter
    var photoList = ArrayList<PhotoDetail>()

    private val recyclerView: RecyclerView
        get() = findViewById(R.id.recyclerView)
    private val composeView: ComposeView
        get() = findViewById(R.id.composeView)
    private val showProgress: ProgressBar
        get() = findViewById(R.id.show_progress)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(R.layout.main_activity)
        setupViewModel()
        setupUI()
        setUpObserver()
    }

    private fun setupViewModel() {
        val mainRepository = (application as MainApplication).mainRepository
        viewModel = ViewModelProvider(this, ViewModelFactory(mainRepository))
            .get(MainViewModel::class.java)
    }

    private fun setupUI() {

        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter = PhotoListAdapter(photoList, object : PhotoListAdapter.ClicListener {
            override fun onClick(photo: PhotoDetail) {
                val intent = Intent(this@MainActivity, PhotoDetailActivity::class.java)
                intent.putExtra("photo_detail", photo)
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter
        composeView.visibility = View.GONE

    }

    private fun setUpObserver() {
        viewModel.photoList.observe(this) {
            when (it) {
                is NetworkResult.Success -> {
                    showProgress.visibility = View.GONE
                    composeView.visibility = View.GONE
                    photoList.addAll(it.data!!)
                    adapter.notifyDataSetChanged()
                }

                is NetworkResult.Error -> {
                    showProgress.visibility = View.GONE
                    composeView.visibility = View.VISIBLE
                    composeView.setContent {
                        ShowErrorToast(
                            "Something went wrong. Please try again!",
                            viewModel, R.drawable.oops
                        )
                    }
                }

                is NetworkResult.Loading -> {
                    showProgress.visibility = View.VISIBLE
                }
            }
        }
        viewModel.loading.observe(this) {
            if (it) {
                showProgress.visibility = View.VISIBLE
            } else {
                showProgress.visibility = View.GONE
            }
        }
        viewModel.returnNetworkState.observe(this, Observer {
            if (it.equals(NetworkState.ERROR)) {
                composeView.visibility = View.VISIBLE
                composeView.setContent {
                    ShowErrorToast(
                        "Please check your network connection\nand try again!",
                        viewModel, R.drawable.no_network_img
                    )
                }
            } else {
                composeView.visibility = View.GONE
            }

        })
        viewModel.getPhotosList()
    }
}

@Composable
fun ShowErrorToast(message: String, viewModel: MainViewModel, drawable: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = message,
            Modifier.size(200.dp)
        )
        Text(text = message, fontSize = 20.sp, textAlign = TextAlign.Center)
        Button(
            onClick = { viewModel.getPhotosList() },
            Modifier.padding(10.dp),
        ) {
            Text(text = "Retry")
        }
    }
}
