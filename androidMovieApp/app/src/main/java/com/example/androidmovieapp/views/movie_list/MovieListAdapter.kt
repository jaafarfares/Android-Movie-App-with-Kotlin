    package com.example.androidmovieapp.views.movie_list

    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.recyclerview.widget.DiffUtil
    import androidx.recyclerview.widget.ListAdapter
    import androidx.recyclerview.widget.RecyclerView
    import com.example.androidmovieapp.data.model.Movie
    import com.example.androidmovieapp.views.components.MovieCard

    class MovieListAdapter(
        private val onMovieClick: (Movie) -> Unit
    ) : ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(MovieDiffCallback()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
            val card = MovieCard(parent.context)
            return MovieViewHolder(card)
        }

        override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            val movie = getItem(position)
            holder.bind(movie)
            holder.itemView.setOnClickListener {
                onMovieClick(movie)
            }
        }

        class MovieViewHolder(private val movieCard: MovieCard) : RecyclerView.ViewHolder(movieCard) {
            fun bind(movie: Movie) {
                movie.title?.let { movieCard.setTitle(it) }
                movie.poster_path?.let { movieCard.setPoster(it) }
                movie.release_date?.let { movieCard.setReleaseDate(it) }
                movie.vote_average?.let { movieCard.setVotingAverage(it) }
                movie.adult?.let { movieCard.showAdultRating(it) }
            }
        }


        class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
