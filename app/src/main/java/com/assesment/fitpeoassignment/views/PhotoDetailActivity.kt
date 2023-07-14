package com.assesment.fitpeoassignment.views

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.assesment.fitpeoassignment.R
import com.assesment.fitpeoassignment.databinding.ActivityPhotoDetailActivityBinding
import com.assesment.fitpeoassignment.model.PhotoDetail
import com.assesment.fitpeoassignment.utils.ValidationUtil
import com.squareup.picasso.Picasso

class PhotoDetailActivity : ComponentActivity() {

    private lateinit var binding: ActivityPhotoDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPhotoDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var photo: PhotoDetail? = null

        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.apply {
            strokeWidth = 5f
            centerRadius = 30f
            setColorSchemeColors(
                getColor(R.color.teal_200)
            )
            start()
        }
        if (intent.hasExtra("photo_detail")) {
            photo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("photo_detail", PhotoDetail::class.java)
            } else {
                intent.getParcelableExtra("photo_detail")
            }
            if (photo != null) {
                if (ValidationUtil.isUrlValid(photo)) {
                    Picasso.get().load(photo.url).placeholder(circularProgressDrawable)
                        .error(R.drawable.oops)
                        .into(binding.img)
                }
                binding.title.text = photo.title
                binding.desc.text = "${photo.title}\n${photo.title}\n${photo.title}"
            }


        }

    }

}