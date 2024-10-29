package xyz.malkki.neostumbler.ui.composables.troubleshooting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import xyz.malkki.neostumbler.R


@Composable
fun TroubleshootingItem(
    title: String,
    stateFlow: Flow<Boolean>,
    fixAction: () -> Unit
) {
    val state = stateFlow.collectAsState(initial = null)

    Row(
        modifier = Modifier.defaultMinSize(minHeight = 48.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.wrapContentHeight().weight(1.0f),
            text = title
        )

        state.value?.let { isOk ->
            Icon(
                modifier = Modifier.padding(horizontal = 8.dp),
                painter = if (isOk) {
                    painterResource(id = R.drawable.check_circle_24)
                } else {
                    painterResource(id = R.drawable.error_24)
                },
                tint = if (isOk) {
                    Color.Green
                } else {
                    Color.Red
                },
                contentDescription = ""
            )

            Button(
                enabled = !isOk,
                onClick = {
                    fixAction.invoke()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.troubleshooting_fix)
                )
            }
        }
    }
}