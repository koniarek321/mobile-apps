import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.project.R
import com.example.project.Screen
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random


// Dodane zmienne do przechowywania wyników i czasu
var resultList: MutableList<String> = mutableListOf()

fun scorer(faction: String, aaction: String): Int {
    // winner calculator 1 for player 0 for android 2 means a tie
    var win = 0
    if (faction == aaction)
        win = 2
    else if (faction == "Rock" && aaction == "Paper")
        win = 0
    else if (faction == "Rock" && aaction == "Scissor")
        win = 1
    else if (faction == "Paper" && aaction == "Rock")
        win = 1
    else if (faction == "Paper" && aaction == "Scissor")
        win = 0
    else if (faction == "Scissor" && aaction == "Paper")
        win = 1
    else if (faction == "Scissor" && aaction == "Rock")
        win = 0
    return win
}

fun genfora(): String {
    //Android choice generator
    val list = listOf("Rock", "Paper", "Scissor")
    val randomIndex = Random.nextInt(list.size)
    return list[randomIndex]
}

@Composable
fun Rockpbutton(bvlaue: String, onClick: () -> Unit) {
    // buttons generator
    Button(
        modifier = Modifier
            .height(108.dp)
            .width(108.dp)
            .padding(10.dp),
        onClick = onClick,
        shape = RoundedCornerShape(14.dp)
    ) {
        Text(text = bvlaue)
    }
}

@Composable
fun Playeraction(playera: String, actionc: String) {
    //player and android choices display
    Text(
        text = playera,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
    Text(
        text = actionc,
        fontSize = 32.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
        fontWeight = FontWeight.Bold
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GameScreen(
    navController: NavController,
    argList: MutableList<Triple<Int, Int, String>> = mutableListOf()
) {
    Image(painter = painterResource(id = R.drawable.tlo),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    //main compose

    var faction by remember { mutableStateOf("Rock") }
    var aaction by remember { mutableStateOf("Paper") }
    var tscore by remember { mutableStateOf("0 / 0") }
    var pscore by remember { mutableStateOf(0) }
    var ascore by remember { mutableStateOf(0) }
    Column {
        //main column

        Text(
            text = "Score", fontSize = 30.sp, modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )
        Text(
            text = tscore, fontSize = 50.sp, modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )

        Row(modifier = Modifier.padding(top = 85.dp)) {
            Column(
                Modifier
                    .fillMaxWidth(0.5f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Playeraction(playera = "You Chose", actionc = faction)
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Playeraction(playera = "Computer chose", actionc = aaction)
            }

        }
        Row(modifier = Modifier.padding(top = 70.dp)) {
            Column(
                Modifier
                    .fillMaxWidth(0.33f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Rockpbutton(bvlaue = "Rock") {
                    faction = "Rock"
                    aaction = genfora()
                    val win = scorer(faction, aaction)
                    if (win == 1)
                        pscore++
                    else if (win == 0)
                        ascore++
                    tscore = "$pscore / $ascore"
                }
            }
            Column(
                Modifier
                    .fillMaxWidth(0.5f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Rockpbutton(bvlaue = "Paper") {
                    faction = "Paper"
                    aaction = genfora()
                    val win = scorer(faction, aaction)
                    if (win == 1)
                        pscore++
                    else if (win == 0)
                        ascore++
                    tscore = "$pscore / $ascore"
                }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Rockpbutton(bvlaue = "Scissor") {
                    faction = "Scissor"
                    aaction = genfora()
                    val win = scorer(faction, aaction)
                    if (win == 1)
                        pscore++
                    else if (win == 0)
                        ascore++
                    tscore = "$pscore / $ascore"
                }
            }

        }
        Row(
            verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.weight(1f))

            // Przycisk do zapisywania wyniku i czasu
            Button(
                onClick = {
                    val currentDateTime = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    val formattedDateTime = currentDateTime.format(formatter)

                    resultList.add("Wynik: ${pscore}:${ascore}, godzina: ${formattedDateTime}")


                    println("Wynik gry zapisany.")
                    println(resultList)

                },
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Save results")
            }

            // Przycisk do kończenia gry i przechodzenia do kolejnego ekranu
            Button(
                onClick = {
                    navController.navigate(route = Screen.Home.route)
                    println("Koniec gry. Przechodzę do kolejnego ekranu.")
                },
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "End game")
            }
            Button(
                onClick = {
                    val argListString = resultList.toString()
                    navController.navigate(route = "${Screen.History.route},${argListString}")
                },
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "History")
            }


        }
    }
}



