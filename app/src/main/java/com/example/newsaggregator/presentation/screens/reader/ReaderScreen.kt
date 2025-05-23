package com.example.newsaggregator.presentation.screens.reader

import android.annotation.SuppressLint
import android.content.Intent
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.newsaggregator.R
import com.example.newsaggregator.presentation.ui.theme.AppTheme


@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderScreen(
    navController: NavHostController,
    topBarColors: TopAppBarColors,
    modifier: Modifier = Modifier,
    urlArticle: String? = null,
    viewModel: ReaderViewModel = hiltViewModel<ReaderViewModel>()
) {
    LaunchedEffect(urlArticle) {
        viewModel.handleEvent(ReaderEvent.UpdateUrl(urlArticle))
    }
    val uiState by viewModel.uiState.collectAsState()
    var webView: WebView? by remember { mutableStateOf(null) }
    val context = LocalContext.current
    val descriptionShare = stringResource(id = R.string.text_top_bar_share)

    BackHandler {
        webView?.let { webView ->
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                navController.popBackStack()
            }
        } ?: navController.popBackStack()
    }
    DisposableEffect(webView) {
        onDispose {
            webView?.stopLoading()
        }
    }

    Scaffold(
        modifier = modifier
            .background(AppTheme.colorsScheme.background)
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        webView?.stopLoading()
                        navController.popBackStack()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = stringResource(id = R.string.text_top_bar_back)
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.text_top_bar_back),
                    )
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.handleEvent(ReaderEvent.UpdateBookmark)
                    }) {
                        Icon(
                            painter = painterResource(
                                id = if (uiState.isSaved) {
                                    R.drawable.ic_bookmark_filled
                                } else {
                                    R.drawable.ic_bookmark
                                }
                            ),
                            contentDescription = stringResource(id = R.string.text_top_bar_back),
                            modifier = Modifier.size(AppTheme.size.large)
                        )
                    }
                    IconButton(onClick = {
                        uiState.url?.let { link ->
                            val shareIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, link)
                                type = "text/plain"
                            }
                            context.startActivity(
                                Intent.createChooser(shareIntent, descriptionShare)
                            )
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = descriptionShare,
                            modifier = Modifier.size(AppTheme.size.large)
                        )
                    }
                },
                colors = topBarColors
            )
        },
    ) { paddingValues ->
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            factory = { context ->
                WebView(context).apply {
                    webView = this
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()
                    uiState.url?.let {
                        loadUrl(it)
                    }
                }
            },
            update = { webView ->
                uiState.url?.let {
                    if (webView.url != it) {
                        webView.loadUrl(it)
                    }
                }
            }
        )
    }
}