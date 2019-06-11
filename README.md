# :black_joker: Black Jack
Progetto a cura di *Daria Gilletti* (**@irjne**), *Claudio Landolfo* (**@clacchisi**), *Pierpaolo Pistorio* (**@Zelos23**)


Implementazione di un'applicazione grafica in JavaFx che simuli l'andamento di una mano a Black Jack


## :small_red_triangle: Regole di base
 - Una volta che il giocatore ha effettuato la puntata, il dealer assegna a sé e al giocatore due carte. 
 -  Se un giocatore supera il 21 perde (*bust*). 
 - Se il giocatore fa 21 con le prime due carte assegnategli dal dealer, cioè riceve un asso (11) o una figura, forma il cosiddetto "black jack", e ha diritto al pagamento di 3 a 2.
 - Se il dealer realizza anche lui il black jack la mano è considerata alla pari (*draw*).
 - Una volta che il giocatore ha finito la sua mano (*stand*) , poiché non chiama più carte (*hit*), il dealer sviluppa il suo gioco seguendo la "regola del banco": deve tirare carta con un punteggio inferiore a 17. Una volta ottenuto o superato 17 si deve fermare. Se oltrepassa il 21 il banco "sballa". 

## :small_red_triangle: Logica (metodi principali)
#### Mescolamento del mazzo
***Parametri in ingresso:*** numero di mescolamenti
Seleziona un elemento random del mazzo e lo colloca nella posizione designata (i). Ripete l'operazione ricorsivamente. 

    public void mixCards (int cicles) {  
	    for (int i=0; i<this.cards.size(); i++) {  
	        Random random = new Random();  
	        int extraction = random.nextInt(51);  
	  
	        Card card = this.cards.get(extraction);  
	        this.cards.remove(card);  
	        this.cards.add(i, card);  
	    }  
	  
	    if (cicles>0) mixCards(cicles-1);  
	}

#### Assegnazione carte
***Parametri in ingresso:*** player
Preleva la prima carta del mazzo e la aggiunge al mazzo del player. 

    public boolean getCard (Player player) {  
	    if (this.cards.size() > 0 ) {  
	        player.getPlayerCards().add(this.cards.get(0));  
	        this.cards.remove(0);  
	        return true;  
	    }  
	    return false;  
	   }

#### Puntata e doppio giro di carte
***Parametri in ingresso:*** player, puntata
Verifica che la puntata sia accettabile (controllo), poi assegna la puntata e distribuisce i primi due giri di carte al player e al dealer per mezzo del metodo *getCard*.

    public boolean betAndGetCards (Player player, double bet) {  
	    if (bet > player.getCredit()) return false;  
	  
	    player.setBet(bet);  
	    player.setCredit(player.getCredit()-bet);  
	    for (int i=0; i<2; i++) {  
	        getCard(player);  
	        getCard(dealer);  
	    }  
	  
	    return true;  
	}

#### Calcolo dello score
***Parametri in ingresso:*** player
Determina il punteggio del player, tenendo conto delle regole di base del gioco. 

    public int getScore (Player player) {  
	    int score = 0;  
	    boolean ace = false;  
	    boolean figure = false;  
	  
	    for (Card card : player.getPlayerCards()) {  
	        if (card.getNumber() > 10) {  
	            score+=10;  
	            figure = true;  
	        }  
	        else if (card.getNumber() == 1) {  
	            ace = true;  
	            score+=1;  
	        }  
	        else score+=card.getNumber();  
	    }  
	  
	    if (player.getPlayerCards().size() == 2 && ace && (figure || score > 5)) score+=10;  
	  
	    return score;  
	}

#### Verifica della condizione di vittoria
***Parametri in ingresso:*** player
Verifica quale delle condizioni espresse dalle regole di base si sia verificata a seguito del doppio giro di carte iniziale (per black jack), stand (vittoria, perdita, pareggio), di un hit (bound). 

    public int stand (Player player) {  
	    int score = getScore(player), dealerScore = getScore(dealer);  
	  
	    if (score == dealerScore && score < 22) {  
	        player.setCredit(player.getCredit()+player.getBet());  
	        System.out.println("DRAW.");  
	        return 0;  
	    }  
	  
	    else if ((score < 22 && score > dealerScore) || dealerScore > 21) {  
	        player.setCredit(player.getCredit()+player.getBet()*2);  
	        System.out.println("WIN.");  
	        return 1;  
	    }  
	  
	    else if ((score < 22 && score < dealerScore) || dealerScore == 21) {  
	        System.out.println("LOSE.");  
	        return 2;  
	    }  
	    return -1;  
	}  
	  
	public int hit (Player player) {  
	    if (bustOrBlackJack(player) == 2) {  
	        getCard(player);  
	  
	        if (bustOrBlackJack(player) == 0) return 3;  
	    }  
	    return -1;  
	}  
	  
	public int blackJackOrDraw (Player player) {  
	    if (bustOrBlackJack(player) == 1 && getScore(getDealer()) < 21) {  
	        player.setCredit(player.getCredit()+player.getBet()+(player.getBet()*1.5));  
	        System.out.println("BLACK JACK.");  
	        return 4;  
	    }  
	  
	    if (bustOrBlackJack(player) == 1 && getScore(getDealer()) == 21) {  
	        player.setCredit(player.getCredit()+player.getBet());  
	        System.out.println("DRAW.");  
	        return 0;  
	    }  
	    return -1;  
	}

## :small_red_triangle: Interfaccia (metodi principali)
#### Reset
Azzera l'ambiente di gioco, ricominciando di fatto una nuova partita.

    public void restart () {  
	    game = new Game ();  
	    game.dealer = new Player();  
	    player = new Player(300);  
	  
	    StringProperty valueZero = new SimpleStringProperty("0");  
	    StringProperty stringNull = new SimpleStringProperty();  
	  
	    credit.textProperty().bind(new SimpleStringProperty(Double.toString(player.getCredit())));  
	    playerScore.textProperty().bind(valueZero);  
	    dealerScore.textProperty().bind(valueZero);  
	  
	    cardD1V.textProperty().bind(stringNull);  
	    cardD2V.textProperty().bind(stringNull);  
	    cardD3V.textProperty().bind(stringNull);  
	    cardD4V.textProperty().bind(stringNull);  
	    cardD5V.textProperty().bind(stringNull);  
	    cardD6V.textProperty().bind(stringNull);  
	  
	    cardP1V.textProperty().bind(stringNull);  
	    cardP2V.textProperty().bind(stringNull);  
	    cardP3V.textProperty().bind(stringNull);  
	    cardP4V.textProperty().bind(stringNull);  
	    cardP5V.textProperty().bind(stringNull);  
	    cardP6V.textProperty().bind(stringNull);  
	  
	    alert.textProperty().bind(stringNull);  
	  
	    cardD1S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));  
	    cardD2S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));  
	    cardD3S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));  
	    cardD4S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));  
	    cardD5S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));  
	    cardD6S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));  
	  
	    cardP1S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));  
	    cardP2S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));  
	    cardP3S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));  
	    cardP4S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));  
	    cardP5S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));  
	    cardP6S.setImage(new Image("http://irjneishere.altervista.org/other/javafxproject/noSeed.png"));  
	}

#### Conversione del valore (per figure e asso)
Qualora trovasse un asso o una figura, consente di stampare a video le lettere corrispondenti, come segue: 

 - A (asso) = 1; 
 - J (fante) = 11; 
 - Q (regina) = 12; 
 - K (re) = 13.

		public StringProperty conversion (int card) {  
		    String auxCardValue;  
		    StringProperty cardValue;  
		  
		    if (card == 1) auxCardValue = "A";  
		    else if (card == 11) auxCardValue = "J";  
		    else if (card == 12) auxCardValue = "Q";  
		    else if (card == 13) auxCardValue = "K";  
		    else auxCardValue = Integer.toString(card);  
		    cardValue = new SimpleStringProperty(auxCardValue);  
		  
		    return cardValue;  
		}

#### Assegnazione di una nuova carta (hit)

    public void newCard () {  
	    Label name;  
	    ImageView seedName;  
	    StringProperty cardValue;  
	  
	    int position = player.getPlayerCards().size();  
	    if (position == 3) {  
	        name = cardP3V;  
	        seedName = cardP3S;  
	    }  
	    else if (position == 4) {  
	        name = cardP4V;  
	        seedName = cardP4S;  
	    }  
	    else if (position == 5) {  
	        name = cardP5V;  
	        seedName = cardP5S;  
	    }  
	    else {  
	        name = cardP6V;  
	        seedName = cardP6S;  
	    }  
	  
	    int card = player.getPlayerCards().get(position-1).getNumber();  
	    Card.Seed seed = player.getPlayerCards().get(position-1).getSeed();  
	  
	    cardValue = conversion(card);  
	  
	    seedName.setImage(setSeed(seed));  
	    name.textProperty().bind(cardValue);  
	}

#### Action: puntata

    void bet5() {  
	    game.mixCards(10);  
	    game.betAndGetCards(player, 5);  
	    initialize();  
	  
	    initialCards(game.dealer, 0);  
	    initialCards(player, 1);  
	  
	    refreshScore();  
	  
	    int result = game.blackJackOrDraw(player);  
	    alert(result);  
	    initialize();  
	}

#### Verifica della condizione di vittoria

    void hit() {  
	    int result = game.hit(player);  
	  
	    newCard();  
	    refreshScore();  
	  
	    alert(result);  
	    initialize();  
	}  
	  

	void stand() {  
	    game.dealerScore();  
	    int result = game.stand(player);  
	  
	    if (game.dealer.getPlayerCards().size() > 2) dealerHitCards();  
	  
	    refreshScore();  
	  
	    alert(result);  
	    initialize();  
	}
