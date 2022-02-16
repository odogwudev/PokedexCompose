package com.odogwudev.pokedexcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.odogwudev.pokedexcompose.data.remote.responses.PokemonList
import com.odogwudev.pokedexcompose.pokemonlist.Pokedex_List
import com.odogwudev.pokedexcompose.ui.theme.PokedexComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexComposeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "pokedex_list") {
                    composable("pokedex_list") {
                        Pokedex_List(navController = navController)

                    }
                    composable("pokedex_detail/{dormantColor}/{pokemon_name}",
                        arguments = listOf(
                            navArgument("dormantColor") {
                                type = NavType.IntType
                            },
                            navArgument("pokemon_name") {
                                type = NavType.StringType
                            }
                        )
                    )

                    {
                        val dormantColor = remember {
                            val color = it.arguments?.getInt("dormantColor")
                            color?.let {
                                Color(it)
                            } ?: Color.White
                        }
                        val pokemon_name = remember {
                            it.arguments?.getString("pokemon_name")
                        }


                    }

                }
            }
        }
    }
}