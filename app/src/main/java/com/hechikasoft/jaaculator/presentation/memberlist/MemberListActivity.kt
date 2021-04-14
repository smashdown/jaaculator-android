package com.hechikasoft.jaaculator.presentation.memberlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.hechikasoft.jaaculator.presentation.memberlist.composable.ChannelNameBar
import com.hechikasoft.jaaculator.presentation.memberlist.composable.MemberListContent
import com.hechikasoft.jaaculator.presentation.memberlist.composable.MemberListScreen
import com.hechikasoft.jaaculator.presentation.memberlist.composable.exampleUiState
import com.hechikasoft.jaaculator.ui.theme.JaaculatorTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MemberListActivity : ComponentActivity() {
    private val viewModel: MemberListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObservers()

        initViews()
    }

    private fun initViews() {
        setContent {
            JaaculatorTheme {
                MemberListScreen(
                    uiStateFlow = MutableStateFlow(exampleUiState),
                    navigateToProfile = { },
                    onAddMemberClickListener = {
                        viewModel.setIntent(MemberListContract.Intent.OnAddMemberClicked)
                    }
                )
            }
        }
    }

    private fun initObservers() {
        // Collect ui state
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect {
//                when (it.randomNumberState) {
//                    is MainContract.RandomNumberState.Idle -> { binding.progressBar.isVisible = false }
//                    is MainContract.RandomNumberState.Loading -> { binding.progressBar.isVisible = true }
//                    is MainContract.RandomNumberState.Success -> {
//                        binding.progressBar.isVisible = false
//                        binding.number.text = it.randomNumberState.number.toString()
//                    }
//                }
            }
        }

        // Collect side effects
        lifecycleScope.launchWhenStarted {
            viewModel.effect.collect {
                when(it) {
                    is MemberListContract.Effect.ShowAddMemberPopup -> {

                    }
                }
//                when (it) {
//                    is MainContract.Effect.ShowToast -> {
//                        binding.progressBar.isVisible = false
//                        // Simple method that shows a toast
//                        showToast("Error, number is even")
//                    }
//                }
            }
        }
    }
}
