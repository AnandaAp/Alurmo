package com.anlian.alurmo.ui.view

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anlian.alurmo.R
import com.anlian.alurmo.viewmodel.BaseAuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    email: MutableState<String> = mutableStateOf(""),
    pass: MutableState<String> = mutableStateOf(""),
//    state: State<InputState>,
    focusManager: FocusManager = LocalFocusManager.current,
    togglePass: MutableState<Boolean> = mutableStateOf(false),
    viewModel: BaseAuthViewModel
) {
    val TAG = "InputField"
    val textSpacer by remember { mutableStateOf(8.dp) }
    val textFieldSpacer by remember { mutableStateOf(20.dp) }
    val state = viewModel.inputState.collectAsState()
    val btnClick = viewModel.btnClick.collectAsState()

    Log.d(
        TAG,
        "InputField: state\nEmail state = ${state.value.emailState}\nPassword state = ${state.value.passState}"
    )
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(id = R.string.email_input_label),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        Spacer(Modifier.height(textSpacer))
//        TextField(
//            modifier = Modifier
//                .fillMaxWidth()
//                .border(
//                    width = 2.dp,
//                    color = if (state.value.emailState) MaterialTheme.colorScheme.error
//                    else MaterialTheme.colorScheme.primary,
//                    shape = RoundedCornerShape(8.dp)
//                ),
//            value = email.value,
//            onValueChange = { value ->
//                email.value = value
//            },
//            colors = TextFieldDefaults.textFieldColors(
//                disabledTextColor = Color.Transparent,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//                disabledIndicatorColor = Color.Transparent,
//                cursorColor = MaterialTheme.colorScheme.primary,
//                errorCursorColor = MaterialTheme.colorScheme.error,
//                errorLabelColor = MaterialTheme.colorScheme.error,
//                errorTrailingIconColor = MaterialTheme.colorScheme.error,
//                else MaterialTheme.colorScheme.onSurface,
//                else MaterialTheme.colorScheme.onSurface
//            ),
//            placeholder = {
//                Text(text = stringResource(id = R.string.email_input_hint))
//            },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Email,
//                imeAction = ImeAction.Next
//            ),
//            keyboardActions = KeyboardActions(
//                onNext = {
//                    focusManager.moveFocus(FocusDirection.Down)
//                }
//            ),
//            singleLine = true,
//            shape = RoundedCornerShape(8.dp),
//            isError = state.value.emailState
//        )
        Spacer(Modifier.height(textFieldSpacer))
        Text(
            text = stringResource(id = R.string.password_input_label),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        Spacer(Modifier.height(textSpacer))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = if (btnClick.value) {
                        if (!state.value.emailState) MaterialTheme.colorScheme.error
                        else MaterialTheme.colorScheme.primary
                    } else MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                ),
            value = pass.value,
            onValueChange = { value ->
                pass.value = value
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary,
                errorCursorColor = MaterialTheme.colorScheme.error,
                errorLabelColor = MaterialTheme.colorScheme.error,
                errorTrailingIconColor = MaterialTheme.colorScheme.error
//                placeholderColor = if(btnClick.value){
//                    if(!state.value.emailState) MaterialTheme.colorScheme.error
//                    else MaterialTheme.colorScheme.onSurface
//                } else MaterialTheme.colorScheme.onSurface,
//                textColor = if(btnClick.value){
//                    if(!state.value.emailState) MaterialTheme.colorScheme.error
//                    else MaterialTheme.colorScheme.onSurface
//                } else MaterialTheme.colorScheme.onSurface
            ),
            trailingIcon = {
                val image = if (togglePass.value)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                val tint = if (btnClick.value) {
                    if (!state.value.emailState || state.value.passState) {
                        if (togglePass.value) MaterialTheme.colorScheme.error
                        else MaterialTheme.colorScheme.error
                    } else {
                        if (togglePass.value) MaterialTheme.colorScheme.primary
                        else Color.Gray
                    }
                } else {
                    if (togglePass.value) MaterialTheme.colorScheme.primary
                    else Color.Gray
                }

                IconButton(onClick = { togglePass.value = !togglePass.value }) {
                    Icon(
                        imageVector = image,
                        contentDescription = "password toggle",
                        tint = tint
                    )
                }
            },
            visualTransformation = if (togglePass.value) VisualTransformation.None
            else PasswordVisualTransformation(),
            placeholder = {
                Text(text = stringResource(id = R.string.password_input_hint))
            },
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            isError = state.value.passState
        )
        Spacer(Modifier.height(1.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.QuestionMark,
                contentDescription = "Requirement Icon",
                tint = if (btnClick.value) {
                    if (!state.value.passState) {
                        MaterialTheme.colorScheme.error
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                } else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(2.dp)
                    .border(
                        width = 2.dp,
                        color = if (btnClick.value) {
                            if (!state.value.passState) {
                                MaterialTheme.colorScheme.error
                            } else Color.Gray
                        } else MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
            )
            Text(
                text = stringResource(id = R.string.password_requirement),
                fontSize = 14.sp,
                color = if (btnClick.value) {
                    if (!state.value.passState) {
                        MaterialTheme.colorScheme.error
                    } else Color.Gray
                } else Color.Gray
            )
        }
    }
}