package com.assesment.fitpeoassignment.views

import android.content.Intent
import android.os.Bundle
import android.view.View
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.assesment.fitpeoassignment.R
import com.assesment.fitpeoassignment.RetrofitService
import com.assesment.fitpeoassignment.adapters.PhotoListAdapter
import com.assesment.fitpeoassignment.databinding.MainActivityBinding
import com.assesment.fitpeoassignment.model.PhotoDetail
import com.assesment.fitpeoassignment.networkCheck.OnlineChecker
import com.assesment.fitpeoassignment.repository.MainRepository
import com.assesment.fitpeoassignment.utils.NetworkState
import com.assesment.fitpeoassignment.viewmodels.MainViewModel
import com.assesment.fitpeoassignment.viewmodels.ViewModelFactory
import javax.inject.Inject


class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainActivityBinding
    private lateinit var adapter: PhotoListAdapter
    var photoList = ArrayList<PhotoDetail>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupUI()
        setUpObserver()
    }

    private fun setupViewModel() {
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        viewModel = ViewModelProvider(this, ViewModelFactory(mainRepository))
            .get(MainViewModel::class.java)
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter = PhotoListAdapter(photoList, object : PhotoListAdapter.ClicListener {

            override fun onClick(photo: PhotoDetail) {
                val intent = Intent(this@MainActivity, PhotoDetailActivity::class.java)
                intent.putExtra("photo_detail", photo)
                startActivity(intent)
            }

        })
        binding.recyclerView.adapter = adapter
        binding.composeView.visibility = View.GONE

    }

    private fun setUpObserver() {
        viewModel.photoList.observe(this) {
            binding.composeView.visibility = View.GONE
            photoList.addAll(it)
            adapter.notifyDataSetChanged()
        }
        viewModel.errorMessage.observe(this) {
            binding.composeView.visibility = View.VISIBLE
            binding.composeView.setContent {
                ShowErrorToast(
                    "Something went wrong. Please try again!",
                    viewModel, R.drawable.oops
                )
            }
        }
        viewModel.loading.observe(this) {
            if (it) {
                binding.showProgress.visibility = View.VISIBLE
            } else {
                binding.showProgress.visibility = View.GONE
            }
        }
        viewModel.returnNetworkState.observe(this, Observer {
            if (it.equals(NetworkState.ERROR)) {
                binding.composeView.visibility = View.VISIBLE
                binding.composeView.setContent {
                    ShowErrorToast(
                        "Please check your network connection\nand try again!",
                        viewModel, R.drawable.no_network_img
                    )
                }
            } else {
                binding.composeView.visibility = View.GONE
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
