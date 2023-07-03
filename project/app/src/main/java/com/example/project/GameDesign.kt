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
import com.example.project.R
import com.example.project.Screen
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random


// Dodana lista do przechowywania wyników i czasu
var resultList: MutableList<String> = mutableListOf()


//Sprawdzamy wynik gry - 1 jeśli wygrał gracz, 0 komputer, 2 remis
fun scorer(faction: String, aaction: String): Int {
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

//Funkcja do generowania wyboru komputera
fun compChoice(): String {
    //Android choice generator
    val list = listOf("Rock", "Paper", "Scissor")
    val randomIndex = Random.nextInt(list.size)
    return list[randomIndex]
}

//funkcja do stworzenia przycisku o konkretnej wartości
@Composable
fun Rockpbutton(bvlaue: String, onClick: () -> Unit) {

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
//funkcja wypisująca wybór gracza i komputera
@Composable
fun Playeraction(playerChosedItemTitle: String, playerChosedItem: String) {
    Text(
        text = playerChosedItemTitle,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
    Text(
        text = playerChosedItem,
        fontSize = 32.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
        fontWeight = FontWeight.Bold
    )
}

//Główna funkcja
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GameScreen(
    navController: NavController
) {
    Image(painter = painterResource(id = R.drawable.tlo),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    //Zmienne
    var playerChoice by remember { mutableStateOf("Rock") }
    var computerChoice by remember { mutableStateOf("Paper") }
    var pointsRatio by remember { mutableStateOf("0 / 0") }
    var playerScores by remember { mutableStateOf(0) }
    var computerScores by remember { mutableStateOf(0) }
    Column {
        //główna kolumna
        Text(
            text = "Score", fontSize = 30.sp, modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )
        Text(
            text = pointsRatio, fontSize = 50.sp, modifier = Modifier
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
                Playeraction(playerChosedItemTitle = "You Chose", playerChosedItem = playerChoice)
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Playeraction(playerChosedItemTitle = "Computer chose", playerChosedItem = computerChoice)
            }
        }
        Row(modifier = Modifier.padding(top = 70.dp)) {
            Column(
                Modifier
                    .fillMaxWidth(0.33f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Rockpbutton(bvlaue = "Rock") {
                    playerChoice = "Rock"
                    //generujemy wybór komputera
                    computerChoice = compChoice()
                    //sprawdzamy kto wygrał
                    val win = scorer(playerChoice, computerChoice)
                    if (win == 1)
                        playerScores++
                    else if (win == 0)
                        computerScores++
                    pointsRatio = "$playerScores / $computerScores"
                }
            }
            Column(
                Modifier
                    .fillMaxWidth(0.5f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Rockpbutton(bvlaue = "Paper") {
                    playerChoice = "Paper"
                    computerChoice = compChoice()
                    val win = scorer(playerChoice, computerChoice)
                    if (win == 1)
                        playerScores++
                    else if (win == 0)
                        computerScores++
                    pointsRatio = "$playerScores / $computerScores"
                }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Rockpbutton(bvlaue = "Scissor") {
                    playerChoice = "Scissor"
                    computerChoice = compChoice()
                    val win = scorer(playerChoice, computerChoice)
                    if (win == 1)
                        playerScores++
                    else if (win == 0)
                        computerScores++
                    pointsRatio = "$playerScores / $computerScores"
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

                    resultList.add("Wynik: ${playerScores}:${computerScores}, godzina: ${formattedDateTime}")

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



