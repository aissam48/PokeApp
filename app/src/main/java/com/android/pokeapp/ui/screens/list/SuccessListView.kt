package com.android.pokeapp.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.pokeapp.R
import com.android.pokeapp.models.PokemonItem
import com.android.pokeapp.ui.screens.components.ItemListView

@Composable
fun SuccessListView(
    list: List<PokemonItem>,
    isLoading: Boolean,
    onClick: (item: PokemonItem) -> Unit,
    loadMore: () -> Unit,
) {

    val scrollState = rememberLazyListState()

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 30.dp)
            .fillMaxSize()
    ) {

        Text(
            text = stringResource(R.string.pokemon_list),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp
            ),
        )

        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        ) {
            itemsIndexed(list) { index, item ->
                ItemListView(item.name){
                    onClick(item)
                }

                if (index >= list.size - 1 && !isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { layoutCoordinates ->
                                val endPosition = layoutCoordinates.positionInWindow().y +
                                        layoutCoordinates.size.height
                                val screenHeight = layoutCoordinates.localToRoot(Offset.Zero).y

                                if (endPosition >= screenHeight) {
                                    loadMore()
                                }
                            }
                    ){
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }


                }

            }

        }
    }
}


