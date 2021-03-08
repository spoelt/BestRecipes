package com.example.bestrecipes.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bestrecipes.presentation.components.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    val viewModel: RecipeListViewModel by viewModels()
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
                // "observe" mutable states of view model
                val recipes = viewModel.recipes.value

                val query = viewModel.query.value

                val selectedCategory = viewModel.selectedCategory.value

                val loading = viewModel.loading.value

                Column {
                    SearchAppBar(
                        query = query,
                        onQueryChanged = viewModel::onQueryChanged,
                        onExecuteSearch = viewModel::newSearch,
                        scrollPosition = viewModel.categoryScrollPosition,
                        selectedCategory = selectedCategory,
                        onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                        onChangeCategoryScrollPosition = viewModel::onChangeCategoryScrollPosition
                    )
/*                    LoadingRecipeListShimmer(imageHeight = 250.dp)*/
                    Box(
                        modifier = Modifier.fillMaxSize()
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