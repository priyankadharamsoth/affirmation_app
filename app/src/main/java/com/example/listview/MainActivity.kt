package com.example.listview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listview.data.Datasource
import com.example.listview.model.Affirmation
import com.example.listview.ui.theme.ListviewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListviewTheme {
                val layoutDirection = LocalLayoutDirection.current
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .padding(
                            start = WindowInsets.safeDrawing
                                .asPaddingValues()
                                .calculateStartPadding(layoutDirection),
                            end = WindowInsets.safeDrawing
                                .asPaddingValues()
                                .calculateEndPadding(layoutDirection),
                        ), color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationList(
                        affirmations = Datasource().loadAffirmations(),
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Composable
fun AffirmationList(affirmations: List<Affirmation>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(affirmations) { affirmation ->
            AffirmationCard(affirmation = affirmation, modifier = modifier.padding(8.dp))
        }

    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(id = affirmation.imageResourceId),
                contentDescription = stringResource(
                    id = affirmation.stringResourceId
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ListviewTheme {
        AffirmationCard(affirmation = Datasource().loadAffirmations().first(), modifier = Modifier)
    }
}