package com.guillermomuntaner.adoptapuppy.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
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
import androidx.navigation.compose.*
import com.guillermomuntaner.adoptapuppy.*
import com.guillermomuntaner.adoptapuppy.R
import com.guillermomuntaner.adoptapuppy.model.Puppy
import com.guillermomuntaner.adoptapuppy.viewmodel.PuppiesListViewModel


@Preview
@Composable
fun MainNavigationController() {
    val navController = rememberNavController()

    // Note: Transition animation is WIP, not yet available.

    // Note: Workaround/Solution to control the app bar title & back navigation
    val baseTitle = stringResource(id = com.guillermomuntaner.adoptapuppy.R.string.app_name)
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
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
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
    setTitle(stringResource(id = R.string.app_name))
    val puppies: State<List<Puppy>> = puppiesListViewModel.puppies.observeAsState(emptyList())
    PuppiesList(puppies = puppies.value, onClick = { puppy ->
        setTitle("")
        navController.navigate("detail/${puppy.id}")
    })
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