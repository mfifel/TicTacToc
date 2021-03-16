# Tic Tac Toe opdracht m'hamed

## regels

1 - X gaat altijd eerst.

2 - Spelers kunnen niet spelen op een al gespeelde positie.

3 - Spelers plaatsen afwisselend X's en O's op het bord totdat:

    - Een speler er drie op een rij heeft, horizontaal, verticaal of diagonaal

    - Alle negen vierkanten zijn gevuld.

4 - Als een speler in staat is om drie X's of drie O's achter elkaar te plaatsen, wint die speler.

5 - Als alle negen vierkanten zijn gevuld en geen van beide spelers er drie op een rij heeft, is het spel gelijkspel.



## Run de app
### Met command line
- download de project (TicTacToe directory) naar je local pc (click op code --> download zip)
- zorg dat u java 1.8 heeft geïnstalleerd.
- compileer en run de app (windows 10):
  - Gebruik 'Zoeken' om te zoeken naar 'Systeem' (Configuratiescherm) en selecteer deze optie.
  Klik op de koppeling Geavanceerde systeeminstellingen.
  Klik op Omgevingsvariabelen. Zoek in het gedeelte Systeemvariabelen de omgevingsvariabele PATH en selecteer deze. Klik op Bewerken. voeg het path naar de bin directory van de geïnstalleerde java-versie. bijv. c:/jdk1.8.0_111.jdk/Contents/Home/bin
    
  - open cmd programma (of andere command line program) en ga naar de 'src/com/mhamed/fifel/' directory in project
  - run  javac model/enums/*\*.java model/entities/*\*.java Main.java
  - ga naar 'src' directory (cd ../../../)
  - run  java com.mhamed.fifel.Main
    
- compileer en run de app (mac):
  - open the terminal
  - ga naar de 'src/com/mhamed/fifel/' directory in project
  - run <installatie directory van java>/bin/javac  model/enums/*\*.java model/entities/*\*.java Main.java
    (bijv /Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/bin/javac  model/enums/*\*.java model/entities/*\*.java Main.java)
  - ga naar 'src' directory (cd ../../../)
  - run <installatie directory van java>/bin/java com.mhamed.fifel.Main
    (bijv /Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/bin/java com.mhamed.fifel.Main )
    
### Met een IDE
- zorg dat u een IDE op uw PC geïnstalleerd hebt. (bij een intelliJ IDE)
- zorg dat u java 1.8 heeft geïnstalleerd. 
- ga naar de link '  '
- clik op 'code' button, download de project zip file en unzip deze
  (het is ook mogelijk om het project meteen te clonen vanuit de repository...hier moet je eerst git local installeren)
- open de IDE, ga naar file --> open, en open de TicTacToe project. (unziped directory)
- IDE configureert bij default de java versie die bekend is op de PC (ga naar Run --> edit configuration om deze te checken)
- open file Main.java  --> rechtsklik -->  run main




# Ontwikkel stappen
### 1\- technologies en tools
- junit 5
- mockito
- java 8
- intelliJ IDE
- redenen ? 
  - meeste gebruikte in enterprise project
  - innovatie
  - ervaring mee



### 2 - design
- OO ipv alles in één file
- redenen ?
  - game makkelijk uitbreidbaar
  - fouten kunnen snel worden getraceerd
  - testen (TDD) van elke afzonderlijk unit (class) wordt éénvoudig en vergemakkelijkt het testen van andere relation classes
  - java zelf is ook OO taal en kan hiermee overweg.
  - 

#### 2.1 - classes en relaties
- uit de bovenstaand requirements/regels kan ik volgende objecten identificeren:
  - speler 
  - bord (3x3)
  - vierkant
  - het spel
  - rij
  - X en O
  
- verder analyseren geeft dit volgende relaties en props:
  - het spel zelf bestaat/omvat het bord
  - het spel houdt ook de huidige toestand en huidige speler bij die voortdurend verandert. 
  - het bord zelf bestaat uit 9 vierkanten/cellen
  - het bord heeft 3 rijen en 3 kolommen om op 9 vierkanten te komen
  - verder houdt het bord ook de positie (y,x) van de vierkant waarop de huidig speler speelt
  - de inhoud van een vierkant kan X, O of leeg zijn
  - verder heeft een vierkant een positie (y,x) op het bord --> (row,column)
  - speler speelt het spel en vormt dan een actor die het systeem van buiten benadert
  
- binnen het systeem definiëren we dan zeker de volgende Klassen: 
  - het spel zelf (Main)
  - het spelbord (Board)
  - vierkant binnen het bord (Cell)
  - huidige toestand van het spel: gelijk/X wint/O wint of nog aan het spelen (GameState)
  - markering die aangeeft of een vierkant X, O bevat of leeg is (Mark)

- uitgaan van het spelcontext kunnen volgende gedragen (methods) gedefinieerd worden.
  - class Cell: voorlopig niets 
  - class Board: voorlopig niets
  - class Main:  initGame() die het spel zelf initialiseert
                 playerTurn() die het spelbord manipuleert aan de hand van speler input 
                 updateGame() die de state van het spel bijhoudt.

- dit resulteert tot het klassendiagram in het document 'diagram.docx'

### 3 - coderen met TDD (zie testen in code)
- zorg eerst dat u JUNIT 5 framework local hebt geïnstalleerd en toevoegt aan libraries in project
- definiëren van user stories aan de hand van de spelregels
  - regel 2 / story: Spelers kunnen niet spelen op een al gespeelde positie.
  - functionaliteit: plaatsen van een X of O
  - test requirements (uitgebreid) : 
    - speler geeft een rij-positie groter 3 (bord size) dan wordt een message getoond.
    - speler geeft een kolom-positie groter 3 (bord size) dan wordt een message getoond.
    - speler geeft een rij-positie/kolom-positie die al bezit is dan wordt een message getoond.
    - speler geeft een correct rij-positie/kolom-positie
  - test cases (respectievelijk):
    - zie tests
  
  - regel 1 / story: X gaat altijd eerst
  - functionaliteit: bij start gaat X als eerste spelen.
  - test requirements (uitgebreid) : 
    - check of het bord bij start leeg is
    - check of X de huidig bij start de huidige speler is
    - check of de status van de game 'aan het spelen is'
  - test cases (respectievelijk):
    - zie tests

  -regel 4 en 5 / story: 
  - functionaliteit: status van de game.
  - test requirements (uitgebreid) :
   - check wie er wint
   - check gelijk spel


De TDD process heeft geleid om het gedrag / methods in de klassen 'Cell' en 'Board' te implementeren:
  - class Cell: clear() en paint() die respectievelijk de inhoud van het vierkant wist en toont.
  - class Board: init() en paint() die respectievelijk de inhoud van het hele bord initialiseert en toont.  
                 isDraw() die checkt of het bord gelijkspel vertoont.
                 hasWon() die checkt of het bord een winnaar vertoont. 



###EIND