package com.odogwudev.example.catalog

import android.annotation.SuppressLint
import androidx.annotation.IdRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.odogwudev.example.theme.AppTheme

@Composable
fun AuthScreenDelimiter(
    text: String,
    modifier: Modifier = Modifier
) = Box(modifier = modifier.fillMaxWidth()) {
    Divider(
        color = Color.LightGray.copy(alpha = if (isSystemInDarkTheme()) 0.4f else 1f),
        modifier = Modifier.align(alignment = Alignment.Center)
    )
    Text(
        text = text,
        style = AppTheme.typography.body1,
        fontWeight = FontWeight.Bold,
        color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray,
        modifier = Modifier
            .background(color = AppTheme.colors.primary)
            .padding(horizontal = AppTheme.dimens.contentPadding)
            .align(alignment = Alignment.Center)
    )
}

@SuppressLint("ResourceType")
@Composable
fun SocialLoginOption(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String? = null,
    @IdRes iconId: Int
) = OutlinedButton(
    border = BorderStroke(width = 1.dp, color = Color.LightGray.copy(alpha = if (isSystemInDarkTheme()) 0.4f else 1f)),
    shape = AppTheme.shapes.medium,
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(
        backgroundColor = AppTheme.colors.background,
        contentColor = AppTheme.colors.onBackground
    ),
    contentPadding = PaddingValues(
        horizontal = AppTheme.dimens.contentPadding,
        vertical = AppTheme.dimens.contentPadding * 2
    ),
    modifier = modifier
) {
    Image(
        painter = painterResource(id = iconId),
        contentDescription = null
    )
    if (text != null) {
        Spacer(modifier = Modifier.width(width = AppTheme.dimens.contentPadding * 2))
        Text(
            text = text,
            style = AppTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Composable
fun AuthInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    placeholder: String,
    leadingIcon: ImageVector,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
) {
   val isDark = isSystemInDarkTheme()

    OutlinedTextField(
        value = value ,
        onValueChange = onValueChange,
        label = label,
        placeholder = {
            Text(
                text = placeholder,
                color = if (isDark) Color.LightGray else Color.DarkGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = AppTheme.typography.body2,
                fontWeight = FontWeight.Normal
            )
        },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = if (isDark) Color.LightGray else Color.DarkGray,
                modifier = Modifier.size(size = 20.dp)
            )
        },
        trailingIcon = trailingIcon,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        shape = AppTheme.shapes.medium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = AppTheme.colors.onPrimary,
            cursorColor = AppTheme.colors.onPrimary,
            backgroundColor = AppTheme.colors.background,
            focusedBorderColor = AppTheme.colors.secondary,
            unfocusedBorderColor = Color.LightGray.copy(alpha = if (isSystemInDarkTheme()) 0.4f else 1f)
        ),
        singleLine = true,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun SocialAuthFooter(
    modifier: Modifier = Modifier,
    text: String,
    clickableText: String,
    onSignUpClick: () -> Unit
) = Row(
    modifier = modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
) {
    Text(
        text = text,
        style = AppTheme.typography.body2,
        color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray
    )
    Spacer(modifier = Modifier.width(width = AppTheme.dimens.contentPadding))
    Text(
        text = clickableText,
        style = AppTheme.typography.body1,
        fontWeight = FontWeight.Bold,
        color = AppTheme.colors.secondary,
        modifier = Modifier
            .clickable { onSignUpClick() }
    )
}