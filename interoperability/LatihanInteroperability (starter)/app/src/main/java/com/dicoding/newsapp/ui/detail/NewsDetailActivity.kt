package com.dicoding.newsapp.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat
import com.dicoding.newsapp.R
import com.dicoding.newsapp.data.local.entity.NewsEntity
import com.dicoding.newsapp.databinding.ActivityNewsDetailBinding
import com.dicoding.newsapp.ui.ViewModelFactory

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var newsDetail: NewsEntity
    private val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
    private val viewModel: NewsDetailViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsDetail = IntentCompat.getParcelableExtra(intent, NEWS_DATA, NewsEntity::class.java) as NewsEntity
    }

    companion object {
        const val NEWS_DATA = "data"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailContent(
    title: String,
    url: String,
    bookmarkStatus: Boolean,
    updateBookmarkStatus: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                actions = {
                    IconButton(onClick = updateBookmarkStatus) {
                        Icon(
                            painter = if (bookmarkStatus) {
                                painterResource(R.drawable.ic_bookmarked_white)
                            } else {
                                painterResource(R.drawable.ic_bookmark_white)
                            },
                            contentDescription = stringResource(R.string.save_bookmark),
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsDetailContentPreview() {
    MaterialTheme {
        NewsDetailContent("New News", "www.dicoding.com", false, {})
    }
}