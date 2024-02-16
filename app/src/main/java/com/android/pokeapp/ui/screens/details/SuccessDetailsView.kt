package com.android.pokeapp.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.pokeapp.R
import com.android.pokeapp.models.PokemonDetails
import com.android.pokeapp.ui.screens.components.ImageView
import com.android.pokeapp.ui.screens.components.ItemListView

@Composable
fun SuccessDetailsView(pokemon: PokemonDetails) {

    LazyColumn(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 30.dp)

    ) {
        item {
            Column {

                Text(
                    text = pokemon.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 35.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Row {
                    ImageView(pokemon.sprites.frontDefault)
                    ImageView(pokemon.sprites.backDefault)
                }

                Row(modifier = Modifier.padding(top = 10.dp)) {
                    Text(
                        text = stringResource(R.string.details_screen_weight_label),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp
                        ),
                        color = MaterialTheme.colorScheme.onBackground,
                    )

                    Text(
                        text = pokemon.weight.toString(),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp
                        ),
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }

                Row(modifier = Modifier.padding(top = 10.dp)) {
                    Text(
                        text = stringResource(R.string.details_screen_order_label),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp
                        ),
                        color = MaterialTheme.colorScheme.onBackground,
                    )

                    Text(
                        text = pokemon.order.toString(),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp
                        ),
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }

                Text(
                    text = stringResource(R.string.details_screen_weight_types),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(top = 15.dp)
                )

                Spacer(modifier = Modifier.height(15.dp))

            }
        }

        items(pokemon.types) {
            ItemListView(it.name) {}
        }

        item {
            Column {
                Text(
                    text = stringResource(R.string.details_screen_weight_abilities),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
        }

        items(pokemon.abilities) {
            ItemListView(it.name) {}
        }

        item {
            Column {
                Text(
                    text = stringResource(R.string.details_screen_weight_moves),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Spacer(modifier = Modifier.height(15.dp))
            }
        }

        items(pokemon.moves) {
            ItemListView(it.name) {}
        }
    }

}

