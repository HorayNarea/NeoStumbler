package xyz.malkki.neostumbler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import xyz.malkki.neostumbler.ui.composables.ReportMap
import xyz.malkki.neostumbler.ui.screens.ReportsScreen
import xyz.malkki.neostumbler.ui.screens.SettingsScreen
import xyz.malkki.neostumbler.ui.theme.NeoStumblerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NeoStumblerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(text = getString(R.string.app_name)) },
                                colors = TopAppBarDefaults.smallTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                                ),
                                actions = {

                                }
                            )
                         },
                        content = {
                            val selectedTabIndex = remember { mutableIntStateOf(1) }

                            val items = listOf(
                                stringResource(R.string.map_tab_title) to Icons.Filled.Place,
                                stringResource(R.string.reports_tab_title) to Icons.Filled.List,
                                stringResource(R.string.settings_tab_title)  to Icons.Filled.Settings,
                            )

                            Column(modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues = it)) {
                                Column(modifier = Modifier
                                    .weight(1.0f)
                                    .padding(16.dp)) {
                                    when (selectedTabIndex.intValue) {
                                        0 -> {
                                            ReportMap()
                                        }
                                        1 -> {
                                            ReportsScreen()
                                        }
                                        2 -> {
                                            SettingsScreen()
                                        }
                                    }
                                }

                                NavigationBar {
                                    items.forEachIndexed { index, (title, icon) ->
                                        NavigationBarItem(
                                            icon = { Icon(icon, contentDescription = title) },
                                            label = { Text(title) },
                                            selected = selectedTabIndex.intValue == index,
                                            onClick = { selectedTabIndex.intValue = index }
                                        )
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}