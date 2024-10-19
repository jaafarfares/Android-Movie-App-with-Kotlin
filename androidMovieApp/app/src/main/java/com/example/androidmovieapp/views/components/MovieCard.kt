    package com.example.androidmovieapp.views.components

    import android.content.Context
    import android.util.AttributeSet
    import android.view.LayoutInflater
    import android.widget.FrameLayout
    import android.widget.TextView
    import com.bumptech.glide.Glide
    import com.example.androidmovieapp.R
    import com.example.androidmovieapp.databinding.MovieCardBinding

    class MovieCard @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) : FrameLayout(context, attrs, defStyleAttr) {

        private val binding = MovieCardBinding.inflate(LayoutInflater.from(context), this, true)

        fun setTitle(title: String) {
            binding.movieTitle.text = title
        }

        fun setPoster(imageUrl: String) {
            val baseUrl = context.getString(R.string.image_base_url)

            val fullImageUrl = "$baseUrl$imageUrl"
            Glide.with(this)
                .load(fullImageUrl)
                .into(binding.moviePoster)
        }

        fun setReleaseDate(date: String) {
            binding.movieReleaseDate.text = date
        }
        fun setVotingAverage(voteAverage: Double) {
            binding.ratingText.text = "Rating: ${voteAverage.toString()}"
        }

        fun showAdultRating(isAdult: Boolean) {
            if (isAdult) {
                binding.adultRating.visibility = VISIBLE
            } else {
                binding.adultRating.visibility = GONE
            }
        }
    }
