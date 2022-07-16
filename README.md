# Simple Football Score Board

Gradle was added for junit tests.
</br>
The application made as a simple library and does not have a startup method, only methods that call selected actions.
</br>
Reset and getGame methods have been added to facilitate management and testing.
</br>
A method has been added to return the games in the order described in the task. In the same way, I added a method that prints in the console this information as it was mentioned in the task.
</br>
</br>
</br>

### Description: ###

The Application is using by access to static method in ScoreBoard class.
</br>

Supported methods:
1. newGame(String homeTeam, String awayTeam) - adds a new game between homeTeam and awayTeam and returns a created Game
~~~java
    Game game = ScoreBoard.newGame("England", "Italy");
~~~
2. getGame(int id) - returns a Game having given id
~~~java
    Game game = ScoreBoard.getGame(1);
~~~
3. startGame(int id) - starts a Game having given id. Unable to start started or finished game.
~~~java
    Game game = ScoreBoard.startGame(1);
~~~
4. finishGame(int id) - finishes a Game having given id. Unable to finish not started or finished game.
~~~java
    Game game = ScoreBoard.finishGame(1);
~~~
5. updateScore(int id, int homeScore, int awayScore) - changes score for Game having given id to parameters homeScore(for homeTeam) and awayScore(awayTeam). Unable to change score for not started and finished games. Unable to set negative values.
~~~java
    //change score for 2:1
    Game game = ScoreBoard.updateScore(1, 2, 1);
~~~
6. listOrderedByTotalScore() - returns a games list sorted by total score.
~~~java
    List<Game> games = ScoreBoard.listOrderedByTotalScore();
~~~
7. printOrderedByTotalScore() - prints to console output a games list sorted by total score.
~~~java
    ScoreBoard.printOrderedByTotalScore();
~~~
Prints to console output something like that:
~~~
1. Uruguay 6 - Italy 6
2. Spain 10 - Brazil 2
3. Mexico 0 - Canada 5
4. Argentina 3 - Australia 1
5. Germany 2 - France 2
~~~
8. reset() - reset and clear storage.
~~~java
    ScoreBoard.reset();
~~~