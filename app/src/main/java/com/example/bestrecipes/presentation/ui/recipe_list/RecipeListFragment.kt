package com.example.bestrecipes.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Search
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bestrecipes.presentation.components.CircularIndeterminateProgressBar
import com.example.bestrecipes.presentation.components.LoadingRecipeListShimmer
import com.example.bestrecipes.presentation.components.RecipeCard
import com.example.bestrecipes.presentation.components.SearchAppBar
import com.example.bestrecipes.presentation.theme.AppTheme
import com.example.bestrecipes.presentation.ui.BaseApplication
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: RecipeListViewModel by viewModels()
    // use 'by activityViewModels()' if you want to share the viewModel between different fragments

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // classical way to observe LiveData:
        /*viewModel.recipes.observe(viewLifecycleOwner, { recipes ->
            // update UI
        })*/

        return ComposeView(requireContext()).apply {
            setContent {

                AppTheme(
                    darkTheme = application.isDark.value
                ) {
                    // "observe" mutable states of view model
                    val recipes = viewModel.recipes.value

                    val query = viewModel.query.value

                    val selectedCategory = viewModel.selectedCategory.value

                    val loading = viewModel.loading.value

                    Scaffold(
                        topBar = {
                            SearchAppBar(
                                query = query,
                                onQueryChanged = viewModel::onQueryChanged,
                                onExecuteSearch = viewModel::newSearch,
                                scrollPosition = viewModel.categoryScrollPosition,
                                selectedCategory = selectedCategory,
                                onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                                onChangeCategoryScrollPosition = viewModel::onChangeCategoryScrollPosition,
                                onToggleTheme = {
                                    application.toggleTheme()
                                }
                            )
                        }/*,
                        bottomBar = {
                            MyBottomBar()
                        },
                        drawerContent = {
                            MyDrawer()
                        }*/
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = MaterialTheme.colors.surface)
                        ) {
                            if (loading) {
                                LoadingRecipeListShimmer(imageHeight = 250.dp)
                            } else {
                                LazyColumn {
                                    itemsIndexed(
                                        items = recipes
                                    ) { index, recipe ->
                                        RecipeCard(recipe = recipe, onClick = { })
                                    }
                                }
                            }
                            CircularIndeterminateProgressBar(
                                isDisplayed = loading
                            )
                            // whatever should be shown "in front" must be the least entry in a box
                        }
                    }
                }
            }
        }
    }
}

// just for illustration purposes:

@Composable
fun MyBottomBar() {
    BottomNavigation(
        elevation = 12.dp
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.BrokenImage) },
            selected = false,
            onClick = { }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Search) },
            selected = false,
            onClick = { }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.AccountBalanceWallet) },
            selected = true,
            onClick = { }
        )
    }
}

@Composable
fun MyDrawer(){
    Column() {
        Text("Item1")
        Text("Item2")
        Text("Item3")
        Text("Item4")
        Text("Item5")
    }
}