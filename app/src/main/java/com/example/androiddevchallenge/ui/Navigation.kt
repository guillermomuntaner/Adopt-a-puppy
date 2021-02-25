/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.viewmodel.PuppiesListViewModel

@Preview
@Composable
fun MainNavigationController() {
    val navController = rememberNavController()

    // Note: Transition animation is WIP, not yet available.

    // Note: Workaround/Solution to control the app bar title & back navigation
    val baseTitle = stringResource(id = com.example.androiddevchallenge.R.string.app_name)
    val (title, setTitle) = remember { mutableStateOf(baseTitle) }

    val (canPop, setCanPop) = remember { mutableStateOf(false) }

    val scaffoldState: ScaffoldState = rememberScaffoldState()

    navController.addOnDestinationChangedListener { controller, _, _ ->
        setCanPop(controller.previousBackStackEntry != null)
    }

    Column {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = title) },
                    navigationIcon = if (canPop) {
                        {
                            IconButton(
                                onClick = {
                                    navController.popBackStack()
                                }
                            ) {
                                Icon(Icons.Outlined.ArrowBack, "back")
                            }
                        }
                    } else { null },
                )
            },
            scaffoldState = scaffoldState
        ) {
            NavHost(
                navController = navController,
                startDestination = "master"
            ) {
                composable("master") {
                    PuppiesMasterScreen(navController, setTitle)
                }
                composable(
                    route = "detail/{puppyId}",
                    arguments = listOf(navArgument("puppyId") { type = NavType.IntType })
                ) { backStackEntry ->
                    PuppyDetailsScreen(backStackEntry.arguments?.getInt("puppyId")!!, setTitle)
                }
            }
        }
    }
}

@Composable
fun PuppiesMasterScreen(
    navController: NavController,
    setTitle: (String) -> Unit,
    puppiesListViewModel: PuppiesListViewModel = viewModel()
) {
    setTitle(stringResource(id = R.string.puppies))
    val puppies: State<List<Puppy>> = puppiesListViewModel.puppies.observeAsState(emptyList())
    PuppiesList(
        puppies = puppies.value,
        onClick = { puppy ->
            setTitle("")
            navController.navigate("detail/${puppy.id}")
        }
    )
}

@Composable
fun PuppyDetailsScreen(
    puppyId: Int,
    setTitle: (String) -> Unit,
    puppiesListViewModel: PuppiesListViewModel = viewModel()
) {
    val puppies: State<List<Puppy>> = puppiesListViewModel.puppies.observeAsState(emptyList())
    puppies.value.firstOrNull { it.id == puppyId }?.let {
        setTitle(it.name)
        PuppyDetails(it)
    }
}
