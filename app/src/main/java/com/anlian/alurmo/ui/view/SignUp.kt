package com.anlian.alurmo.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.anlian.alurmo.R
import com.anlian.alurmo.models.Account
import com.anlian.alurmo.ui.states.AuthState
import com.anlian.alurmo.viewmodel.SignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(
    navController: NavHostController,
    authState: AuthState
) {
    val viewModel = hiltViewModel<SignUpViewModel>()
    val email = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val togglePass = remember { mutableStateOf(false) }
    val inputState = viewModel.inputState.collectAsState()
    val signUpState = viewModel.signUpState.collectAsState()

    Scaffold(
        topBar = { TopBar(stringResource(id = R.string.sign_up_label)) }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(it)
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxSize()
        ) {
            val (
                doodle,
                textField,
                lowerRow
            ) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.sign_up_doodle),
                contentDescription = "Sign Up Doodle",
                modifier = Modifier.constrainAs(doodle){
                    top.linkTo(parent.top, margin = 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            Box(
                modifier = Modifier
                    .constrainAs(textField) {
                        top.linkTo(doodle.bottom, margin = 24.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
            ) {
                InputField(
                    email = email,
                    pass = pass,
//                    state = inputState,
                    viewModel = viewModel,
                    focusManager = focusManager,
                    togglePass = togglePass
                )
            }
            Box(
                modifier = Modifier
                    .constrainAs(lowerRow) {
                        bottom.linkTo(parent.bottom, margin = 34.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
            ){
                AccountButtonLayout(
                    code = authState,
                    account = Account(email = email.value, password = pass.value),
                    navController = navController,
                    lowerRowText = stringResource(id = R.string.sign_in_attention_label),
                    signUpViewModel = viewModel
                )
            }
        }
    }
}