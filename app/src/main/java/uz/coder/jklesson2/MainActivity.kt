package uz.coder.jklesson2

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import uz.coder.jklesson2.ui.theme.JkLesson2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.RED))
        setContent {
            JkLesson2Theme {
                Scaffold(modifier = Modifier.fillMaxSize(),floatingActionButton = { ActionButton() }) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

}
    private val showDialog = mutableStateOf(false)
@Composable
private fun ActionButton() {
    FloatingActionButton(onClick = { showDialog.value = true }, containerColor = androidx.compose.ui.graphics.Color.Red, contentColor = androidx.compose.ui.graphics.Color.White) {
        Icon(Icons.Filled.Add, contentDescription = "")
    }
    AlertDialog()
}

@Composable
fun AlertDialog() {
    val context = LocalContext.current
    if (showDialog.value) {
        Dialog(onDismissRequest = { showDialog.value = false }, properties = DialogProperties(false)){
            OutlinedCard(modifier = Modifier, shape = RoundedCornerShape(15.dp)) {
                Text(text = stringResource(R.string.dialog_title), modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(), textAlign = TextAlign.Center)
                Text(text = stringResource(R.string.dialog_text), modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(), textAlign = TextAlign.End)
                HorizontalDivider()
                Button({
                    showDialog.value = false
                    Toast.makeText(context, context.getString(R.string.ok), Toast.LENGTH_SHORT).show()
                }, modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()){
                    Text(text = stringResource(R.string.ok))
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val tabs = listOf("Tab 1", "Tab 2", "Tab 3","Tab 4", "Tab 5", "Tab 6")
    val state = rememberPagerState(0)
    Column(modifier = modifier.fillMaxSize()) {
        ScrollableTabRow(selectedTabIndex = state.currentPage) {
            tabs.forEachIndexed { index, text ->
                Tab(selected = state.currentPage == index, onClick = { scope.launch { state.animateScrollToPage(index) } },text = { Text(text = text)}, modifier = modifier.fillMaxWidth())
            }
        }
        HorizontalPager(count = tabs.size, state = state, modifier = modifier.fillMaxSize()) {
            LazyColumn {
                items(count = tabs.size){
                    Card(modifier = modifier){
                        Text(text = tabs[it], modifier = modifier.padding(10.dp))
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JkLesson2Theme {
        Greeting()
    }
}